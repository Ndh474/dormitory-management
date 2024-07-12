package dao;

import entity.DormRoomStatus;

import java.util.List;

public interface DormRoomStatusDAO {
    List<DormRoomStatus> getAll();

    DormRoomStatus getById(int id);

    DormRoomStatus getByName(String name);

    int add(DormRoomStatus dormRoomStatus);

    int update(DormRoomStatus dormRoomStatus);

}
