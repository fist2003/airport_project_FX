package service;

import dao.FlightsDAO;
import dao.PassengersDAO;
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
}
