package entity;

import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class News {
    private int newsId;
    private String title;
    private String content;
    private String dateCreated;
    private Account account;

    public News(String title, String content, String dateCreated, Account account) {
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.account = account;
    }
}
