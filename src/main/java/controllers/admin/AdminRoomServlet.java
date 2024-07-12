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

@WebServlet(name = "AdminRoomServlet", value = "/admin-room")
public class AdminRoomServlet extends HttpServlet {

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

        String dormName = request.getParameter("dormName");
        request.setAttribute("dormName", dormName);

        String roomName = request.getParameter("roomName");
        request.setAttribute("roomName", roomName);

        String floorNumber = request.getParameter("floorNumber");
        if (floorNumber != null && floorNumber.equals("0")) {
            floorNumber = null;
        }
        request.setAttribute("floorNumber", floorNumber);

        String roomTypeId = request.getParameter("roomType");
        if (roomTypeId != null && roomTypeId.equals("0")) {
            roomTypeId = null;
        }
        request.setAttribute("roomTypeId", roomTypeId);

        RoomService roomService = new RoomServiceImpl();
        List<Room> roomList = roomService.getAllByFilter(dormName, roomName, floorNumber, roomTypeId, null);
        request.setAttribute("roomList", roomList);

        int totalRoom = roomService.totalRoom(dormName, null);
        request.setAttribute("totalRoom", totalRoom);

        int totalFullRoom = roomService.totalFullRoom(dormName, null);
        request.setAttribute("totalFullRoom", totalFullRoom);

        int totalNotFullRoom = roomService.totalNotFullRoomByGender(dormName, null);
        request.setAttribute("totalNotFullRoom", totalNotFullRoom);

        DormService dormService = new DormServiceImpl();
        int numberOfFloor = dormService.getNumberOfFloorsByDormName(dormName);
        List<Dorm> dormList = dormService.getAll();
        request.setAttribute("dormList", dormList);
        request.setAttribute("numberOfFloor", numberOfFloor);

        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        List<RoomType> roomTypeList = roomTypeService.getAll();
        request.setAttribute("roomTypeList", roomTypeList);

        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        List<DormRoomStatus> dormRoomStatusList = dormRoomStatusService.getAll();
        request.setAttribute("dormRoomStatusList", dormRoomStatusList);

        request.getRequestDispatcher("view/admin/admin-room.jsp").forward(request, response);
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
