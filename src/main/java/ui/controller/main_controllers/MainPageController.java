package ui.controller.main_controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Priority;
import ui.view.*;

/**
 * Created by slavik on 17.01.2017.
 */
public class MainPageController extends MainPage {
    public MainPageController(){}

    @FXML
    public void showFlights(){
        eastPane = new FlightsGUI().getEastPane();
        bottomPane.getChildren().remove(1);
        bottomPane.getChildren().add(1,eastPane);
        bottomPane.setHgrow(eastPane, Priority.ALWAYS);
    }

    @FXML
    public void showPrices(){
        eastPane = new PricesGUI().getEastPane();
        bottomPane.getChildren().remove(1);
        bottomPane.getChildren().add(1,eastPane);
        bottomPane.setHgrow(eastPane, Priority.ALWAYS);
    }

    @FXML
    public void showPassengers(){
        eastPane = new PassengersGUI().getEastPane();
        bottomPane.getChildren().remove(1);
        bottomPane.getChildren().add(1,eastPane);
        bottomPane.setHgrow(eastPane, Priority.ALWAYS);
    }

    @FXML
    public void showSearchPassengers(){
        eastPane = new SearchPassengersGUI().getEastPane();
        bottomPane.getChildren().remove(1);
        bottomPane.getChildren().add(1,eastPane);
        bottomPane.setHgrow(eastPane, Priority.ALWAYS);
    }

    @FXML
    public void showEditData(){
        eastPane = new EditDataGUI().getEastPane();
        bottomPane.getChildren().remove(1);
        bottomPane.getChildren().add(1,eastPane);
        bottomPane.setHgrow(eastPane, Priority.ALWAYS);
    }

    @FXML
    public void showLoginMenu(){
        new LoginPaneGUI().displayLoginScene();
    }

}
