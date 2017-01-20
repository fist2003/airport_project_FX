package service;

import model.Flights;
import model.Passengers;

import java.util.ArrayList;


/**
 * Created by slavik on 20.01.2017.
 */
public class PassengersSearchService extends ServiceAbstract {

    public PassengersSearchService(){}

    public ArrayList<String> getListFlightNumbers(Object departPortvalue, Object destinPortValue, Object priceValue){
        return getListFlightNumbers(makeListOfFlightsSorted(null,departPortvalue,destinPortValue,priceValue));
    }

    public ArrayList<String> getListDepartPorts(Object numberValue, Object destinPortValue, Object priceValue){
        return getListDepartPorts(makeListOfFlightsSorted(numberValue,null,destinPortValue,priceValue));
    }

    public ArrayList<String> getListDestinPorts(Object numberValue, Object departPortValue, Object priceValue){
        return getListDestinPorts(makeListOfFlightsSorted(numberValue,departPortValue,null,priceValue));
    }

    public ArrayList<Integer> getListPrices(Object numberValue, Object departPortValue, Object destinPortValue){
        return getListPrices(makeListOfFlightsSorted(numberValue,departPortValue,destinPortValue,null));
    }

    public ArrayList<Flights> makeListOfFlightsSorted(Object numberValue,Object departPortValue, Object destinportValue,Object pricevalue){
        ArrayList<Flights> listAllFlights = new FlightsService().getAllService();
        ArrayList<Flights> listFlightsByNumber = getListFlightsByNumber(listAllFlights,numberValue);
        ArrayList<Flights> listFlightsByDepartPort = getListFlightsByDepartPort(listFlightsByNumber,departPortValue);
        ArrayList<Flights> listFlightsByDestinPort = getListFlightsByDestinPort(listFlightsByDepartPort,destinportValue);
        ArrayList<Flights> listFlightsByPrice = getListFlightsByPrice(listFlightsByDestinPort,pricevalue);
        return listFlightsByPrice;
    }

    public ArrayList<Passengers> makeListPassengersSortedByFlightInf(Object numberValue,Object departPortValue,Object destinPortValue,Object priceValue){
        ArrayList<Flights> listFlights = makeListOfFlightsSorted(numberValue, departPortValue, destinPortValue, priceValue);
        return makeListPassengersFromFlightsList(listFlights);
    }

    private ArrayList<Passengers> getListPassengersByLastName(ArrayList<Passengers> arrayList,Object lastNameValue){
        ArrayList<Passengers> listPassengers = new ArrayList<>();
        if(lastNameValue != null) {
            for (Passengers passenger : arrayList) {
                if (passenger.getLastName().equals(lastNameValue.toString())) {
                    listPassengers.add(passenger);
                }
            }
        }
        else {listPassengers.addAll(arrayList);}
        return listPassengers;
    }

    protected ArrayList<Passengers> getListPassengersByFirstName(ArrayList<Passengers> arrayList,Object firstNameValue){
        ArrayList<Passengers> listPassengers = new ArrayList<>();
        if(firstNameValue != null) {
            for (Passengers passenger : arrayList) {
                if (passenger.getFirstName().equals(firstNameValue.toString())) {
                    listPassengers.add(passenger);
                }
            }
        }
        else {listPassengers.addAll(arrayList);}
        return listPassengers;
    }

    protected ArrayList<Passengers> getListPassengersByPassport(ArrayList<Passengers> arrayList,Object passportValue){
        ArrayList<Passengers> listPassengers = new ArrayList<>();
        if(passportValue != null) {
            for (Passengers passenger : arrayList) {
                if (passenger.getPassportNumber().equals(passportValue.toString())) {
                    listPassengers.add(passenger);
                }
            }
        }
        else {listPassengers.addAll(arrayList);}
        return listPassengers;
    }

    protected ArrayList<Passengers> getListPassengersByCountry(ArrayList<Passengers> arrayList,Object countryValue){
        ArrayList<Passengers> listPassengers = new ArrayList<>();
        if(countryValue != null) {
            for (Passengers passenger : arrayList) {
                if (passenger.getCountry().equals(countryValue.toString())) {
                    listPassengers.add(passenger);
                }
            }
        }
        else {listPassengers.addAll(arrayList);}
        return listPassengers;
    }

    private ArrayList<String> getListLastNames(ArrayList<Passengers> arrayList){
        ArrayList<String> listString = new ArrayList<>();
        for (Passengers passenger : arrayList) {
            listString.add(passenger.getLastName());
        }
        return checkingForRepeatString(listString);
    }

    public ArrayList<String> getListLastNames(Object firstNameValue,Object passportValue,Object countryValue){
        return getListLastNames(makeListOfPassengersSortedByPersonalInf(null,firstNameValue,passportValue,countryValue));
    }

    private ArrayList<String> getListFirstNames(ArrayList<Passengers> arrayList){
        ArrayList<String> listString = new ArrayList<>();
        for (Passengers passenger : arrayList) {
            listString.add(passenger.getFirstName());
        }
        return checkingForRepeatString(listString);
    }

    public ArrayList<String> getListFirstNames(Object lastNameValue,Object passportValue,Object countryValue){
        return getListFirstNames(makeListOfPassengersSortedByPersonalInf(lastNameValue,null,passportValue,countryValue));
    }

    private ArrayList<String> getListPassports(ArrayList<Passengers> arrayList){
        ArrayList<String> listString = new ArrayList<>();
        for (Passengers passenger : arrayList) {
            listString.add(passenger.getPassportNumber());
        }
        return checkingForRepeatString(listString);
    }

    public ArrayList<String> getListPassports(Object lastNameValue,Object firstNameValue,Object countryValue){
        return getListPassports(makeListOfPassengersSortedByPersonalInf(lastNameValue,firstNameValue,null,countryValue));
    }

    private ArrayList<String> getListCountry(ArrayList<Passengers> arrayList){
        ArrayList<String> listString = new ArrayList<>();
        for (Passengers passenger : arrayList) {
            listString.add(passenger.getCountry());
        }
        return checkingForRepeatString(listString);
    }

    public ArrayList<String> getListCountry(Object lastNameValue,Object firstNameValue,Object passportValue){
        return getListCountry(makeListOfPassengersSortedByPersonalInf(lastNameValue,firstNameValue,passportValue,null));
    }

    public ArrayList<Passengers> makeListOfPassengersSortedByPersonalInf(Object lastNameValue,Object firstNameValue, Object passportValue,Object countryValue){
        ArrayList<Passengers> listAllPassengers = new PassengersService().getAllService();
        ArrayList<Passengers> listPassengersByLastName = getListPassengersByLastName(listAllPassengers,lastNameValue);
        ArrayList<Passengers> listPassengersByFirstName = getListPassengersByFirstName(listPassengersByLastName,firstNameValue);
        ArrayList<Passengers> listPassengersByPassport = getListPassengersByPassport(listPassengersByFirstName,passportValue);
        ArrayList<Passengers> listPassengersByCountry = getListPassengersByCountry(listPassengersByPassport,countryValue);
        ArrayList<Flights> AllFlights = new FlightsService().getAllService();
        for(Passengers passenger:listPassengersByCountry){
            for (Flights flight:AllFlights){
                if (flight.getId() == passenger.getFlight_id()){
                    passenger.setFlight_number(flight.getNumber());
                }
            }
        }
        return listPassengersByCountry;
    }
}
