package ui.controller.prices_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import service.FlightsDepartSortService;
import ui.view.PricesGUI;

import java.time.LocalDate;

/**
 * Created by slavik on 17.01.2017.
 */
public class PricesDepartController extends PricesGUI {

    public PricesDepartController(){}

    private FlightsDepartSortService instFlightsDepartSortService = new FlightsDepartSortService();

    private ObservableList<String> cbTicketClassObservList = FXCollections.observableList(instFlightsDepartSortService.getListForCbTicketClass());

    private ObservableList<String> getDirectionObservList(Object dateValue,Object timeValue){
        return FXCollections.observableList(instFlightsDepartSortService.getListDirections(dateValue, timeValue));
    }

    private ObservableList<LocalDate> getDatesObservList(Object directionValue, Object timeValue){
        return FXCollections.observableList(instFlightsDepartSortService.getListDates(directionValue, timeValue));
    }

    private ObservableList<String> getTimesObservList(Object directionValue,Object dateValue){
        return FXCollections.observableList(instFlightsDepartSortService.getListTimes(directionValue, dateValue));
    }

    @FXML
    private ComboBox cbDirection;

    @FXML
    private ComboBox cbDateDepart;

    @FXML
    private ComboBox cbTimeDepart;

    @FXML
    private ComboBox cbTicketClass;

    @FXML
    public void initialize(){
        cbTicketClass.setValue(cbTicketClassObservList.get(0));
        cbTicketClass.setItems(cbTicketClassObservList);
        cbDirection.setItems(getDirectionObservList(null,null));
        cbDateDepart.setItems(getDatesObservList(null,null));
        cbTimeDepart.setItems(getTimesObservList(null,null));
    }

    @FXML
    public void chooseDirection(){
        cbDateDepart.setItems(getDatesObservList(cbDirection.getValue(),cbTimeDepart.getValue()));
        cbTimeDepart.setItems(getTimesObservList(cbDirection.getValue(),cbDateDepart.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue());
    }

    @FXML
    public void chooseDate(){
        cbDirection.setItems(getDirectionObservList(cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbTimeDepart.setItems(getTimesObservList(cbDirection.getValue(),cbDateDepart.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue());
    }

    @FXML
    public void chooseTime(){
        cbDirection.setItems(getDirectionObservList(cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbDateDepart.setItems(getDatesObservList(cbDirection.getValue(),cbTimeDepart.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue());
    }

    @FXML
    public void chooseTicketClass(){
        setCheckClass(cbTicketClass.getValue().toString());
        loadPricesTable(cbDirection.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue());
    }

    @FXML
    public void chooseRefresh(){
        loadDepartPrices();
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
