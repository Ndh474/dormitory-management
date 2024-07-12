package services;

import entity.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAll();

    Bill getById(int id);

    List<Bill> getByInformationId(int informationId);

    int update(Bill bill);

    int add(Bill bill);

    int delete(int id);

    Bill getByCurrentMonthAndYearFromInformationId(int informationId);

}
