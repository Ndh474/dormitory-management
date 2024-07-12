package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BillStatus {
    private int statusId;
    private String statusName;

    public BillStatus(String statusName) {
        this.statusName = statusName;
    }

    public BillStatus(int statusId) {
        this.statusId = statusId;
    }
}
