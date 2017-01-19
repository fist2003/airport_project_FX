package ui.controller.prices_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import service.FlightsArrivalSortService;
import ui.view.PricesGUI;

import java.time.LocalDate;

/**
 * Created by slavik on 17.01.2017.
 */
public class PricesArrivalController extends PricesGUI {

    public PricesArrivalController(){}

    private FlightsArrivalSortService instFlightsArrivalSortService = new FlightsArrivalSortService();

    private ObservableList<String> cbTicketClassObservList = FXCollections.observableList(instFlightsArrivalSortService.getListForCbTicketClass());

    private ObservableList<String> getDirectionObservList(Object dateValue,Object timeValue){
        return FXCollections.observableList(instFlightsArrivalSortService.getListDirections(dateValue, timeValue));
    }

    private ObservableList<LocalDate> getDatesObservList(Object directionValue, Object timeValue){
        return FXCollections.observableList(instFlightsArrivalSortService.getListDates(directionValue, timeValue));
    }

    private ObservableList<String> getTimesObservList(Object directionValue,Object dateValue){
        return FXCollections.observableList(instFlightsArrivalSortService.getListTimes(directionValue, dateValue));
    }

    @FXML
    private ComboBox cbDirection;

    @FXML
    private ComboBox cbDateArrive;

    @FXML
    private ComboBox cbTimeArrive;

    @FXML
    private ComboBox cbTicketClass;

    @FXML
    public void initialize(){
        cbTicketClass.setValue(cbTicketClassObservList.get(0));
        cbTicketClass.setItems(cbTicketClassObservList);
        cbDirection.setItems(getDirectionObservList(null,null));
        cbDateArrive.setItems(getDatesObservList(null,null));
        cbTimeArrive.setItems(getTimesObservList(null,null));
    }

    @FXML
    public void chooseDirection(){
        cbDateArrive.setItems(getDatesObservList(cbDirection.getValue(),cbTimeArrive.getValue()));
        cbTimeArrive.setItems(getTimesObservList(cbDirection.getValue(),cbDateArrive.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateArrive.getValue(),cbTimeArrive.getValue());
    }

    @FXML
    public void chooseDate(){
        cbDirection.setItems(getDirectionObservList(cbDateArrive.getValue(),cbTimeArrive.getValue()));
        cbTimeArrive.setItems(getTimesObservList(cbDirection.getValue(),cbDateArrive.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateArrive.getValue(),cbTimeArrive.getValue());
    }

    @FXML
    public void chooseTime(){
        cbDirection.setItems(getDirectionObservList(cbDateArrive.getValue(),cbTimeArrive.getValue()));
        cbDateArrive.setItems(getDatesObservList(cbDirection.getValue(),cbTimeArrive.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateArrive.getValue(),cbTimeArrive.getValue());
    }

    @FXML
    public void chooseTicketClass(){
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateArrive.getValue(),cbTimeArrive.getValue());
    }

    @FXML
    public void chooseRefresh(){
        loadArrivalPrices();
    }

    @FXML
    public void showDepartPrices(){
        loadDepartPrices();
    }

    @FXML
    public void showArrivalPrices(){loadArrivalPrices();}

    @FXML
    public void showAllPrices(){loadAllPrices();}

}
