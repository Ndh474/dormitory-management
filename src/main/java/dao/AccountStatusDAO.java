package dao;

import entity.AccountStatus;

import java.util.List;

public interface AccountStatusDAO {
    List<AccountStatus> getAll();

    AccountStatus getById(int id);

    AccountStatus getByName(String name);

    int update(AccountStatus accountStatus);

    int add(AccountStatus accountStatus);
}
