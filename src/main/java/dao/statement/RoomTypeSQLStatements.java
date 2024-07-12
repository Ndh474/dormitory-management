package dao.statement;

public class RoomTypeSQLStatements {
    private RoomTypeSQLStatements() {
    }

    public static final String getAllRoomType = """
            select
            	room_type_id,
                room_type_capacity,
                room_type_price,
                electricity_free,
                water_free
            from
            	room_type
            """;
    public static final String getRoomTypeById = getAllRoomType + """
            where
            	room_type_id = ?
            """;
    public static final String addRoomType = """
            insert into room_type
            (room_type_capacity, room_type_price, electricity_free, water_free)
            values
            (?, ?, ?, ?)
            """;
    public static final String updateRoomType = """
            update room_type
            set
                room_type_capacity = ?,
                room_type_price = ?,
                electricity_free = ?,
                water_free = ?
            where
                room_type_id = ?
            """;
}
