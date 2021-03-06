package ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Passengers;
import service.PassengersSortService;
import ui.model.PassengersTableModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by slavik on 19.01.2017.
 */
public class PassengersGUI extends MainPage {
    public PassengersGUI(){}

    protected PassengersSortService instPassengersSortService = new PassengersSortService();

    private String choosedTypeStr;

    public String getChoosedTypeStr() {return choosedTypeStr;}

    protected void setChoosedTypeStr(String choosedTypeStr) {this.choosedTypeStr = choosedTypeStr;}

    public VBox getEastPane() {
        loadDepartPassengers();
        return eastPane;
    }

    private BorderPane tablePane;

    protected void loadDepartPassengers(){
        if (eastPane != null){eastPane.getChildren().clear();}
        String eastPaneFxmlFile = "/fxml/passengers/showPassengersDepart.fxml";
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

    protected void loadArrivalPassengers(){
        eastPane.getChildren().clear();
        String eastPaneFxmlFile = "/fxml/passengers/showPassengersArrival.fxml";
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

    protected void loadPassengersTable(String typeValue,Object directionValue,Object dateValue,Object timeValue,Object flightNumberValue){
        ArrayList<Passengers> listPassengers =  new PassengersSortService().makeListPassengersSorted(typeValue,directionValue,dateValue,timeValue,flightNumberValue);
        tablePane = new PassengersTableModel().getTablePane(listPassengers);
        eastPane.getChildren().remove(1);
        eastPane.getChildren().add(1,tablePane);
        eastPane.setVgrow(tablePane, Priority.ALWAYS);
    }

}
