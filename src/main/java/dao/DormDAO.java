package dao;

import entity.Dorm;

import java.util.List;

public interface DormDAO {
    List<Dorm> getAll();

    Dorm getByDormName(String dormName);

    Dorm getByDormID(int dormID);

    int getNumberOfFloorsByDormName(String dormName);

    int update(Dorm dorm, String oldDormName);

    int add(Dorm dorm);

    int numberOfDorm();
}
