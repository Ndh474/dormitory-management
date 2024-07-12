package dao.impl;

import connection.*;
import dao.*;
import entity.*;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.*;

import static dao.statement.RoomTypeSQLStatements.getAllRoomType;
import static dao.statement.RoomUsageStatements.*;

public class RoomUsageDAOImpl implements RoomUsageDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<RoomUsage> getAll() {
        List<RoomUsage> roomUsageList = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllRoomUsage);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                roomUsageList.add(rowMapper.roomUsageMapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomUsageList;
    }

    @Override
    public RoomUsage getById(int id) {
        RoomUsage roomUsage = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRoomUsageById)
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    roomUsage = rowMapper.roomUsageMapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomUsage;
    }

    @Override
    public List<RoomUsage> getByRoomName(String roomName) {
        List<RoomUsage> roomUsageList = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRoomUsageByRoomName)
        ) {
            st.setString(1, roomName);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    roomUsageList.add(rowMapper.roomUsageMapper(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomUsageList;
    }

    @Override
    public int add(RoomUsage roomUsage) {
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(insertRoomUsage)
        ) {
            st.setDouble(1, roomUsage.getElectricityUsed());
            st.setDouble(2, roomUsage.getWaterUsed());
            st.setInt(3, roomUsage.getMonth());
            st.setInt(4, roomUsage.getYear());
            st.setString(5, roomUsage.getRoomName());
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(RoomUsage roomUsage) {
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateRoomUsage)
        ) {
            st.setDouble(1, roomUsage.getElectricityUsed());
            st.setDouble(2, roomUsage.getWaterUsed());
            st.setInt(3, roomUsage.getMonth());
            st.setInt(4, roomUsage.getYear());
            st.setString(5, roomUsage.getRoomName());
            st.setInt(6, roomUsage.getRoomUsageId());
            return st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RoomUsage getByRoomAndMonthAndYear(String roomName, int month, int year) {
        RoomUsage roomUsage = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRoomUsageByRoomAndMonthAndYear)
        ) {
            st.setString(1, roomName);
            st.setInt(2, month);
            st.setInt(3, year);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    roomUsage = rowMapper.roomUsageMapper(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomUsage;
    }

    public static void main(String[] args) {
        RoomUsageDAO roomUsageDAO = new RoomUsageDAOImpl();
        System.out.println(roomUsageDAO.getAll());
        System.out.println(roomUsageDAO.getById(1));
        System.out.println(roomUsageDAO.getByRoomName("A101"));
        System.out.println(roomUsageDAO.add(new RoomUsage(0, 100, 100, 1, 2021, "A101")));
        System.out.println(roomUsageDAO.update(new RoomUsage(1, 200, 200, 2, 2021, "A101")));
    }
}
