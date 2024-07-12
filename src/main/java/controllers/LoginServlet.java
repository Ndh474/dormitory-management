package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import entity.Account;
import entity.Information;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.InformationService;
import services.impl.AccountServiceImpl;
import services.impl.InformationServiceImpl;
import util.Encode;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertBrandServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertBrandServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account != null) {
            if (account.getRole().getRoleName().equals("admin")) {
                request.getRequestDispatcher("/view/admin/admin-dashboard.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/view/user/user-dashboard.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("inputEmail", email);
            request.setAttribute("inputPassword", password);
            request.setAttribute("loginMessage", "Email or password is empty!");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
            return;
        }
        AccountServiceImpl accountService = new AccountServiceImpl();
        Account account = accountService.getAccountByEmail(email);
        if (account == null) {
            request.setAttribute("inputEmail", email);
            request.setAttribute("loginMessage", "Account is not existed!");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
            return;
        }
        if (account.getAccountStatus().getStatusName().equals("deleted")) {
            request.setAttribute("inputEmail", email);
            request.setAttribute("loginMessage", "Your information does not have access rights, please contact administrator.");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
            return;
        }
        String hashPassword = Encode.sha1Hash(password);
        if (!account.getPassword().equals(hashPassword)) {
            request.setAttribute("inputEmail", email);
            request.setAttribute("loginMessage", "Password is incorrect!");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
            return;
        }
        InformationServiceImpl informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(account.getAccountID());
        request.getSession().setAttribute("account", account);
        request.getSession().setMaxInactiveInterval(600);
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
