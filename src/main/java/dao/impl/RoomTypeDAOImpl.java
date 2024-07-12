package dao.impl;

import connection.*;
import dao.*;
import entity.*;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.*;

import static dao.statement.RoleSQLStatements.getAllRole;
import static dao.statement.RoomTypeSQLStatements.*;

public class RoomTypeDAOImpl implements RoomTypeDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<RoomType> getAll() {
        List<RoomType> roomTypes = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllRoomType);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                roomTypes.add(rowMapper.roomTypeMapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomTypes;
    }

    @Override
    public RoomType getById(int roomTypeId) {
        RoomType roomType = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRoomTypeById)
        ) {
            st.setInt(1, roomTypeId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) roomType = rowMapper.roomTypeMapper(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomType;
    }

    @Override
    public int add(RoomType roomType) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(addRoomType)
        ) {
            st.setInt(1, roomType.getRoomCapacity());
            st.setDouble(2, roomType.getRoomPrice());
            st.setDouble(3, roomType.getElectricityFree());
            st.setDouble(4, roomType.getWaterFree());
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(RoomType roomType) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateRoomType)
        ) {
            st.setInt(1, roomType.getRoomCapacity());
            st.setDouble(2, roomType.getRoomPrice());
            st.setDouble(3, roomType.getElectricityFree());
            st.setDouble(4, roomType.getWaterFree());
            st.setInt(5, roomType.getRoomTypeId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        RoomTypeDAO roomTypeDAO = new RoomTypeDAOImpl();
        System.out.println(roomTypeDAO.getAll());
        System.out.println(roomTypeDAO.getById(1));
        System.out.println(roomTypeDAO.add(RoomType.builder()
                .roomCapacity(10)
                .roomPrice(1000000)
                .electricityFree(100000)
                .waterFree(100000)
                .build()));
        System.out.println(roomTypeDAO.update(RoomType.builder()
                .roomTypeId(7)
                .roomCapacity(20)
                .roomPrice(1000000)
                .electricityFree(100000)
                .waterFree(100000)
                .build()));
    }
}
