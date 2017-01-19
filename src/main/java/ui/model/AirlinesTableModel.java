package ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Airlines;

import java.io.IOException;

/**
 * Created by ПК on 22.12.2016.
 */
public class AirlinesTableModel {

    public AirlinesTableModel(){}

    private ObservableList<Airlines> airlinesData = FXCollections.observableArrayList();

    private BorderPane tableAirlines;

    public BorderPane getTablePane() {
        loadTablePane();
        return tableAirlines;
    }

    @FXML
    private TableView<Airlines> airlinesTable;
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

    private void addDataToAirlinesData(){
        airlinesData = null;
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        adressColumn.setCellValueFactory(cellData -> cellData.getValue().adressProperty());
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
        websiteColumn.setCellValueFactory(cellData -> cellData.getValue().websiteProperty());
    }

    public void setMainApp() {
        addDataToAirlinesData();
        airlinesTable.setItems(getAirlinesData());
    }

    private void loadTablePane(){
        String tablePaneFxmlFile = "/fxml/tableAirlines.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tableAirlines = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AirlinesTableModel controller = loaderTablePane.getController();
        controller.setMainApp();
    }
}
