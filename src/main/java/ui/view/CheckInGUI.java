package ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.model.AirlinesTableModel;

import java.io.IOException;

/**
 * Created by slavik on 27.01.2017.
 */
public class CheckInGUI extends MainPage {

    public CheckInGUI(){}

    private final String checkInPaneFxmlUrl = "/fxml/CheckInPane.fxml";

    public VBox getEastPane() {
        loadAirlinesPane();
        return eastPane;
    }

    private BorderPane tablePane;

    public void loadAirlinesPane() {
        if (eastPane != null){eastPane.getChildren().clear();}
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(checkInPaneFxmlUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (eastPane != null){
            eastPane.getChildren().add(pane);
            eastPane.setVgrow(pane, Priority.ALWAYS);}
        else eastPane = pane;
    }
}
