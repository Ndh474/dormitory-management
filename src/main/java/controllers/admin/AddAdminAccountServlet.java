package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import entity.Account;
import entity.AccountStatus;
import entity.Information;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AccountService;
import services.AccountStatusService;
import services.InformationService;
import services.RoleService;
import services.impl.AccountServiceImpl;
import services.impl.AccountStatusServiceImpl;
import services.impl.InformationServiceImpl;
import services.impl.RoleServiceImpl;
import util.Encode;

@WebServlet(name = "AddAdminAccountServlet", value = "/add-admin-account")
public class AddAdminAccountServlet extends HttpServlet {

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
        String password = request.getParameter("password");
        String status = request.getParameter("status");

        RoleService roleService = new RoleServiceImpl();
        Role role = roleService.getByRoleName("admin");

        AccountStatusService accountStatusService = new AccountStatusServiceImpl();
        AccountStatus accountStatus = accountStatusService.getById(Integer.parseInt(status));

        AccountService accountService = new AccountServiceImpl();
        if (accountService.checkEmailExist(email)) {
            request.getSession().setAttribute("addAccountStatus", "fail");
            response.sendRedirect(request.getContextPath() + "/admin-account-manager");
            return;
        }
        accountService.addAccount(new Account(email, Encode.sha1Hash(password), accountStatus, role));
        request.getSession().setAttribute("addAccountStatus", "success");
        response.sendRedirect(request.getContextPath() + "/admin-account-manager");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
