package dao.impl;

import connection.MySQLConnection;
import dao.DormRoomStatusDAO;
import entity.DormRoomStatus;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.util.*;
import java.sql.*;

import static dao.statement.DormRoomStatusSQLStatements.*;

public class DormRoomStatusDAOImpl implements DormRoomStatusDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<DormRoomStatus> getAll() {
        List<DormRoomStatus> dormRoomStatuses = new ArrayList<>();

        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllStatus);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                DormRoomStatus dormRoomStatus = rowMapper.dormRoomStatusMapper(resultSet); // replace with your actual row mapper method
                dormRoomStatuses.add(dormRoomStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dormRoomStatuses;
    }

    @Override
    public DormRoomStatus getById(int id) {
        DormRoomStatus dormRoomStatus = null;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getStatusById)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    dormRoomStatus = rowMapper.dormRoomStatusMapper(resultSet); // replace with your actual row mapper method
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dormRoomStatus;
    }

    @Override
    public DormRoomStatus getByName(String name) {
        DormRoomStatus dormRoomStatus = null;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getStatusByName)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    dormRoomStatus = rowMapper.dormRoomStatusMapper(resultSet); // replace with your actual row mapper method
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dormRoomStatus;
    }

    @Override
    public int add(DormRoomStatus dormRoomStatus) {
        int status = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addStatus)) {
            preparedStatement.setString(1, dormRoomStatus.getStatusName());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int update(DormRoomStatus dormRoomStatus) {
        int status = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateStatus)) {
            preparedStatement.setString(1, dormRoomStatus.getStatusName());
            preparedStatement.setInt(2, dormRoomStatus.getStatusId());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void main(String[] args) {
        DormRoomStatusDAO dormRoomStatusDAO = new DormRoomStatusDAOImpl();
        System.out.println(dormRoomStatusDAO.getAll());
        System.out.println(dormRoomStatusDAO.getById(1));
    }
}
