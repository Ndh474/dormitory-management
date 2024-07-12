package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Room {
    private int roomId;
    private String roomName;
    private int floorNumber;
    private String roomGender;
    private int numberOfStudent;
    private DormRoomStatus roomStatus;
    private Dorm dorm;
    private RoomType roomType;

    public Room(String roomName, int floorNumber, String roomGender, int numberOfStudent, String roomStatus, Dorm dorm, RoomType roomType) {
        this.roomName = roomName;
        this.floorNumber = floorNumber;
        this.roomGender = roomGender;
    }

}
