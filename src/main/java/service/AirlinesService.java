package service;

import dao.AirlinesDAO;
import model.Airlines;
import model.Airplanes;

import java.util.ArrayList;

/**
 * Created by slavik on 21.01.2017.
 */
public class AirlinesService implements CRUDServiceInterface<Airlines> {

    public AirlinesService(){
        this.instAirlinesDAO = new AirlinesDAO();
    }

    private AirlinesDAO instAirlinesDAO;

    public ArrayList<Airlines> getAllService(){return instAirlinesDAO.getAllDAO();}

    public void insertNewService(Airlines airline){instAirlinesDAO.insertNewDAO(airline);}

    public void editDataService(Airlines airline){
        instAirlinesDAO.editInDBDAO(airline);
    }

    public void deleteDataService(Airlines airline){
        instAirlinesDAO.deleteInDBDAO(airline);
    }

    public Airlines getByIdService(Airlines airline){
        return instAirlinesDAO.getByIdDAO(airline);
    }

    public boolean isAirplanesRegisteredOnThisAirline(Airlines airline){
        ArrayList<Airplanes> listAirplanes = new AirplanesService().getAllService();
        for (Airplanes airplane:listAirplanes){
            if(airplane.getAirline_id() == airline.getId()){
                return true;
            }
        }
        return false;
    }
}
