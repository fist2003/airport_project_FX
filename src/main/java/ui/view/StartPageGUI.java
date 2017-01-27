package ui.view;

import dao.ConnectToMySQLDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import service.PutStartDataService;

import java.io.IOException;

/**
 * Created by slavik on 26.01.2017.
 */
public class StartPageGUI extends Application {

    public StartPageGUI(){}

    private GridPane startPane;

    private final String startPaneFxmlFile = "/fxml/startPane.fxml";
    private final String connectionFail = "Check your password and choose make Data Base!";

    private ConnectToMySQLDAO instConnectToMySqlDAO = new ConnectToMySQLDAO();

    @FXML
    private PasswordField pfPassword;
    @FXML
    private RadioButton rbPutStartData;
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnCancel;


    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadStartPane();
        stage.setScene(new Scene(startPane));
        stage.show();
    }

    private void loadStartPane(){

        FXMLLoader loaderManePane = new FXMLLoader();
        try {
            startPane = (GridPane) loaderManePane.load(getClass().getResourceAsStream(startPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void chooseConnectBtn(){
        String password = pfPassword.getText();
        if(rbPutStartData.isSelected()){
            instConnectToMySqlDAO.createSQLDB(password);
            instConnectToMySqlDAO.connectToDB(password);
            instConnectToMySqlDAO.addTablesToDataBase();
            new PutStartDataService().putStartData();
        }
        else{
            instConnectToMySqlDAO.connectToDB(password);
        }
        if (ConnectToMySQLDAO.getConnection() != null){
            Stage stage = (Stage)btnConnect.getParent().getScene().getWindow();
            new MainPage().displayMainPane(stage);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, connectionFail, ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    @FXML
    public void chooseDemo(){
        Stage stage = (Stage)btnCancel.getParent().getScene().getWindow();
        new MainPage().displayMainPane(stage);
    }


}
