package controllers;

import entity.Account;
import entity.GooglePOJO;
import entity.Information;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AccountService;
import services.impl.AccountServiceImpl;
import services.impl.InformationServiceImpl;
import util.Encode;
import util.Google;

import java.io.IOException;

@WebServlet("/google-login-callback")
public class GoogleLoginCallback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        Google google = new Google();
        AccountService accountService = new AccountServiceImpl();
        String token;
        try{
            token =  google.getToken(code);
        } catch (Exception e){
            response.sendRedirect("/login");
            return;
        }
        String email = google.getInfo(token);

        Account account = accountService.getAccountByEmail(email);
        if (account == null) {
            request.setAttribute("loginMessage", "Account is not registered");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
            return;
        }
        if (account.getAccountStatus().getStatusName().equals("deleted")) {
            request.setAttribute("loginMessage", "Your information does not have access rights, please contact administrator.");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("account", account);
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}


