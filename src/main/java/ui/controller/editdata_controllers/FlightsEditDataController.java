package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Priority;
import model.Flights;
import service.EditDataSortService;
import ui.model.FlightsTableModel;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by slavik on 27.01.2017.
 */
public class FlightsEditDataController extends EditDataController{

    public FlightsEditDataController(){}

    private ObservableList<String> getPortsDepartObservList(Object destinPortValue,Object dateValue,Object timeValue){
        return FXCollections.observableList(instEditDataSortService.getListPortDeparts(destinPortValue, dateValue, timeValue));
    }

    private ObservableList<String> getPortDestinObservList(Object departPortValue,Object dateValue,Object timeValue){
        return FXCollections.observableList(instEditDataSortService.getListPortDestin(departPortValue, dateValue, timeValue));
    }
    private ObservableList<LocalDate> getDateObservList(Object departPortValue,Object destinPortValue,Object timeValue){
        return FXCollections.observableList(instEditDataSortService.getListDatesDepart(departPortValue,destinPortValue,timeValue));
    }
    private ObservableList<String> getTimeObservList(Object departPortValue,Object destinPortValue,Object dateValue){
        return FXCollections.observableList(instEditDataSortService.getListTimesDepart(departPortValue, destinPortValue, dateValue));
    }

    @FXML
    private ComboBox cbType;

    @FXML
    private ComboBox cbOption;

    @FXML
    private ComboBox cbPortDepart;
    @FXML
    private ComboBox cbPortDestination;
    @FXML
    private ComboBox cbDateDepart;
    @FXML
    private ComboBox cbTimeDepart;

    @FXML
    public void initialize(){
        cbType.setItems(typeDataObservList);
        cbOption.setItems(optionsObservList);
        cbPortDepart.setItems(getPortsDepartObservList(null,null,null));
        cbPortDestination.setItems(getPortDestinObservList(null,null,null));
        cbDateDepart.setItems(getDateObservList(null,null,null));
        cbTimeDepart.setItems(getTimeObservList(null,null,null));
    }

    @FXML
    public void chooseDepart(){
        cbPortDestination.setItems(getPortDestinObservList(cbPortDepart.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbDateDepart.setItems(getDateObservList(cbPortDepart.getValue(),cbPortDestination.getValue(),cbTimeDepart.getValue()));
        cbTimeDepart.setItems(getTimeObservList(cbPortDepart.getValue(),cbPortDestination.getValue(),cbDateDepart.getValue()));
        loadTable();
    }

    @FXML
    public void chooseDestin(){
        cbPortDepart.setItems(getPortsDepartObservList(cbPortDestination.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbDateDepart.setItems(getDateObservList(cbPortDepart.getValue(),cbPortDestination.getValue(),cbTimeDepart.getValue()));
        cbTimeDepart.setItems(getTimeObservList(cbPortDepart.getValue(),cbPortDestination.getValue(),cbDateDepart.getValue()));
        loadTable();
    }

    @FXML
    public void chooseDate(){
        cbPortDepart.setItems(getPortsDepartObservList(cbPortDestination.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbPortDestination.setItems(getPortDestinObservList(cbPortDepart.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbTimeDepart.setItems(getTimeObservList(cbPortDepart.getValue(),cbPortDestination.getValue(),cbDateDepart.getValue()));
        loadTable();
    }

    @FXML
    public void chooseTime(){
        cbPortDepart.setItems(getPortsDepartObservList(cbPortDestination.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbPortDestination.setItems(getPortDestinObservList(cbPortDepart.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue()));
        cbDateDepart.setItems(getDateObservList(cbPortDepart.getValue(),cbPortDestination.getValue(),cbTimeDepart.getValue()));
        loadTable();
    }

    @FXML
    public void chooseRefresh(){
        loadEditDataPane(choose);
    }

    @FXML
    public void chooseOptionFlights(){
        chooseOptionAll();
    }

    private void loadTable(){
        ArrayList<Flights> listFlights =  new EditDataSortService().makeListOfFlightsSorted(cbPortDepart.getValue(),cbPortDestination.getValue(),cbDateDepart.getValue(),cbTimeDepart.getValue());
        tablePane = new FlightsTableModel().getTablePane(listFlights);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }

}
