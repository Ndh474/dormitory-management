package services.impl;

import dao.RequestTypeDAO;
import dao.impl.*;
import entity.RequestType;
import services.*;

import java.util.*;

public class RequestTypeServiceImpl implements RequestTypeService {
    private RequestTypeDAO requestTypeDAO = new RequestTypeDAOImpl();

    @Override
    public List<RequestType> getAll() {
        return requestTypeDAO.getAll();
    }

    @Override
    public RequestType getByRequestTypeId(int requestTypeId) {
        return requestTypeDAO.getByRequestTypeId(requestTypeId);
    }

    @Override
    public RequestType getByRequestTypeName(String requestTypeName) {
        return requestTypeDAO.getByRequestTypeName(requestTypeName);
    }

    @Override
    public int update(RequestType requestType) {
        return requestTypeDAO.update(requestType);
    }

    @Override
    public int add(RequestType requestType) {
        return requestTypeDAO.add(requestType);
    }

    public static void main(String[] args) {
        RequestTypeService requestTypeService = new RequestTypeServiceImpl();
        List<RequestType> requestTypes = requestTypeService.getAll();
        for (RequestType requestType : requestTypes) {
            System.out.println(requestType);
        }
    }
}
