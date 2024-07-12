package services.impl;

import dao.DormRoomStatusDAO;
import dao.impl.DormRoomStatusDAOImpl;
import entity.DormRoomStatus;
import services.DormRoomStatusService;

import java.util.List;

public class DormRoomStatusServiceImpl implements DormRoomStatusService {
    DormRoomStatusDAO dormRoomStatusDAO = new DormRoomStatusDAOImpl();

    @Override
    public List<DormRoomStatus> getAll() {
        return dormRoomStatusDAO.getAll();
    }

    @Override
    public DormRoomStatus getById(int id) {
        return dormRoomStatusDAO.getById(id);
    }

    @Override
    public DormRoomStatus getByName(String name) {
        return dormRoomStatusDAO.getByName(name);
    }

    @Override
    public int update(DormRoomStatus dormRoomStatus) {
        return dormRoomStatusDAO.update(dormRoomStatus);
    }

    @Override
    public int add(DormRoomStatus dormRoomStatus) {
        return dormRoomStatusDAO.add(dormRoomStatus);
    }
}
