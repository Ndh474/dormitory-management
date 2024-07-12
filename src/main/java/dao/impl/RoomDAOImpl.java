package dao.impl;

import connection.MySQLConnection;
import dao.RoomDAO;
import dao.statement.RoomSQLStatements;
import entity.Room;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;

import java.util.*;

import static dao.statement.RoomSQLStatements.*;

public class RoomDAOImpl implements RoomDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<Room> getAllByFilter(String dormName, String roomName, String floorNumber, String roomTypeId, String roomGender) {
        List<Room> rooms = new ArrayList<>();
        StringBuilder sql = getStringBuilder(dormName, roomName, floorNumber, roomTypeId, roomGender);
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(sql.toString())
        ) {
            int index = 1;
            if (dormName != null) {
                st.setString(index++, dormName);
            }
            if (roomName != null) {
                st.setString(index++, "%" + roomName + "%");
            }
            if (floorNumber != null) {
                st.setString(index++, floorNumber);
            }
            if (roomTypeId != null) {
                st.setString(index++, roomTypeId);
            }
            if (roomGender != null) {
                st.setString(index, roomGender);
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    rooms.add(rowMapper.roomMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    private static StringBuilder getStringBuilder(String dormName, String roomName, String floorNumber, String roomTypeId, String roomGender) {
        StringBuilder sql = new StringBuilder(selectFrom);
        boolean whereAdded = false;
        if (dormName != null) {
            sql.append("WHERE r.dorm_name = ? ");
            whereAdded = true;
        }
        if (roomName != null) {
            if (whereAdded) {
                sql.append("AND r.room_name LIKE ?");
            } else {
                sql.append("WHERE r.room_name LIKE ?");
                whereAdded = true;
            }
        }
        if (floorNumber != null) {
            if (whereAdded) {
                sql.append("AND r.floor_number = ? ");
            } else {
                sql.append("WHERE r.floor_number = ? ");
                whereAdded = true;
            }
        }
        if (roomTypeId != null) {
            if (whereAdded) {
                sql.append("AND r.room_type_id = ? ");
            } else {
                sql.append("WHERE r.room_type_id = ? ");
            }
        }
        if (roomGender != null) {
            if (whereAdded) {
                sql.append("AND r.room_gender IN (?, 'not set') ");
            } else {
                sql.append("WHERE r.room_gender IN (?, 'not set')");
            }
        }
        sql.append(groupBy);
        sql.append("""
                ORDER BY
                    r.room_status = 'active' DESC,
                    r.room_name ASC
                """);
        return sql;
    }

    @Override
    public Room getRoomByName(String roomName) {
        Room room = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRoomByName)
        ) {
            st.setString(1, roomName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    room = rowMapper.roomMapper(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public int totalRoom(String dormName, String gender) {
        int totalRoom = 0;
        StringBuilder sql = new StringBuilder(RoomSQLStatements.totalRoom);
        if (gender != null) {
            sql.append("AND room_gender in (?, 'not set')");
        }
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(sql.toString())
        ) {
            st.setString(1, dormName);
            if (gender != null) {
                st.setString(2, gender);
            }
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalRoom = rs.getInt("total_room");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRoom;
    }

    @Override
    public int totalFullRoom(String dormName, String gender) {
        int totalRoom = 0;
        StringBuilder sql = new StringBuilder("""
                    SELECT
                    COUNT(*) AS total_room
                FROM
                    (
                        SELECT
                            r.room_id,
                            r.room_name,
                            r.floor_number,
                            r.room_gender,
                            IFNULL(COUNT(i.information_id), 0) AS number_of_student,
                            r.room_status,
                            d.dorm_id,
                            d.dorm_name,
                            d.number_of_floor,
                            rt.room_type_id,
                            rt.room_type_capacity,
                            rt.room_type_price,
                            rt.electricity_free,
                            rt.water_free
                        FROM
                            room r
                            LEFT JOIN information i ON r.room_name = i.room_name
                            JOIN dorm d ON r.dorm_name = d.dorm_name
                            JOIN room_type rt ON r.room_type_id = rt.room_type_id
                        WHERE
                            d.dorm_name = ?
                """);
        if (gender != null) {
            sql.append("""
                    AND
                        r.room_gender = ? AND
                        r.room_status = 'active'
                    """);
        }
        sql.append("""
                    GROUP BY
                            r.room_name,
                            r.floor_number,
                            r.room_gender,
                            r.room_status,
                            d.dorm_name,
                            d.number_of_floor,
                            rt.room_type_id,
                            rt.room_type_capacity,
                            rt.room_type_price,
                            rt.electricity_free,
                            rt.water_free
                    ) AS rooms
                WHERE
                    rooms.number_of_student = rooms.room_type_capacity
                """);
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(sql.toString())
        ) {
            st.setString(1, dormName);
            if (gender != null) {
                st.setString(2, gender);
            }
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalRoom = rs.getInt("total_room");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRoom;
    }

    @Override
    public int add(Room room) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(addRoom);
        ) {
            st.setString(1, room.getRoomName());
            st.setInt(2, room.getFloorNumber());
            st.setInt(3, room.getRoomStatus().getStatusId());
            st.setString(4, room.getDorm().getDormName());
            st.setInt(5, room.getRoomType().getRoomTypeId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Room room) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateRoom);
        ) {
            st.setString(1, room.getRoomName());
            st.setInt(2, room.getFloorNumber());
            st.setString(3, room.getRoomGender());
            st.setInt(4, room.getRoomStatus().getStatusId());
            st.setString(5, room.getDorm().getDormName());
            st.setInt(6, room.getRoomType().getRoomTypeId());
            st.setInt(7, room.getRoomId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Room> getAllRoomHavingStudent() {
        List<Room> rooms = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllRoomHavingStudent)
        ) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    rooms.add(rowMapper.roomMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public int numberOfRoom() {
        int total = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getNumberOfRoom)
        ) {
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("total_room");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public int updateRoomFK(Room room, String oldRoomName) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                Statement stmt = connection.createStatement();
                PreparedStatement stUpdateRoom = connection.prepareStatement(updateRoom);
                PreparedStatement stUpdateRoomUsage = connection.prepareStatement(updateRoomUsage)
        ) {
            // Drop foreign key
            stmt.executeUpdate(dropForeignKey);

            // Update room table
            stUpdateRoom.setString(1, room.getRoomName());
            stUpdateRoom.setInt(2, room.getFloorNumber());
            stUpdateRoom.setString(3, room.getRoomGender());
            stUpdateRoom.setInt(4, room.getRoomStatus().getStatusId());
            stUpdateRoom.setString(5, room.getDorm().getDormName());
            stUpdateRoom.setInt(6, room.getRoomType().getRoomTypeId());
            stUpdateRoom.setInt(7, room.getRoomId());
            result = stUpdateRoom.executeUpdate();

            // Update room_usage table
            stUpdateRoomUsage.setString(1, room.getRoomName());
            stUpdateRoomUsage.setString(2, oldRoomName);
            stUpdateRoomUsage.executeUpdate();

            // Add foreign key
            stmt.executeUpdate(addForeignKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        RoomDAO roomDAO = new RoomDAOImpl();
        Room room = roomDAO.getRoomByName("A101");
        String roomName = room.getRoomName();
        room.setRoomName("A999");
        System.out.println(roomDAO.updateRoomFK(room, "A101"));
    }
}
