package services.impl;

import dao.RequestDAO;
import dao.impl.RequestDAOImpl;
import entity.Request;
import services.*;

import java.util.*;

public class RequestServiceImpl implements RequestService {
    private RequestDAO requestDAO = new RequestDAOImpl();

    @Override
    public List<Request> getAll() {
        return requestDAO.getAll();
    }

    @Override
    public List<Request> getByAccountId(int accountId) {
        return requestDAO.getByAccountId(accountId);
    }

    @Override
    public Request getById(int id) {
        return requestDAO.getById(id);
    }

    @Override
    public int add(Request message) {
        return requestDAO.add(message);
    }

    @Override
    public int update(Request message) {
        return requestDAO.update(message);
    }

    @Override
    public int countNumberOfRequest() {
        return requestDAO.countNumberOfRequest();
    }

    @Override
    public int countNumberOfRequestByAccount(int accountId) {
        return requestDAO.countNumberOfRequestByAccount(accountId);
    }

    @Override
    public List<Request> getRequestOfAccountByPagination(int page, int accountId) {
        return requestDAO.getRequestOfAccountByPagination(page, accountId);
    }

    @Override
    public List<Request> getRequestsByPagination(int page) {
        return requestDAO.getAllRequestByPagination(page);
    }

    @Override
    public int countNumberOfRequestByStatus(String status) {
        return requestDAO.countNumberOfRequestByStatus(status);
    }

    @Override
    public List<Request> getRequestByStatusAndPagination(int page, String status) {
        return requestDAO.getRequestByStatusAndPagination(page, status);
    }

    @Override
    public int countNumberOfRequestBySearchKey(String searchKey) {
        return requestDAO.countNumberOfRequestBySearchKey(searchKey);
    }

    @Override
    public List<Request> getRequestsBySearchKeyByPagination(int page, String searchKey) {
        return requestDAO.getRequestsBySearchKeyByPagination(page, searchKey);
    }

    @Override
    public int countNumberOfRequestByStatusAndSearchKey(String filterByStatus, String searchKey) {
        return requestDAO.countNumberOfRequestByStatusAndSearchKey(filterByStatus, searchKey);
    }

    @Override
    public List<Request> getRequestsByStatusAndSearchKeyAndPagination(int page, String filterByStatus, String searchKey) {
        return requestDAO.getRequestsByStatusAndSearchKeyAndPagination(page, filterByStatus, searchKey);
    }

    public static void main(String[] args) {
        RequestService requestService = new RequestServiceImpl();
        List<Request> messages = requestService.getRequestByStatusAndPagination(1, "pending");
        for (Request message : messages) {
            System.out.println(message);
        }
    }

}
