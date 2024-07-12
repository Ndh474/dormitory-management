package services;

import entity.ActivityHistory;

import java.util.List;

public interface ActivityHistoryService {
    List<ActivityHistory> getAll();

    List<ActivityHistory> getByInformationId(int informationId);

    ActivityHistory getByActivityHistoryId(int activityHistoryId);

    int insert(ActivityHistory activityHistory);

    int update(ActivityHistory activityHistory);

    int delete(int activityHistoryId);
}
