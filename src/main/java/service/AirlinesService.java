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

    public ArrayList<String> getListOfNames(){
        ArrayList<Airlines> listAirlines = getAllService();
        ArrayList<String> listNames = new ArrayList<>();
        for (Airlines airline:listAirlines){
            listNames.add(airline.getName());
        }
        return listNames;
    }

    public int getAirlineIdByName(String nameValue) {
        ArrayList<Airlines> listAirlines = getAllService();
        int id = 0;
        for (Airlines airline : listAirlines) {
            if (airline.getName().toLowerCase().equals(nameValue.toLowerCase())) {
                id = airline.getId();
            }
        }
        return id;
    }

}
