package services.impl;

import dao.*;
import dao.impl.*;
import entity.*;
import services.*;

import java.util.*;

public class RoomUsageServiceImpl implements RoomUsageService {
    private RoomUsageDAO roomUsageDAO = new RoomUsageDAOImpl();

    @Override
    public List<RoomUsage> getAll() {
        return roomUsageDAO.getAll();
    }

    @Override
    public RoomUsage getById(int id) {
        return roomUsageDAO.getById(id);
    }

    @Override
    public List<RoomUsage> getByRoomName(String roomName) {
        return roomUsageDAO.getByRoomName(roomName);
    }

    @Override
    public int add(RoomUsage roomUsage) {
        return roomUsageDAO.add(roomUsage);
    }

    @Override
    public int update(RoomUsage roomUsage) {
        return roomUsageDAO.update(roomUsage);
    }

    @Override
    public boolean checkRoomUsageExist(String roomName, int month, int year) {
        List<RoomUsage> roomUsages = roomUsageDAO.getByRoomName(roomName);
        for (RoomUsage roomUsage : roomUsages) {
            if (roomUsage.getMonth() == month && roomUsage.getYear() == year) {
                return true;
            }
        }
        return false;
    }

    @Override
    public RoomUsage getByRoomAndMonthAndYear(String roomName, int month, int year) {
        return roomUsageDAO.getByRoomAndMonthAndYear(roomName, month, year);
    }

    @Override
    public RoomUsage getByPreviousMonth(String roomName) {
        //get year and previous month, and save it in a variable roomUsage using getByRoomAndMonthAndYear, and while it is null, decrease the month by 1
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        RoomUsage roomUsage = null;
        while (roomUsage == null) {
            roomUsage = getByRoomAndMonthAndYear(roomName, currentMonth, currentYear);
            if (roomUsage == null) {
                currentMonth--;
                if (currentMonth == 0) {
                    currentMonth = 12;
                    currentYear--;
                }
            }
        }
        return roomUsage;
    }

    @Override
    public List<Chart> getUsagePerMonth(String room_name) {
        List<Chart> chartList = new ArrayList<>();
        List<RoomUsage> roomUsageList = getByRoomName(room_name);
        for (RoomUsage roomUsage : roomUsageList) {
            chartList.add(new Chart(roomUsage.getElectricityUsed(), roomUsage.getWaterUsed(), roomUsage.getMonth(), roomUsage.getYear()));
        }
        chartList.sort(Comparator.comparing(Chart::getYear).thenComparing(Chart::getMonth));
        if(chartList.size() <=6) {
            return chartList;
        } else {
            return chartList.subList(chartList.size()-6, chartList.size());
        }
    }

    public static void main(String[] args) {
        RoomUsageService roomUsageService = new RoomUsageServiceImpl();
        System.out.println(roomUsageService.getByPreviousMonth("A101"));
    }
}
