package ui.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Entity;

import javax.xml.soap.Text;
import java.io.IOException;

/**
 * Created by slavik on 22.01.2017.
 */
public class OptionPaneGUI extends EditDataGUI {

    public OptionPaneGUI(){}

    private static Entity entity;
    private static boolean flag;
    private static String optionStr;

    private final String nameIsAlreadyExist = "This Name is already exist";
    private final String inputIsTooLong = "Input is too long";
    private final String inputIsIncorect = "input is incorect";
    private final String enterSomeValue = "Please enter some value";

    protected String getNameIsAlreadyExist() {return nameIsAlreadyExist;}
    protected String getInputIsTooLong() {return inputIsTooLong;}

    protected static Entity getEntity() {return entity;}
    protected static void setEntity(Entity entity) {OptionPaneGUI.entity = entity;}
    protected static boolean isFlag() {return flag;}
    protected static void setFlag(boolean flag) {OptionPaneGUI.flag = flag;}
    protected static String getOptionStr() {return optionStr;}
    protected static void setOptionStr(String optionStr) {OptionPaneGUI.optionStr = optionStr;}

    public boolean displayOptionPaneInsert(String option, String fxmlUrl){
        setOptionStr(option);
        Stage windowOption = new Stage();
        windowOption.setTitle(option);
        windowOption.initModality(Modality.APPLICATION_MODAL);
        GridPane modulePane;
        FXMLLoader loaderManePane = new FXMLLoader();
        try {
            modulePane = (GridPane) loaderManePane.load(getClass().getResourceAsStream(fxmlUrl));
        } catch (IOException e) {
            modulePane = new GridPane();
        }
        windowOption.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                setEntity(null);
                setOptionStr(null);
                setFlag(false);
            }
        });
        windowOption.setScene(new Scene(modulePane));
        windowOption.showAndWait();
        return flag;
    }

    public boolean displayOptionPaneEdit(String option,String fxmlUrl,Entity entityPar){
        if(entityPar != null) {
            setEntity(entityPar);
        }
        return displayOptionPaneInsert(option,fxmlUrl);
    }

    protected boolean isInputCorrect(TextField textField, Label label) {
        boolean isBigLength = instEditDataService.isBigLength(textField.getText(), 50);
        boolean isEmpty = textField.getText().isEmpty();
        boolean isFirstWhiteSpace = instEditDataService.isFirstWhiteSpace(textField.getText());
        boolean isDoubleWhiteSpaceIn = instEditDataService.isDoubleWhiteSpace(textField.getText());
        if (((!isBigLength) && (!isFirstWhiteSpace) && (!isDoubleWhiteSpaceIn))) {
            label.setText("");
            return true;
        }
        else if(isEmpty){
            label.setText(enterSomeValue);
            return false;
        }else if (isBigLength) {
            label.setText(inputIsTooLong);
            return false;
        } else if (isDoubleWhiteSpaceIn) {
            label.setText(inputIsIncorect);
            return false;
        } else {
            label.setText(inputIsIncorect);
            return false;
        }
    }

    protected boolean isInputCorrect(TextField textField, Label label,int maxLength){
        boolean isBigLength = instEditDataService.isBigLength(textField.getText(),maxLength);
        boolean isEmpty = textField.getText().isEmpty();
        boolean isFirstWhiteSpace = instEditDataService.isFirstWhiteSpace(textField.getText());
        boolean isDoubleWhiteSpaceIn = instEditDataService.isDoubleWhiteSpace(textField.getText());
        if (((!isBigLength) && (!isFirstWhiteSpace) && (!isDoubleWhiteSpaceIn))) {
            label.setText("");
            return true;
        }
        else if(isEmpty){
            label.setText(enterSomeValue);
            return false;
        }else if (isBigLength) {
            label.setText(inputIsTooLong);
            return false;
        } else if (isDoubleWhiteSpaceIn) {
            label.setText(inputIsIncorect);
            return false;
        } else {
            label.setText(inputIsIncorect);
            return false;
        }
    }

    protected void displayErrorDialog(String title){
        Alert alert = new Alert(Alert.AlertType.WARNING, title, ButtonType.CLOSE);
        alert.showAndWait();
    }

}
