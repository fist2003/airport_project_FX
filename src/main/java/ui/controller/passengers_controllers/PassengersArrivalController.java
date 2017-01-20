package ui.controller.passengers_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import ui.view.PassengersGUI;

import java.time.LocalDate;

/**
 * Created by slavik on 19.01.2017.
 */
public class PassengersArrivalController extends PassengersGUI {

    public PassengersArrivalController(){
        setChoosedTypeStr(instPassengersSortService.getArrivalsTypeStr());
    }

    private ObservableList<String> getDirectionObservList(String typeValue, Object dateValue, Object timeValue){
        return FXCollections.observableList(instPassengersSortService.getListDirections(typeValue,dateValue, timeValue));
    }

    private ObservableList<LocalDate> getDatesObservList(String typeValue, Object directionValue, Object timeValue){
        return FXCollections.observableList(instPassengersSortService.getListDates(typeValue,directionValue, timeValue));
    }

    private ObservableList<String> getTimesObservList(String typeValue,Object directionValue,Object dateValue){
        return FXCollections.observableList(instPassengersSortService.getListTimes(typeValue,directionValue, dateValue));
    }

    private ObservableList<String> getFlightNumbersObservList(String typeValue,Object directionValue,Object dateValue, Object timeValue){
        return FXCollections.observableList(instPassengersSortService.getListFlightNumbersSorted(typeValue, directionValue, dateValue, timeValue));
    }

    @FXML
    private ComboBox cbDirection;

    @FXML
    private ComboBox cbDate;

    @FXML
    private ComboBox cbTime;

    @FXML
    private ComboBox cbFlight;

    @FXML
    public void initialize(){
        cbFlight.setItems(getFlightNumbersObservList(getChoosedTypeStr(),null,null,null));
        cbDirection.setItems(getDirectionObservList(getChoosedTypeStr(),null,null));
        cbDate.setItems(getDatesObservList(getChoosedTypeStr(),null,null));
        cbTime.setItems(getTimesObservList(getChoosedTypeStr(),null,null));

    }

    @FXML
    public void chooseDirection(){
        cbDate.setItems(getDatesObservList(getChoosedTypeStr(),cbDirection.getValue(),cbTime.getValue()));
        cbTime.setItems(getTimesObservList(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue()));
        cbFlight.setItems(getFlightNumbersObservList(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue(),cbTime.getValue()));
        loadPassengersTable(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue(),cbTime.getValue(),cbFlight.getValue());

    }

    @FXML
    public void chooseDate(){
        cbDirection.setItems(getDirectionObservList(getChoosedTypeStr(),cbDate.getValue(),cbTime.getValue()));
        cbTime.setItems(getTimesObservList(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue()));
        cbFlight.setItems(getFlightNumbersObservList(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue(),cbTime.getValue()));
        loadPassengersTable(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue(),cbTime.getValue(),cbFlight.getValue());
    }

    @FXML
    public void chooseTime(){
        cbDirection.setItems(getDirectionObservList(getChoosedTypeStr(),cbDate.getValue(),cbTime.getValue()));
        cbDate.setItems(getDatesObservList(getChoosedTypeStr(),cbDirection.getValue(),cbTime.getValue()));
        cbFlight.setItems(getFlightNumbersObservList(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue(),cbTime.getValue()));
        loadPassengersTable(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue(),cbTime.getValue(),cbFlight.getValue());
    }

    @FXML
    public void chooseFlight(){
        loadPassengersTable(getChoosedTypeStr(),cbDirection.getValue(),cbDate.getValue(),cbTime.getValue(),cbFlight.getValue());
    }

    @FXML
    public void chooseRefresh(){
        loadArrivalPassengers();
    }

    @FXML
    public void showDepart(){
        loadDepartPassengers();
    }

    @FXML
    public void showArrival(){
        loadArrivalPassengers();
    }
}
