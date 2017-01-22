package ui.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Entity;

import java.io.IOException;

/**
 * Created by slavik on 22.01.2017.
 */
public class OptionPaneGUI extends EditDataGUI {

    public OptionPaneGUI(){}

    private static Entity entity;
    private static boolean flag;
    private static String optionStr;

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
        AnchorPane modulePane;
        FXMLLoader loaderManePane = new FXMLLoader();
        try {
            modulePane = (AnchorPane) loaderManePane.load(getClass().getResourceAsStream(fxmlUrl));
        } catch (IOException e) {
            modulePane = new AnchorPane();
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
}
