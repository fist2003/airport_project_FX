package ui.controller.prices_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import service.PricesSortService;
import ui.view.PricesGUI;

import java.time.LocalDate;

/**
 * Created by slavik on 17.01.2017.
 */
public class PricesAllController extends PricesGUI {

    public PricesAllController(){}



    private PricesSortService instPricesSortService = new PricesSortService();

    private ObservableList<String> cbTicketClassObservList = FXCollections.observableList(instPricesSortService.getListForCbTicketClass());

    private ObservableList<String> getDepartPortsObservList(Object portArriveValue,Object dateValue){
        return FXCollections.observableList(instPricesSortService.getListDepartPorts(portArriveValue, dateValue));
    }

    private ObservableList<String> getArrivePortsObservList(Object portdepartValue, Object dateValue){
        return FXCollections.observableList(instPricesSortService.getListArrivePorts(portdepartValue, dateValue));
    }

    private ObservableList<LocalDate> getDatesDepartObservList(Object portDepartValue,Object portArriveValue){
        return FXCollections.observableList(instPricesSortService.getListDates(portDepartValue, portArriveValue));
    }

    @FXML
    private ComboBox cbPortDepart;

    @FXML
    private ComboBox cbPortArrive;

    @FXML
    private ComboBox cbDateDepart;

    @FXML
    private ComboBox cbTicketClass;

    @FXML
    public void initialize(){
        cbTicketClass.setValue(cbTicketClassObservList.get(0));
        cbTicketClass.setItems(cbTicketClassObservList);
        cbPortDepart.setItems(getDepartPortsObservList(null,null));
        cbPortArrive.setItems(getArrivePortsObservList(null,null));
        cbDateDepart.setItems(getDatesDepartObservList(null,null));
    }

    @FXML
    public void chooseDepart(){
        cbPortArrive.setItems(getArrivePortsObservList(cbPortDepart.getValue(),cbDateDepart.getValue()));
        cbDateDepart.setItems(getDatesDepartObservList(cbPortDepart.getValue(),cbPortArrive.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadAllPricesTable(cbPortDepart.getValue(),cbPortArrive.getValue(),cbDateDepart.getValue());
    }

    @FXML
    public void chooseArrive(){
        cbPortDepart.setItems(getDepartPortsObservList(cbPortArrive.getValue(),cbDateDepart.getValue()));
        cbDateDepart.setItems(getDatesDepartObservList(cbPortDepart.getValue(),cbPortArrive.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadAllPricesTable(cbPortDepart.getValue(),cbPortArrive.getValue(),cbDateDepart.getValue());
    }

    @FXML
    public void chooseDate(){
        cbPortDepart.setItems(getDepartPortsObservList(cbPortArrive.getValue(),cbDateDepart.getValue()));
        cbPortArrive.setItems(getArrivePortsObservList(cbPortDepart.getValue(),cbDateDepart.getValue()));
        setCheckClass(cbTicketClass.getValue().toString());
        loadAllPricesTable(cbPortDepart.getValue(),cbPortArrive.getValue(),cbDateDepart.getValue());
    }

    @FXML
    public void chooseTicketClass(){
        setCheckClass(cbTicketClass.getValue().toString());
        loadAllPricesTable(cbPortDepart.getValue(),cbPortArrive.getValue(),cbDateDepart.getValue());
    }

    @FXML
    public void chooseRefresh(){
        loadAllPrices();
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
