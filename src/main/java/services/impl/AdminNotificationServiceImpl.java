package services.impl;

import dao.AdminNotificationDAO;
import dao.impl.AdminNotificationDAOImpl;
import entity.AdminNotification;
import services.AdminNotificationService;

import java.util.List;

public class AdminNotificationServiceImpl implements AdminNotificationService {
    AdminNotificationDAO adminNotificationDAO = new AdminNotificationDAOImpl();

    @Override
    public List<AdminNotification> getAll() {
        return adminNotificationDAO.getAll();
    }

    @Override
    public AdminNotification getById(int id) {
        return adminNotificationDAO.getById(id);
    }

    @Override
    public List<AdminNotification> getByInformationId(int informationId) {
        return adminNotificationDAO.getByInformationId(informationId);
    }

    @Override
    public int update(AdminNotification adminNotification) {
        return adminNotificationDAO.update(adminNotification);
    }

    @Override
    public int add(AdminNotification adminNotification) {
        return adminNotificationDAO.add(adminNotification);
    }

    @Override
    public int delete(int id) {
        return adminNotificationDAO.delete(id);
    }

    @Override
    public AdminNotification getByCurrentMonthAndYearFromInformationId(int informationId) {
        return adminNotificationDAO.getByCurrentMonthAndYearFromInformationId(informationId);
    }
}
