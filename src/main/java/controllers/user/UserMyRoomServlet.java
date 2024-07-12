package controllers.user;

import entity.Account;
import entity.Information;
import entity.Room;
import entity.RoomUsage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AccountService;
import services.InformationService;
import services.RoomService;
import services.RoomUsageService;
import services.impl.InformationServiceImpl;
import services.impl.RoomServiceImpl;
import services.impl.RoomUsageServiceImpl;
import util.AppConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "UserNewsDetailsServlet", value = "/user/myroom")
public class UserMyRoomServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = (calendar.get(Calendar.MONTH) + 1) % 12;
        int currentYear = calendar.get(Calendar.YEAR);

        RoomService roomService = new RoomServiceImpl();
        InformationService informationService = new InformationServiceImpl();
        Account account = (Account) req.getSession().getAttribute("account");
        Information information = informationService.getByAccountID(account.getAccountID());
        Room room = roomService.getRoomByName(information.getRoomName());

        RoomUsageService roomUsageService = new RoomUsageServiceImpl();
        RoomUsage roomUsageCurrentMonth = roomUsageService.getByRoomAndMonthAndYear(information.getRoomName(), currentMonth, currentYear);
        req.setAttribute("roomUsageCurrentMonth", roomUsageCurrentMonth);
        RoomUsage roomUsagePreviousMonth = roomUsageService.getByPreviousMonth(information.getRoomName());
        req.setAttribute("roomUsagePreviousMonth", roomUsagePreviousMonth);

        double totalAmount = getTotalAmount(roomUsageCurrentMonth);
        req.setAttribute("totalAmount", totalAmount);

        List<Information> roomMemberList = informationService.getFromRoomName(information.getRoomName());
        req.setAttribute("roomMemberList", roomMemberList);
        req.setAttribute("room", room);
        req.getRequestDispatcher("../view/user/user-my-room.jsp").forward(req, resp);
    }

    private double getTotalAmount(RoomUsage roomUsage) {
        RoomService roomService = new RoomServiceImpl();
        Room room = roomService.getRoomByName(roomUsage.getRoomName());

        double electricityPrice = (roomUsage.getElectricityUsed() - room.getRoomType().getElectricityFree()) * AppConfig.electricityPrice;
        if(electricityPrice < 0) {
            electricityPrice = 0;
        }
        double waterPrice = (roomUsage.getWaterUsed() - room.getRoomType().getWaterFree()) * AppConfig.waterPrice;
        if(waterPrice < 0) {
            waterPrice = 0;
        }
        return electricityPrice + waterPrice;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
