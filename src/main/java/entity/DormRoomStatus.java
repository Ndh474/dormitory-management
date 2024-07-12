package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class DormRoomStatus {
    private int statusId;
    private String statusName;

    public DormRoomStatus(int statusId) {
        this.statusId = statusId;
    }
}
