package service;

import model.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slavik on 21.01.2017.
 */
public class EditDataService extends ServiceAbstract {

    public EditDataService(){}

    public String getDateFormater(){return dateFormaterStr;}

    private final String airlinesTypeStr = "AIRLINES";
    private final String airplanesTypeStr = "AIRPLANES";
    private final String flightsTypeStr = "FLIGHTS";
    private final String passengersTypeStr = "PASSENGERS";
    private final String userssTypeStr = "USERS";

    public final String insertNewOptionStr = "INSERT NEW";
    private final String editOptionStr = "EDIT DATA";
    private final String deleteOptionStr = "DELETE DATA";

    public String getAirlinesTypeStr() {return airlinesTypeStr;}
    public String getAirplanesTypeStr() {return airplanesTypeStr;}
    public String getFlightsTypeStr() {return flightsTypeStr;}
    public String getPassengersTypeStr() {return passengersTypeStr;}
    public String getUserssTypeStr() {return userssTypeStr;}
    public String getInsertNewOptionStr() {return insertNewOptionStr;}
    public String getEditOptionStr() {return editOptionStr;}
    public String getDeleteOptionStr() {return deleteOptionStr;}

    public String getEconomStr(){return economClassStr;}
    public String getBusinessStr(){return businessClassStr;}

    public String getTypeUserAdmin(){return typeUserAdmin;}
    public String getTypeUserUser(){return typeUserUser;}

    public ArrayList<String> getTypeUsersList(){
        ArrayList<String> list = new ArrayList<>();
        list.add(typeUserAdmin);
        list.add(typeUserUser);
        return list;
    }

    public ArrayList<String> getTypeList(){
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add(airlinesTypeStr);
        typeList.add(airplanesTypeStr);
        typeList.add(flightsTypeStr);
        typeList.add(passengersTypeStr);
        typeList.add(userssTypeStr);
        return typeList;
    }

    public ArrayList<String> getOptionList(){
        ArrayList<String> optionList = new ArrayList<String>();
        optionList.add(insertNewOptionStr);
        optionList.add(editOptionStr);
        optionList.add(deleteOptionStr);
        return optionList;
    }

    public ArrayList<String> getSexList(){
        ArrayList<String> list = new ArrayList<>();
        list.add(sexMaleStr);
        list.add(sexFemaleStr);
        return list;
    }

    public ArrayList<String> getTicketList(){
        ArrayList<String> list = new ArrayList<>();
        list.add(economClassStr);
        list.add(businessClassStr);
        return list;
    }

    public boolean isFirstWhiteSpace(String querry){
        Pattern patWhiteSpace = Pattern.compile("\\s+.*| ?|.*\\s{1,}$");
        Matcher matWhiteSpace = patWhiteSpace.matcher(querry);
        return matWhiteSpace.matches();
    }

    public boolean isDoubleWhiteSpace(String querry){
        Pattern patWhiteSpace = Pattern.compile("^.*\\s{2,}.*$");
        Matcher matWhiteSpace = patWhiteSpace.matcher(querry);
        return matWhiteSpace.matches();
    }

    public boolean isWebsite(String querry){
        Pattern patWebsite = Pattern.compile("^(www\\.|http://|https://).+\\..+");
        Matcher matWebsite = patWebsite.matcher(querry);
        return matWebsite.matches();
    }

    public boolean isInputNumber(String value){
        Pattern patWhiteSpace = Pattern.compile("\\d+");
        Matcher matWhiteSpace = patWhiteSpace.matcher(value);
        return matWhiteSpace.matches();
    }

    public boolean isBigLength(String querry,int sizeMax){
        if(querry.length() >= sizeMax){
            return true;
        }
        return false;
    }

    public boolean checkInputYear(String year){
        Pattern patDate = Pattern.compile("^(19[0-9][0-9]|200[0-9]|201[0-7])$");
        Matcher matDate = patDate.matcher(year);
        return matDate.matches();
    }

