package dao;

import entity.ActivityHistory;

import java.util.List;

public interface ActivityHistoryDAO {
    List<ActivityHistory> getAll();

    List<ActivityHistory> getByInformationId(int informationId);

    ActivityHistory getByActivityHistoryId(int activityHistoryId);

    int insert(ActivityHistory activityHistory);

    int update(ActivityHistory activityHistory);

    int delete(int activityHistoryId);
}
