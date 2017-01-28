package ui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage extends StartPageGUI {

    private static boolean isAdmin = false;
    private static boolean isLogined = false;

    private final String bottomPaneFxmlFile = "/fxml/BottomPane.fxml";
    private final String tablePaneFxmlFile = "/fxml/emptyTablePane.fxml";
    private final  String mainPaneFxmlFile = "/fxml/MainPane.fxml";

    public static boolean isIsAdmin() {return isAdmin;}
    public static void setIsAdmin(boolean isAdmin) {MainPage.isAdmin = isAdmin;}
    public static boolean isIsLogined() {return isLogined;}
    public static void setIsLogined(boolean isLogined) {MainPage.isLogined = isLogined;}

    private VBox mainPane;

    @FXML
    protected HBox bottomPane;

    @FXML
    protected VBox eastPane;

    @Override
    public void start(Stage stage) throws Exception {

        displayMainPane(stage);
        stage.show();
    }

    public void displayMainPane(Stage stage){
        loadMainPane();
        loadBottomPane();
        eastPane = new FlightsGUI().getEastPane();
        bottomPane.getChildren().remove(1);
        bottomPane.getChildren().add(1,eastPane);
        bottomPane.setHgrow(eastPane,Priority.ALWAYS);
        mainPane.setVgrow(bottomPane,Priority.ALWAYS);
        mainPane.getChildren().set(3,bottomPane);
        stage.setScene(new Scene(mainPane));

    }

    private void loadMainPane(){
        FXMLLoader loaderManePane = new FXMLLoader();
        try {
            mainPane = (VBox) loaderManePane.load(getClass().getResourceAsStream(mainPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBottomPane(){
        FXMLLoader loaderBottomPane = new FXMLLoader();
        try {
            bottomPane = (HBox) loaderBottomPane.load(getClass().getResourceAsStream(bottomPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BorderPane getEmptyTablePane(BorderPane tablePane){
        FXMLLoader loaderTablePane = new FXMLLoader();
        try {
            tablePane = (BorderPane) loaderTablePane.load(getClass().getResourceAsStream(tablePaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tablePane;
    }
}


