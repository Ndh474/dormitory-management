package dao;

import entity.RequestType;

import java.util.List;

public interface RequestTypeDAO {
    List<RequestType> getAll();

    RequestType getByRequestTypeId(int requestTypeId);

    RequestType getByRequestTypeName(String requestTypeName);

    int update(RequestType requestType);

    int add(RequestType requestType);
}
