package services.impl;

import dao.AccountDAO;
import dao.impl.AccountDAOImpl;
import entity.Account;
import entity.AccountStatus;
import entity.Role;
import services.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    AccountDAO accountDao = new AccountDAOImpl();

    @Override
    public List<Account> getAllAccount() {
        return accountDao.getAll();
    }

    @Override
    public Account getAccountById(int id) {
        return accountDao.getById(id);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountDao.getByEmail(email);
    }

    @Override
    public int updateAccount(Account account) {
        return accountDao.update(account);
    }

    @Override
    public int addAccount(Account account) {
        return accountDao.add(account);
    }

    @Override
    public boolean checkEmailExist(String email) {
        List<Account> allAccounts = accountDao.getAll();
        for (Account account : allAccounts) {
            if (account.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberOfAccount() {
        return accountDao.numberOfAccount();
    }

    @Override
    public List<Account> getAllAdminAccount() {
        return accountDao.getAllAdminAccount();
    }

    public static void main(String[] args) {
        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.updateAccount(new Account(4, "hieundhe180314@fpt.edu.vn", "hieu123", new AccountStatus(1, "activate"), new Role(1, "admin")));
    }
}
