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

@WebServlet(name = "AdminDorm", value = "/admin-dorm")
public class AdminDormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DormService dormService = new DormServiceImpl();
        List<Dorm> dorms = dormService.getAll();

        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        List<DormRoomStatus> dormRoomStatuses = dormRoomStatusService.getAll();

        request.setAttribute("statuses", dormRoomStatuses);
        request.setAttribute("dormList", dorms);
        request.getRequestDispatcher("/view/admin/admin-dorm.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}