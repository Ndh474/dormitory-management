package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Dorm {
    private int dormId;
    private String dormName;
    private int numberOfFloor;
    private int numberOfRoom;
    private int available;
    private DormRoomStatus status;
}
