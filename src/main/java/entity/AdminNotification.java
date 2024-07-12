package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class AdminNotification {
    private int notificationId;
    private String title;
    private String content;
    private String dateCreated;
    private Information information;

    public AdminNotification(String title, String content, Information information) {
        this.title = title;
        this.content = content;
        this.information = information;
    }
}
