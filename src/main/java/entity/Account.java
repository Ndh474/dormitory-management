package entity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Account {
    private int accountID;
    private String email;
    private String password;
    private AccountStatus accountStatus;
    private Role role;

    public Account(int accountID) {
        this.accountID = accountID;
    }

    public Account(String email, String password, AccountStatus accountStatus, Role role) {
        this.email = email;
        this.password = password;
        this.accountStatus = accountStatus;
        this.role = role;
    }

}
