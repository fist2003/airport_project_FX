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
import service.PricesSortService;
import service.FlightsArrivalSortService;
import service.FlightsDepartSortService;
import ui.view.PricesGUI;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by slavik on 17.01.2017.
 */
public class PricesTableModel {

    public PricesTableModel(){}

    private ObservableList<Flights> pricesData = FXCollections.observableArrayList();
    private BorderPane tablePane;

    @FXML
    private TableView<Flights> pricesTable;
    @FXML
    private TableColumn<Flights, String> columnNumber;
    @FXML
    private TableColumn<Flights, String> columnDirection;
    @FXML
    private TableColumn<Flights, String> columnDateDepart;
    @FXML
    private TableColumn<Flights, String> columnTimeDepart;
    @FXML
    private TableColumn<Flights, String> columnDateArrive;
    @FXML
    private TableColumn<Flights, String> columnTimeArrive;
    @FXML
    private TableColumn<Flights, Integer> columnPrice;

    public ObservableList<Flights> getArrivalData() {
        return pricesData;
    }


    public BorderPane getTablePane(boolean isDepartChoosed,Object directionValue,Object dateDepartValue,Object timeDepartValue){
        loadTablePane(isDepartChoosed,directionValue,dateDepartValue,timeDepartValue);
        return tablePane;
    }

    private void addDataToTable(boolean isDepartChoosed,Object directionValue,Object dateDepartValue,Object timeDepartValue){
        ArrayList<Flights> data;
        if(isDepartChoosed){
            data = new FlightsDepartSortService().makeListOfFlightsSorted(directionValue,dateDepartValue,timeDepartValue);
        }
        else {
            data  = new FlightsArrivalSortService().makeListOfFlightsSorted(directionValue,dateDepartValue,timeDepartValue);
        }
        pricesData.addAll(data);
        pricesTable.setItems(getArrivalData());
        pricesTable.setPlaceholder(new Label("THERE IS NO FLIGHTS"));
    }

    @FXML
    private void initialize() {
        columnNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        if(PricesGUI.getIsDepartChoosed()){columnDirection.setCellValueFactory(cellData -> cellData.getValue().destinationPortProperty());}
        else{columnDirection.setCellValueFactory(cellData -> cellData.getValue().departPortProperty());}
        columnDateDepart.setCellValueFactory(cellData -> cellData.getValue().dateDepartProperty());
        columnTimeDepart.setCellValueFactory(cellData -> cellData.getValue().timeDepartProperty());
        columnDateArrive.setCellValueFactory(cellData -> cellData.getValue().dateDestinProperty());
        columnTimeArrive.setCellValueFactory(cellData -> cellData.getValue().timeDestinProperty());
        if(PricesGUI.getCheckClass().equals(new PricesSortService().getEconomClassStr())) {
            columnPrice.setCellValueFactory(cellData -> cellData.getValue().priceEconomProperty().asObject());
        }
        else {
            columnPrice.setCellValueFactory(cellData -> cellData.getValue().priceBusinessProperty().asObject());
        }
    }

    private void loadTablePane(boolean isDepartChoosed,Object directionValue,Object dateDepartValue,Object timeDepartValue){
        String tablePaneFxmlFile = "/fxml/prices/pricesTable.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PricesTableModel controller = loaderTablePane.getController();
        controller.addDataToTable(isDepartChoosed, directionValue, dateDepartValue, timeDepartValue);
    }
}
