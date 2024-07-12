package dao.statement;

public class ActivityHistorySQLStatements {
    public static final String getAllActivityHistory = """
            SELECT
                ah.activity_history_id,
                ah.activity_name,
                ah.detail,
                ah.date_created,
                inf.information_id,
                inf.roll_number,
                inf.citizen_identification,
                inf.full_name,
                inf.dob,
                inf.gender,
                inf.phone_number,
                inf.address,
                inf.balance,
                inf.is_paid,
                inf.room_name,
                acc.account_id,
                acc.email,
                acc.password,
                acc.status_id,
                ast.status_name,
                acc.role_id,
                rl.role_name
            FROM
                dorm.activity_history AS ah
            JOIN
                dorm.information AS inf ON ah.information_id = inf.information_id
            JOIN
                dorm.account AS acc ON inf.account_id = acc.account_id
            JOIN
                dorm.account_status AS ast ON acc.status_id = ast.status_id
            JOIN
                dorm.role AS rl ON acc.role_id = rl.role_id
            """;
    public static final String getActivityByInformationId = getAllActivityHistory + """
            WHERE
                ah.information_id = ?
            order by ah.date_created desc
            limit 10;
            """;
    public static final String getActivityByActivityHistoryId = getAllActivityHistory + """
            WHERE
                ah.activity_history_id = ?;
            """;
    public static final String insertActivityHistory = """
            INSERT INTO dorm.activity_history(
                activity_name,
                detail,
                information_id
            )
            VALUES (?, ?, ?);
            """;
    public static final String updateActivityHistory = """
            UPDATE dorm.activity_history
            SET
                activity_name = ?,
                detail = ?
            WHERE
                activity_history_id = ?;
            """;
    public static final String deleteActivityHistory = """
            DELETE FROM dorm.activity_history
            WHERE
                activity_history_id = ?;
            """;
}
