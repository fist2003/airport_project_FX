package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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


}
