package dao.statement;

public class AccountStatusSQLStatements {
    private AccountStatusSQLStatements() {
    }

    public static final String getAllAccountStatus = """
            select
                status_id,
                status_name
            from
                account_status
            """;
    public static final String getAccountStatusById = getAllAccountStatus + """
            where status_id = ?
            """;
    public static final String getAccountStatusByName = getAllAccountStatus + """
            where status_name = ?
            """;
    public static final String updateAccountStatus = """
            update account_status
            set
                status_name = ?
                where status_id = ?
            """;
    public static final String addAccountStatus = """
            insert into account_status(status_name)
            values (?)
            """;
}
