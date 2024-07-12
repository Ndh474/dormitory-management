package services.impl;

import dao.*;
import dao.impl.*;
import entity.*;
import services.*;

import java.util.*;

public class DormServiceImpl implements DormService {
    DormDAO dormDAO = new DormDAOImpl();

    @Override
    public List<Dorm> getAll() {
        return dormDAO.getAll();
    }

    @Override
    public Dorm getByDormID(int id) {
        return dormDAO.getByDormID(id);
    }

    @Override
    public Dorm getByDormName(String name) {
        return dormDAO.getByDormName(name);
    }

    @Override
    public int getNumberOfFloorsByDormName(String dormName) {
        return dormDAO.getNumberOfFloorsByDormName(dormName);
    }

    @Override
    public int update(Dorm dorm, String oldDormName) {
        return dormDAO.update(dorm, oldDormName);
    }

    @Override
    public int add(Dorm dorm) {
        return dormDAO.add(dorm);
    }

    @Override
    public boolean checkExistDormName(String dormName) {
        List<Dorm> dormList = dormDAO.getAll();
        for (Dorm dorm : dormList) {
            if (dorm.getDormName().equals(dormName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberOfDorm() {
        return dormDAO.numberOfDorm();
    }

    public static void main(String[] args) {
        DormService dormService = new DormServiceImpl();
        System.out.println(dormService.checkExistDormName("R"));
    }
}
