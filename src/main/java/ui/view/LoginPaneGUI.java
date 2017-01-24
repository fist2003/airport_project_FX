package ui.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Created by slavik on 23.01.2017.
 */

public class LoginPaneGUI {

    public LoginPaneGUI(){}

    private final String loginFxmlUrl = "/fxml/login/loginMenu.fxml";

    public boolean displayLoginScene(){
        Stage windowLogin = new Stage();
        windowLogin.setTitle("Login Menu");
        windowLogin.initModality(Modality.APPLICATION_MODAL);
        GridPane modulePane;
        FXMLLoader loaderManePane = new FXMLLoader();
        try {
            modulePane = (GridPane) loaderManePane.load(getClass().getResourceAsStream(loginFxmlUrl));
        } catch (IOException e) {
            modulePane = new GridPane();
        }
        windowLogin.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            }
        });
        windowLogin.setScene(new Scene(modulePane));
        windowLogin.showAndWait();
        return true;
    }

}
