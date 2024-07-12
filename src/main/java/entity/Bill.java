package entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Bill {
    private int billId;
    private String billName;
    private double totalAmount;
    private String dateCreated;
    private BillStatus billStatus;
    private Information information;

    public Bill(String billName, double totalAmount, BillStatus billStatus, Information information) {
        this.billName = billName;
        this.totalAmount = totalAmount;
        this.billStatus = billStatus;
        this.information = information;
    }
}
