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
public class AirlinesGUI extends MainPage {

    public AirlinesGUI(){}

    private final String airlinePaneFxmlUrl = "/fxml/AirlinesPane.fxml";

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
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(airlinePaneFxmlUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = new AirlinesTableModel().getTablePane();
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        if (eastPane != null){
            eastPane.getChildren().add(pane);
            eastPane.setVgrow(pane, Priority.ALWAYS);}
        else eastPane = pane;
    }


}
