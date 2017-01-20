package ui.view;

import dao.ConnectToMySQLDAO;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ui.model.PricesTableModel;

import java.io.IOException;

public class MainPage extends Application {

    private VBox mainPane;

    @FXML
    protected HBox bottomPane;

    @FXML
    protected VBox eastPane;

    @FXML
    private BorderPane center;

    public static void main(String[] args) throws Exception {
        new ConnectToMySQLDAO().connectToDB("password");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadMainPane();
        loadBottomPane();

        eastPane = new FlightsGUI().getEastPane();
        bottomPane.getChildren().remove(1);
        bottomPane.getChildren().add(1,eastPane);
        bottomPane.setHgrow(eastPane,Priority.ALWAYS);

        mainPane.setVgrow(bottomPane,Priority.ALWAYS);
        mainPane.getChildren().set(3,bottomPane);
        stage.setScene(new Scene(mainPane));
        stage.show();
    }

    private void loadMainPane(){
        String mainPaneFxmlFile = "/fxml/MainPane.fxml";
        FXMLLoader loaderManePane = new FXMLLoader();
        try {
            mainPane = (VBox) loaderManePane.load(getClass().getResourceAsStream(mainPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBottomPane(){
        String bottomPaneFxmlFile = "/fxml/BottomPane.fxml";
        FXMLLoader loaderBottomPane = new FXMLLoader();
        try {
            bottomPane = (HBox) loaderBottomPane.load(getClass().getResourceAsStream(bottomPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BorderPane getEmptyTablePane(BorderPane tablePane){
        String tablePaneFxmlFile = "/fxml/emptyTablePane.fxml";
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tablePane;
    }

}


