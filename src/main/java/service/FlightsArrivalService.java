package service;

import model.Flights;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by slavik on 10.01.2017.
 */
public class FlightsArrivalService extends ServiceAbstract implements ScheduleTableInterface {

    public FlightsArrivalService(){}

    //get All arrival flights to port Odessa list
    @Override
    public ArrayList<Flights> getAllFlightsList() {
        ArrayList<Flights> listAllFlights = new FlightsService().getAllService();
        ArrayList<Flights> arrivalFlights = new ArrayList<Flights>();
        for (Flights flight:listAllFlights){
            if (flight.getDestinationPort().toLowerCase().equals(odessaStr)){
                arrivalFlights.add(flight);
            }
        }
        Collections.sort(arrivalFlights, new Comparator<Flights>() {
            @Override
            public int compare(Flights o1, Flights o2) {
                LocalTime time1 = LocalTime.parse(o1.getTimeDestin());
                LocalTime time2 = LocalTime.parse(o2.getTimeDestin());
                return getDifferenceInTime(time1,time2);
            }
        });
        return arrivalFlights;
    }

    //sort list all arrival flights to port Odessa by date
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

    //make list of dates for arrival combo box
    @Override
    public ArrayList<LocalDate> getListOfDatesForComboBox() {
        ArrayList<Flights> listAllArrival = getAllFlightsList();
        ArrayList<LocalDate> listDatesFutureAndCurrent = new ArrayList<>();
        for (Flights flight:listAllArrival){
            LocalDate checkedDate = convertStringToLocalDate(flight.getDateDestin());
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

    //make schedule arrive table and check status
    @Override
    public ArrayList<Flights> makeListOfFlightsForScheduleTable(int jSliderValue, LocalDate dateValue) {
        ArrayList<Flights> listAllArrivalFlightsByDate = sortAllFlightsListByDate(dateValue);
        ArrayList<Flights> listArrivalFlightsByJSlider = new ArrayList<Flights>();
        LocalDate curDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now();
        for (Flights flight : listAllArrivalFlightsByDate) {
            String status = flight.getStatusOfFlight();
            String currentTimeArrive = flight.getCurrentTime();
            String gateName = flight.getGateName();
            LocalDate dateArrive = convertStringToLocalDate(flight.getDateDestin());
            LocalTime timeArrive = LocalTime.parse(flight.getTimeDestin());
            LocalTime timeDepart = LocalTime.parse(flight.getTimeDepart());
            if (status == null) {
                if (dateArrive.equals(curDate)) {
                    if (getDifferenceInTime(timeDepart, timeNow) >= 0) {
                        status = onTimeStatus;
                    } else if ((getDifferenceInTime(timeArrive, timeNow) >= 0) && (getDifferenceInTime(timeDepart, timeNow) <= 0)) {
                        status = inFlightStatus;
                    } else if (getDifferenceInTime(timeArrive, timeNow) < 0) {
                        status = arrivedStatus;
                        currentTimeArrive = timeArrive.toString();
                        gateName = gateNameA1;
                    }
                } else if (dateArrive.isBefore(curDate)) {
                    status = arrivedStatus;
                    currentTimeArrive = timeArrive.toString();
                    gateName = gateNameA1;
                } else if (dateArrive.isAfter(curDate)) {
                    status = byScheduleStatus;
                }
                flight.setStatusOfFlight(status);
                flight.setCurrentTime(currentTimeArrive);
                flight.setGateName(gateName);
            }
                int valueJSliderModul = Math.abs((jSliderValue));
                String valueJSliderStr = "";
                if (valueJSliderModul < 10) {
                    valueJSliderStr = "0" + valueJSliderModul + ":00:00";
                } else if (valueJSliderModul == 24) {
                    valueJSliderStr = "23:59:59";
                } else {
                    valueJSliderStr = valueJSliderModul + ":00:00";
                }
                LocalTime jSliderTime = LocalTime.parse(valueJSliderStr);
                int a = getDifferenceInTime(jSliderTime, timeArrive);
                if (a <= 0) {
                    listArrivalFlightsByJSlider.add(flight);
                }
            }
        return listArrivalFlightsByJSlider;
    }

    @Override
    public ArrayList<Flights> makeListOfFlightsForSearchTable(Object dateValue, Object portValue, Object numberValue) {
        ArrayList<Flights> listArrivalFlightsByDateValue;
        if(dateValue != null){
            listArrivalFlightsByDateValue = makeListOfFlightsForScheduleTable(0,(LocalDate)dateValue);}
        else {listArrivalFlightsByDateValue = getAllFlightsList();}
        ArrayList<Flights> listArrivalFlightsByPortValue;
        if (portValue == null){
            listArrivalFlightsByPortValue = new ArrayList<>(listArrivalFlightsByDateValue);
        }
        else{
            listArrivalFlightsByPortValue = new ArrayList<>();
            for(Flights flight:listArrivalFlightsByDateValue){
                if(flight.getDepartPort().equals(portValue.toString())){
                    listArrivalFlightsByPortValue.add(flight);
                }
            }
        }
        ArrayList<Flights> listArrivalFlightsByNumber;
        if (numberValue == null){
            listArrivalFlightsByNumber = new ArrayList<>(listArrivalFlightsByPortValue);
        }
        else{
            listArrivalFlightsByNumber = new ArrayList<>();
            for(Flights flight:listArrivalFlightsByPortValue){
                if(flight.getNumber().equals(numberValue.toString())){
                    listArrivalFlightsByNumber.add(flight);
                }
            }
        }
        return listArrivalFlightsByNumber;
    }

}
