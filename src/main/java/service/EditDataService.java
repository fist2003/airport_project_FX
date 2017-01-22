package service;

import java.util.ArrayList;

/**
 * Created by slavik on 21.01.2017.
 */
public class EditDataService {

    public EditDataService(){}

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
}
