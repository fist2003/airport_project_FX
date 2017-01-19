package service;

import model.Flights;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by slavik on 17.01.2017.
 */
public class FlightsDepartSortService extends ServiceAbstract implements FlightsSortInterface{

    public FlightsDepartSortService(){}

    @Override
    public ArrayList<String> getListDirections(Object dateValue, Object timeValue) {
        ArrayList<Flights> listAllFlights;
        ArrayList<String> listAllDirections = new ArrayList<String>();
        listAllFlights = new FlightsDepartService().getAllFlightsList();
        if((dateValue != null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if((convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))&&
                                (flight.getTimeDepart().equals(timeValue.toString()))){
                            listAllDirections.add(flight.getDestinationPort());
                }
            }
        }
        else if((dateValue != null)&&(timeValue == null)) {
            for (Flights flight:listAllFlights){
                if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue)){
                    listAllDirections.add(flight.getDestinationPort());}
                }
        }
        else if((dateValue == null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if (flight.getTimeDepart().equals(timeValue.toString())){
                    listAllDirections.add(flight.getDestinationPort());
                }
            }
        }
        else if((dateValue == null)&&(timeValue == null)){
            for (Flights flight:listAllFlights){
                listAllDirections.add(flight.getDestinationPort());
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
        listAllFlights = new FlightsDepartService().getAllFlightsList();
        if((directionValue != null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if((flight.getDestinationPort().equals(directionValue.toString()))&&
                        (flight.getTimeDepart().equals(timeValue.toString()))){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
                }
            }
        }
        else if((directionValue != null)&&(timeValue == null)) {
            for (Flights flight:listAllFlights){
                if (flight.getDestinationPort().equals(directionValue.toString())){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));}
            }
        }
        else if((directionValue == null)&&(timeValue != null)) {
            for (Flights flight:listAllFlights){
                if (flight.getTimeDepart().equals(timeValue.toString())){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
                }
            }
        }
        else if((directionValue == null)&&(timeValue == null)){
            for (Flights flight:listAllFlights){
                listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
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
        listAllFlights = new FlightsDepartService().getAllFlightsList();
        if((directionValue != null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if((flight.getDestinationPort().equals(directionValue.toString()))&&
                        (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))){
                    listAllTimes.add(flight.getTimeDepart());
                }
            }
        }
        else if((directionValue != null)&&(dateValue == null)) {
            for (Flights flight : listAllFlights) {
                if (flight.getDestinationPort().equals(directionValue.toString())) {
                    listAllTimes.add(flight.getTimeDepart());
                }
            }
        }
        else if((directionValue == null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue)){
                    listAllTimes.add(flight.getTimeDepart());
                }
            }
        }
        else if((directionValue == null)&&(dateValue == null)){
            for (Flights flight:listAllFlights){
                listAllTimes.add(flight.getTimeDepart());
            }
        }
        Set<String> set = new HashSet<>(listAllTimes);
        listAllTimes.clear();
        listAllTimes.addAll(set);
        return listAllTimes;
    }

    @Override
    public ArrayList<Flights> makeListOfFlightsSorted(Object directionValue, Object dateDepartValue, Object timeDepartValue){
        ArrayList<Flights> listAllDepartPrices = new FlightsDepartService().getAllFlightsList();
        ArrayList<Flights> listAllPricesByPortDestin = new ArrayList<>();
        if(directionValue != null) {
            for (Flights flight : listAllDepartPrices) {
                if (flight.getDestinationPort().equals(directionValue.toString())) {
                    listAllPricesByPortDestin.add(flight);
                }
            }
        }
        else {listAllPricesByPortDestin.addAll(listAllDepartPrices);}
        ArrayList<Flights> listAllPricesByDateDepart = new ArrayList<>();
        if (dateDepartValue != null){
            for(Flights flight:listAllPricesByPortDestin) {
                if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateDepartValue)) {
                    listAllPricesByDateDepart.add(flight);
                }
            }
        }
        else{listAllPricesByDateDepart.addAll(listAllPricesByPortDestin);}
        ArrayList<Flights> listAllPricesByTimeDepart = new ArrayList<>();
        if(timeDepartValue != null) {
            for (Flights flight : listAllPricesByDateDepart) {
                if (flight.getTimeDepart().equals(timeDepartValue.toString())) {
                    listAllPricesByTimeDepart.add(flight);
                }
            }
        }
        else {listAllPricesByTimeDepart.addAll(listAllPricesByDateDepart);}

        return listAllPricesByTimeDepart;
    }




}
