package dao.statement;

public class BillStatusSQLStatements {
    public static final String getAll = "SELECT * FROM bill_status";

    public static final String getById = "SELECT * FROM bill_status WHERE bill_status_id = ?";

    public static final String getByName = "SELECT * FROM bill_status WHERE bill_status_name = ?";

    public static final String update = "UPDATE bill_status SET bill_status_name = ? WHERE bill_status_id = ?";

    public static final String add = "INSERT INTO bill_status (bill_status_name) VALUES (?)";

    public static final String delete = "DELETE FROM bill_status WHERE bill_status_id = ?";
}
