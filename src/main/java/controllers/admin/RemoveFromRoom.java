package controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import entity.ActivityHistory;
import entity.Information;
import entity.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.ActivityHistoryService;
import services.InformationService;
import services.RoomService;
import services.impl.ActivityHistoryServiceImpl;
import services.impl.InformationServiceImpl;
import services.impl.RoomServiceImpl;

@WebServlet(name = "RemoveFromRoom", value = "/remove-from-room")
public class RemoveFromRoom extends HttpServlet {

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
        String roomName = information.getRoomName();
        information.setRoomName(null);
        informationService.update(information);

        RoomService roomService = new RoomServiceImpl();
        Room room = roomService.getRoomByName(roomName);
        if (room.getNumberOfStudent() == 0) {
            room.setRoomGender("not set");
            roomService.update(room);
        }

        ActivityHistoryService activityHistoryService = new ActivityHistoryServiceImpl();
        activityHistoryService.insert(new ActivityHistory("Remove from room", information.getRollNumber()+" has been removed from room " + roomName + " by admin", information));

        response.sendRedirect(request.getContextPath() + "/admin-detail-room?roomName=" + roomName);
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
