package dao;

import entity.Account;

import java.util.List;

public interface AccountDAO {

    List<Account> getAll();

    Account getById(int id);

    Account getByEmail(String email);

    int update(Account account);

    int add(Account account);

    int numberOfAccount();

    List<Account> getAllAdminAccount();

}
