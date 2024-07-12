package dao;

import entity.RoomType;

import java.util.List;

public interface RoomTypeDAO {
    List<RoomType> getAll();

    RoomType getById(int roomTypeId);

    int add(RoomType roomType);

    int update(RoomType roomType);
}
