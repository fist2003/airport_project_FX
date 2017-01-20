package ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Passengers;
import ui.model.PassengersTableModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by slavik on 20.01.2017.
 */
public class SearchPassengersGUI extends MainPage {

    public SearchPassengersGUI(){}

    public VBox getEastPane() {
        loadSearchPassengersByPersonalInf();
        return eastPane;
    }

    private BorderPane tablePane;

    protected void loadSearchPassengersByPersonalInf(){
        if (eastPane != null){eastPane.getChildren().clear();}
        String eastPaneFxmlFile = "/fxml/passengers/searchPassengersByPersonalInformation.fxml";
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

    protected void loadSearchPassengersByFlightInf(){
        eastPane.getChildren().clear();
        String eastPaneFxmlFile = "/fxml/passengers/searchPassengersByFlightInformation.fxml";
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

    protected void loadPassengersSearchTable(ArrayList<Passengers> listPassengers){
        tablePane = new PassengersTableModel().getTablePane(listPassengers);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }
}
