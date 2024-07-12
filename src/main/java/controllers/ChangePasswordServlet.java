package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import entity.Account;
import entity.ActivityHistory;
import entity.Information;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ActivityHistoryService;
import services.InformationService;
import services.impl.AccountServiceImpl;
import services.impl.ActivityHistoryServiceImpl;
import services.impl.InformationServiceImpl;
import util.Encode;

@WebServlet(name = "ChangePasswordServlet", value = "/change-password")
public class ChangePasswordServlet extends HttpServlet {

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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String newPassword = request.getParameter("newPassword");
        Account account = (Account) request.getSession().getAttribute("account");
        account.setPassword(Encode.sha1Hash(newPassword));
        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.updateAccount(account);

        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(account.getAccountID());

        ActivityHistoryService activityHistoryService = new ActivityHistoryServiceImpl();
        activityHistoryService.insert(new ActivityHistory("Change Password", information.getRollNumber()+" has changed password", information));

        request.getSession().setAttribute("account", account);
        request.getSession().setAttribute("successUpdateInformation", "success");
        response.sendRedirect(request.getContextPath() + "/user/info");


    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
