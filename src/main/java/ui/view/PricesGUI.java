package ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.model.AllPricesTableModel;
import ui.model.ArrivalFlightsTableModel;
import ui.model.PricesTableModel;

import java.io.IOException;


/**
 * Created by slavik on 17.01.2017.
 */
public class PricesGUI extends MainPage {

    public PricesGUI(){}

    private static boolean isDepartChoosed = true;
    public static boolean getIsDepartChoosed(){return isDepartChoosed;}

    private static String checkClass;

    public static String getCheckClass(){return checkClass;}
    protected static void setCheckClass(String checkClass) {PricesGUI.checkClass = checkClass;}

    public VBox getEastPane() {
        loadDepartPrices();
        return eastPane;
    }

    private BorderPane tablePane;

    protected void loadDepartPrices(){
        isDepartChoosed = true;
        if (eastPane != null){eastPane.getChildren().clear();}
        String eastPaneFxmlFile = "/fxml/prices/pricesDepart.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = getEmptyTablePane(tablePane);
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        if (eastPane != null){
            eastPane.getChildren().add(pane);
            eastPane.setVgrow(pane, Priority.ALWAYS);}
        else eastPane = pane;

    }

    protected void loadArrivalPrices(){
        isDepartChoosed = false;
        eastPane.getChildren().clear();
        String eastPaneFxmlFile = "/fxml/prices/pricesArrival.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = getEmptyTablePane(tablePane);
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        eastPane.getChildren().add(pane);
        eastPane.setVgrow(pane, Priority.ALWAYS);
    }

    protected void loadAllPrices(){
        isDepartChoosed = false;
        eastPane.getChildren().clear();
        String eastPaneFxmlFile = "/fxml/prices/pricesAll.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = getEmptyTablePane(tablePane);
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        eastPane.getChildren().add(pane);
        eastPane.setVgrow(pane, Priority.ALWAYS);
    }

    protected void loadPricesTable(Object directionValue,Object dateDepartValue,Object timeDepartValue){
        tablePane = new PricesTableModel().getTablePane(getIsDepartChoosed(),directionValue,dateDepartValue,timeDepartValue);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }

    protected void loadAllPricesTable(Object portDepartValue,Object portArriveValue, Object dateDepartValue){
        tablePane = new AllPricesTableModel().getTablePane(portDepartValue,portArriveValue,dateDepartValue);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }

}
