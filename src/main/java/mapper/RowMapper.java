package mapper;

import entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {

    Account accountMapper(ResultSet rs) throws SQLException;

    AccountStatus accountStatusMapper(ResultSet rs) throws SQLException;

    ActivityHistory activityHistoryMapper(ResultSet rs) throws SQLException;

    AdminNotification adminNotificationMapper(ResultSet rs) throws SQLException;

    Bill billMapper(ResultSet rs) throws SQLException;

    BillStatus billStatusMapper(ResultSet rs) throws SQLException;

    Dorm dormMapper(ResultSet rs) throws SQLException;

    DormRoomStatus dormRoomStatusMapper(ResultSet rs) throws SQLException;

    Information informationMapper(ResultSet rs) throws SQLException;

    Request requestMapper(ResultSet rs) throws SQLException;

    RequestType requestTypeMapper(ResultSet rs) throws SQLException;

    News newsMapper(ResultSet rs) throws SQLException;

    Role roleMapper(ResultSet rs) throws SQLException;

    Room roomMapper(ResultSet rs) throws SQLException;

    RoomType roomTypeMapper(ResultSet rs) throws SQLException;

    RoomUsage roomUsageMapper(ResultSet rs) throws SQLException;

}
