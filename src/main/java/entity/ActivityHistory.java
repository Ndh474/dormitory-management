package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ActivityHistory {
    private int activityHistoryId;
    private String activityName;
    private String detail;
    private String dateCreated;
    private Information information;
    public ActivityHistory(String activityName, String detail, Information information) {
        this.activityName = activityName;
        this.detail = detail;
        this.information = information;
    }
}
