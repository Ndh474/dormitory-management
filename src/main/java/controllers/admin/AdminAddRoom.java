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

@WebServlet(name = "AdminAddRoom", value = "/admin-add-room")
public class AdminAddRoom extends HttpServlet {

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
        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        List<DormRoomStatus> roomStatusList = dormRoomStatusService.getAll();
        request.setAttribute("roomStatusList", roomStatusList);

        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        List<RoomType> roomTypeList = roomTypeService.getAll();
        request.setAttribute("roomTypeList", roomTypeList);

        String dormName = request.getParameter("dormName");
        request.setAttribute("dormName", dormName);

        DormService dormService = new DormServiceImpl();
        Dorm dorm = dormService.getByDormName(dormName);

        int numberOfFloor = dorm.getNumberOfFloor();
        request.setAttribute("numberOfFloor", numberOfFloor);

        request.getRequestDispatcher("view/admin/admin-add-room.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = (calendar.get(Calendar.MONTH) + 1) % 12;
        int currentYear = calendar.get(Calendar.YEAR);
        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        List<DormRoomStatus> roomStatusList = dormRoomStatusService.getAll();
        request.setAttribute("roomStatusList", roomStatusList);

        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        List<RoomType> roomTypeList = roomTypeService.getAll();
        request.setAttribute("roomTypeList", roomTypeList);

        String dormName = request.getParameter("dormName");
        request.setAttribute("dormName", dormName);

        DormService dormService = new DormServiceImpl();
        Dorm dorm = dormService.getByDormName(dormName);

        int numberOfFloor = dorm.getNumberOfFloor();
        request.setAttribute("numberOfFloor", numberOfFloor);

        String roomNumber = request.getParameter("roomNumber");
        request.setAttribute("roomNumber", roomNumber);

        String floorNumber = request.getParameter("floorNumber");
        request.setAttribute("floorNumber", floorNumber);

        String roomStatus = request.getParameter("roomStatus");
        request.setAttribute("roomStatus", roomStatus);

        String roomType = request.getParameter("roomType");
        request.setAttribute("roomType", roomType);

        DormRoomStatus status = new DormRoomStatus();
        status.setStatusId(Integer.parseInt(roomStatus));

        String roomName = dormName + floorNumber + roomNumber;

        RoomService roomService = new RoomServiceImpl();
        if (roomService.checkExistRoomName(dormName, roomName)) {
            request.setAttribute("roomMessage", "Room name is already exist");
            request.getRequestDispatcher("view/admin/admin-add-room.jsp").forward(request, response);
            return;
        }
        Dorm temp = new Dorm();
        temp.setDormName(dormName);

        RoomType type = new RoomType();
        type.setRoomTypeId(Integer.parseInt(roomType));

        Room room = new Room();
        room.setRoomName(roomName);
        room.setFloorNumber(Integer.parseInt(floorNumber));
        room.setRoomStatus(status);
        room.setDorm(temp);
        room.setRoomType(type);

        roomService.add(room);
        RoomUsageService roomUsageService = new RoomUsageServiceImpl();
        roomUsageService.add(new RoomUsage(0, 0, currentMonth - 1, currentYear, roomName));
        request.getSession().setAttribute("successAddRoom", "success");

        response.sendRedirect(request.getContextPath() + "/admin-room?dormName=" + dormName);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
