package services;

import entity.AccountStatus;

import java.util.List;

public interface AccountStatusService {
    List<AccountStatus> getAll();

    AccountStatus getById(int id);

    AccountStatus getByName(String name);

    int update(AccountStatus accountStatus);

    int add(AccountStatus accountStatus);
}
