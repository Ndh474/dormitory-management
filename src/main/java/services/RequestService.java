package services;

import entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> getAll();

    List<Request> getByAccountId(int accountId);

    Request getById(int id);

    int add(Request message);

    int update(Request message);

    int countNumberOfRequest();

    int countNumberOfRequestByAccount(int accountId);

    List<Request> getRequestOfAccountByPagination(int page, int accountId);

    List<Request> getRequestsByPagination(int page);

    int countNumberOfRequestByStatus(String status);

    List<Request> getRequestByStatusAndPagination(int page, String status);

    int countNumberOfRequestBySearchKey(String searchKey);

    List<Request> getRequestsBySearchKeyByPagination(int page, String searchKey);

    int countNumberOfRequestByStatusAndSearchKey(String filterByStatus, String searchKey);

    List<Request> getRequestsByStatusAndSearchKeyAndPagination(int page, String filterByStatus, String searchKey);
}
