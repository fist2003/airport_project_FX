package ui.controller.search_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import service.PassengersSearchService;
import ui.view.SearchPassengersGUI;

/**
 * Created by slavik on 20.01.2017.
 */
public class SearchByFlightInfController extends SearchPassengersGUI {

    public SearchByFlightInfController(){}

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
        cbFlight.setItems(getFlightObservList(null,null,null));
        new AutoCompleteComboBoxListener<>(cbFlight);
        cbPortDepart.setItems(getDepartPortsObservList(null,null,null));
        new AutoCompleteComboBoxListener<>(cbPortDepart);
        cbPortDestin.setItems(getDestinPortsObservList(null,null,null));
        new AutoCompleteComboBoxListener<>(cbPortDestin);
        cbPrice.setItems(getPricesObservList(null,null,null));
        new AutoCompleteComboBoxListener<>(cbPrice);
    }

    @FXML
    public void chooseFlight(){
        cbPortDepart.setItems(getDepartPortsObservList(cbFlight.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDestin.setItems(getDestinPortsObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPrice.getValue()));
        cbPrice.setItems(getPricesObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPortDestin.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListPassengersSortedByFlightInf(cbFlight.getValue(),
                cbPortDepart.getValue(), cbPortDestin.getValue(),cbPrice.getValue()));
    }

    @FXML
    public void chooseDepart(){
        cbFlight.setItems(getFlightObservList(cbPortDepart.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDestin.setItems(getDestinPortsObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPrice.getValue()));
        cbPrice.setItems(getPricesObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPortDestin.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListPassengersSortedByFlightInf(cbFlight.getValue(),
                cbPortDepart.getValue(), cbPortDestin.getValue(),cbPrice.getValue()));
    }

    @FXML
    public void chooseDestin(){
        cbFlight.setItems(getFlightObservList(cbPortDepart.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDepart.setItems(getDepartPortsObservList(cbFlight.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPrice.setItems(getPricesObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPortDestin.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListPassengersSortedByFlightInf(cbFlight.getValue(),
                cbPortDepart.getValue(), cbPortDestin.getValue(),cbPrice.getValue()));
    }

    @FXML
    public void choosePrice(){
        cbFlight.setItems(getFlightObservList(cbPortDepart.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDepart.setItems(getDepartPortsObservList(cbFlight.getValue(),cbPortDestin.getValue(),cbPrice.getValue()));
        cbPortDestin.setItems(getDestinPortsObservList(cbFlight.getValue(),cbPortDepart.getValue(),cbPrice.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListPassengersSortedByFlightInf(cbFlight.getValue(),
                cbPortDepart.getValue(), cbPortDestin.getValue(),cbPrice.getValue()));
    }

    @FXML
    public void chooseRefresh(){loadSearchPassengersByFlightInf();}

    @FXML
    public void showSearchByPersonalInf(){
        loadSearchPassengersByPersonalInf();
    }

    @FXML
    public void showSearchByFlightInf(){
        loadSearchPassengersByFlightInf();
    }
}
