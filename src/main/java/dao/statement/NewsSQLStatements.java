package dao.statement;

public class NewsSQLStatements {

    public static final String getAll = """
            SELECT
                n.news_id,
                n.title,
                n.content,
                n.date_created AS date_created,
                a.account_id,
                a.email,
                a.password,
                s.status_id,
                s.status_name,
                r.role_id,
                r.role_name
            FROM news n
            JOIN account a ON n.account_id = a.account_id
            JOIN account_status s ON s.status_id = a.status_id
            JOIN role r ON r.role_id = a.role_id
            """;

    public static final String countNumberOfNews = """
            SELECT COUNT(news_id) AS total_news FROM news;
            """;
    public static final String getAllByPagination = getAll +
                                                    """
                                                            ORDER BY n.news_id DESC
                                                            LIMIT 10 OFFSET ?;
                                                            """;

    public static final String getNewsById = getAll +
                                             """
                                                     WHERE n.news_id = ?;
                                                     """;

    public static final String getAllNewsByTitle = getAll +
                                                   """
                                                           WHERE n.title LIKE ?
                                                           ORDER BY date_created DESC
                                                           LIMIT ? OFFSET ?;
                                                           """;
    public static final String getAllNewsByNumber = getAll +
            """
                    
                    ORDER BY date_created DESC
                    LIMIT ? OFFSET 0;
                    """;

    public static final String getAllNewsByDate = getAll +
                                                  " WHERE date(date_created) = ? " +
                                                  " ORDER BY date_created DESC " ;

    public static final String addNews = """
            INSERT INTO `dorm`.`news`
            (`title`, `content`, `date_created`, `account_id`)
            VALUES
            (?, ?, CURRENT_TIMESTAMP, ?)
            """;

    public static final String updateNews = """
            UPDATE `dorm`.`news`
            SET
            `title` = ?,
            `content` = ?,
            `account_id` = ?
            WHERE `news_id` = ?
            """;

    public static final String deleteNews = "DELETE FROM `dorm`.`news`\n" +
                                            "WHERE news_id = ?;";

}
