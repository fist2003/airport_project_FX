package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Priority;
import model.Passengers;
import service.PassengersSearchService;
import ui.model.PassengersTableModel;

import java.util.ArrayList;

/**
 * Created by slavik on 27.01.2017.
 */
public class PassengersEditDataControllet extends EditDataController {

    public PassengersEditDataControllet(){}

    private PassengersSearchService instPassengersSearchService = new PassengersSearchService();

    private ObservableList<String> getFlightObservList(Object departPortValue, Object destinportValue, Object priceValue){
        return FXCollections.observableList(instPassengersSearchService.getListFlightNumbers(departPortValue,destinportValue,priceValue));
    }

    private ObservableList<String> getDepartPortsObservList(Object numberValue, Object destinportValue, Object priceValue){
        return FXCollections.observableList(instPassengersSearchService.getListDepartPorts(numberValue,destinportValue,priceValue));
    }

    private ObservableList<String> getDestinPortsObservList(Object numberValue, Object departPortValue, Object priceValue){
        return FXCollections.observableList(instPassengersSearchService.getListDestinPorts(numberValue,departPortValue, priceValue));
    }

    private ObservableList<Integer> getPricesObservList(Object numberValue, Object departPortValue, Object destinportValue){
        return FXCollections.observableList(instPassengersSearchService.getListPrices(numberValue, departPortValue, destinportValue));
    }

    @FXML
    private ComboBox cbFlight;

    @FXML
    private ComboBox cbPrice;

    @FXML
    private ComboBox cbPortDepart;

    @FXML
    private ComboBox cbPortDestin;

    @FXML
    public void initialize(){
        cbType.setItems(typeDataObservList);
        cbOption.setItems(optionsObservList);
        cbFlight.setItems(getFlightObservList(null,null,null));
        cbPortDepart.setItems(getDepartPortsObservList(null,null,null));
        cbPortDestin.setItems(getDestinPortsObservList(null,null,null));
        cbPrice.setItems(getPricesObservList(null,null,null));
    }

    @FXML
    public void chooseFlight(){
        cbPortDepart.setItems(getDepartPortsObservList(cbFlight.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDestin.setItems(getDestinPortsObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPrice.getValue()));
        cbPrice.setItems(getPricesObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPortDestin.getValue()));
        loadTable();
    }

    @FXML
    public void chooseDepart(){
        cbFlight.setItems(getFlightObservList(cbPortDepart.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDestin.setItems(getDestinPortsObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPrice.getValue()));
        cbPrice.setItems(getPricesObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPortDestin.getValue()));
        loadTable();
    }

    @FXML
    public void chooseDestin(){
        cbFlight.setItems(getFlightObservList(cbPortDepart.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDepart.setItems(getDepartPortsObservList(cbFlight.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPrice.setItems(getPricesObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPortDestin.getValue()));
        loadTable();
    }

    @FXML
    public void choosePrice(){
        cbFlight.setItems(getFlightObservList(cbPortDepart.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDepart.setItems(getDepartPortsObservList(cbFlight.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDestin.setItems(getDestinPortsObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPrice.getValue()));
        loadTable();
    }


    @FXML
    public void chooseRefresh(){
        loadEditDataPane(choose);
    }

    private void loadTable(){
        ArrayList<Passengers> listPassengers =  instPassengersSearchService.makeListPassengersSortedByFlightInf(cbFlight.getValue(),
                cbPortDepart.getValue(), cbPortDestin.getValue(),cbPrice.getValue());
        tablePane = new PassengersTableModel().getTablePane(listPassengers);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }
}
