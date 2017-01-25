package service;

import dao.FlightsDAO;
import dao.PassengersDAO;
import model.Airplanes;
import model.Flights;
import model.Passengers;

import java.util.ArrayList;

/**
 * Created by slavik on 19.01.2017.
 */
public class PassengersService {

    public PassengersService(){
        this.instPassengersDAO = new PassengersDAO();
    }

    private PassengersDAO instPassengersDAO;

    public ArrayList<Passengers> getAllService(){return instPassengersDAO.getAllDAO();}

    public void insertNewService(Passengers passenger){
        instPassengersDAO.insertNewDAO(passenger);
    }

    public void editDataService(Passengers passenger){
        instPassengersDAO.editInDBDAO(passenger);
    }

    public void deleteDataService(Passengers passenger){
        instPassengersDAO.deleteInDBDAO(passenger);
    }

    public void getByIdService(Passengers passenger){
        instPassengersDAO.getByIdDAO(passenger);
    }

    public ArrayList<Passengers> getAllForTableView(){
        ArrayList<Passengers> allList = getAllService();
        ArrayList<Flights> allFlightslist = new FlightsService().getAllService();
        for (Passengers passenger:allList){
            for (Flights flight:allFlightslist){
                if(flight.getId() == passenger.getFlight_id()){
                    passenger.setFlight_number(flight.getNumber());
                }
            }
        }
        return allList;
    }

    public int getQuantityFreePlaces(Passengers passenger){
        return instPassengersDAO.getQuantityFreePlaceInFlightDAO(passenger);
    }
}
