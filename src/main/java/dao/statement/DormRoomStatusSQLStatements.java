package dao.statement;

public class DormRoomStatusSQLStatements {
    public static final String getAllStatus = "SELECT * FROM dorm_room_status";
    public static final String getStatusById = "SELECT * FROM dorm_room_status WHERE status_id = ?";
    public static final String getStatusByName = "SELECT * FROM dorm_room_status WHERE status_name = ?";
    public static final String addStatus = "INSERT INTO dorm_room_status (status_name) VALUES (?)";
    public static final String updateStatus = "UPDATE dorm_room_status SET status_name = ? WHERE status_id = ?";
}
