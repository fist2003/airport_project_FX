package ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Passengers;
import service.PassengersSortService;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by slavik on 19.01.2017.
 */
public class PassengersTableModel {

    public PassengersTableModel(){}

    private ObservableList<Passengers> passengersData = FXCollections.observableArrayList();
    private BorderPane tablePane;

    @FXML
    private TableView<Passengers> passengersTable;
    @FXML
    private TableColumn<Passengers, String> columnNumber;
    @FXML
    private TableColumn<Passengers, String> columnLastName;
    @FXML
    private TableColumn<Passengers, String> columnFirstName;
    @FXML
    private TableColumn<Passengers, String> columnPassport;
    @FXML
    private TableColumn<Passengers, String> columnSex;
    @FXML
    private TableColumn<Passengers, String> columnBirthday;
    @FXML
    private TableColumn<Passengers, String> columnCountry;
    @FXML
    private TableColumn<Passengers, String> columnTicketClass;

    public ObservableList<Passengers> getPassengersData() {
        return passengersData;
    }

    public BorderPane getTablePane(String typeValue,Object directionValue,Object dateValue,Object timeValue,Object flightNumberValue){
        loadTablePane(typeValue,directionValue, dateValue, timeValue, flightNumberValue);
        return tablePane;
    }

    private void addDataToTable(String typeValue,Object directionValue,Object dateValue,Object timeValue,Object flightNumberValue){
        ArrayList<Passengers> data = new PassengersSortService().makeListPassengersSorted(typeValue, directionValue, dateValue, timeValue, flightNumberValue);
        passengersData.addAll(data);
        passengersTable.setItems(getPassengersData());
        passengersTable.setPlaceholder(new Label("THERE ARE NOT FLIGHTS"));
    }

    @FXML
    private void initialize() {
        columnNumber.setCellValueFactory(cellData -> cellData.getValue().flight_numberProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        columnPassport.setCellValueFactory(cellData -> cellData.getValue().passportNumberProperty());
        columnSex.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        columnBirthday.setCellValueFactory(cellData -> cellData.getValue().birtdayProperty());
        columnCountry.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        columnTicketClass.setCellValueFactory(cellData -> cellData.getValue().classTicketProperty());
    }

    private void loadTablePane(String typeValue,Object directionValue,Object dateValue,Object timeValue,Object flightNumberValue){
        String tablePaneFxmlFile = "/fxml/passengers/passengersTable.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PassengersTableModel controller = loaderTablePane.getController();
        controller.addDataToTable(typeValue,directionValue, dateValue, timeValue, flightNumberValue);
    }
}
