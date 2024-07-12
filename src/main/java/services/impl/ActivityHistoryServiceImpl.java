package services.impl;

import dao.ActivityHistoryDAO;
import dao.impl.ActivityHistoryDAOImpl;
import entity.ActivityHistory;
import services.ActivityHistoryService;

import java.util.List;

public class ActivityHistoryServiceImpl implements ActivityHistoryService {
    ActivityHistoryDAO activityHistoryDAO = new ActivityHistoryDAOImpl();

    @Override
    public List<ActivityHistory> getAll() {
        return activityHistoryDAO.getAll();
    }

    @Override
    public List<ActivityHistory> getByInformationId(int informationId) {
        return activityHistoryDAO.getByInformationId(informationId);
    }

    @Override
    public ActivityHistory getByActivityHistoryId(int activityHistoryId) {
        return activityHistoryDAO.getByActivityHistoryId(activityHistoryId);
    }

    @Override
    public int insert(ActivityHistory activityHistory) {
        return activityHistoryDAO.insert(activityHistory);
    }

    @Override
    public int update(ActivityHistory activityHistory) {
        return activityHistoryDAO.update(activityHistory);
    }

    @Override
    public int delete(int activityHistoryId) {
        return activityHistoryDAO.delete(activityHistoryId);
    }

}
