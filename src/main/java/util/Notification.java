package util;

import entity.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import services.*;
import services.impl.*;

import java.util.*;

@WebListener
public class Notification implements ServletContextListener {

    private static Timer timer;
    private static final Calendar calendar = Calendar.getInstance();
    private static final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    private static final int currentMonth = (calendar.get(Calendar.MONTH) + 1) % 12;
    public static final int currentYear = calendar.get(Calendar.YEAR);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduleTask(AppConfig.notificationDay);
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
                    sendNotification();
                    System.out.println("Notification sent!");
                }
            }
        }, 0, 1000 * 60 * 60 * 8);
    }

    private static void sendNotification() {
        BillService billService = new BillServiceImpl();
        AdminNotificationService adminNotificationService = new AdminNotificationServiceImpl();

        InformationService informationService = new InformationServiceImpl();
        List<Information> informationList = informationService.getByHasRoomNameNotPaid();
        for (Information information : informationList) {
            Bill bill = billService.getByCurrentMonthAndYearFromInformationId(information.getInformationId());
            if (bill != null && information.getBalance() < bill.getTotalAmount()) {
                adminNotificationService.add(new AdminNotification("Thông báo không đủ số dư tài khoản", "Tài khoản của " + information.getFullName() + " không đủ để thanh toán tiền hóa đơn tháng " + currentMonth + "/" + currentYear + ", vui lòng nạp đủ tiền trước ngày " + AppConfig.roomAndUtilityPaymentDay + ", nếu không sẽ coi như rời khỏi phòng.", information));
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
