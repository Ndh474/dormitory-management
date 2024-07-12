package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class Chart {
    private int electric_usage;
    private int water_usage;
    private int month;
    private int year;
}
