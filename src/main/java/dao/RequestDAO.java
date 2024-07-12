package dao;

import java.util.*;

import entity.*;

public interface RequestDAO {
    List<Request> getAll();

    List<Request> getByAccountId(int accountId);

    Request getById(int id);

    int add(Request message);

    int update(Request message);

    int countNumberOfRequest();

    int countNumberOfRequestByAccount(int accountId);

    List<Request> getRequestOfAccountByPagination(int page, int accountId);

    List<Request> getAllRequestByPagination(int page);

    int countNumberOfRequestByStatus(String status);

    List<Request> getRequestByStatusAndPagination(int page, String status);

    int countNumberOfRequestBySearchKey(String searchKey);

    List<Request> getRequestsBySearchKeyByPagination(int page, String searchKey);

    int countNumberOfRequestByStatusAndSearchKey(String filterByStatus, String searchKey);

    List<Request> getRequestsByStatusAndSearchKeyAndPagination(int page, String filterByStatus, String searchKey);
}
