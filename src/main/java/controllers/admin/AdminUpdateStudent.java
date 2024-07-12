package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.*;
import services.impl.*;

@WebServlet(name = "AdminUpdateStudent", value = "/admin-update-student")
public class AdminUpdateStudent extends HttpServlet {

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
        String rollNumber = request.getParameter("rollNumber");
        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByRollNumber(rollNumber);
        request.setAttribute("information", information);

        AccountStatusService accountStatusService = new AccountStatusServiceImpl();
        List<AccountStatus> accountStatusList = accountStatusService.getAll();
        request.setAttribute("accountStatusList", accountStatusList);

        ActivityHistoryService activityHistoryService = new ActivityHistoryServiceImpl();
        List<ActivityHistory> activityHistoryList = activityHistoryService.getByInformationId(information.getInformationId());
        request.setAttribute("activityHistoryList", activityHistoryList);

        BillService billService = new BillServiceImpl();
        List<Bill> billList = billService.getByInformationId(information.getInformationId());
        request.setAttribute("billList", billList);

        request.getRequestDispatcher("view/admin/admin-update-student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String rollNumber = request.getParameter("rollNumber");

        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByRollNumber(rollNumber);

        String dob = request.getParameter("dob");
        information.setDob(dob);

        String citizenIdentification = request.getParameter("citizenIdentification");
        information.setCitizenIdentification(citizenIdentification);

        String phoneNumber = request.getParameter("phoneNumber");
        information.setPhoneNumber(phoneNumber);

        String address = request.getParameter("address");
        information.setAddress(address);

        String balance = request.getParameter("balance").replace(",","");
        information.setBalance(Double.parseDouble(balance));

        informationService.update(information);

        AccountService accountService = new AccountServiceImpl();
        Account account = information.getAccount();

        String status = request.getParameter("accountStatus");
        account.getAccountStatus().setStatusID(Integer.parseInt(status));

        request.getSession().setAttribute("successUpdate", "success");
        accountService.updateAccount(account);

        response.sendRedirect("/admin-update-student?rollNumber=" + rollNumber);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
