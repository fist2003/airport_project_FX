package service;

import model.Flights;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by slavik on 15.01.2017.
 */
public class SearchFlightsService extends ServiceAbstract{
    public SearchFlightsService(){}

    public String getArrivalsTypeStr() {return arrivalsTypeStr;}
    public String getDeparturesTypeStr() {return departuresTypeStr;}

    public ArrayList<LocalDate> getListDatesForComboBox(String typeValue,Object portValue,Object numberValue) {
        ArrayList<Flights> listAllFlights;
        ArrayList<LocalDate> listAllDates = new ArrayList<>();
        switch (typeValue){
            case arrivalsTypeStr:
                listAllFlights = new FlightsArrivalService().getAllFlightsList();
                if((portValue != null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights){
                        if((flight.getDepartPort().equals(portValue.toString()))&&
                                (flight.getNumber().equals(numberValue.toString()))){
                            listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));
                        }
                    }
                }
                else if((portValue != null)&&(numberValue == null)) {
                    for (Flights flight:listAllFlights){
                        if (flight.getDepartPort().equals(portValue.toString()))
                        {listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));}
                    }
                }
                else if((portValue == null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights) {
                        if (flight.getNumber().equals(numberValue.toString())) {
                            listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));
                        }
                    }
                }
                else if((portValue == null)&&(numberValue == null)){
                    for (Flights flight:listAllFlights) {listAllDates.add(convertStringToLocalDate(flight.getDateDestin()));}
                }
                break;
            case departuresTypeStr:
                listAllFlights = new FlightsDepartService().getAllFlightsList();
                if((portValue != null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights){
                        if((flight.getDestinationPort().equals(portValue.toString()))&&
                                (flight.getNumber().equals(numberValue.toString()))){
                            listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
                        }
                    }
                }
                else if((portValue != null)&&(numberValue == null)) {
                    for (Flights flight:listAllFlights){
                        if (flight.getDestinationPort().equals(portValue.toString()))
                        {listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));}
                    }
                }
                else if((portValue == null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights) {
                        if (flight.getNumber().equals(numberValue.toString())) {
                            listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));
                        }
                    }
                }
                else if((portValue == null)&&(numberValue == null)){
                    for (Flights flight:listAllFlights) {listAllDates.add(convertStringToLocalDate(flight.getDateDepart()));}
                }
                break;
        }
        Set<LocalDate> set = new HashSet<>(listAllDates);
        listAllDates.clear();
        listAllDates.addAll(set);
        Collections.sort(listAllDates, new Comparator<LocalDate>() {
            @Override
            public int compare(LocalDate o1, LocalDate o2) {
                return o1.getDayOfYear()-o2.getDayOfYear();
            }
        });
        return listAllDates;
    }

    public ArrayList<String> getListPortsForComboBox(String typeValue,Object dateValue,Object numberValue) {
        ArrayList<Flights> listAllFlights;
        ArrayList<String> listAllPorts = new ArrayList<String>();
        switch (typeValue){
            case arrivalsTypeStr:
                listAllFlights = new FlightsArrivalService().getAllFlightsList();
                if((dateValue != null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights){
                        if((convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue))&&
                                (flight.getNumber().equals(numberValue.toString()))){
                            listAllPorts.add(flight.getDepartPort());
                        }
                    }
                }
                else if((dateValue != null)&&(numberValue == null)) {
                    for (Flights flight:listAllFlights){
                    if (convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue))
                        {listAllPorts.add(flight.getDepartPort());}
                    }
                }
                else if((dateValue == null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights) {
                        if (flight.getNumber().equals(numberValue.toString())) {
                            listAllPorts.add(flight.getDepartPort());
                        }
                    }
                }
                else if((dateValue == null)&&(numberValue == null)){
                    for (Flights flight:listAllFlights) {listAllPorts.add(flight.getDepartPort());}
                }
                break;
            case departuresTypeStr:
                listAllFlights = new FlightsDepartService().getAllFlightsList();
                if((dateValue != null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights){
                        if((convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))
                                &&(flight.getNumber().equals(numberValue.toString()))){
                            listAllPorts.add(flight.getDestinationPort());
                        }
                    }
                }
                else if((dateValue != null)&&(numberValue == null)) {
                    for (Flights flight:listAllFlights){
                        if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))
                        {listAllPorts.add(flight.getDestinationPort());}
                    }
                }
                else if((dateValue == null)&&(numberValue != null)) {
                    for (Flights flight:listAllFlights) {
                        if (flight.getNumber().equals(numberValue.toString())) {
                            listAllPorts.add(flight.getDestinationPort());
                        }
                    }
                }
                else if((dateValue == null)&&(numberValue == null)){
                    for (Flights flight:listAllFlights) {listAllPorts.add(flight.getDestinationPort());}
                }
                break;
        }
        Set<String> set = new HashSet<>(listAllPorts);
        listAllPorts.clear();
        listAllPorts.addAll(set);
        return listAllPorts;
    }

    public ArrayList<String> getListFlightNumbersForComboBox(String typeValue,Object dateValue,Object portValue) {
        ArrayList<Flights> listAllFlights;
        ArrayList<String> listAllNumbers = new ArrayList<String>();
        switch (typeValue){
            case arrivalsTypeStr:
                listAllFlights = new FlightsArrivalService().getAllFlightsList();
                if((dateValue != null)&&(portValue != null)) {
                    for (Flights flight:listAllFlights){
                        if((convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue))&&
                                (flight.getDepartPort().equals(portValue.toString()))){
                            listAllNumbers.add(flight.getNumber());
                        }
                    }
                }
                else if((dateValue != null)&&(portValue == null)) {
                    for (Flights flight:listAllFlights){
                        if (convertStringToLocalDate(flight.getDateDestin()).isEqual((LocalDate)dateValue))
                        {listAllNumbers.add(flight.getNumber());}
                    }
                }
                else if((dateValue == null)&&(portValue != null)) {
                    for (Flights flight:listAllFlights) {
                        if (flight.getDepartPort().equals(portValue.toString())) {
                            listAllNumbers.add(flight.getNumber());;
                        }
                    }
                }
                else if((dateValue == null)&&(portValue == null)){
                    for (Flights flight:listAllFlights) {listAllNumbers.add(flight.getNumber());}
                }
                break;
            case departuresTypeStr:
                listAllFlights = new FlightsDepartService().getAllFlightsList();
                if((dateValue != null)&&(portValue != null)) {
                    for (Flights flight:listAllFlights){
                        if((convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))&&
                                (flight.getDestinationPort().equals(portValue.toString()))){
                            listAllNumbers.add(flight.getNumber());
                        }
                    }
                }
                else if((dateValue != null)&&(portValue == null)) {
                    for (Flights flight:listAllFlights){
                        if (convertStringToLocalDate(flight.getDateDepart()).isEqual((LocalDate)dateValue))
                        {listAllNumbers.add(flight.getNumber());}
                    }
                }
                else if((dateValue == null)&&(portValue != null)) {
                    for (Flights flight:listAllFlights) {
                        if (flight.getDestinationPort().equals(portValue.toString())) {
                            listAllNumbers.add(flight.getNumber());;
                        }
                    }
                }
                else if((dateValue == null)&&(portValue == null)){
                    for (Flights flight:listAllFlights) {listAllNumbers.add(flight.getNumber());}
                }
                break;
        }
        return listAllNumbers;
    }


}
