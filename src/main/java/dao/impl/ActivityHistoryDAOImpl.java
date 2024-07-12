package dao.impl;

import connection.MySQLConnection;
import dao.ActivityHistoryDAO;
import entity.ActivityHistory;
import entity.Information;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import static dao.statement.ActivityHistorySQLStatements.*;

public class ActivityHistoryDAOImpl implements ActivityHistoryDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<ActivityHistory> getAll() {
        List<ActivityHistory> activityHistories = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllActivityHistory);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                activityHistories.add(rowMapper.activityHistoryMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityHistories;
    }

    @Override
    public List<ActivityHistory> getByInformationId(int informationId) {
        List<ActivityHistory> activityHistories = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getActivityByInformationId)
        ) {
            st.setInt(1, informationId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    activityHistories.add(rowMapper.activityHistoryMapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityHistories;
    }

    @Override
    public ActivityHistory getByActivityHistoryId(int activityHistoryId) {
        ActivityHistory activityHistory = new ActivityHistory();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getActivityByActivityHistoryId)
        ) {
            st.setInt(1, activityHistoryId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) activityHistory = rowMapper.activityHistoryMapper(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityHistory;
    }

    @Override
    public int insert(ActivityHistory activityHistory) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(insertActivityHistory)
        ) {
            st.setString(1, activityHistory.getActivityName());
            st.setString(2, activityHistory.getDetail());
            st.setInt(3, activityHistory.getInformation().getInformationId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(ActivityHistory activityHistory) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateActivityHistory)
        ) {
            st.setString(1, activityHistory.getActivityName());
            st.setString(2, activityHistory.getDetail());
            st.setInt(3, activityHistory.getActivityHistoryId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int activityHistoryId) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(deleteActivityHistory)
        ) {
            st.setInt(1, activityHistoryId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        ActivityHistoryDAO activityHistoryDAO = new ActivityHistoryDAOImpl();
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
        System.out.println(activityHistoryDAO.insert(new ActivityHistory("Test", "Test", new Information(2))));
    }
}
