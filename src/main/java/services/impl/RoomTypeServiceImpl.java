package services.impl;

import dao.*;
import dao.impl.*;
import entity.*;
import services.*;

import java.util.*;

public class RoomTypeServiceImpl implements RoomTypeService {
    private RoomTypeDAO roomTypeDAO = new RoomTypeDAOImpl();

    @Override
    public List<RoomType> getAll() {
        return roomTypeDAO.getAll();
    }

    @Override
    public RoomType getById(int roomTypeId) {
        return roomTypeDAO.getById(roomTypeId);
    }

    @Override
    public int add(RoomType roomType) {
        return roomTypeDAO.add(roomType);
    }

    @Override
    public int update(RoomType roomType) {
        return roomTypeDAO.update(roomType);
    }

    public static void main(String[] args) {
        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        List<RoomType> roomTypes = roomTypeService.getAll();
        for (RoomType roomType : roomTypes) {
            System.out.println(roomType);
        }
    }
}
