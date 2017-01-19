package service;

import model.Flights;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by slavik on 10.01.2017.
 */
public interface ScheduleTableInterface {

    ArrayList<Flights> getAllFlightsList();

    ArrayList<Flights> sortAllFlightsListByDate(LocalDate dateValue);

    ArrayList<Flights> makeListOfFlightsForScheduleTable(int jSliderValue,LocalDate date);

    ArrayList<Flights> makeListOfFlightsForSearchTable(Object dateValue,Object portValue,Object numberValue);

    ArrayList<LocalDate> getListOfDatesForComboBox();


}
