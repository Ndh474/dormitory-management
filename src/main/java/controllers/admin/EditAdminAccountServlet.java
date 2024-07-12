package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AccountService;
import services.AccountStatusService;
import services.impl.AccountServiceImpl;
import services.impl.AccountStatusServiceImpl;

@WebServlet(name = "EditAdminAccountServlet", value = "/edit-admin-account")
public class EditAdminAccountServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        String status = request.getParameter("status");

        AccountService accountService = new AccountServiceImpl();
        Account account = accountService.getAccountByEmail(email);

        if (Integer.parseInt(status) == account.getAccountStatus().getStatusID()) {
            request.getSession().setAttribute("editAccountStatus", "fail");
            response.sendRedirect(request.getContextPath() + "/admin-account-manager");
            return;
        }

        AccountStatusService accountStatusService = new AccountStatusServiceImpl();
        account.setAccountStatus(accountStatusService.getById(Integer.parseInt(status)));
        accountService.updateAccount(account);

        request.getSession().setAttribute("editAccountStatus", "success");
        response.sendRedirect(request.getContextPath() + "/admin-account-manager");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
