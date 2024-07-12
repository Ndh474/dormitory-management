package controllers.user;

import entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import services.*;
import services.impl.*;
import util.AppConfig;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "UserDetailDormRedirectServlet", value = "/user-dorm-detail")
public class UserDormDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");

        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(account.getAccountID());
        request.setAttribute("information", information);

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
        List<Room> roomList = roomService.getAllByFilter(dormName, roomName, floorNumber, roomTypeId, information.getGender());
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

        boolean bookRoom = AppConfig.bookRoom;
        request.setAttribute("bookRoom", bookRoom);

        request.getRequestDispatcher("/view/user/user-detail-dorm.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dormName = request.getParameter("dormName");
        String roomName = request.getParameter("roomName");
        Account account = (Account) request.getSession().getAttribute("account");
        InformationService informationService = new InformationServiceImpl();
        Information information = informationService.getByAccountID(account.getAccountID());

        RoomService roomService = new RoomServiceImpl();
        Room room = roomService.getRoomByName(roomName);
        if (room.getNumberOfStudent() == 0) {
            room.setRoomGender(information.getGender());

            Calendar calendar = Calendar.getInstance();

            RoomUsageService roomUsageService = new RoomUsageServiceImpl();
            if (!roomUsageService.checkRoomUsageExist(roomName, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR))) {
                roomUsageService.add(new RoomUsage(0, 0, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), roomName));
            }
        }
        roomService.update(room);

        double price = room.getRoomType().getRoomPrice();
        information.setBalance(information.getBalance() - price);
        information.setRoomName(roomName);
        informationService.update(information);

        ActivityHistoryService activityHistoryService = new ActivityHistoryServiceImpl();
        activityHistoryService.insert(new ActivityHistory("Enter room", information.getRollNumber() + " has entered room " + roomName, information));

        request.setAttribute("information", information);
        request.getSession().setAttribute("successPayment", "success");

        response.sendRedirect(request.getContextPath() + "/user-dorm-detail?dormName=" + dormName);
    }
}