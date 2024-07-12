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
public class RoomAndUtilityIssue implements ServletContextListener {

    private static Timer timer;
    private static final Calendar calendar = Calendar.getInstance();
    private static final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    private static final int currentMonth = (calendar.get(Calendar.MONTH) + 1) % 12;
    public static final int currentYear = calendar.get(Calendar.YEAR);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduleTask(AppConfig.roomAndUtilityBilIssueDay);
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
                    utilityIssue();
                    System.out.println("Create Utility Bill is done!");
                    if (AppConfig.roomPaymentMonths.contains(currentMonth)) {
                        roomIssue();
                        System.out.println("Create Room Bill is done!");
                    }
                }
            }
        }, 0, 1000 * 60 * 60 * 8);
    }

    private static void utilityIssue() {
        RoomUsageService roomUsageService = new RoomUsageServiceImpl();
        BillService billService = new BillServiceImpl();
        RoomService roomService = new RoomServiceImpl();

        BillStatusService billStatusService = new BillStatusServiceImpl();
        BillStatus billStatus = billStatusService.getByName("pending");

        InformationService informationService = new InformationServiceImpl();
        List<Information> informationList = informationService.getByHasRoomNameNotPaid();
        for (Information information : informationList) {
            if (billService.getByCurrentMonthAndYearFromInformationId(information.getInformationId()) == null) {
                Room room = roomService.getRoomByName(information.getRoomName());
                RoomUsage roomUsage = roomUsageService.getByRoomAndMonthAndYear(information.getRoomName(), currentMonth, currentYear);

                double totalElectricity = (roomUsage.getElectricityUsed() - room.getRoomType().getElectricityFree()) * AppConfig.electricityPrice;
                if (totalElectricity < 0) totalElectricity = 0;

                double totalWater = (roomUsage.getWaterUsed() - room.getRoomType().getWaterFree()) * AppConfig.waterPrice;
                if (totalWater < 0) totalWater = 0;

                double total = (totalElectricity + totalWater) / room.getNumberOfStudent();

                billService.add(new Bill("Thanh toán tiền điện, nước tháng " + currentMonth + "/" + currentYear + " của " + information.getFullName(), total, billStatus, information));
            }
        }
    }

    private static void roomIssue() {
        BillService billService = new BillServiceImpl();
        RoomService roomService = new RoomServiceImpl();

        InformationService informationService = new InformationServiceImpl();
        List<Information> informationList = informationService.getByHasRoomNameNotPaid();
        for (Information information : informationList) {
            Room room = roomService.getRoomByName(information.getRoomName());
            Bill bill = billService.getByCurrentMonthAndYearFromInformationId(information.getInformationId());
            bill.setBillName("Thanh toán tiền điện, nước tháng " + currentMonth + "/" + currentYear + " và tiền phòng hàng kỳ của " + information.getFullName());
            bill.setTotalAmount(bill.getTotalAmount() + room.getRoomType().getRoomPrice());
            billService.update(bill);
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
