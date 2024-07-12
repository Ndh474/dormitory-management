package services;

import entity.Chart;
import entity.Room;
import entity.RoomUsage;

import java.util.List;

public interface RoomUsageService {
    List<RoomUsage> getAll();

    RoomUsage getById(int id);

    List<RoomUsage> getByRoomName(String roomName);

    int add(RoomUsage roomUsage);

    int update(RoomUsage roomUsage);

    boolean checkRoomUsageExist(String roomName, int month, int year);

    RoomUsage getByRoomAndMonthAndYear(String roomName, int month, int year);

    RoomUsage getByPreviousMonth(String roomName);

    List<Chart> getUsagePerMonth(String room_name);
}
