package controllers.user;

import dao.impl.InformationDAOImpl;
import entity.Account;
import entity.ActivityHistory;
import entity.Information;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ActivityHistoryService;
import services.impl.ActivityHistoryServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserInfoServlet", value = "/user/info")
public class StudentInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        InformationDAOImpl informationDAO = new InformationDAOImpl();
        Information information = informationDAO.getByAccountID(account.getAccountID());
        request.setAttribute("rollNumber", information.getRollNumber());
        request.setAttribute("gender", information.getGender());
        request.setAttribute("dob", information.getDob());
        request.setAttribute("fullName", information.getFullName());
        request.setAttribute("cid", information.getCitizenIdentification());
        request.setAttribute("phoneNumber", information.getPhoneNumber());
        request.setAttribute("email", information.getAccount().getEmail());
        request.setAttribute("address", information.getAddress());
        request.setAttribute("balance", information.getBalance());

        ActivityHistoryService activityHistoryService = new ActivityHistoryServiceImpl();
        List<ActivityHistory> activityHistoryList = activityHistoryService.getByInformationId(information.getInformationId());
        request.setAttribute("activityHistoryList", activityHistoryList);


        request.getRequestDispatcher("/view/user/user-info.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("user_phonenumber");
        String address = request.getParameter("user_address");
        Account account = (Account) request.getSession().getAttribute("account");

        InformationDAOImpl informationDAO = new InformationDAOImpl();
        Information info = informationDAO.getByAccountID(account.getAccountID());
        info.setAddress(address);
        info.setPhoneNumber(phoneNumber);
        informationDAO.update(info);

        ActivityHistoryService activityHistoryService = new ActivityHistoryServiceImpl();
        activityHistoryService.insert(new ActivityHistory("Update Information", info.getRollNumber()+" has updated information, new phone number: "+phoneNumber+", new Address: "+address, info));

        request.getSession().setAttribute("successUpdateInformation", "success");
        response.sendRedirect(request.getContextPath() + "/user/info");
    }
}
