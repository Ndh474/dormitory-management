package mapper;

import entity.*;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
public class RowMapperImpl implements RowMapper {
    public RowMapperImpl() {
    }

    /**
     * Cách tạo {@code Mapper}.<br>
     * {@code B1.} Tại các {@code Entity} cần phải có {@code @Builder} của Lombok. <br>
     * {@code B2.} Viết interface cho nó {@code <return type> [method name](ResultSet rs);} <br>
     * {@code B3.} Viết {@code Mapper.}<br><br>
     * {@code Mapper} cơ bản là đập 2 bước lấy data từ DB và tạo object từ data đấy lại làm một.<br>
     * Để tạo nó thì cần nắm được syntax của {@code @Builder}<br><br>
     * {@code @Builder} có syntax cơ bản là {@code <Entity>.builder().build();}. Để truyền từng trường giá trị vào thì sẽ sử dụng
     * {@code <Entity>.builder().<entity attribute 1>(rs.get<Type>("[column name]")).<entity attribute 2>(rs.get<Type>("[column name]"))....build();}<br><br>
     * Trong đó: <br>
     * &emsp - {@code <Entity>} là tên {@code Entity} muốn trả về. <br>
     * &emsp - {@code .builder()} khởi tạo {@code Builder}. <br>
     * &emsp - {@code <entity atribbute>()} tương ứng với một trường trong Object, nó đảm nhiệm việc gán giá trị vào trường tương ứng.<br>
     * &emsp&emsp Ví dụ {@code .accountID()} sẽ lấy dữ liệu truyền vào và gán cho mục {@code accountID}<br><br>
     * &emsp - {@code rs.get<Type>("[column name]")} dùng để lôi data từ {@code ResultSet} ra, như JDBC bình thường.<br>
     * &emsp&emsp Ví dụ {@code rs.getInt("account_id")}.<br><br>
     * &emsp - {@code .build()} sẽ thực hiện khời tạo đối tượng.
     *
     * @param rs ResultSet lấy từ {@code DAO}.
     * @return An {@code Entity}.
     */
    @Override
    public Account accountMapper(ResultSet rs) throws SQLException {
        return Account.builder()
                .accountID(rs.getInt("account_id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .accountStatus(accountStatusMapper(rs))
                .role(roleMapper(rs))
                .build();
    }

    @Override
    public AccountStatus accountStatusMapper(ResultSet rs) throws SQLException {
        return AccountStatus.builder()
                .statusID(rs.getInt("status_id"))
                .statusName(rs.getString("status_name"))
                .build();
    }

    @Override
    public ActivityHistory activityHistoryMapper(ResultSet rs) throws SQLException {
        return ActivityHistory.builder()
                .activityHistoryId(rs.getInt("activity_history_id"))
                .activityName(rs.getString("activity_name"))
                .detail(rs.getString("detail"))
                .dateCreated(rs.getString("date_created"))
                .information(informationMapper(rs))
                .build();
    }

    @Override
    public AdminNotification adminNotificationMapper(ResultSet rs) throws SQLException {
        return AdminNotification.builder()
                .notificationId(rs.getInt("notification_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .dateCreated(rs.getString("date_created"))
                .information(informationMapper(rs))
                .build();
    }

    @Override
    public Bill billMapper(ResultSet rs) throws SQLException {
        return Bill.builder()
                .billId(rs.getInt("bill_id"))
                .billName(rs.getString("bill_name"))
                .totalAmount(rs.getDouble("total_amount"))
                .dateCreated(rs.getString("date_created"))
                .billStatus(billStatusMapper(rs))
                .information(informationMapper(rs))
                .build();
    }

    @Override
    public BillStatus billStatusMapper(ResultSet rs) throws SQLException {
        return BillStatus.builder()
                .statusId(rs.getInt("bill_status_id"))
                .statusName(rs.getString("bill_status_name"))
                .build();
    }

    @Override
    public Dorm dormMapper(ResultSet rs) throws SQLException {
        return Dorm.builder()
                .dormId(rs.getInt("dorm_id"))
                .dormName(rs.getString("dorm_name"))
                .numberOfFloor(rs.getInt("number_of_floor"))
                .numberOfRoom(rs.getInt("number_of_room"))
                .available(rs.getInt("available"))
                .status(dormRoomStatusMapper(rs))
                .build();
    }

    @Override
    public DormRoomStatus dormRoomStatusMapper(ResultSet rs) throws SQLException {
        return DormRoomStatus.builder()
                .statusId(rs.getInt("status_id"))
                .statusName(rs.getString("status_name"))
                .build();
    }

    @Override
    public Information informationMapper(ResultSet rs) throws SQLException {
        return Information.builder()
                .informationId(rs.getInt("information_id"))
                .rollNumber(rs.getString("roll_number"))
                .citizenIdentification(rs.getString("citizen_identification"))
                .fullName(rs.getString("full_name"))
                .dob(rs.getString("dob"))
                .gender(rs.getString("gender"))
                .phoneNumber(rs.getString("phone_number"))
                .address(rs.getString("address"))
                .balance(rs.getDouble("balance"))
                .isPaid(rs.getString("is_paid"))
                .account(accountMapper(rs))
                .roomName(rs.getString("room_name"))
                .build();
    }

    @Override
    public Request requestMapper(ResultSet rs) throws SQLException {
        return Request.builder()
                .requestId(rs.getInt("request_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .response(rs.getString("response"))
                .dateCreated(rs.getString("date_created"))
                .status(rs.getString("status"))
                .requestType(requestTypeMapper(rs))
                .account(accountMapper(rs))
                .image(rs.getString("image"))
                .build();
    }

    @Override
    public RequestType requestTypeMapper(ResultSet rs) throws SQLException {
        return RequestType.builder()
                .requestTypeId(rs.getInt("request_type_id"))
                .requestTypeName(rs.getString("request_type_name"))
                .build();
    }

    @Override
    public News newsMapper(ResultSet rs) throws SQLException {
        return News.builder()
                .newsId(rs.getInt("news_id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .dateCreated(rs.getString("date_created"))
                .account(accountMapper(rs))
                .build();
    }

    @Override
    public Role roleMapper(ResultSet rs) throws SQLException {
        return Role.builder()
                .roleID(rs.getInt("role_id"))
                .roleName(rs.getString("role_name"))
                .build();
    }

    @Override
    public Room roomMapper(ResultSet rs) throws SQLException {
        return Room.builder()
                .roomId(rs.getInt("room_id"))
                .roomName(rs.getString("room_name"))
                .floorNumber(rs.getInt("floor_number"))
                .roomGender(rs.getString("room_gender"))
                .numberOfStudent(rs.getInt("number_of_student"))
                .roomStatus(dormRoomStatusMapper(rs))
                .dorm(Dorm.builder()
                        .dormId(rs.getInt("dorm_id"))
                        .dormName(rs.getString("dorm_name"))
                        .numberOfFloor(rs.getInt("number_of_floor"))
                        .build())
                .roomType(roomTypeMapper(rs))
                .build();
    }

    @Override
    public RoomType roomTypeMapper(ResultSet rs) throws SQLException {
        return RoomType.builder()
                .roomTypeId(rs.getInt("room_type_id"))
                .roomCapacity(rs.getInt("room_type_capacity"))
                .roomPrice(rs.getDouble("room_type_price"))
                .electricityFree(rs.getInt("electricity_free"))
                .waterFree(rs.getInt("water_free"))
                .build();
    }

    @Override
    public RoomUsage roomUsageMapper(ResultSet rs) throws SQLException {
        return RoomUsage.builder()
                .roomUsageId(rs.getInt("room_usage_id"))
                .electricityUsed(rs.getInt("electricity_used"))
                .waterUsed(rs.getInt("water_used"))
                .month(rs.getInt("month"))
                .year(rs.getInt("year"))
                .roomName(rs.getString("room_name"))
                .build();
    }
}
