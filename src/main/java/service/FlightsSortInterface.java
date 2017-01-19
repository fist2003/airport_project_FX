package service;

import model.Flights;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by slavik on 19.01.2017.
 */
public interface FlightsSortInterface {

    public ArrayList<String> getListDirections(Object dateValue, Object timeValue);

    public ArrayList<LocalDate> getListDates(Object directionValue, Object timeValue);

    public ArrayList<String> getListTimes(Object directionValue, Object dateValue);

    public ArrayList<Flights> makeListOfFlightsSorted(Object directionValue, Object dateDepartValue, Object timeDepartValue);
}
