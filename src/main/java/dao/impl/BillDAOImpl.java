package dao.impl;

import connection.MySQLConnection;
import dao.BillDAO;
import entity.*;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.*;

import static dao.statement.BillSQLStatements.*;

public class BillDAOImpl implements BillDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<Bill> getAll() {
        Connection connection = mySQLConnection.getConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill bill = rowMapper.billMapper(resultSet);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public Bill getById(int id) {
        Connection connection = mySQLConnection.getConnection();
        Bill bill = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bill = rowMapper.billMapper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public List<Bill> getByInformationId(int informationId) {
        Connection connection = mySQLConnection.getConnection();
        List<Bill> bills = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByInformationId);
            preparedStatement.setInt(1, informationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill bill = rowMapper.billMapper(resultSet);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public int add(Bill bill) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(add)
        ) {
            preparedStatement.setString(1, bill.getBillName());
            preparedStatement.setDouble(2, bill.getTotalAmount());
            preparedStatement.setInt(3, bill.getBillStatus().getStatusId());
            preparedStatement.setInt(4, bill.getInformation().getInformationId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Bill bill) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(update)
        ) {
            preparedStatement.setString(1, bill.getBillName());
            preparedStatement.setDouble(2, bill.getTotalAmount());
            preparedStatement.setInt(3, bill.getBillStatus().getStatusId());
            preparedStatement.setInt(4, bill.getInformation().getInformationId());
            preparedStatement.setInt(5, bill.getBillId());
            result = preparedStatement.executeUpdate();
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
                PreparedStatement preparedStatement = connection.prepareStatement(delete)
        ) {
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Bill getByCurrentMonthAndYearFromInformationId(int informationId) {
        Bill bill = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(getByMonthAndYearFromInformationId)
        ) {
            preparedStatement.setInt(1, informationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bill = rowMapper.billMapper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    public static void main(String[] args) {
        BillDAO billDAO = new BillDAOImpl();
        System.out.println(billDAO.getByCurrentMonthAndYearFromInformationId(2));
    }

}
