package dao.impl;

import connection.MySQLConnection;
import dao.RequestDAO;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.*;

import entity.Request;

import static dao.statement.RequestSQLStatements.*;

public class RequestDAOImpl implements RequestDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    public List<Request> getAll() {
        List<Request> requests = new ArrayList<>();
        String sql = getAllRequest + orderBy;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
        ) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    requests.add(rowMapper.requestMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Request> getByAccountId(int accountId) {
        List<Request> requests = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRequestByAccountId);
        ) {
            st.setInt(1, accountId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    requests.add(rowMapper.requestMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public Request getById(int id) {
        Request request = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRequestById);
        ) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    request = rowMapper.requestMapper(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    public int add(Request request) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(addRequest);
        ) {
            st.setString(1, request.getTitle());
            st.setString(2, request.getContent());
            st.setString(3, request.getResponse());
            st.setString(4, request.getDateCreated());
            st.setString(5, request.getStatus());
            st.setInt(6, request.getRequestType().getRequestTypeId());
            st.setInt(7, request.getAccount().getAccountID());
            st.setString(8, request.getImage());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Request request) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateRequest);
        ) {
            st.setString(1, request.getTitle());
            st.setString(2, request.getContent());
            st.setString(3, request.getResponse());
            st.setString(4, request.getDateCreated());
            st.setString(5, request.getStatus());
            st.setInt(6, request.getRequestType().getRequestTypeId());
            st.setInt(7, request.getRequestId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int countNumberOfRequestByAccount(int accountId) {
        int totalRequests = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countNumberOfRequestByAccount)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRequests = resultSet.getInt("total_request");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRequests;
    }

    @Override
    public List<Request> getRequestOfAccountByPagination(int page, int accountId) {
        List<Request> requestList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getRequestOfAccountByPagination)) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, (page - 1) * 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(rowMapper.requestMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    @Override
    public int countNumberOfRequest() {
        int totalRequests = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countNumberOfRequest)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRequests = resultSet.getInt("total_request");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRequests;
    }

    @Override
    public List<Request> getAllRequestByPagination(int page) {
        List<Request> requestList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllRequest + orderBy + offSet)) {
            preparedStatement.setInt(1, (page - 1) * 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(rowMapper.requestMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    @Override
    public int countNumberOfRequestByStatus(String status) {
        int totalRequests = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countNumberOfRequest + byStatus)) {
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRequests = resultSet.getInt("total_request");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRequests;
    }

    @Override
    public List<Request> getRequestByStatusAndPagination(int page, String status) {
        List<Request> requestList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllRequest + byStatus + orderBy + offSet)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, (page - 1) * 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(rowMapper.requestMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    @Override
    public int countNumberOfRequestBySearchKey(String searchKey) {
        int totalRequests = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countNumberOfRequest + bySeachKey)) {
            preparedStatement.setString(1, searchKey);
            preparedStatement.setString(2, searchKey);
            preparedStatement.setString(3, searchKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRequests = resultSet.getInt("total_request");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRequests;
    }

    @Override
    public List<Request> getRequestsBySearchKeyByPagination(int page, String searchKey) {
        List<Request> requestList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllRequest + bySeachKey + orderBy + offSet)) {
            preparedStatement.setString(1, searchKey);
            preparedStatement.setString(2, searchKey);
            preparedStatement.setString(3, searchKey);
            preparedStatement.setInt(4, (page - 1) * 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(rowMapper.requestMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    @Override
    public int countNumberOfRequestByStatusAndSearchKey(String filterByStatus, String searchKey) {
        int totalRequests = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countNumberOfRequest + byStatusAndSearchKey)) {
            preparedStatement.setString(1, filterByStatus);
            preparedStatement.setString(2, searchKey);
            preparedStatement.setString(3, searchKey);
            preparedStatement.setString(4, searchKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalRequests = resultSet.getInt("total_request");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRequests;
    }

    @Override
    public List<Request> getRequestsByStatusAndSearchKeyAndPagination(int page, String filterByStatus, String searchKey) {
        List<Request> requestList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllRequest + byStatusAndSearchKey + orderBy + offSet)) {
            preparedStatement.setString(1, filterByStatus);
            preparedStatement.setString(2, searchKey);
            preparedStatement.setString(3, searchKey);
            preparedStatement.setString(4, searchKey);
            preparedStatement.setInt(5, (page - 1) * 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(rowMapper.requestMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    public static void main(String[] args) {
    }
}
