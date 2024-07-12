package services.impl;

import dao.BillDAO;
import dao.impl.BillDAOImpl;
import entity.Bill;
import services.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {
    BillDAO billDao = new BillDAOImpl();

    @Override
    public List<Bill> getAll() {
        return billDao.getAll();
    }

    @Override
    public Bill getById(int id) {
        return billDao.getById(id);
    }

    @Override
    public List<Bill> getByInformationId(int informationId) {
        return billDao.getByInformationId(informationId);
    }

    @Override
    public int update(Bill bill) {
        return billDao.update(bill);
    }

    @Override
    public int add(Bill bill) {
        return billDao.add(bill);
    }

    @Override
    public int delete(int id) {
        return billDao.delete(id);
    }

    @Override
    public Bill getByCurrentMonthAndYearFromInformationId(int informationId) {
        return billDao.getByCurrentMonthAndYearFromInformationId(informationId);
    }

    public static void main(String[] args) {
        BillService billService = new BillServiceImpl();
    }
}
