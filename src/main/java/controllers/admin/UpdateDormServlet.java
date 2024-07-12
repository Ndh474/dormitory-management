package controllers.admin;

import entity.Account;
import entity.Dorm;
import entity.DormRoomStatus;
import entity.Room;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.DormRoomStatusService;
import services.DormService;
import services.RoomService;
import services.impl.DormRoomStatusServiceImpl;
import services.impl.DormServiceImpl;
import services.impl.RoomServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateDormServlet", value = "/admin-dorm-update")
public class UpdateDormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DormService dormService = new DormServiceImpl();
        String dormName = request.getParameter("dormName").toUpperCase();
        Dorm dorm = dormService.getByDormID(Integer.parseInt(request.getParameter("dormId")));
        String oldDormName = dorm.getDormName();

        RoomService roomService = new RoomServiceImpl();
        if (roomService.checkStudentFromDorm(dorm.getDormName())) {
            request.getSession().setAttribute("failedUpdateDorm", "hasStudent");
            response.sendRedirect(request.getContextPath() + "/admin-dorm");
            return;
        }

        if (dormService.checkExistDormName(dormName) && !dormName.equals(oldDormName)) {
            request.getSession().setAttribute("failedUpdateDorm", "existDormName");
            response.sendRedirect(request.getContextPath() + "/admin-dorm");
            return;
        }

        DormRoomStatusService dormRoomStatusService = new DormRoomStatusServiceImpl();
        int status = Integer.parseInt(request.getParameter("status"));
        DormRoomStatus dormRoomStatus = dormRoomStatusService.getById(status);
        dorm.setStatus(dormRoomStatus);

        roomService.setRoomStatusByDormName(dorm.getDormName(), dormRoomStatus);


        dorm.setDormName(dormName);
        dormService.update(dorm, oldDormName);

        roomService.updateAllRoomAndRoomUsage(dormName);

        request.getSession().setAttribute("successUpdateDorm", "success");

        response.sendRedirect(request.getContextPath() + "/admin-dorm");
    }
}