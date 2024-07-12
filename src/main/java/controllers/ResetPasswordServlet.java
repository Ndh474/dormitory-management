package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.impl.AccountServiceImpl;
import util.Encode;

@WebServlet(name = "ResetPasswordServlet", value = "/reset-password")
public class ResetPasswordServlet extends HttpServlet {

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
        request.getRequestDispatcher("/view/resetpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String emailTo = (String) request.getSession().getAttribute("emailTo");
        String newPassword = request.getParameter("newPassword");
        PrintWriter out = response.getWriter();
        AccountServiceImpl accountService = new AccountServiceImpl();
        Account account = accountService.getAccountByEmail(emailTo);
        account.setPassword(Encode.sha1Hash(newPassword));
        accountService.updateAccount(account);
        response.sendRedirect("/login");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
