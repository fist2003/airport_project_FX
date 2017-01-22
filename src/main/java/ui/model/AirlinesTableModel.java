package ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Airlines;
import service.AirlinesService;
import service.FlightsArrivalService;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by ПК on 22.12.2016.
 */
public class AirlinesTableModel implements TableModelInterface {

    public AirlinesTableModel(){}

    private ObservableList<Airlines> airlinesData = FXCollections.observableArrayList();
    private BorderPane tableAirlines;
    private final  String tablePaneFxmlFile = "/fxml/edit/tableAirlines.fxml";

    public TableView<Airlines> getTable() {return airlinesTable;}

    @FXML
    private TableView<Airlines> airlinesTable;
    @FXML
    private TableColumn<Airlines, Integer> idColumn;
    @FXML
    private TableColumn<Airlines, String> nameColumn;
    @FXML
    private TableColumn<Airlines, String> adressColumn;
    @FXML
    private TableColumn<Airlines, String> telephoneColumn;
    @FXML
    private TableColumn<Airlines, String> websiteColumn;

    public ObservableList<Airlines> getAirlinesData() {
        return airlinesData;
    }

    public BorderPane getTablePane() {
        loadTablePane();
        return tableAirlines;
    }

    private void addDataToTable() {
        airlinesData.addAll(new AirlinesService().getAllService());
        airlinesTable.setItems(getAirlinesData());
        airlinesTable.setPlaceholder(new Label("THERE AREN`T NO DATA"));
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        adressColumn.setCellValueFactory(cellData -> cellData.getValue().adressProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
        websiteColumn.setCellValueFactory(cellData -> cellData.getValue().websiteProperty());
    }

    private void loadTablePane(){
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tableAirlines = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AirlinesTableModel controller = loaderTablePane.getController();
        controller.addDataToTable();
    }

}
