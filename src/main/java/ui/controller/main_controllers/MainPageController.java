package ui.controller.main_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ui.view.*;

/**
 * Created by slavik on 17.01.2017.
 */
public class MainPageController extends MainPage {
    public MainPageController(){}

    @FXML
    private Button btnLogin;

    @FXML
    public void showLoginMenu(){
        if(isIsLogined()){
            setIsLogined(false);
            setIsAdmin(false);
            hiddenAdminButtons(isIsAdmin());
            changeButtonText(isIsLogined());
            VBox mainPane = (VBox)btnLogin.getParent().getParent();
            bottomPane = (HBox)mainPane.getChildren().get(3);
            eastPane = (VBox)bottomPane.getChildren().get(1);
            eastPane = new FlightsGUI().getEastPane();
            bottomPane.getChildren().remove(1);
            bottomPane.getChildren().add(1,eastPane);
        }
        else {
            new LoginPaneGUI().displayLoginScene();
            hiddenAdminButtons(isIsAdmin());
            changeButtonText(isIsLogined());
        }
    }

    private void hiddenAdminButtons(boolean value){
        VBox mainPane = (VBox)btnLogin.getParent().getParent();
        HBox bottonPane = (HBox)mainPane.getChildren().get(3);
        VBox menuPane = (VBox)bottonPane.getChildren().get(0);
        menuPane.getChildren().get(6).setVisible(value);
        menuPane.getChildren().get(7).setVisible(value);
    }

    private void changeButtonText(boolean value){
        if(value){btnLogin.setText("LogOut");}
        else btnLogin.setText("LogIn");
    }



}
