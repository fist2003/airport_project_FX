package ui.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Entity;
import ui.model.ArrivalFlightsTableModel;

import java.io.IOException;

/**
 * Created by slavik on 23.01.2017.
 */

public class LoginPaneGUI {

    public LoginPaneGUI(){}

    private final String loginFxmlUrl = "/fxml/login/loginMenu.fxml";

    protected GridPane modulePane;

    public void displayLoginScene(){
        Stage windowLogin = new Stage();
        windowLogin.setTitle("Login Menu");
        windowLogin.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loaderManePane = new FXMLLoader();
        try {
            modulePane = (GridPane) loaderManePane.load(getClass().getResourceAsStream(loginFxmlUrl));
        } catch (IOException e) {
            modulePane = new GridPane();
        }
        windowLogin.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                MainPage.setIsAdmin(false);
                MainPage.setIsLogined(false);
            }
        });
        windowLogin.setScene(new Scene(modulePane));
        windowLogin.showAndWait();
    }

    protected void loadRegisterPane(TextField textField){

        Stage stage = (Stage) textField.getScene().getWindow();

        String eastPaneFxmlFile = "/fxml/login/registerMenu.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        try {
            modulePane = (GridPane) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(modulePane));

    }

}
