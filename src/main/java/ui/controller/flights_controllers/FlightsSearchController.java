package ui.controller.flights_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import service.SearchFlightsService;
import ui.view.FlightsGUI;

import java.time.LocalDate;

/**
 * Created by slavik on 12.01.2017.
 */
public class FlightsSearchController extends FlightsGUI{
    public FlightsSearchController(){}

    private SearchFlightsService instSearchFlightService = new SearchFlightsService();

    private ObservableList<String> cbTypeObservList = FXCollections.observableList(new SearchFlightsService().getListForTypeCB());

    private ObservableList<LocalDate> getDateObservList(String typeValue,Object portValue,Object numberValue){
        return FXCollections.observableList(instSearchFlightService.getListDatesForComboBox(typeValue,portValue,numberValue));
    }

    private ObservableList<String> getPortsObservList(String typeValue,Object dateValue,Object numberValue){
        return FXCollections.observableList(instSearchFlightService.getListPortsForComboBox(typeValue,dateValue,numberValue));
    }

    private ObservableList<String> getFlightsNumbersObservList(String typeValue,Object dateValue,Object portValue){
        return FXCollections.observableList(instSearchFlightService.getListFlightNumbersForComboBox(typeValue,dateValue,portValue));
    }

    @FXML
    private ComboBox cbType;

    @FXML
    private ComboBox cbDates;

    @FXML
    private ComboBox cbDirection;

    @FXML
    private ComboBox cbFlight;

    @FXML
    private Button buttonSearchFlights;

    @FXML
    public void initialize(){
        cbType.setValue(cbTypeObservList.get(0));
        cbType.setItems(cbTypeObservList);
        cbDates.setItems(getDateObservList(cbType.getValue().toString(),null,null));
        cbDirection.setItems(getPortsObservList(cbType.getValue().toString(),null,null));
        cbFlight.setItems(getFlightsNumbersObservList(cbType.getValue().toString(),null,null));
    }

    @FXML
    public void chooseType(){
        cbDates.setItems(getDateObservList(cbType.getValue().toString(),null,null));
        cbDirection.setItems(getPortsObservList(cbType.getValue().toString(),null,null));
        cbFlight.setItems(getFlightsNumbersObservList(cbType.getValue().toString(),null,null));
    }

    @FXML
    public void chooseDate(){
        cbDirection.setItems(getPortsObservList(cbType.getValue().toString(),cbDates.getValue(),cbFlight.getValue()));
        cbFlight.setItems(getFlightsNumbersObservList(cbType.getValue().toString(),cbDates.getValue(),cbDirection.getValue()));
    }

    @FXML
    public void choosePort(){
            cbDates.setItems(getDateObservList(cbType.getValue().toString(), cbDirection.getValue(), cbFlight.getValue()));
            cbFlight.setItems(getFlightsNumbersObservList(cbType.getValue().toString(), cbDates.getValue(), cbDirection.getValue()));
    }

    @FXML
    public void chooseNumber(){
            cbDates.setItems(getDateObservList(cbType.getValue().toString(), cbDirection.getValue(), cbFlight.getValue()));
            cbDirection.setItems(getPortsObservList(cbType.getValue().toString(), cbDates.getValue(), cbFlight.getValue()));
    }

    @FXML
    public void searchFlight(){
        if(cbType.getValue().toString().equals(instSearchFlightService.getArrivalsTypeStr())){
            loadArrivalTable(cbDates.getValue(),cbDirection.getValue(),cbFlight.getValue());
        }
        else if(cbType.getValue().toString().equals(instSearchFlightService.getDeparturesTypeStr())){
            loadDepartureTable(cbDates.getValue(),cbDirection.getValue(),cbFlight.getValue());
        }
    }

    @FXML
    public void showArrivals(){loadArrivalEastPane();}

    @FXML
    public void showDepartures(){loadDepartureEastPane();}

    @FXML
    public void showSearch(){
        loadSearchEastPane();
    }


}
