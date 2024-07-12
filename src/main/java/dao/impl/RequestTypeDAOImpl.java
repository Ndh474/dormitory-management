package dao.impl;

import connection.MySQLConnection;
import dao.RequestTypeDAO;
import entity.RequestType;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.statement.RequestTypeSQLStatements.*;

public class RequestTypeDAOImpl implements RequestTypeDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<RequestType> getAll() {
        List<RequestType> messageTypes = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllRequestType);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                messageTypes.add(rowMapper.requestTypeMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageTypes;
    }

    @Override
    public RequestType getByRequestTypeId(int messageTypeId) {
        RequestType requestTypeType = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRequestTypeById)
        ) {
            st.setInt(1, messageTypeId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) requestTypeType = rowMapper.requestTypeMapper(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestTypeType;
    }

    @Override
    public RequestType getByRequestTypeName(String requestTypeName) {
        RequestType requestType = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRequestTypeByName)
        ) {
            st.setString(1, requestTypeName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) requestType = rowMapper.requestTypeMapper(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestType;
    }


    @Override
    public int update(RequestType requestType) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateMessageType)
        ) {
            st.setString(1, requestType.getRequestTypeName());
            st.setInt(2, requestType.getRequestTypeId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int add(RequestType requestType) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(addMessageType)
        ) {
            st.setString(1, requestType.getRequestTypeName());
            st.setInt(2, requestType.getRequestTypeId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        RequestTypeDAO requestTypeDAO = new RequestTypeDAOImpl();
        System.out.println(requestTypeDAO.getAll());
        System.out.println(requestTypeDAO.getByRequestTypeId(1));
    }
}