    public boolean checkInputTime(String value){
        Pattern pat = Pattern.compile("^((0[0-9]|1[0-9]|2[0-3]):[0-5][0-9])|((0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])$");
        Matcher mat = pat.matcher(value);
        return mat.matches();
    }

    public boolean checkInputDate(String date){
        Pattern patDate = Pattern.compile("^(19[0-9][0-9]|200[0-9]|201[0-7])-(0[1-9]|1[0-2])-([0-2][0-9]|3[0-1])$");
        Matcher matDate = patDate.matcher(date);
        return matDate.matches();
    }

    public boolean checkInputQuantityPlaces(String number){
        Pattern pat = Pattern.compile("^([0-9]|[0-9][0-9]|[0-4][0-9][0-9]|5[0][0])$");
        Matcher mat = pat.matcher(number);
        return mat.matches();
    }

    public boolean isDataAlreadyExist(String typeData,Entity entity){
        switch (typeData){
            case airlinesTypeStr:
                ArrayList<Airlines> listAirlines = new AirlinesService().getAllService();
                Airlines instAirlines = (Airlines)entity;
                for (Airlines airline:listAirlines){
                    if(airline.getName().toLowerCase().equals(instAirlines.getName().toLowerCase())){
                        return true;
                    }
                }
                break;
            case airplanesTypeStr:
                ArrayList<Airplanes> listAirplanes = new AirplanesService().getAllService();
                Airplanes instAirplanes = (Airplanes)entity;
                for(Airplanes airplane:listAirplanes){
                    if (airplane.getNumberISO().toLowerCase().equals(instAirplanes.getNumberISO().toLowerCase())){
                        return true;
                    }
                }
                break;
            case flightsTypeStr:
                ArrayList<Flights> listFlights = new FlightsService().getAllService();
                Flights instFlights = (Flights)entity;
                for(Flights flight:listFlights){
                    if (flight.getNumber().toLowerCase().equals(instFlights.getNumber().toLowerCase())){
                        return true;
                    }
                }
                break;
            case passengersTypeStr:
                ArrayList<Passengers> listPassengers = new PassengersService().getAllService();
                Passengers instPassenger = (Passengers) entity;
                for(Passengers passenger:listPassengers){
                    if ((passenger.getFlight_id() == instPassenger.getFlight_id())&&
                            (passenger.getPassportNumber().toLowerCase().equals(instPassenger.getPassportNumber().toLowerCase()))){
                        return true;
                    }
                }
                break;
            case userssTypeStr:
                ArrayList<Users> listUsers = new UsersService().getAllService();
                Users instUsers = (Users) entity;
                for(Users user:listUsers){
                    if ((user.getLogin().toLowerCase().equals(instUsers.getLogin().toLowerCase()))||
                            (user.getEmail().toLowerCase().equals(instUsers.getEmail().toLowerCase()))){
                        return true;
                    }
                }
                break;

        }
        return false;
    }

    public int convertStringToInt(String value) {
        int intValue = 0;
        try {
            intValue = Integer.valueOf(value);
        } catch (NumberFormatException e) {
        }
        return intValue;
    }

    public boolean isSafeDelete(String type,Entity entity){
        switch (type){
            case airlinesTypeStr:
                ArrayList<Airplanes> listAirplanes = new AirplanesService().getAllService();
                for (Airplanes airplane:listAirplanes){
                    if(airplane.getAirline_id() == entity.getId()){
                        return false;
                    }
                }
                break;
            case airplanesTypeStr:
                ArrayList<Flights> listFlights = new FlightsService().getAllService();
                for (Flights flight:listFlights){
                    if(flight.getAirplane_id() == entity.getId()){
                        return false;
                    }
                }
                break;
            case flightsTypeStr:
                ArrayList<Passengers> listPassengers = new PassengersService().getAllService();
                for (Passengers passenger:listPassengers){
                    if(passenger.getFlight_id() == entity.getId()){
                        return false;
                    }
                }
                break;
        }
        return true;
    }
}
