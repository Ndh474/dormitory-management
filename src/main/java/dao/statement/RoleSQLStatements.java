package dao.statement;

public class RoleSQLStatements {
    private RoleSQLStatements() {
    }

    public static final String getAllRole = """
            select
            	role_id,
                role_name
            from
            	role
            """;
    public static final String getRoleById = getAllRole + """
            where
            	role_id = ?
            """;
    public static final String insertRole = """
            insert into role
            (role_name)
            values
            (?)
            """;
    public static final String updateRole = """
            update role
            set
                role_name = ?
            where
                role_id = ?
            """;

    public static final String getByRoleName = getAllRole + """
            where
                role_name = ?
            """;
}
