package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet(name = "AdminAddInformation", value = "/admin-add-information")
public class AdminAddInformation extends HttpServlet {

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

        request.getRequestDispatcher("view/admin/admin-add-student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        request.setAttribute("email", email);

        String password = request.getParameter("password");
        request.setAttribute("password", password);

        String status = request.getParameter("accountStatus");
        request.setAttribute("status", status);

        String rollNumber = request.getParameter("rollNumber");
        request.setAttribute("rollNumber", rollNumber);

        String fullName = request.getParameter("fullName");
        request.setAttribute("fullName", fullName);

        String gender = request.getParameter("gender");
        request.setAttribute("gender", gender);

        String dob = request.getParameter("dob");
        request.setAttribute("dob", dob);

        String citizenIdentification = request.getParameter("citizenIdentification");
        request.setAttribute("citizenIdentification", citizenIdentification);

        String phoneNumber = request.getParameter("phoneNumber");
        request.setAttribute("phoneNumber", phoneNumber);

        String address = request.getParameter("address");
        request.setAttribute("address", address);

        AccountStatusService accountStatusService = new AccountStatusServiceImpl();
        List<AccountStatus> accountStatusList = accountStatusService.getAll();
        request.setAttribute("accountStatusList", accountStatusList);

        RoleService roleService = new RoleServiceImpl();
        int roleId = roleService.getRoleId("student");

        AccountService accountService = new AccountServiceImpl();
        if (accountService.checkEmailExist(email)) {
            request.setAttribute("accountMessage", "Email already exists");
            request.getRequestDispatcher("view/admin/admin-add-student.jsp").forward(request, response);
            return;
        }

        InformationService informationService = new InformationServiceImpl();
        if (informationService.checkExistRollNumber(rollNumber)) {
            request.setAttribute("informationMessage", "Roll number already exists");
            request.getRequestDispatcher("view/admin/admin-add-student.jsp").forward(request, response);
            return;
        }
        if (informationService.checkExistCitizenIdentification(citizenIdentification)) {
            request.setAttribute("informationMessage", "Citizen identification already exists");
            request.getRequestDispatcher("view/admin/admin-add-student.jsp").forward(request, response);
            return;
        }
        Account account = new Account(0, email, Encode.sha1Hash(password), new AccountStatus(Integer.parseInt(status)), new Role(roleId));
        accountService.addAccount(account);

        Account temp = accountService.getAccountByEmail(email);
        Information information = new Information(0, rollNumber, citizenIdentification, fullName, dob, gender, phoneNumber, address, 0, "no", temp, null);
        informationService.add(information);

        request.getSession().setAttribute("successAdd", "success");
        response.sendRedirect(request.getContextPath()+"/admin-add-information");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
