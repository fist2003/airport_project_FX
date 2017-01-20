package service;

import model.Flights;
import model.Passengers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by slavik on 10.01.2017.
 */
public abstract class ServiceAbstract {
    protected final String odessaStr = "odessa";
    protected final String onTimeStatus = "ON TIME";
    protected final String inFlightStatus = "IN FLIGHT";
    protected final String arrivedStatus = "ARRIVED";
    protected final String departedStatus = "DEPARTED";
    protected final String byScheduleStatus = "BY SCHEDULE";
    protected final String checkInStatus = "CHECK IN";
    protected final String checkInClosedStatus = "CHECK IN CLOSED";
    protected final String gateClosedStatus = "GATE CLOSED";
    protected final String gateNameA1 = "A1";
    protected final String gateNameB1 = "B1";

    protected final String arrivalsTypeStr = "ARRIVALS";
    protected final String departuresTypeStr = "DEPARTURES";

    protected final String economClassStr = "ECONOM";
    protected final String businessClassStr = "BUSINESS";

    protected LocalDate convertStringToLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(value, formatter);
        return date1;
    }

    protected boolean isDateEnd(LocalDate dateValue){
        LocalDate current = LocalDate.now();
        if(dateValue.isBefore(current)) return true;
        else return false;
    }

    protected int getDifferenceInTime(LocalTime time1, LocalTime time2){
        int time1int = time1.getHour()* 3600 + time1.getMinute() * 60 + time1.getSecond();
        int time2int =  time2.getHour()* 3600 + time2.getMinute() * 60 + time2.getSecond();
        return (time1int - time2int);
    }

    public ArrayList<String> getListForTypeCB(){
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add(arrivalsTypeStr);
        typeList.add(departuresTypeStr);
        return typeList;
    }

    public ArrayList<String> getListForCbTicketClass(){
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add(economClassStr);
        typeList.add(businessClassStr);
        return typeList;
    }

    protected ArrayList<Flights> getListFlightsByNumber(ArrayList<Flights> arrayList,Object numberValue){
        ArrayList<Flights> listFlightsByNumber = new ArrayList<>();
        if(numberValue != null) {
            for (Flights flight : arrayList) {
                if (flight.getNumber().equals(numberValue.toString())) {
                    listFlightsByNumber.add(flight);
                }
            }
        }
        else {listFlightsByNumber.addAll(arrayList);}
        return listFlightsByNumber;
    }

    protected ArrayList<Flights> getListFlightsByDepartPort(ArrayList<Flights> arrayList,Object departPortValue){
        ArrayList<Flights> listFlightsByDepartPort = new ArrayList<>();
        if(departPortValue != null) {
            for (Flights flight : arrayList) {
                if (flight.getDepartPort().equals(departPortValue.toString())) {
                    listFlightsByDepartPort.add(flight);
                }
            }
        }
        else {listFlightsByDepartPort.addAll(arrayList);}
        return listFlightsByDepartPort;
    }

    protected ArrayList<Flights> getListFlightsByDestinPort(ArrayList<Flights> arrayList,Object destinPortValue){
        ArrayList<Flights> listFlightsByDestinPort = new ArrayList<>();
        if(destinPortValue != null) {
            for (Flights flight : arrayList) {
                if (flight.getDestinationPort().equals(destinPortValue.toString())) {
                    listFlightsByDestinPort.add(flight);
                }
            }
        }
        else {listFlightsByDestinPort.addAll(arrayList);}
        return listFlightsByDestinPort;
    }

    protected ArrayList<Flights> getListFlightsByPrice(ArrayList<Flights> arrayList,Object priceValue){
        ArrayList<Flights> listFlightsByPrice = new ArrayList<>();
        if(priceValue != null) {
            for (Flights flight : arrayList) {
                if ((flight.getPriceBusiness() == (int)priceValue)||(flight.getPriceEconom() == (int)priceValue)) {
                    listFlightsByPrice.add(flight);
                }
            }
        }
        else {listFlightsByPrice.addAll(arrayList);}
        return listFlightsByPrice;
    }

    protected ArrayList<String> getListFlightNumbers(ArrayList<Flights> arrayList){
        ArrayList<String> listString = new ArrayList<>();
        for (Flights flight : arrayList) {
            listString.add(flight.getNumber());
        }
        return checkingForRepeatString(listString);
    }

    protected ArrayList<String> getListDepartPorts(ArrayList<Flights> arrayList){
        ArrayList<String> listString = new ArrayList<>();
        for (Flights flight : arrayList) {
            listString.add(flight.getDepartPort());
        }
        return checkingForRepeatString(listString);
    }

    protected ArrayList<String> getListDestinPorts(ArrayList<Flights> arrayList){
        ArrayList<String> listString = new ArrayList<>();
        for (Flights flight : arrayList) {
            listString.add(flight.getDestinationPort());
        }
        return checkingForRepeatString(listString);
    }

    protected ArrayList<Integer> getListPrices(ArrayList<Flights> arrayList){
        ArrayList<Integer> listString = new ArrayList<>();
        for (Flights flight : arrayList) {
            listString.add(flight.getPriceBusiness());
            listString.add(flight.getPriceEconom());
        }
        return checkingForRepeatInteger(listString);
    }

    protected ArrayList<String> checkingForRepeatString(ArrayList<String> arrayList){
        Set<String> set = new HashSet<String>(arrayList);
        arrayList.clear();
        arrayList.addAll(set);
        return arrayList;
    }

    protected ArrayList<Integer> checkingForRepeatInteger(ArrayList<Integer> arrayList){
        Set<Integer> set = new HashSet<Integer>(arrayList);
        arrayList.clear();
        arrayList.addAll(set);
        return arrayList;
    }

    protected ArrayList<Passengers> makeListPassengersFromFlightsList(ArrayList<Flights> listFlights){
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
