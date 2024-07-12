package dao.impl;

import connection.MySQLConnection;
import dao.*;
import entity.*;
import mapper.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.statement.AccountSQLStatements.*;

public class AccountDAOImpl implements AccountDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    /**
     * Dùng {@code try with resource để tự động đóng kết nối, ResultSet và PreparedStatement}<br><br>
     * Bỏ {@code Connection}, {@code PreparedStatement} và {@code ResultSet} vào {@code try()} để nó tự động đóng tài nguyên
     * sau khi chạy xong. <br><br>
     * Với những method cần đặt giá trị cho {@code PreparedStatement}, sử dụng hai lần try.
     */
    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllAccount);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                accounts.add(rowMapper.accountMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account getById(int id) {
        Account account = new Account();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAccountById)
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) account = rowMapper.accountMapper(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getByEmail(String email) {
        Account account = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAccountByEmail)
        ) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) account = rowMapper.accountMapper(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public int update(Account account) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateAccount)
        ) {
            st.setString(1, account.getEmail());
            st.setString(2, account.getPassword());
            st.setInt(3, account.getAccountStatus().getStatusID());
            st.setInt(4, account.getRole().getRoleID());
            st.setInt(5, account.getAccountID());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int add(Account account) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(addAccount)
        ) {
            st.setString(1, account.getEmail());
            st.setString(2, account.getPassword());
            st.setInt(3, account.getAccountStatus().getStatusID());
            st.setInt(4, account.getRole().getRoleID());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int numberOfAccount() {
        int total = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getNumberOfAccount);
                ResultSet rs = st.executeQuery()
        ) {
            if (rs.next()) total = rs.getInt("total_account");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public List<Account> getAllAdminAccount() {
        List<Account> accounts = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllAdminAccount);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                accounts.add(rowMapper.accountMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static void main(String[] args) {
        AccountDAOImpl accountService = new AccountDAOImpl();
        System.out.println(accountService.getByEmail("admin@fpt.edu.vn"));
    }
}
