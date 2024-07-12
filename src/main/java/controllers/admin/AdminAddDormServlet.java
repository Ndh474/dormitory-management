package controllers.admin;

import entity.Account;
import entity.Dorm;
import entity.DormRoomStatus;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.DormRoomStatusService;
import services.DormService;
import services.impl.DormRoomStatusServiceImpl;
import services.impl.DormServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddDormServlet", value = "/admin-add-dorm")
public class AdminAddDormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        List<DormRoomStatus> dormRoomStatus = dormRoomStatusService.getAll();
        request.setAttribute("dormRoomStatus", dormRoomStatus);
        request.getRequestDispatcher("view/admin/admin-add-dorm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dormName = request.getParameter("dormName").toUpperCase();
        request.setAttribute("dormName", dormName);

        String numberOfFloor = request.getParameter("numberOfFloor");
        request.setAttribute("numberOfFloor", numberOfFloor);

        String status = request.getParameter("dormStatus");
        request.setAttribute("dormStatus", status);

        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        List<DormRoomStatus> dormRoomStatus = dormRoomStatusService.getAll();
        request.setAttribute("dormRoomStatus", dormRoomStatus);

        DormService dormService = new DormServiceImpl();
        if (dormService.checkExistDormName(dormName)) {
            request.setAttribute("dormMessage", "Dorm name already exists");
            request.getRequestDispatcher("view/admin/admin-add-dorm.jsp").forward(request, response);
            return;
        }
        Dorm dorm = new Dorm(0, dormName, Integer.parseInt(numberOfFloor), 0, 0, new DormRoomStatus(Integer.parseInt(status)));
        dormService.add(dorm);

        request.getSession().setAttribute("successAddDorm", "success");
        response.sendRedirect("/admin-add-dorm");
    }
}