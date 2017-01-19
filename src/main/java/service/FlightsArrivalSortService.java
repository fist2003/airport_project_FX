package service;

import model.Flights;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by slavik on 17.01.2017.
 */
public class FlightsArrivalSortService extends ServiceAbstract implements FlightsSortInterface {

    public FlightsArrivalSortService(){}

    @Override
    public ArrayList<String> getListDirections(Object dateValue, Object timeValue) {
        ArrayList<Flights> listAllFlights;
        ArrayList<String> listAllDirections = new ArrayList<String>();
        listAllFlights = new FlightsArrivalService().getAllFlightsList();
        if((dateValue != null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if((convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue))&&
                        (flight.getTimeDestin().equals(timeValue.toString()))){
                    listAllDirections.add(flight.getDepartPort());
                }
            }
        }
        else if((dateValue != null)&&(timeValue == null)) {
            for (Flights flight:listAllFlights){
                if (convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue)){
                    listAllDirections.add(flight.getDepartPort());}
            }
        }
        else if((dateValue == null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if (flight.getTimeDestin().equals(timeValue.toString())){
                    listAllDirections.add(flight.getDepartPort());
                }
            }
        }
        else if((dateValue == null)&&(timeValue == null)){
            for (Flights flight:listAllFlights){
                listAllDirections.add(flight.getDepartPort());
            }
        }
        Set<String> set = new HashSet<>(listAllDirections);
        listAllDirections.clear();
        listAllDirections.addAll(set);
        return listAllDirections;
    }

    @Override
    public ArrayList<LocalDate> getListDates(Object directionValue, Object timeValue) {
        ArrayList<Flights> listAllFlights;
        ArrayList<LocalDate> listAllDates = new ArrayList<LocalDate>();
        listAllFlights = new FlightsArrivalService().getAllFlightsList();
        if((directionValue != null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if((flight.getDepartPort().equals(directionValue.toString()))&&
                        (flight.getTimeDestin().equals(timeValue.toString()))){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));
                }
            }
        }
        else if((directionValue != null)&&(timeValue == null)) {
            for (Flights flight:listAllFlights){
                if (flight.getDepartPort().equals(directionValue.toString())){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));}
            }
        }
        else if((directionValue == null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if (flight.getTimeDestin().equals(timeValue.toString())){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));
                }
            }
        }
        else if((directionValue == null)&&(timeValue == null)){
            for (Flights flight:listAllFlights){
                listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));
            }
        }
        Set<LocalDate> set = new HashSet<>(listAllDates);
        listAllDates.clear();
        listAllDates.addAll(set);
        return listAllDates;
    }

    @Override
    public ArrayList<String> getListTimes(Object directionValue, Object dateValue) {
        ArrayList<Flights> listAllFlights;
        ArrayList<String> listAllTimes = new ArrayList<String>();
        listAllFlights = new FlightsArrivalService().getAllFlightsList();
        if((directionValue != null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if((flight.getDepartPort().equals(directionValue.toString()))&&
                        (convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue))){
                    listAllTimes.add(flight.getTimeDestin());
                }
            }
        }
        else if((directionValue != null)&&(dateValue == null)) {
            for (Flights flight : listAllFlights) {
                if (flight.getDepartPort().equals(directionValue.toString())) {
                    listAllTimes.add(flight.getTimeDestin());
                }
            }
        }
        else if((directionValue == null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if (convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue)){
                    listAllTimes.add(flight.getTimeDestin());
                }
            }
        }
        else if((directionValue == null)&&(dateValue == null)){
            for (Flights flight:listAllFlights){
                listAllTimes.add(flight.getTimeDestin());
            }
        }
        Set<String> set = new HashSet<>(listAllTimes);
        listAllTimes.clear();
        listAllTimes.addAll(set);
        return listAllTimes;
    }

    @Override
    public ArrayList<Flights> makeListOfFlightsSorted(Object directionValue,Object dateDepartValue, Object timeDestinValue){
        ArrayList<Flights> listAllDepartPrices = new FlightsArrivalService().getAllFlightsList();
        ArrayList<Flights> listAllPricesByPortDepart = new ArrayList<>();
        if(directionValue != null) {
            for (Flights flight : listAllDepartPrices) {
                if (flight.getDepartPort().equals(directionValue.toString())) {
                    listAllPricesByPortDepart.add(flight);
                }
            }
        }
        else {listAllPricesByPortDepart.addAll(listAllDepartPrices);}
        ArrayList<Flights> listAllPricesByDateDepart = new ArrayList<>();
        if (dateDepartValue != null){
            for(Flights flight:listAllPricesByPortDepart) {
                if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateDepartValue)) {
                    listAllPricesByDateDepart.add(flight);
                }
            }
        }
        else{listAllPricesByDateDepart.addAll(listAllPricesByPortDepart);}
        ArrayList<Flights> listAllPricesByTimeDepart = new ArrayList<>();
        if(timeDestinValue != null) {
            for (Flights flight : listAllPricesByDateDepart) {
                if (flight.getTimeDestin().equals(timeDestinValue.toString())) {
                    listAllPricesByTimeDepart.add(flight);
                }
            }
        }
        else {listAllPricesByTimeDepart.addAll(listAllPricesByDateDepart);}

        return listAllPricesByTimeDepart;
    }
}
