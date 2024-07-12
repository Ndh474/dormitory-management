package dao.statement;

public class RequestSQLStatements {
    private RequestSQLStatements() {
    }

    public static final String getAllRequest = """
            select
                r.request_id,
                r.title,
                r.content,
                r.response,
                r.date_created,
                r.status,
                rt.request_type_id,
                rt.request_type_name,
                a.account_id,
                a.email,
                a.password,
                ast.status_id,
                ast.status_name,
                rl.role_id,
                rl.role_name,
                r.image
            from
                request r join request_type rt on r.request_type_id = rt.request_type_id
                join account a on r.account_id = a.account_id
                join account_status ast on a.status_id = ast.status_id
                join role rl on a.role_id = rl.role_id
            """;
    public static final String orderBy= """
            ORDER BY
                CASE
                WHEN status = 'pending' THEN 1
                WHEN status = 'approved' THEN 2
                WHEN status = 'rejected' THEN 3
                ELSE 4
                END,
                date_created DESC
            """;

    public static final String offSet= """
            LIMIT 10 OFFSET ?
            """;
    public static final String getRequestByAccountId = getAllRequest + """
            where r.account_id = ?
            """;
    public static final String getRequestById = getAllRequest + """
            where r.request_id = ?
            """;
    public static final String updateRequest = """
            update request
            set
                title = ?,
                content = ?,
                response = ?,
                date_created = ?,
                status = ?,
                request_type_id = ?
                where request_id = ?
            """;
    public static final String addRequest = """
            insert into request
            (title, content, response, date_created,status, request_type_id, account_id, image)
            values (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    public static final String countNumberOfRequestByAccount = """
            SELECT COUNT(request_id) AS total_request FROM request WHERE account_id = ?;
            """;

    public static final String getRequestOfAccountByPagination = getAllRequest +
            """
                    WHERE a.account_id = ?
                    ORDER BY CASE
                        WHEN status = 'pending' THEN 1
                        WHEN status = 'approved' THEN 2
                        WHEN status = 'rejected' THEN 3
                        ELSE 4
                        END,
                        date_created DESC
                    LIMIT 10 OFFSET ?
                    """;
    public static final String countNumberOfRequest = """
            SELECT COUNT(request_id) AS total_request FROM
                request r join request_type rt on r.request_type_id = rt.request_type_id
                join account a on r.account_id = a.account_id
                join account_status ast on a.status_id = ast.status_id
                join role rl on a.role_id = rl.role_id
            
            """;

    public static final String byStatus ="""
                    WHERE status = ?
                    """;

    public static final String bySeachKey = """
            		WHERE
            			(lower(a.email) like lower(concat('%',?,'%')))
            			OR (lower(r.title) like lower(concat('%',?,'%')))
                        OR (lower(r.content) like lower(concat('%', ?,'%')))
            """;

    public static final String byStatusAndSearchKey = """
            WHERE status = ?
            AND
            (lower(a.email) LIKE lower(concat('%', ?, '%'))
			OR lower(r.title) LIKE lower(concat('%', ?, '%'))
			OR lower(r.content) LIKE lower(concat('%', ?, '%')))
            """;



}
