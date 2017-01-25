package service;

import dao.AirplanesDAO;
import model.Airlines;
import model.Airplanes;
import model.Flights;

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
    public Airplanes getByIdService(Airplanes airplane) {return instAirplanesDAO.getByIdDAO(airplane);}

    public ArrayList<Airplanes> getAllForTableView(){
        ArrayList<Airplanes> allList = getAllService();
        ArrayList<Airlines> allAirlineslist = new AirlinesService().getAllService();
        for (Airplanes airplane:allList){
            for (Airlines airline:allAirlineslist){
                if(airline.getId() == airplane.getAirline_id()){
                    airplane.setAirline_name(airline.getName());
                }
            }
        }
        return allList;
    }

    public ArrayList<String> getListOfNumbers(){
        ArrayList<Airplanes> list = getAllService();
        ArrayList<String> listNumbers = new ArrayList<>();
        for (Airplanes airplane:list){
            listNumbers.add(airplane.getNumberISO());
        }
        return listNumbers;
    }


}
