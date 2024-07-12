package dao.statement;

public class RequestTypeSQLStatements {
    private RequestTypeSQLStatements() {
    }

    public static final String getAllRequestType = """
            select * from request_type
            """;
    public static final String getRequestTypeById = getAllRequestType + """
            where request_type_id = ?
            """;
    public static final String getRequestTypeByName = getAllRequestType + """
            where request_type_name = ?
            """;
    public static final String updateMessageType = """
            update request_type
            set
                request_type_name = ?,
            where request_type_id = ?
            """;
    public static final String addMessageType = """
            insert into message_type
            (request_type_name)
            values (?)
            """;
}
