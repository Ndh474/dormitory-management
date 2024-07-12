package services.impl;

import dao.RoomDAO;
import dao.impl.RoomDAOImpl;
import entity.DormRoomStatus;
import entity.Room;
import services.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public List<Room> getAllByFilter(String dormName, String roomName, String floorNumber, String roomTypeId, String roomGender) {
        return roomDAO.getAllByFilter(dormName, roomName, floorNumber, roomTypeId, roomGender);
    }

    @Override
    public Room getRoomByName(String roomName) {
        return roomDAO.getRoomByName(roomName);
    }

    @Override
    public int totalRoom(String dormName, String gender) {
        return roomDAO.totalRoom(dormName, gender);
    }

    @Override
    public int totalFullRoom(String dormName, String gender) {
        return roomDAO.totalFullRoom(dormName, gender);
    }

    @Override
    public int totalNotFullRoomByGender(String dormName, String gender) {
        return totalRoom(dormName, gender) - totalFullRoom(dormName, gender);
    }

    @Override
    public int add(Room room) {
        return roomDAO.add(room);
    }

    @Override
    public int update(Room room) {
        return roomDAO.update(room);
    }

    @Override
    public boolean checkExistRoomName(String dormName, String roomName) {
        List<Room> roomList = roomDAO.getAllByFilter(dormName, null, null, null, null);
        for (Room room : roomList) {
            if (room.getRoomName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Room> getAllRoomHavingStudent() {
        return roomDAO.getAllRoomHavingStudent();
    }

    @Override
    public int numberOfRoom() {
        return roomDAO.numberOfRoom();
    }

    @Override
    public boolean checkStudentFromDorm(String dormName) {
        List<Room> roomList = roomDAO.getAllByFilter(dormName, null, null, null, null);
        for (Room room : roomList) {
            if (room.getNumberOfStudent() > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setRoomStatusByDormName(String dormName, DormRoomStatus status) {
        List<Room> roomList = getAllByFilter(dormName, null, null, null, null);
        for (Room room : roomList) {
            room.setRoomStatus(status);
            roomDAO.update(room);
        }
    }

    @Override
    public void setPrefixRoomName(String dormName, String prefix) {
        List<Room> roomList = getAllByFilter(dormName, null, null, null, null);
        for (Room room : roomList) {
            room.setRoomName(prefix + room.getRoomName().substring(1));
            roomDAO.update(room);
        }
    }

    @Override
    public int updateRoomFK(Room room, String oldRoomName) {
        return roomDAO.updateRoomFK(room, oldRoomName);
    }

    @Override
    public void updateAllRoomAndRoomUsage(String dormName) {
        List<Room> roomList = getAllByFilter(dormName, null, null, null, null);
        for (Room room : roomList) {
            String oldRoomName = room.getRoomName();
            room.setRoomName(dormName + room.getRoomName().substring(1));
            roomDAO.updateRoomFK(room, oldRoomName);
        }
    }

    public static void main(String[] args) {
        RoomService roomService = new RoomServiceImpl();
        roomService.setPrefixRoomName("A", "S");
    }
}
