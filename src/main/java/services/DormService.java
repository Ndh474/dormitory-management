package services;

import entity.Dorm;

import java.util.List;

public interface DormService {
    List<Dorm> getAll();

    Dorm getByDormName(String dormName);

    Dorm getByDormID(int dormID);

    int getNumberOfFloorsByDormName(String dormName);

    int update(Dorm dorm, String oldDormName);

    int add(Dorm dorm);

    boolean checkExistDormName(String dormName);

    int numberOfDorm();
}
