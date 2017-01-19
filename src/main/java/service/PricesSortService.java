package service;

import model.Flights;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by slavik on 17.01.2017.
 */
public class PricesSortService extends ServiceAbstract {

    public PricesSortService(){}

    public String getEconomClassStr(){return economClassStr;}
    public String getBusinessClassStr(){return businessClassStr;}

    public ArrayList<String> getListDepartPorts(Object portArriveValue, Object dateValue) {
        ArrayList<Flights> listAllFlights = new FlightsService().getAllService();
        ArrayList<String> listAllDepartPorts = new ArrayList<String>();
        if((portArriveValue != null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if((flight.getDestinationPort().equals(portArriveValue.toString()))&&
                        (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))){
                    listAllDepartPorts.add(flight.getDepartPort());
                }
            }
        }
        else if((portArriveValue != null)&&(dateValue == null)) {
            for (Flights flight:listAllFlights){
                if (flight.getDestinationPort().equals(portArriveValue.toString())){
                    listAllDepartPorts.add(flight.getDepartPort());}
            }
        }
        else if((portArriveValue == null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue)){
                    listAllDepartPorts.add(flight.getDepartPort());
                }
            }
        }
        else if((portArriveValue == null)&&(dateValue == null)){
            for (Flights flight:listAllFlights){
                listAllDepartPorts.add(flight.getDepartPort());
            }
        }
        Set<String> set = new HashSet<>(listAllDepartPorts);
        listAllDepartPorts.clear();
        listAllDepartPorts.addAll(set);
        return listAllDepartPorts;
    }

    public ArrayList<String> getListArrivePorts(Object portDepartValue, Object dateValue) {
        ArrayList<Flights> listAllFlights = new FlightsService().getAllService();
        ArrayList<String> listAllArrivePorts = new ArrayList<String>();
        if((portDepartValue != null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if((flight.getDepartPort().equals(portDepartValue.toString()))&&
                        (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))){
                    listAllArrivePorts.add(flight.getDestinationPort());
                }
            }
        }
        else if((portDepartValue != null)&&(dateValue == null)) {
            for (Flights flight:listAllFlights){
                if (flight.getDepartPort().equals(portDepartValue.toString())){
                    listAllArrivePorts.add(flight.getDestinationPort());}
            }
        }
        else if((portDepartValue == null)&&(dateValue != null)) {
            for (Flights flight:listAllFlights){
                if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue)){
                    listAllArrivePorts.add(flight.getDestinationPort());
                }
            }
        }
        else if((portDepartValue == null)&&(dateValue == null)){
            for (Flights flight:listAllFlights){
                listAllArrivePorts.add(flight.getDestinationPort());
            }
        }
        Set<String> set = new HashSet<>(listAllArrivePorts);
        listAllArrivePorts.clear();
        listAllArrivePorts.addAll(set);
        return listAllArrivePorts;
    }

    public ArrayList<LocalDate> getListDates(Object portDepartValue, Object portArriveValue) {
        ArrayList<Flights> listAllFlights = new FlightsService().getAllService();
        ArrayList<LocalDate> listAllDates = new ArrayList<LocalDate>();
        if((portDepartValue != null)&&(portArriveValue != null)) {
            for (Flights flight:listAllFlights){
                if((flight.getDepartPort().equals(portDepartValue.toString()))&&
                        (flight.getDestinationPort().equals(portArriveValue.toString()))){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
                }
            }
        }
        else if((portDepartValue != null)&&(portArriveValue == null)) {
            for (Flights flight:listAllFlights){
                if (flight.getDepartPort().equals(portDepartValue.toString())){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));}
            }
        }
        else if((portDepartValue == null)&&(portArriveValue != null)) {
            for (Flights flight:listAllFlights){
                if (flight.getDestinationPort().equals(portArriveValue.toString())){
                    listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
                }
            }
        }
        else if((portDepartValue == null)&&(portArriveValue == null)){
            for (Flights flight:listAllFlights){
                listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
            }
        }
        Set<LocalDate> set = new HashSet<>(listAllDates);
        listAllDates.clear();
        listAllDates.addAll(set);
        return listAllDates;
    }

    public ArrayList<Flights> makeListOfFlightsSorted(Object portDepartValue, Object portDestinValue, Object dateDepartValue){
        ArrayList<Flights> listAllPrices = new FlightsService().getAllService();
        ArrayList<Flights> listAllPricesByPortDepart = new ArrayList<>();
        if(portDepartValue != null){
            for(Flights flight:listAllPrices){
                if(flight.getDepartPort().equals(portDepartValue.toString())){
                    listAllPricesByPortDepart.add(flight);
                }
            }
        }
        else {listAllPricesByPortDepart.addAll(listAllPrices);}
        ArrayList<Flights> listAllPricesByPortDestin = new ArrayList<>();
        if(portDestinValue != null) {
            for (Flights flight : listAllPricesByPortDepart) {
                if (flight.getDestinationPort().equals(portDestinValue.toString())) {
                    listAllPricesByPortDestin.add(flight);
                }
            }
        }
        else {listAllPricesByPortDestin.addAll(listAllPricesByPortDepart);}
        ArrayList<Flights> listAllPricesByDateDepart = new ArrayList<>();
        if (dateDepartValue != null){
            for(Flights flight:listAllPricesByPortDestin) {
                if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateDepartValue)) {
                    listAllPricesByDateDepart.add(flight);
                }
            }
        }
        else{listAllPricesByDateDepart.addAll(listAllPricesByPortDestin);}
        return listAllPricesByDateDepart;
    }
}
