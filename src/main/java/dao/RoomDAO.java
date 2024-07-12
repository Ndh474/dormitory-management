package dao;

import entity.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> getAllByFilter(String dormName, String roomName, String floorNumber, String roomTypeId, String roomGender);

    Room getRoomByName(String roomName);

    int totalRoom(String dormName, String gender);

    int totalFullRoom(String dormName, String gender);

    int add(Room room);

    int update(Room room);

    List<Room> getAllRoomHavingStudent();

    int numberOfRoom();

    int updateRoomFK(Room room, String oldRoomName);
}
