package services;

import entity.RoomType;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> getAll();

    RoomType getById(int roomTypeId);

    int add(RoomType roomType);

    int update(RoomType roomType);
}
