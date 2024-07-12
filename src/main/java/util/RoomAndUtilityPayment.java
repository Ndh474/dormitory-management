package util;

import entity.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import services.*;
import services.impl.*;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class RoomAndUtilityPayment implements ServletContextListener {

    private static Timer timer;
    private static final Calendar calendar = Calendar.getInstance();
    private static final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    private static final int currentMonth = (calendar.get(Calendar.MONTH) + 1) % 12;
    public static final int currentYear = calendar.get(Calendar.YEAR);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduleTask(AppConfig.roomAndUtilityPaymentDay);
    }

    public static void scheduleTask(int dayOfMonth) {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (dayOfMonth == currentDay) {
                    payment();
                    System.out.println("Payment is done!");
                }
            }
        }, 0, 1000 * 60 * 60 * 8);
    }


    private static void payment() {
        ActivityHistoryService activityHistoryService = new ActivityHistoryServiceImpl();
        BillService billService = new BillServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        BillStatusService billStatusService = new BillStatusServiceImpl();
        BillStatus paidStatus = billStatusService.getByName("paid");
        BillStatus notPaidStatus = billStatusService.getByName("not paid");

        InformationService informationService = new InformationServiceImpl();
        List<Information> informationList = informationService.getByHasRoomNameNotPaid();
        for (Information information : informationList) {
            String roomName = information.getRoomName();
            Bill bill = billService.getByCurrentMonthAndYearFromInformationId(information.getInformationId());
            if (information.getBalance() < bill.getTotalAmount()) {
                bill.setBillStatus(notPaidStatus);
                information.setRoomName(null);
                activityHistoryService.insert(new ActivityHistory("Leave room", information.getRollNumber()+" has left the room " + roomName, information));
            } else {
                bill.setBillStatus(paidStatus);
                information.setBalance(information.getBalance() - bill.getTotalAmount());
                activityHistoryService.insert(new ActivityHistory("Monthly Payment", information.getRollNumber()+" has successfully paid the bill for room " + roomName+", total amount: "+bill.getTotalAmount()+", account balance: "+information.getBalance(), information));
            }
            billService.update(bill);
            informationService.update(information);

            Room room = roomService.getRoomByName(roomName);
            if (room.getNumberOfStudent() == 0) {
                room.setRoomGender("not set");
                roomService.update(room);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
