package service;

import model.Flights;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by slavik on 11.01.2017.
 */
public class FlightsDepartService extends ServiceAbstract implements ScheduleTableInterface  {

    public FlightsDepartService(){}

    //get All depart flights from port Odessa list
    @Override
    public ArrayList<Flights> getAllFlightsList() {
        ArrayList<Flights> listAllFlights = new FlightsService().getAllService();
        ArrayList<Flights> departFlights = new ArrayList<Flights>();
        for (Flights flight:listAllFlights){
            if (flight.getDepartPort().toLowerCase().equals(odessaStr)){
                departFlights.add(flight);
            }
        }
        Collections.sort(departFlights, new Comparator<Flights>() {
            @Override
            public int compare(Flights o1, Flights o2) {
                LocalTime time1 = LocalTime.parse(o1.getTimeDestin());
                LocalTime time2 = LocalTime.parse(o2.getTimeDestin());
                return getDifferenceInTime(time1,time2);
            }
        });
        return departFlights;
    }

    //sort list all departure flights from port Odessa by date
    @Override
    public ArrayList<Flights> sortAllFlightsListByDate(LocalDate dateValue) {
        ArrayList<Flights> listAllFlights = getAllFlightsList();
        ArrayList<Flights> listFlightsByDate = new ArrayList<Flights>();
        for (Flights flight:listAllFlights) {
            LocalDate arriveDate = convertStringToLocalDate(flight.getDateDestin());
            if(arriveDate.equals(dateValue)){
                listFlightsByDate.add(flight);
            }
        }
        return listFlightsByDate;
    }

    //make list of dates for depart combo box
    @Override
    public ArrayList<LocalDate> getListOfDatesForComboBox() {
        ArrayList<Flights> listAllDeparts = getAllFlightsList();
        ArrayList<LocalDate> listDatesFutureAndCurrent = new ArrayList<>();
        for (Flights flight:listAllDeparts){
            LocalDate checkedDate = convertStringToLocalDate(flight.getDateDepart());
            if (!isDateEnd(checkedDate)){
                listDatesFutureAndCurrent.add(checkedDate);
            }
        }
        Set<LocalDate> set = new HashSet<>(listDatesFutureAndCurrent);
        listDatesFutureAndCurrent.clear();
        listDatesFutureAndCurrent.addAll(set);
        Collections.sort(listDatesFutureAndCurrent, new Comparator<LocalDate>() {
            @Override
            public int compare(LocalDate o1, LocalDate o2) {
                return o1.getDayOfYear()-o2.getDayOfYear();
            }
        });
        return listDatesFutureAndCurrent;
    }

    //make schedule departure table and check status
    @Override
    public ArrayList<Flights> makeListOfFlightsForScheduleTable(int jSliderValue, LocalDate dateValue) {
        ArrayList<Flights> listAllDepartFlightsByDate = sortAllFlightsListByDate(dateValue);
        ArrayList<Flights> listDepartFlightsByJSlider = new ArrayList<Flights>();
        LocalDate curDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        for (Flights flight : listAllDepartFlightsByDate) {
            String status = flight.getStatusOfFlight();
            String currentTimeDepart = flight.getCurrentTime();
            String gateName = flight.getGateName();
            LocalDate dateDepart = convertStringToLocalDate(flight.getDateDepart());
            LocalTime timeDepart = LocalTime.parse(flight.getTimeDepart());
            if (status == null) {
                if (dateDepart.equals(curDate)) {
                    if (getDifferenceInTime(timeDepart, timeNow) >= (60 * 60 * 2)) {
                        status = onTimeStatus;
                    } else if ((getDifferenceInTime(timeDepart, timeNow) < (60 * 60 * 2)) && (getDifferenceInTime(timeDepart, timeNow) >= (45 * 60))) {
                        status = checkInStatus;
                        gateName = gateNameB1;
                    } else if ((getDifferenceInTime(timeDepart, timeNow) < (45 * 60)) && (getDifferenceInTime(timeDepart, timeNow) >= (30 * 60))) {
                        status = gateClosedStatus;
                        gateName = gateNameB1;
                    } else if ((getDifferenceInTime(timeDepart, timeNow) < (30 * 60)) && (getDifferenceInTime(timeDepart, timeNow) > (0))) {
                        status = gateClosedStatus;
                        gateName = gateNameB1;
                    } else if (getDifferenceInTime(timeDepart, timeNow) <= 0) {
                        status = departedStatus;
                        gateName = gateNameB1;
                        currentTimeDepart = flight.getTimeDepart();
                    }
                } else if (dateDepart.isBefore(curDate)) {
                    status = departedStatus;
                    gateName = gateNameB1;
                    currentTimeDepart = flight.getTimeDepart();
                } else if (dateDepart.isAfter(curDate)) {
                    status = byScheduleStatus;
                }
                flight.setStatusOfFlight(status);
                flight.setCurrentTime(currentTimeDepart);
                flight.setGateName(gateName);
            }
            int valueJSliderModul = Math.abs((jSliderValue));
            String valueJSliderStr = "";
            if (valueJSliderModul < 10) {
                valueJSliderStr = "0" + valueJSliderModul + ":00:00";
            } else if(valueJSliderModul == 24){
                valueJSliderStr = "23:59:59";
            }else {
                valueJSliderStr = valueJSliderModul + ":00:00";
            }
            LocalTime jSliderTime = LocalTime.parse(valueJSliderStr);
            int a = getDifferenceInTime(jSliderTime,timeDepart);
            if(a <= 0){
                listDepartFlightsByJSlider.add(flight);
            }
        }
        return listDepartFlightsByJSlider;
    }

    @Override
    public ArrayList<Flights> makeListOfFlightsForSearchTable(Object dateValue, Object portValue, Object numberValue) {
        ArrayList<Flights> listDepartFlightsByDateValue;
        if(dateValue != null){
            listDepartFlightsByDateValue = makeListOfFlightsForScheduleTable(0,(LocalDate)dateValue);}
        else {listDepartFlightsByDateValue = getAllFlightsList();}
        ArrayList<Flights> listDepartFlightsByPortValue;
        if (portValue == null){
            listDepartFlightsByPortValue = new ArrayList<>(listDepartFlightsByDateValue);
        }
        else{
            listDepartFlightsByPortValue = new ArrayList<>();
            for(Flights flight:listDepartFlightsByDateValue){
                if(flight.getDestinationPort().equals(portValue.toString())){
                    listDepartFlightsByPortValue.add(flight);
                }
            }
        }
        ArrayList<Flights> listDepartFlightsByNumber;
        if (numberValue == null){
            listDepartFlightsByNumber = new ArrayList<>(listDepartFlightsByPortValue);
        }
        else{
            listDepartFlightsByNumber = new ArrayList<>();
            for(Flights flight:listDepartFlightsByPortValue){
                if(flight.getNumber().equals(numberValue.toString())){
                    listDepartFlightsByNumber.add(flight);
                }
            }
        }
        return listDepartFlightsByNumber;
    }
}
