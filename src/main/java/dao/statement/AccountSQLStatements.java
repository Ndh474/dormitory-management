package dao.statement;

public class AccountSQLStatements {

    private AccountSQLStatements() {
    }

    public static final String getAllAccount = """
            SELECT
             a.account_id,
             a.email,
             a.password,
             s.status_id,
             s.status_name,
             r.role_id,
             r.role_name
            FROM account a
            JOIN account_status s
            ON s.status_id = a.status_id
            JOIN role r
            ON r.role_id = a.role_id
            """;
    public static final String getAccountById = getAllAccount + """
            WHERE a.account_id = ?
            """;
    public static final String getAccountByEmail = getAllAccount + """
            WHERE a.email = ?
            """;
    public static final String getNumberOfAccount = """
            SELECT COUNT(account_id) AS total_account
            FROM account
            """;
    public static final String updateAccount = """
            UPDATE account
            SET
               email = ?,
               password = ?,
               status_id = ?,
               role_id = ?
            WHERE account_id = ?
            """;
    public static final String addAccount = """
            INSERT INTO Account (
               email,
               password,
               status_id,
               role_id
            ) VALUES (?, ?, ?, ?)
            """;

    public static final String getAllAdminAccount = getAllAccount + """
            WHERE r.role_name = 'admin'
            """;
}
