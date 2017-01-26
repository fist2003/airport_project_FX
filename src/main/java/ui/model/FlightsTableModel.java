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
import service.FlightsService;

import java.io.IOException;

/**
 * Created by slavik on 25.01.2017.
 */
public class FlightsTableModel implements TableModelInterface {

    public FlightsTableModel(){}

    private ObservableList<Flights> dataList = FXCollections.observableArrayList();
    private BorderPane tablePane;
    private final  String tablePaneFxmlFile = "/fxml/edit/tableFlights.fxml";

    @FXML
    private TableView<Flights> tableView;
    @FXML
    private TableColumn<Flights, String> numberColumn;
    @FXML
    private TableColumn<Flights, String> portDepartColumn;
    @FXML
    private TableColumn<Flights, String> portDestinColumn;
    @FXML
    private TableColumn<Flights, String> dateDepartColumn;
    @FXML
    private TableColumn<Flights, String> dateArriveColumn;
    @FXML
    private TableColumn<Flights, String> timeDepartColumn;
    @FXML
    private TableColumn<Flights, String> timeArriveColumn;
    @FXML
    private TableColumn<Flights, Integer> priceEconomColumn;
    @FXML
    private TableColumn<Flights, Integer> priceBusinessColumn;
    @FXML
    private TableColumn<Flights, String> airplaneColumn;
    @FXML
    private TableColumn<Flights, String> statusColumn;
    @FXML
    private TableColumn<Flights, String> currentTimeColumn;
    @FXML
    private TableColumn<Flights, String> gateColumn;

    public ObservableList<Flights> getDataList() {
        return dataList;
    }

    public BorderPane getTablePane() {
        loadTablePane();
        return tablePane;
    }

    private void addDataToTable() {
        dataList.addAll(new FlightsService().getAllForTableView());
        tableView.setItems(getDataList());
        tableView.setPlaceholder(new Label("THERE IS NO DATA"));
    }

    @FXML
    private void initialize() {
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        portDepartColumn.setCellValueFactory(cellData -> cellData.getValue().departPortProperty());
        portDestinColumn.setCellValueFactory(cellData -> cellData.getValue().destinationPortProperty());
        dateDepartColumn.setCellValueFactory(cellData -> cellData.getValue().dateDepartProperty());
        dateArriveColumn.setCellValueFactory(cellData -> cellData.getValue().dateDestinProperty());
        timeDepartColumn.setCellValueFactory(cellData -> cellData.getValue().timeDepartProperty());
        timeArriveColumn.setCellValueFactory(cellData -> cellData.getValue().timeDestinProperty());
        priceEconomColumn.setCellValueFactory(cellData -> cellData.getValue().priceEconomProperty().asObject());
        priceBusinessColumn.setCellValueFactory(cellData -> cellData.getValue().priceBusinessProperty().asObject());
        airplaneColumn.setCellValueFactory(cellData -> cellData.getValue().airplane_numberProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusOfFlightProperty());
        currentTimeColumn.setCellValueFactory(cellData -> cellData.getValue().currentTimeProperty());
        gateColumn.setCellValueFactory(cellData -> cellData.getValue().gateNameProperty());
    }

    private void loadTablePane(){
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FlightsTableModel controller = loaderTablePane.getController();
        controller.addDataToTable();
    }
}
