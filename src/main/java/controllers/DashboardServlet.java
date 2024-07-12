package controllers;

import dao.impl.InformationDAOImpl;
import dao.impl.NewsDAOImpl;
import entity.Account;
import entity.Information;
import entity.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AccountService;
import services.DormService;
import services.RoomService;
import services.impl.AccountServiceImpl;
import services.impl.DormServiceImpl;
import services.impl.RoomServiceImpl;
import util.AppConfig;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        Account account = (Account) request.getSession().getAttribute("account");
        InformationDAOImpl informationDAO = new InformationDAOImpl();
        Information information = informationDAO.getByAccountID(account.getAccountID());
        if (account.getRole().getRoleName().equals("admin")) {
            request.setAttribute("information", information);

            AccountService accountService = new AccountServiceImpl();
            int numberOfAccount = accountService.numberOfAccount();
            request.setAttribute("numberOfAccount", numberOfAccount);

            DormService dormService = new DormServiceImpl();
            int numberOfDorm = dormService.numberOfDorm();
            request.setAttribute("numberOfDorm", numberOfDorm);

            RoomService roomService = new RoomServiceImpl();
            int numberOfRoom = roomService.numberOfRoom();
            request.setAttribute("numberOfRoom", numberOfRoom);

            request.getRequestDispatcher("/view/admin/admin-dashboard.jsp").forward(request, response);
        } else {
            NewsDAOImpl newsDAO = new NewsDAOImpl();
            List<News> newsList = newsDAO.getAllByNumber(8);
            request.setAttribute("newsList", newsList);

            request.setAttribute("information", information);
            request.getRequestDispatcher("/view/user/user-dashboard.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
