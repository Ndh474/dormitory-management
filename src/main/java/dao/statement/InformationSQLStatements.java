package dao.statement;

public class InformationSQLStatements {

    private InformationSQLStatements() {
    }

    public static final String getAllInformation = """
            select
            	i.information_id,
                i.roll_number,
                i.citizen_identification,
                i.full_name,
                i.dob,
                i.gender,
                i.phone_number,
                i.address,
                i.balance,
                i.is_paid,
                a.account_id,
                a.email,
                a.password,
                ast.status_id,
                ast.status_name,
                rl.role_id,
                rl.role_name,
                r.room_name
            from
            	information i left join room r on i.room_name = r.room_name
                join account a on i.account_id = a.account_id
                join account_status ast on a.status_id = ast.status_id
                join role rl on a.role_id = rl.role_id
            """;
    public static final String getInformationByAccountId = getAllInformation + """
            where i.account_id = ?
            """;
    public static final String getInformationByName = getAllInformation + """
            where i.full_name like CONCAT(?, CONVERT(?, BINARY),?)
            """;
    public static final String getInformationByPaging = getAllInformation + """
            where rl.role_name = 'student'
            LIMIT ? OFFSET ?
            """;
    public static final String getInformationByRollNumber = getAllInformation + """
            where i.roll_number = ?
            """;
    public static final String getCountOfStudents = """
        SELECT COUNT(*) AS total_students
        FROM information i
        LEFT JOIN room r ON i.room_name = r.room_name
        JOIN account a ON i.account_id = a.account_id
        JOIN account_status ast ON a.status_id = ast.status_id
        JOIN role rl ON a.role_id = rl.role_id
        """;
    public static final String getInformationById = getAllInformation + """
            where i.information_id = ?
            """;
    public static final String getInformationByEmail = getAllInformation + """
            where a.email = ?
            """;
    public static final String getInformationByRoomName = getAllInformation + """
            where r.room_name = ?
            """;
    public static final String getInformationHasRoomNotPaid = getAllInformation + """
            where i.room_name is not null and i.is_paid = 'no'
            """;
    public static final String updateInformation = """
            update information
            set
            roll_number = ?,
            citizen_identification = ?,
            full_name = ?,
            dob = ?,
            gender = ?,
            phone_number = ?,
            address = ?,
            balance = ?,
            is_paid = ?,
            room_name = ?
            where information_id = ?
            """;
    public static final String addInformation = """
            insert into information
            (roll_number, citizen_identification, full_name, dob, gender, phone_number, address, balance, is_paid, account_id, room_name)
            values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
}
