package service;

import model.Flights;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by slavik on 27.01.2017.
 */
public class EditDataSortService extends ServiceAbstract {

    public EditDataSortService(){}

    public ArrayList<String> getListPortDeparts(Object destinPortValue,Object dateValue, Object timeValue){
        return getListDepartPorts(makeListOfFlightsSorted(null,destinPortValue,dateValue,timeValue));
    }
    public ArrayList<String> getListPortDestin(Object departPortValue,Object dateValue, Object timeValue){
        return getListDestinPorts(makeListOfFlightsSorted(departPortValue,null,dateValue,timeValue));
    }

    public ArrayList<LocalDate> getListDatesDepart(Object departPortValue,Object destinPortValue, Object timeValue){
        return getListDatesDepart(makeListOfFlightsSorted(departPortValue,destinPortValue,null,timeValue));
    }

    public ArrayList<String> getListTimesDepart(Object departPortValue,Object destinPortValue, Object dateValue){
        return getListTimeDeparts(makeListOfFlightsSorted(departPortValue,destinPortValue,dateValue,null));
    }

    public ArrayList<Flights> makeListOfFlightsSorted(Object departPortValue, Object destinPortValue,Object dateValue, Object timeValue){
        ArrayList<Flights> listAllFlights = new FlightsService().getAllService();
        ArrayList<Flights> listFlightsByPortDepart = getListFlightsByDepartPort(listAllFlights,departPortValue);
        ArrayList<Flights> listFlightsByPortDestin = getListFlightsByDestinPort(listFlightsByPortDepart,destinPortValue);
        ArrayList<Flights> listFlightsByDateDepart = getListFlightsByDateDepart(listFlightsByPortDestin,dateValue);
        ArrayList<Flights> listFlightsByTimeDepart = getListFlightsByTimeDepart(listFlightsByDateDepart,timeValue);
        return new FlightsService().getAllForTableView(listFlightsByTimeDepart);
    }

}
