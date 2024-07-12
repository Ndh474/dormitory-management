package services;

import entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccount();

    Account getAccountById(int id);

    Account getAccountByEmail(String email);

    int updateAccount(Account account);

    int addAccount(Account account);

    boolean checkEmailExist(String email);

    int numberOfAccount();

    List<Account> getAllAdminAccount();
}
