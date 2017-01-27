package ui.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
    @FXML
    private TextFlow tfText;

    @FXML
    public void initialize(){
        String checkStr = "      You are advised to check in as early as possible, as required by the airline " +
                "(normally at least two hours before scheduled departure).\n    Check that you have the right travel documents" +
                " for the destination country and that they are still valid. When booking it is important to mention any " +
                "passengers with special needs: children under the age of two, unaccompanied minors, people with mobility " +
                "problems, etc.\n " +
                "   All hand baggage and hold baggage must carry a tag showing your name, surname, telephone, " +
                "address and if possible flight number. For your safety we recommend you carry valuable and strictly " +
                "indispensable items (drugs, baby clothes, etc.) in your hand baggage. \n " +
                "   The size and weight of your hand baggage must comply with the rules of the airline you are flying with. " +
                "For more information contact the airline. The rules vary depending on the airline and may also apply to baggage packing.\n" +
                "\n" +
                "   A hand luggage only is allowed that generally can have a maximum weight of 5 kg, the sum of the sides " +
                "must not exceed 115 cm (eg 55x40x20 cm).";
        Text check = new Text(checkStr);
        tfText.getChildren().add(check);
    }

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
