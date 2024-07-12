package dao;

import entity.AdminNotification;

import java.util.List;

public interface AdminNotificationDAO {
    List<AdminNotification> getAll();

    AdminNotification getById(int id);

    List<AdminNotification> getByInformationId(int informationId);

    int update(AdminNotification adminNotification);

    int add(AdminNotification adminNotification);

    int delete(int id);

    AdminNotification getByCurrentMonthAndYearFromInformationId(int informationId);
}
