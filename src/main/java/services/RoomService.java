package services;

import entity.DormRoomStatus;
import entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllByFilter(String dormName, String roomName, String floorNumber, String roomTypeId, String roomGender);

    Room getRoomByName(String roomName);

    int totalRoom(String dormName, String gender);

    int totalFullRoom(String dormName, String gender);

    int totalNotFullRoomByGender(String dormName, String gender);

    int add(Room room);

    int update(Room room);

    boolean checkExistRoomName(String dormName, String roomName);

    List<Room> getAllRoomHavingStudent();

    int numberOfRoom();

    boolean checkStudentFromDorm(String dormName);

    void setRoomStatusByDormName(String dormName, DormRoomStatus status);

    void setPrefixRoomName(String dormName, String prefix);

    int updateRoomFK(Room room, String oldRoomName);

    void updateAllRoomAndRoomUsage(String dormName);
}
