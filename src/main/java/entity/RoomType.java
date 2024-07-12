package entity;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class RoomType {
    private int roomTypeId;
    private int roomCapacity;
    private double roomPrice;
    private int electricityFree;
    private int waterFree;

    public RoomType(int roomCapacity, double roomPrice, int electricityFree, int waterFree) {
        this.roomCapacity = roomCapacity;
        this.roomPrice = roomPrice;
        this.electricityFree = electricityFree;
        this.waterFree = waterFree;
    }
}
