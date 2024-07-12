package dao.statement;

public class RoomUsageStatements {
    private RoomUsageStatements() {
    }

    public static final String getAllRoomUsage = """
            select
            	room_usage_id,
                electricity_used,
                water_used,
                month,
                year,
                room_name
            from
            	room_usage
            """;
    public static final String getRoomUsageById = getAllRoomUsage + """
            where
            	room_usage_id = ?
            """;
    public static final String getRoomUsageByRoomName = getAllRoomUsage + """
            where
            	room_name = ?
            """;
    public static final String getRoomUsageByRoomAndMonthAndYear = getRoomUsageByRoomName + """
            and
                month = ?
            and
                year = ?
            """;
    public static final String insertRoomUsage = """
            insert into room_usage
            (electricity_used, water_used, month, year, room_name)
            values
            (?, ?, ?, ?, ?)
            """;
    public static final String updateRoomUsage = """
            update room_usage
            set
                electricity_used = ?,
                water_used = ?,
                month = ?,
                year = ?,
                room_name = ?
            where
                room_usage_id = ?
            """;
}

