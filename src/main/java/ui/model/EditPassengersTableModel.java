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
import service.PassengersService;

import java.io.IOException;

/**
 * Created by slavik on 25.01.2017.
 */
public class EditPassengersTableModel implements TableModelInterface  {

    public EditPassengersTableModel(){}

    private ObservableList<Passengers> dataList = FXCollections.observableArrayList();
    private BorderPane tablePane;
    private final  String tablePaneFxmlFile = "/fxml/edit/tablePassengers.fxml";

    @FXML
    private TableView<Passengers> tableView;
    @FXML
    private TableColumn<Passengers, String> flightColumn;
    @FXML
    private TableColumn<Passengers, String> lastNameColumn;
    @FXML
    private TableColumn<Passengers, String> firstNameColumn;
    @FXML
    private TableColumn<Passengers, String> passportColumn;
    @FXML
    private TableColumn<Passengers, String> sexColumn;
    @FXML
    private TableColumn<Passengers, String> birthdayColumn;
    @FXML
    private TableColumn<Passengers, String> countryColumn;
    @FXML
    private TableColumn<Passengers, String> ticketColumn;

    public ObservableList<Passengers> getDataList() {
        return dataList;
    }

    public BorderPane getTablePane() {
        loadTablePane();
        return tablePane;
    }

    private void addDataToTable() {
        dataList.addAll(new PassengersService().getAllForTableView());
        tableView.setItems(getDataList());
        tableView.setPlaceholder(new Label("THERE IS NO DATA"));
    }

    @FXML
    private void initialize() {
        flightColumn.setCellValueFactory(cellData -> cellData.getValue().flight_numberProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        passportColumn.setCellValueFactory(cellData -> cellData.getValue().passportNumberProperty());
        sexColumn.setCellValueFactory(cellData -> cellData.getValue().sexProperty());
        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().birtdayProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        ticketColumn.setCellValueFactory(cellData -> cellData.getValue().classTicketProperty());
    }

    private void loadTablePane(){
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        EditPassengersTableModel controller = loaderTablePane.getController();
        controller.addDataToTable();
    }

}
