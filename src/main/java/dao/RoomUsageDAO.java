package dao;

import entity.RoomUsage;

import java.util.List;

public interface RoomUsageDAO {
    List<RoomUsage> getAll();

    RoomUsage getById(int id);

    List<RoomUsage> getByRoomName(String roomName);

    int add(RoomUsage roomUsage);

    int update(RoomUsage roomUsage);

    RoomUsage getByRoomAndMonthAndYear(String roomName, int month, int year);


}
