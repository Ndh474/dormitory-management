package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class RoomUsage {
    private int roomUsageId;
    private int electricityUsed;
    private int waterUsed;
    private int month;
    private int year;
    private String roomName;

    public RoomUsage(int electricityUsed, int waterUsed, int month, int year, String roomName) {
        this.electricityUsed = electricityUsed;
        this.waterUsed = waterUsed;
        this.month = month;
        this.year = year;
        this.roomName = roomName;
    }
}
