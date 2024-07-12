package dao.statement;

public class RoomSQLStatements {
    private RoomSQLStatements() {
    }

    public static final String selectFrom = """
            SELECT
                r.room_id,
                r.room_name,
                r.floor_number,
                r.room_gender,
                IFNULL(COUNT(i.information_id), 0) AS number_of_student,
                drs.status_id,
                drs.status_name,
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
                join dorm_room_status drs on drs.status_id = r.room_status
            """;
    public static final String groupBy = """
            GROUP BY
                 r.room_name,
                 r.floor_number,
                 r.room_gender,
                 drs.status_id,
                 drs.status_name,
                 d.dorm_name,
                 d.number_of_floor,
                 rt.room_type_id,
                 rt.room_type_capacity,
                 rt.room_type_price,
                 rt.electricity_free,
                 rt.water_free
            """;
    public static final String getRoomByName = selectFrom + """
            WHERE
                r.room_name = ?
            """ + groupBy;
    public static final String addRoom = """
            INSERT INTO room (
                room_name,
                floor_number,
                room_status,
                dorm_name,
                room_type_id)
            VALUES (?, ?, ?, ?, ?)
            """;
    public static final String updateRoom = """
            UPDATE room
            SET
                room_name = ?,
                floor_number = ?,
                room_gender = ?,
                room_status = ?,
                dorm_name = ?,
                room_type_id = ?
            WHERE
                room_id = ?
            """;
    public static final String totalRoom = """
            SELECT
                COUNT(room_id) AS total_room
            FROM
                room
            WHERE
                dorm_name = ?
            """;
    public static final String totalFullRoom = """
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
                drs.status_id,
                drs.status_name,
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
                join dorm_room_status drs on drs.status_id = r.room_status
                    WHERE
                        d.dorm_name = ? AND
                        r.room_gender = ? AND
                        r.room_status = 'active'
                    GROUP BY
                         r.room_name,
                         r.floor_number,
                         r.room_gender,
                         drs.status_id,
                         drs.status_name,
                         d.dorm_name,
                         d.number_of_floor,
                         rt.room_type_id,
                         rt.room_type_capacity,
                         rt.room_type_price,
                         rt.electricity_free,
                         rt.water_free
                ) AS rooms
            WHERE
                rooms.number_of_student = rooms.room_type_capacity;
            """;
    public static final String getAllRoomHavingStudent = """
            SELECT
                r.room_id,
                r.room_name,
                r.floor_number,
                r.room_gender,
                COUNT(i.information_id) AS number_of_student,
                drs.status_id,
                drs.status_name,
                d.dorm_id,
                d.dorm_name,
                d.number_of_floor,
                rt.room_type_id,
                rt.room_type_capacity,
                rt.room_type_price,
                rt.electricity_free,
                rt.water_free
            FROM
                dorm.room r
            JOIN
                dorm.information i ON r.room_name = i.room_name
            LEFT JOIN
                dorm.dorm d ON r.dorm_name = d.dorm_name
            LEFT JOIN
                dorm.dorm_room_status drs ON r.room_status = drs.status_id
            LEFT JOIN
                dorm.room_type rt ON r.room_type_id = rt.room_type_id
            GROUP BY
                r.room_id,
                r.room_name,
                r.floor_number,
                r.room_gender,
                drs.status_id,
                drs.status_name,
                d.dorm_id,
                d.dorm_name,
                d.number_of_floor,
                rt.room_type_id,
                rt.room_type_capacity,
                rt.room_type_price
            HAVING
                COUNT(i.information_id) > 0;
            """;
    public static final String getNumberOfRoom = """
            SELECT
                COUNT(room_id) AS total_room
            FROM
                room
            """;

    public static final String dropForeignKey = "ALTER TABLE room_usage DROP FOREIGN KEY room_name_fk2;";
    public static final String addForeignKey = """
            ALTER TABLE room_usage
            ADD CONSTRAINT room_name_fk2 FOREIGN KEY (room_name) REFERENCES room(room_name) ON DELETE CASCADE;
            """;
    public static final String updateRoomUsage = """
            UPDATE room_usage
                SET room_name = ?
                WHERE room_name = ?
            """;
}
