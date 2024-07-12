package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class AccountStatus {
    private int statusID;
    private String statusName;

    public AccountStatus(int statusID) {
        this.statusID = statusID;
    }
}
