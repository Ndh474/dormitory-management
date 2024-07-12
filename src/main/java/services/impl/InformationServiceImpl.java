package services.impl;

import dao.InformationDAO;
import dao.impl.InformationDAOImpl;
import entity.Information;
import services.InformationService;

import java.util.List;

public class InformationServiceImpl implements InformationService {
    private InformationDAO informationDAO = new InformationDAOImpl();

    @Override
    public List<Information> getAll() {
        return informationDAO.getAll();
    }

    @Override
    public Information getByAccountID(int accountID) {
        return informationDAO.getByAccountID(accountID);
    }

    @Override
    public Information getByRollNumber(String rollNumber) {
        return informationDAO.getByRollNumber(rollNumber);
    }

    @Override
    public Information getByInformationId(int id) {
        return informationDAO.getByInformationId(id);
    }

    @Override
    public List<Information> getByHasRoomNameNotPaid() {
        return informationDAO.getByHasRoomNameNotPaid();
    }

    @Override
    public List<Information> getFromRoomName(String roomName) {
        return informationDAO.getFromRoomName(roomName);
    }

    @Override
    public int update(Information information) {
        return informationDAO.update(information);
    }

    @Override
    public int add(Information information) {
        return informationDAO.add(information);
    }

    @Override
    public boolean checkExistRollNumber(String rollNumber) {
        List<Information> informationList = informationDAO.getAll();
        for (Information information : informationList) {
            if (information.getRollNumber().equals(rollNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkExistCitizenIdentification(String citizenIdentification) {
        List<Information> informationList = informationDAO.getAll();
        for (Information information : informationList) {
            if (information.getCitizenIdentification().equals(citizenIdentification)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        InformationService informationService = new InformationServiceImpl();
        System.out.println(informationService.getByRollNumber("MC181596"));
    }
}
