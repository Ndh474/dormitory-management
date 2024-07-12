package dao.impl;

import connection.MySQLConnection;
import dao.AccountStatusDAO;
import entity.AccountStatus;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.statement.AccountStatusSQLStatements.*;

public class AccountStatusDAOImpl implements AccountStatusDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<AccountStatus> getAll() {
        List<AccountStatus> accountStatuses = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllAccountStatus);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                accountStatuses.add(rowMapper.accountStatusMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountStatuses;
    }

    @Override
    public AccountStatus getById(int id) {
        AccountStatus accountStatus = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAccountStatusById)
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    accountStatus = rowMapper.accountStatusMapper(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountStatus;
    }

    @Override
    public AccountStatus getByName(String name) {
        AccountStatus accountStatus = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAccountStatusByName)
        ) {
            st.setString(1, name);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    accountStatus = rowMapper.accountStatusMapper(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountStatus;
    }

    @Override
    public int update(AccountStatus accountStatus) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateAccountStatus)
        ) {
            st.setString(1, accountStatus.getStatusName());
            st.setInt(2, accountStatus.getStatusID());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int add(AccountStatus accountStatus) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(addAccountStatus)
        ) {
            st.setString(1, accountStatus.getStatusName());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        AccountStatusDAO accountStatusDAO = new AccountStatusDAOImpl();
        System.out.println(accountStatusDAO.getByName("activate"));
    }
}
