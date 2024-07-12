package services.impl;

import dao.BillStatusDAO;
import dao.impl.BillStatusDAOImpl;
import entity.BillStatus;
import services.BillStatusService;

import java.util.List;

public class BillStatusServiceImpl implements BillStatusService {
    BillStatusDAO billStatusDAO = new BillStatusDAOImpl();

    @Override
    public List<BillStatus> getAll() {
        return billStatusDAO.getAll();
    }

    @Override
    public BillStatus getById(int id) {
        return billStatusDAO.getById(id);
    }

    @Override
    public BillStatus getByName(String name) {
        return billStatusDAO.getByName(name);
    }

    @Override
    public int update(BillStatus billStatus) {
        return billStatusDAO.update(billStatus);
    }

    @Override
    public int add(BillStatus billStatus) {
        return billStatusDAO.add(billStatus);
    }

    @Override
    public int delete(int id) {
        return billStatusDAO.delete(id);
    }
}
