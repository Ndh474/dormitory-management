package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import entity.Account;
import entity.AccountStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AccountService;
import services.AccountStatusService;
import services.impl.AccountServiceImpl;
import services.impl.AccountStatusServiceImpl;

@WebServlet(name = "AdminAccountManagerServlet", value = "/admin-account-manager")
public class AdminAccountManagerServlet extends HttpServlet {

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
        AccountStatusService accountStatusService = new AccountStatusServiceImpl();
        List<AccountStatus> accountStatusList = accountStatusService.getAll();
        request.setAttribute("accountStatusList", accountStatusList);

        AccountService accountService = new AccountServiceImpl();
        List<Account> accountList = accountService.getAllAdminAccount();
        request.setAttribute("accountList", accountList);
        request.getRequestDispatcher("view/admin/admin-account-manager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
