package service;

import javafx.beans.property.SimpleStringProperty;
import model.Flights;
import model.Passengers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by slavik on 19.01.2017.
 */
public class PassengersSortService extends ServiceAbstract{

    public PassengersSortService(){
        mapInstFlightSortInterface.put(getArrivalsTypeStr(),new FlightsArrivalSortService());
        mapInstFlightSortInterface.put(getDepartureTypeStr(),new FlightsDepartSortService());
    }

    public String getArrivalsTypeStr(){return arrivalsTypeStr;}
    public String getDepartureTypeStr(){return departuresTypeStr;}

    private Map<String,FlightsSortInterface> mapInstFlightSortInterface = new HashMap<String,FlightsSortInterface>();
    public Map<String, FlightsSortInterface> getMapInstFlightSortInterface() {return mapInstFlightSortInterface;}

    public ArrayList<String> getListDirections(String typeValue,Object dateValue, Object timeValue){
        return mapInstFlightSortInterface.get(typeValue).getListDirections(dateValue, timeValue);
    }

    public ArrayList<LocalDate> getListDates(String typeValue,Object directionValue, Object timeValue){
        return mapInstFlightSortInterface.get(typeValue).getListDates(directionValue, timeValue);
    }

    public ArrayList<String> getListTimes(String typeValue,Object directionValue, Object dateValue){
        return mapInstFlightSortInterface.get(typeValue).getListTimes(directionValue, dateValue);
    }

    public ArrayList<String> getListFlightNumbersSorted(String typeValue,Object directionValue, Object dateValue, Object timeValue) {
        ArrayList<Flights> listFlightsSorted = mapInstFlightSortInterface.get(typeValue).makeListOfFlightsSorted(directionValue, dateValue, timeValue);
        ArrayList<String> listOfNumbers = new ArrayList<>();
        for(Flights flight:listFlightsSorted){
            listOfNumbers.add(flight.getNumber());
        }
        return listOfNumbers;
    }

    private ArrayList<Flights> makeListFlightsSorted(String typeValue,Object directionValue,Object dateValue, Object timeValue,Object flightNumberValue){
        ArrayList<Flights> listFlightsSorted = mapInstFlightSortInterface.get(typeValue).makeListOfFlightsSorted(directionValue, dateValue, timeValue);
        ArrayList<Flights> listSortedByNumber = new ArrayList<>();
        if(flightNumberValue != null){
            for(Flights flight:listFlightsSorted){
                if(flight.getNumber().equals(flightNumberValue.toString()))
                    listSortedByNumber.add(flight);
            }
        }
        else listSortedByNumber.addAll(listFlightsSorted);
        return listSortedByNumber;
    }

    public ArrayList<Passengers> makeListPassengersSorted(String typeValue,Object directionValue,Object dateValue, Object timeValue,Object flightNumberValue){
        ArrayList<Flights> listFlights = makeListFlightsSorted(typeValue, directionValue, dateValue, timeValue, flightNumberValue);
        ArrayList<Passengers> listAllPassengers = new PassengersService().getAllService();
        ArrayList<Passengers> listPassengersSorted = new ArrayList<Passengers>();
        for(Flights flight:listFlights){
            for(Passengers passenger:listAllPassengers){
                if(flight.getId() == passenger.getFlight_id()){
                    passenger.setFlight_number(flight.getNumber());
                    listPassengersSorted.add(passenger);
                }
            }
        }
        return listPassengersSorted;
    }


}
