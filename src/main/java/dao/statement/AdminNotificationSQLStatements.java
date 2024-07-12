package dao.statement;

public class AdminNotificationSQLStatements {
    public static final String getAll = """
            SELECT
                an.notification_id,
                an.title,
                an.content,
                an.date_created,
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
                i.room_name,
                a.account_id,
                a.email,
                a.password,
                s.status_id,
                s.status_name,
                r.role_id,
                r.role_name
            FROM
                admin_notification an
            LEFT JOIN
                information i ON an.information_id = i.information_id
            LEFT JOIN
                account a ON i.account_id = a.account_id
            LEFT JOIN
                account_status s ON a.status_id = s.status_id
            LEFT JOIN
                role r ON a.role_id = r.role_id
            """;
    public static final String getById = getAll + " where an.notification_id = ?";
    public static final String getByInformationId = getAll + " where i.information_id = ? order by an.date_created desc";
    public static final String getByCurrentMonthAndYearFromInformationId = getAll + """ 
            where month(an.date_created) = month(now()) and year(an.date_created) = year(now()) and i.information_id = ?
            order by an.date_created desc
            """;

    public static final String update = """
            update admin_notification
            set
                title = ?,
                content = ?,
                information_id = ?
            where notification_id = ?
            """;

    public static final String add = """
            insert into admin_notification
            (title, content, information_id)
            values (?, ?, ?)
            """;

    public static final String delete = "delete from admin_notification where notification_id = ?";
}
