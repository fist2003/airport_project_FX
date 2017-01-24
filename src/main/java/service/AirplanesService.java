package service;

import dao.AirplanesDAO;
import model.Airplanes;

import java.util.ArrayList;

/**
 * Created by slavik on 24.01.2017.
 */
public class AirplanesService implements CRUDServiceInterface<Airplanes>{

    public AirplanesService(){
        this.instAirplanesDAO = new AirplanesDAO();
    }

    private AirplanesDAO instAirplanesDAO;

    @Override
    public ArrayList<Airplanes> getAllService() {
        return instAirplanesDAO.getAllDAO();
    }

    @Override
    public void insertNewService(Airplanes airplane) {
        instAirplanesDAO.insertNewDAO(airplane);
    }

    @Override
    public void editDataService(Airplanes airplane) {
        instAirplanesDAO.editInDBDAO(airplane);
    }

    @Override
    public void deleteDataService(Airplanes airplane) {
        instAirplanesDAO.deleteInDBDAO(airplane);
    }

    @Override
    public Airplanes getByIdService(Airplanes airplane) {
        return instAirplanesDAO.getByIdDAO(airplane);
    }
}
