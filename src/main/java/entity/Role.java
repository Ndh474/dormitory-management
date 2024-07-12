package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Role {
    private int roleID;
    private String roleName;

    public Role(int roleID) {
        this.roleID = roleID;
    }
}
