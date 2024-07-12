package entity;

import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Information {
    private int informationId;
    private String rollNumber;
    private String citizenIdentification;
    private String fullName;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String address;
    private double balance;
    private String isPaid;
    private Account account;
    private String roomName;

    public Information(int informationId) {
        this.informationId = informationId;
    }

    public Information(String rollNumber, String citizenIdentification, String fullName, String dob, String gender, String phoneNumber, String address, double balance, String isPaid, Account account, String roomName) {
        this.rollNumber = rollNumber;
        this.citizenIdentification = citizenIdentification;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = balance;
        this.isPaid = isPaid;
        this.account = account;
        this.roomName = roomName;
    }
}

