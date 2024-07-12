package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.*;
import services.impl.*;
import util.AppConfig;

@WebServlet(name = "AdminDetailRoomServlet", value = "/admin-detail-room")
public class AdminDetailRoomServlet extends HttpServlet {

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
        Calendar calendar = Calendar.getInstance();
        int currentMonth = (calendar.get(Calendar.MONTH) + 1) % 12;
        int currentYear = calendar.get(Calendar.YEAR);

        String roomName = request.getParameter("roomName");

        RoomService roomService = new RoomServiceImpl();
        Room room = roomService.getRoomByName(roomName);
        request.setAttribute("room", room);

        RoomUsageService roomUsageService = new RoomUsageServiceImpl();
        RoomUsage roomUsageCurrentMonth = roomUsageService.getByRoomAndMonthAndYear(roomName, currentMonth, currentYear);
        if (roomUsageCurrentMonth == null) {
            roomUsageCurrentMonth = new RoomUsage(0, 0, currentMonth, currentYear, roomName);
        }
        request.setAttribute("roomUsageCurrentMonth", roomUsageCurrentMonth);
        RoomUsage roomUsagePreviousMonth = roomUsageService.getByPreviousMonth(roomName);
        request.setAttribute("roomUsagePreviousMonth", roomUsagePreviousMonth);

        double totalAmount = getTotalAmount(roomUsageCurrentMonth);
        request.setAttribute("totalAmount", totalAmount);

        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        List<RoomType> roomTypeList = roomTypeService.getAll();
        request.setAttribute("roomTypeList", roomTypeList);

        InformationService informationService = new InformationServiceImpl();
        List<Information> roomMemberList = informationService.getFromRoomName(roomName);
        request.setAttribute("roomMemberList", roomMemberList);

        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        List<DormRoomStatus> dormRoomStatusList = dormRoomStatusService.getAll();
        request.setAttribute("dormRoomStatusList", dormRoomStatusList);

        request.getRequestDispatcher("view/admin/admin-detail-room.jsp").forward(request, response);
    }

    private double getTotalAmount(RoomUsage roomUsage) {
        RoomService roomService = new RoomServiceImpl();
        Room room = roomService.getRoomByName(roomUsage.getRoomName());

        double electricityPrice = (roomUsage.getElectricityUsed() - room.getRoomType().getElectricityFree()) * AppConfig.electricityPrice;
        if (electricityPrice < 0) {
            electricityPrice = 0;
        }
        double waterPrice = (roomUsage.getWaterUsed() - room.getRoomType().getWaterFree()) * AppConfig.waterPrice;
        if (waterPrice < 0) {
            waterPrice = 0;
        }
        return electricityPrice + waterPrice;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RoomService roomService = new RoomServiceImpl();
        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();

        Calendar calendar = Calendar.getInstance();
        int currentMonth = (calendar.get(Calendar.MONTH) + 1) % 12;
        int currentYear = calendar.get(Calendar.YEAR);

        String roomName = request.getParameter("roomName");
        String roomStatus = request.getParameter("roomStatus");
        String roomType = request.getParameter("roomType");

        Room room = roomService.getRoomByName(roomName);
        if (room.getNumberOfStudent() != 0 && dormRoomStatusService.getById(Integer.parseInt(roomStatus)).getStatusName().equals("inactive")) {
            request.getSession().setAttribute("errorInactive", "error");
            response.sendRedirect("/admin-detail-room?roomName=" + roomName);
            return;
        }

        room.getRoomStatus().setStatusId(Integer.parseInt(roomStatus));
        room.getRoomType().setRoomTypeId(Integer.parseInt(roomType));

        roomService.update(room);

        RoomUsageService roomUsageService = new RoomUsageServiceImpl();
        RoomUsage roomUsageCurrentMonth = roomUsageService.getByRoomAndMonthAndYear(roomName, currentMonth, currentYear);
        if (roomUsageCurrentMonth != null) {
            roomUsageCurrentMonth.setElectricityUsed(Integer.parseInt(request.getParameter("numberElectricUsed")));
            roomUsageCurrentMonth.setWaterUsed(Integer.parseInt(request.getParameter("numberWaterUsed")));
            roomUsageService.update(roomUsageCurrentMonth);
        }

        request.getSession().setAttribute("successUpdateRoom", "success");
        response.sendRedirect("/admin-detail-room?roomName=" + roomName);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
