package dao;

import entity.BillStatus;

import java.util.List;

public interface BillStatusDAO {
    List<BillStatus> getAll();

    BillStatus getById(int id);

    BillStatus getByName(String name);

    int update(BillStatus billStatus);

    int add(BillStatus billStatus);

    int delete(int id);
}
