package dao.impl;

import connection.MySQLConnection;
import dao.BillStatusDAO;
import entity.BillStatus;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.*;

import static dao.statement.BillStatusSQLStatements.*;

public class BillStatusDAOImpl implements BillStatusDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<BillStatus> getAll() {
        List<BillStatus> billStatuses = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAll);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                billStatuses.add(rowMapper.billStatusMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billStatuses;
    }

    @Override
    public BillStatus getById(int id) {
        BillStatus billStatus = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getById)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                billStatus = rowMapper.billStatusMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billStatus;
    }

    @Override
    public BillStatus getByName(String name) {
        BillStatus billStatus = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getByName)
        ) {
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                billStatus = rowMapper.billStatusMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billStatus;
    }

    @Override
    public int update(BillStatus billStatus) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(update)
        ) {
            st.setString(1, billStatus.getStatusName());
            st.setInt(2, billStatus.getStatusId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int add(BillStatus billStatus) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(add)
        ) {
            st.setString(1, billStatus.getStatusName());
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

    public static void main(String[] args) {
        BillStatusDAO billStatusDAO = new BillStatusDAOImpl();
//        System.out.println(billStatusDAO.getAll());
//        System.out.println(billStatusDAO.getById(1));
//        System.out.println(billStatusDAO.getByName("pending"));
//        System.out.println(billStatusDAO.update(new BillStatus(1, "in processing")));
//        System.out.println(billStatusDAO.add(new BillStatus("test")));
        System.out.println(billStatusDAO.delete(4));
    }
}
