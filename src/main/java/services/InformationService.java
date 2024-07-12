package services;

import entity.Information;

import java.util.List;

public interface InformationService {
    List<Information> getAll();

    Information getByAccountID(int accountID);

    Information getByRollNumber(String rollNumber);

    Information getByInformationId(int id);

    List<Information> getByHasRoomNameNotPaid();

    List<Information> getFromRoomName(String roomName);

    int update(Information information);

    int add(Information information);

    boolean checkExistRollNumber(String rollNumber);

    boolean checkExistCitizenIdentification(String CitizenIdentification);
}
