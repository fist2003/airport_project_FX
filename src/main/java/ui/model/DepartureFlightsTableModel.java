package ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Flights;
import service.FlightsDepartService;
import ui.view.FlightsGUI;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by ПК on 22.12.2016.
 */
public class DepartureFlightsTableModel {

    public DepartureFlightsTableModel(){}

    private ObservableList<Flights> departureData = FXCollections.observableArrayList();
    private BorderPane tablePane;

    @FXML
    private TableView<Flights> departureTable;
    @FXML
    private TableColumn<Flights, String> columnTime;
    @FXML
    private TableColumn<Flights, String> columnFlight;
    @FXML
    private TableColumn<Flights, String> columnDestination;
    @FXML
    private TableColumn<Flights, String> columnStatus;
    @FXML
    private TableColumn<Flights, String> columnGate;
    @FXML
    private TableColumn<Flights, String> columnCurrentTime;

    public ObservableList<Flights> getDepartureData() {
        return departureData;
    }


    public BorderPane getTablePane(int jSliderValue,LocalDate datevalue) {
        loadTablePane(jSliderValue, datevalue);
        return tablePane;
    }

    public BorderPane getTablePane(Object dateValue,Object portValue,Object numberValue) {
        loadTablePane(dateValue, portValue, numberValue);
        return tablePane;
    }

    private void addDataToTable(int jSliderValue,LocalDate datevalue){
        departureData.addAll(new FlightsDepartService().makeListOfFlightsForScheduleTable(jSliderValue, datevalue));
        departureTable.setItems(getDepartureData());
        departureTable.setPlaceholder(new Label("THERE ARE NO FLIGHTS"));
        FlightsGUI.setShoowingDate(datevalue);
    }

    private void addDataToTable(Object dateValue,Object portValue,Object numberValue){
        departureData.addAll(new FlightsDepartService().makeListOfFlightsForSearchTable(dateValue, portValue, numberValue));
        departureTable.setPlaceholder(new Label("THERE ARE NO FLIGHTS"));
        departureTable.setItems(getDepartureData());
    }

    @FXML
    private void initialize() {
        columnTime.setCellValueFactory(cellData -> cellData.getValue().timeDepartProperty());
        columnFlight.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        columnDestination.setCellValueFactory(cellData -> cellData.getValue().destinationPortProperty());
        columnStatus.setCellValueFactory(cellData -> cellData.getValue().statusOfFlightProperty());
        columnGate.setCellValueFactory(cellData -> cellData.getValue().gateNameProperty());
        columnCurrentTime.setCellValueFactory(cellData -> cellData.getValue().currentTimeProperty());
    }

    private void loadTablePane(int jSliderValue,LocalDate datevalue){
        String tablePaneFxmlFile = "/fxml/flights/departureTable.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DepartureFlightsTableModel controller = loaderTablePane.getController();
        controller.addDataToTable(jSliderValue, datevalue);
    }

    private void loadTablePane(Object dateValue,Object portValue,Object numberValue){
        String tablePaneFxmlFile = "/fxml/flights/departureTable.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        DepartureFlightsTableModel controller = loaderTablePane.getController();
        controller.addDataToTable(dateValue, portValue, numberValue);
    }

}
