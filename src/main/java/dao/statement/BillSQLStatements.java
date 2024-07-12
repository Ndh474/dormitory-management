package dao.statement;

public class BillSQLStatements {
    public static final String getAll = """
            SELECT
                b.bill_id,
                b.bill_name,
                b.total_amount,
                b.date_created,
                b.information_id,
                bs.bill_status_id,
                bs.bill_status_name,

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

                ast.status_id,
                ast.status_name,

                r.role_id,
                r.role_name

            FROM
                dorm.bill b
                LEFT JOIN dorm.bill_status bs ON b.bill_status_id = bs.bill_status_id
                LEFT JOIN dorm.information inf ON b.information_id = inf.information_id
                LEFT JOIN dorm.account acc ON inf.account_id = acc.account_id
                LEFT JOIN dorm.account_status ast ON acc.status_id = ast.status_id
                LEFT JOIN dorm.role r ON acc.role_id = r.role_id
            """;

    public static final String getById = getAll + """
            WHERE
                b.bill_id = ?
            """;
    public static final String getByInformationId = getAll + """
            WHERE
                b.information_id = ?
            order by b.date_created desc
            """;
    public static final String add = """
            INSERT INTO dorm.bill (
                bill_name,
                total_amount,
                bill_status_id,
                information_id
            ) VALUES (?, ?, ?, ?)
            """;
    public static final String update = """
            UPDATE dorm.bill
            SET
                bill_name = ?,
                total_amount = ?,
                bill_status_id = ?,
                information_id = ?
            WHERE
                bill_id = ?
            """;
    public static final String delete = """
            delete from bill where bill_id =?
            """;
    public static final String getByMonthAndYearFromInformationId = getAll + """
            WHERE
                MONTH(b.date_created) = MONTH(CURDATE())
                AND YEAR(b.date_created) = YEAR(CURDATE())
                and inf.information_id = ?
            """;
}
