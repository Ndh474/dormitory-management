package services;

import entity.BillStatus;

import java.util.List;

public interface BillStatusService {
    List<BillStatus> getAll();

    BillStatus getById(int id);

    BillStatus getByName(String name);

    int update(BillStatus billStatus);

    int add(BillStatus billStatus);

    int delete(int id);
}
