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
import ui.view.PricesGUI;

import java.io.IOException;

/**
 * Created by slavik on 18.01.2017.
 */
public class AllPricesTableModel {

    public AllPricesTableModel(){}

    private ObservableList<Flights> pricesData = FXCollections.observableArrayList();
    private BorderPane tablePane;

    @FXML
    private TableView<Flights> pricesTable;
    @FXML
    private TableColumn<Flights, String> columnNumber;
    @FXML
    private TableColumn<Flights, String> columnPortDepart;
    @FXML
    private TableColumn<Flights, String> columnPortDestination;
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


    public BorderPane getTablePane(Object portDepartValue,Object portArrivevalue,Object dateDepartValue){
        loadTablePane(portDepartValue,portArrivevalue,dateDepartValue);
        return tablePane;
    }

    private void addDataToTable(Object portDepartValue,Object portArrivevalue,Object dateDepartValue){
        pricesData.addAll(new PricesSortService().makeListOfFlightsSorted(portDepartValue,portArrivevalue,dateDepartValue));
        pricesTable.setItems(getArrivalData());
        pricesTable.setPlaceholder(new Label("THERE ARE NOT FLIGHTS"));
    }

    @FXML
    private void initialize() {
        columnNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        columnPortDepart.setCellValueFactory(cellData -> cellData.getValue().departPortProperty());
        columnPortDestination.setCellValueFactory(cellData -> cellData.getValue().destinationPortProperty());
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

    private void loadTablePane(Object portDepartValue,Object portArrivevalue,Object dateDepartValue){
        String tablePaneFxmlFile = "/fxml/prices/allPricesTable.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AllPricesTableModel controller = loaderTablePane.getController();
        controller.addDataToTable(portDepartValue,portArrivevalue,dateDepartValue);
    }
}
