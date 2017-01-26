package ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Airplanes;
import service.AirplanesService;

import java.io.IOException;

/**
 * Created by slavik on 24.01.2017.
 */
public class AirplanesTableModel implements TableModelInterface {

    public AirplanesTableModel(){}

    private ObservableList<Airplanes> dataList = FXCollections.observableArrayList();
    private BorderPane tablePane;
    private final  String tablePaneFxmlFile = "/fxml/edit/tableAirplanes.fxml";

    @FXML
    private TableView<Airplanes> tableView;
    @FXML
    private TableColumn<Airplanes, String> manufacturerColumn;
    @FXML
    private TableColumn<Airplanes, String> modelColumn;
    @FXML
    private TableColumn<Airplanes, String> numberISOColumn;
    @FXML
    private TableColumn<Airplanes, Integer> yearColumn;
    @FXML
    private TableColumn<Airplanes, Integer> economColumn;
    @FXML
    private TableColumn<Airplanes, Integer> businessColumn;
    @FXML
    private TableColumn<Airplanes, String> airlineColumn;

    public ObservableList<Airplanes> getDataList() {
        return dataList;
    }

    public BorderPane getTablePane() {
        loadTablePane();
        return tablePane;
    }

    private void addDataToTable() {
        dataList.addAll(new AirplanesService().getAllForTableView());
        tableView.setItems(getDataList());
        tableView.setPlaceholder(new Label("THERE IS NO DATA"));
    }

    @FXML
    private void initialize() {
        manufacturerColumn.setCellValueFactory(cellData -> cellData.getValue().manufacturerProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        numberISOColumn.setCellValueFactory(cellData -> cellData.getValue().numberISOProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        economColumn.setCellValueFactory(cellData -> cellData.getValue().economPlacesProperty().asObject());
        businessColumn.setCellValueFactory(cellData -> cellData.getValue().businessPlacesProperty().asObject());
        airlineColumn.setCellValueFactory(cellData -> cellData.getValue().airline_nameProperty());
    }

    private void loadTablePane(){
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AirplanesTableModel controller = loaderTablePane.getController();
        controller.addDataToTable();
    }
}
