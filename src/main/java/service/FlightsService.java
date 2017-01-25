package service;

import dao.FlightsDAO;
import model.Airlines;
import model.Airplanes;
import model.Entity;
import model.Flights;

import java.util.ArrayList;

/**
 * Created by ПК on 22.12.2016.
 */
public class FlightsService {
    public FlightsService(){
        this.instFlightsDAO = new FlightsDAO();
    }

    private FlightsDAO instFlightsDAO;

    public ArrayList<Flights> getAllService(){return instFlightsDAO.getAllDAO();}

    public void insertNewService(Flights flight){
        instFlightsDAO.insertNewDAO(flight);
    }

    public void editDataService(Flights flight){
        instFlightsDAO.editInDBDAO(flight);
    }

    public void deleteDataService(Flights flight){
       instFlightsDAO.deleteInDBDAO(flight);
    }

    public void getByIdService(Flights flight){
        instFlightsDAO.getByIdDAO(flight);
    }

    public ArrayList<Flights> getAllForTableView(){
        ArrayList<Flights> allList = getAllService();
        ArrayList<Airplanes> allAirplaneslist = new AirplanesService().getAllService();
        for (Flights flight:allList){
            for (Airplanes airplane:allAirplaneslist){
                if(airplane.getId() == flight.getAirplane_id()){
                    flight.setAirplane_number(airplane.getNumberISO());
                }
            }
        }
        return allList;
    }

    public ArrayList<String> getListFlightNumbers(){
        ArrayList<Flights> allList = getAllService();
        ArrayList<String> numberslist = new ArrayList<>();
        for(Flights flight:allList){
            numberslist.add(flight.getNumber());
        }
        return numberslist;
    }

    public int getFlightIdByNumber(String nameValue) {
        ArrayList<Flights> listFlights = getAllService();
        int id = 0;
        for (Flights flight : listFlights) {
            if (flight.getNumber().toLowerCase().equals(nameValue.toLowerCase())) {
                id = flight.getId();
            }
        }
        return id;
    }

}
