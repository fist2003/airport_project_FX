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
import service.FlightsArrivalService;
import ui.view.FlightsGUI;

import java.io.IOException;
import java.time.LocalDate;


/**
 * Created by ПК on 22.12.2016.
 */
public class ArrivalFlightsTableModel {

    public ArrivalFlightsTableModel(){}

    private ObservableList<Flights> arrivalData = FXCollections.observableArrayList();
    private BorderPane tablePane;

    @FXML
    private TableView<Flights> arrivalTable;
    @FXML
    private TableColumn<Flights, String> columnTime;
    @FXML
    private TableColumn<Flights, String> columnFlight;
    @FXML
    private TableColumn<Flights, String> columnOrigin;
    @FXML
    private TableColumn<Flights, String> columnStatus;
    @FXML
    private TableColumn<Flights, String> columnGate;
    @FXML
    private TableColumn<Flights, String> columnCurrentTime;

    public ObservableList<Flights> getArrivalData() {
        return arrivalData;
    }


    public BorderPane getTablePane(int sliderValue,LocalDate datevalue) {
        loadTablePane(sliderValue,datevalue);
        return tablePane;
    }

    public BorderPane getTablePane(Object dateValue,Object portValue,Object numberValue) {
        loadTablePane(dateValue, portValue, numberValue);
        return tablePane;
    }

    private void addDataToTable(int jSliderValue,LocalDate datevalue){
        arrivalData.addAll(new FlightsArrivalService().makeListOfFlightsForScheduleTable(jSliderValue,datevalue));
        arrivalTable.setItems(getArrivalData());
        arrivalTable.setPlaceholder(new Label("THERE ARE NO FLIGHTS"));
        FlightsGUI.setShoowingDate(datevalue);
    }

    private void addDataToTable(Object dateValue,Object portValue,Object numberValue){
        arrivalData.addAll(new FlightsArrivalService().makeListOfFlightsForSearchTable(dateValue, portValue, numberValue));
        arrivalTable.setItems(getArrivalData());
        arrivalTable.setPlaceholder(new Label("THERE ARE NO FLIGHTS"));
    }

    @FXML
    private void initialize() {
        columnTime.setCellValueFactory(cellData -> cellData.getValue().timeDestinProperty());
        columnFlight.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        columnOrigin.setCellValueFactory(cellData -> cellData.getValue().departPortProperty());
        columnStatus.setCellValueFactory(cellData -> cellData.getValue().statusOfFlightProperty());
        columnGate.setCellValueFactory(cellData -> cellData.getValue().gateNameProperty());
        columnCurrentTime.setCellValueFactory(cellData -> cellData.getValue().currentTimeProperty());
    }

    private void loadTablePane(int sliderValue,LocalDate datevalue){
        String tablePaneFxmlFile = "/fxml/flights/arrivalTable.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrivalFlightsTableModel controller = loaderTablePane.getController();
        controller.addDataToTable(sliderValue,datevalue);
    }

    private void loadTablePane(Object dateValue,Object portValue,Object numberValue){
        String tablePaneFxmlFile = "/fxml/flights/arrivalTable.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrivalFlightsTableModel controller = loaderTablePane.getController();
        controller.addDataToTable(dateValue, portValue, numberValue);
    }

}
