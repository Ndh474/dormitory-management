package services.impl;

import dao.AccountStatusDAO;
import dao.impl.AccountStatusDAOImpl;
import entity.AccountStatus;
import services.AccountStatusService;

import java.util.List;

public class AccountStatusServiceImpl implements AccountStatusService {
    AccountStatusDAO accountStatusDAO = new AccountStatusDAOImpl();

    @Override
    public List<AccountStatus> getAll() {
        return accountStatusDAO.getAll();
    }

    @Override
    public AccountStatus getById(int id) {
        return accountStatusDAO.getById(id);
    }

    @Override
    public AccountStatus getByName(String name) {
        return accountStatusDAO.getByName(name);
    }

    @Override
    public int update(AccountStatus accountStatus) {
        return accountStatusDAO.update(accountStatus);
    }

    @Override
    public int add(AccountStatus accountStatus) {
        return accountStatusDAO.add(accountStatus);
    }

    public static void main(String[] args) {
        AccountStatusService accountStatusService = new AccountStatusServiceImpl();
        System.out.println(accountStatusService.getAll());
        System.out.println(accountStatusService.getById(1));
        System.out.println(accountStatusService.getByName("Activate"));
    }
}
