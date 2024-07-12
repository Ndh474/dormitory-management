package dao.impl;

import connection.MySQLConnection;
import dao.AccountDAO;
import dao.InformationDAO;
import dao.impl.AccountDAOImpl;
import entity.Account;
import entity.AccountStatus;
import entity.Information;
import entity.Role;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.statement.InformationSQLStatements.*;

public class InformationDAOImpl implements InformationDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<Information> getAll() {
        List<Information> informations = new ArrayList<>();
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAllInformation + " where rl.role_name = 'student'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Information information = rowMapper.informationMapper(resultSet);
                informations.add(information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    public List<Information> getAllByName(String name) {
        List<Information> informations = new ArrayList<>();
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationByName);
            preparedStatement.setString(1, "%");
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Information information = rowMapper.informationMapper(resultSet);
                informations.add(information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    public List<Information> getAllByPaging(int page, int limit) {
        int offset = (page - 1) * limit;
        List<Information> informations = new ArrayList<>();
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationByPaging);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Information information = rowMapper.informationMapper(resultSet);
                informations.add(information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    public int countStudent() {
        int count = 0;
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getCountOfStudents);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Information getByRollNumber(String rollNumber) {
        Information information = null;
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationByRollNumber);
            preparedStatement.setString(1, rollNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                information = rowMapper.informationMapper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }


    public Information getByEmail(String email) {
        Information information = null;
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationByEmail);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                information = rowMapper.informationMapper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    @Override
    public Information getByAccountID(int accountID) {
        Information information = null;
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationByAccountId);
            preparedStatement.setInt(1, accountID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                information = rowMapper.informationMapper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    @Override
    public Information getByInformationId(int id) {
        Information information = null;
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                information = rowMapper.informationMapper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    @Override
    public List<Information> getByHasRoomNameNotPaid() {
        List<Information> informations = new ArrayList<>();
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationHasRoomNotPaid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Information information = rowMapper.informationMapper(resultSet);
                informations.add(information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    @Override
    public List<Information> getFromRoomName(String roomName) {
        List<Information> informations = new ArrayList<>();
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getInformationByRoomName);
            preparedStatement.setString(1, roomName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Information information = rowMapper.informationMapper(resultSet);
                informations.add(information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informations;
    }

    @Override
    public int update(Information information) {
        int result = 0;
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateInformation);
            preparedStatement.setString(1, information.getRollNumber());
            preparedStatement.setString(2, information.getCitizenIdentification());
            preparedStatement.setString(3, information.getFullName());
            preparedStatement.setString(4, information.getDob());
            preparedStatement.setString(5, information.getGender());
            preparedStatement.setString(6, information.getPhoneNumber());
            preparedStatement.setString(7, information.getAddress());
            preparedStatement.setDouble(8, information.getBalance());
            preparedStatement.setString(9, information.getIsPaid());
            preparedStatement.setString(10, information.getRoomName());
            preparedStatement.setInt(11, information.getInformationId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int add(Information information) {
        int result = 0;
        try {
            Connection connection = mySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addInformation);
            preparedStatement.setString(1, information.getRollNumber());
            preparedStatement.setString(2, information.getCitizenIdentification());
            preparedStatement.setString(3, information.getFullName());
            preparedStatement.setString(4, information.getDob());
            preparedStatement.setString(5, information.getGender());
            preparedStatement.setString(6, information.getPhoneNumber());
            preparedStatement.setString(7, information.getAddress());
            preparedStatement.setDouble(8, information.getBalance());
            preparedStatement.setString(9, information.getIsPaid());
            preparedStatement.setInt(10, information.getAccount().getAccountID());
            preparedStatement.setString(11, information.getRoomName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        InformationDAO informationDAO = new InformationDAOImpl();
        System.out.println(informationDAO.getByInformationId(2));
    }
}
