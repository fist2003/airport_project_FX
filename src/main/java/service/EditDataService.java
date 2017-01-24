package service;

import model.Airlines;
import model.Entity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean isDataAlreadyExist(String typeData,String querry){
        switch (typeData){
            case airlinesTypeStr:
                ArrayList<Airlines> list = new AirlinesService().getAllService();
                for (Airlines airline:list){
                    if(airline.getName().toLowerCase().equals(querry.toLowerCase())){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public boolean isSafeDelete(String typeData, Entity entity){
        switch (typeData){
            case airlinesTypeStr:
                return !new AirlinesService().isAirplanesRegisteredOnThisAirline((Airlines)entity);
            default:return false;
        }
    }
}
