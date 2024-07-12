package dao.impl;

import connection.MySQLConnection;
import dao.AdminNotificationDAO;
import entity.AdminNotification;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.*;

import static dao.statement.AdminNotificationSQLStatements.*;

public class AdminNotificationDAOImpl implements AdminNotificationDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<AdminNotification> getAll() {
        List<AdminNotification> adminNotifications = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAll);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                adminNotifications.add(rowMapper.adminNotificationMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminNotifications;
    }

    @Override
    public AdminNotification getById(int id) {
        AdminNotification adminNotification = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getById)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                adminNotification = rowMapper.adminNotificationMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminNotification;
    }

    @Override
    public List<AdminNotification> getByInformationId(int informationId) {
        List<AdminNotification> adminNotifications = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getByInformationId)
        ) {
            st.setInt(1, informationId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                adminNotifications.add(rowMapper.adminNotificationMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminNotifications;
    }

    @Override
    public int update(AdminNotification adminNotification) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(update)
        ) {
            st.setString(1, adminNotification.getTitle());
            st.setString(2, adminNotification.getContent());
            st.setInt(3, adminNotification.getInformation().getInformationId());
            st.setInt(4, adminNotification.getNotificationId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int add(AdminNotification adminNotification) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(add)
        ) {
            st.setString(1, adminNotification.getTitle());
            st.setString(2, adminNotification.getContent());
            st.setInt(3, adminNotification.getInformation().getInformationId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(delete)
        ) {
            st.setInt(1, id);
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public AdminNotification getByCurrentMonthAndYearFromInformationId(int informationId) {
        AdminNotification adminNotification = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getByCurrentMonthAndYearFromInformationId)
        ) {
            st.setInt(1, informationId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                adminNotification = rowMapper.adminNotificationMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminNotification;
    }

    public static void main(String[] args) {
        AdminNotificationDAO adminNotificationDAO = new AdminNotificationDAOImpl();
        System.out.println(adminNotificationDAO.getByCurrentMonthAndYearFromInformationId(2));
    }
}
