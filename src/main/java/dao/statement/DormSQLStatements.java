package dao.statement;

public class DormSQLStatements {
    private DormSQLStatements() {
    }

    public static final String getAllDorms = """
            select
                d.dorm_id,
                d.dorm_name,
                d.number_of_floor,
                count(r.room_id) as 'number_of_room' ,
                count(r.room_id) -
                    COUNT(IF(r.room_status = 'inactive', 1, NULL)) -
                    COUNT(IF(r.room_status = 'maintenance', 1, NULL)) AS `available`,
                dars.status_id,
                dars.status_name
            from dorm d
            left join room r on d.dorm_name =r.dorm_name
            join dorm_room_status dars on dars.status_id = d.dorm_status
            group by d.dorm_id
            """;
    private static final String getAllDormsTemp = """
            select
                d.dorm_id,
                d.dorm_name,
                d.number_of_floor,
                count(r.room_id) as `number_of_room` ,
                count(r.room_id) -
                    COUNT(IF(r.room_status = 'inactive', 1, NULL)) -
                    COUNT(IF(r.room_status = 'maintenance', 1, NULL)) AS `available`,
                dars.status_id,
                dars.status_name
            from dorm d
            left join room r on d.dorm_name =r.dorm_name
            join dorm_room_status dars on dars.status_id = d.dorm_status
            """;

    public static final String getDormByDormName = getAllDormsTemp + """
            where d.dorm_name = ?
            group by d.dorm_id
            """;
    public static final String getDormByDormID = getAllDormsTemp + """
            where d.dorm_id = ?
            group by d.dorm_id
            """;
    public static final String getNumberOfFloorsByDormName = """
            select number_of_floor from dorm
            where dorm_name = ?
            """;
    public static final String updateDorm = """
            update dorm
            set
                dorm_name = ?,
                number_of_floor = ?,
                dorm_status = ?
            where dorm_id = ?
            """;
    public static final String addDorm = """
            insert into dorm(dorm_name, number_of_floor, dorm_status)
            values (?, ?, ?)
            """;
    public static final String getNumberOfDorm = """
            select count(dorm_id) as total_dorm
            from dorm
            """;

    public static final String dropForeignKey = "ALTER TABLE room DROP FOREIGN KEY dorm_name_fk2;";
    public static final String updateDorm2 = """
        UPDATE dorm
        SET dorm_name = ?,
            number_of_floor = ?,
            dorm_status = ?
        WHERE dorm_id = ?;
        """;
    public static final String updateRoom = """
        UPDATE room
        SET dorm_name = ?
        WHERE dorm_name = ?;
        """;
    public static final String addForeignKey = """
        ALTER TABLE room
        ADD CONSTRAINT dorm_name_fk2 FOREIGN KEY (dorm_name) REFERENCES dorm(dorm_name) ON DELETE CASCADE;
        """;
}
