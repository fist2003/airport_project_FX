package ui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.model.ArrivalFlightsTableModel;
import ui.model.DepartureFlightsTableModel;

import java.io.IOException;
import java.time.LocalDate;


public class FlightsGUI extends MainPage {
    public FlightsGUI() {
        shoowingDate = LocalDate.now();
    }

    private static LocalDate shoowingDate;

    public static LocalDate getShoowingDate() {return shoowingDate;}
    public static void setShoowingDate(LocalDate shoowingDate) {FlightsGUI.shoowingDate = shoowingDate;}

 /*   @FXML
    private VBox eastPane;*/

    private BorderPane tablePane;

    public VBox getEastPane() {
        loadArrivalEastPane();
        return eastPane;
    }

    protected void loadArrivalEastPane(){
        if (eastPane != null){eastPane.getChildren().clear();}
        String eastPaneFxmlFile = "/fxml/flights/EastPaneArrivals.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = new ArrivalFlightsTableModel().getTablePane(0,shoowingDate);
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        if (eastPane != null){
            eastPane.getChildren().add(pane);
            eastPane.setVgrow(pane, Priority.ALWAYS);}
        else eastPane = pane;

    }

    protected void loadDepartureEastPane(){
        eastPane.getChildren().clear();
        String eastPaneFxmlFile = "/fxml/flights/EastPaneDeparture.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = new DepartureFlightsTableModel().getTablePane(0,getShoowingDate());
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        eastPane.getChildren().add(pane);
        eastPane.setVgrow(pane, Priority.ALWAYS);
    }

    protected void loadSearchEastPane(){
        eastPane.getChildren().clear();
        String eastPaneFxmlFile = "/fxml/flights/EastPaneSearchFlights.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* tablePane = new DepartureFlightsTableModel().getTablePane(0,getShoowingDate());
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);*/
        eastPane.getChildren().add(pane);
        eastPane.setVgrow(pane, Priority.ALWAYS);
    }


    protected void loadArrivalTable(int sliderValue,LocalDate datevalue){

        tablePane = new ArrivalFlightsTableModel().getTablePane(sliderValue,datevalue);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }
    protected void loadArrivalTable(Object dateValue, Object portValue, Object numberValue){
        tablePane = new ArrivalFlightsTableModel().getTablePane(dateValue, portValue, numberValue);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }

    protected void loadDepartureTable(int sliderValue,LocalDate datevalue){
        tablePane = new DepartureFlightsTableModel().getTablePane(sliderValue, datevalue);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }

    protected void loadDepartureTable(Object dateValue, Object portValue, Object numberValue){
        tablePane = new DepartureFlightsTableModel().getTablePane(dateValue,portValue,numberValue);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }

}
