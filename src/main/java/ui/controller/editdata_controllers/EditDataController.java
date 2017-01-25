package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Airlines;
import model.Entity;
import ui.view.EditDataGUI;
import ui.view.OptionPaneGUI;

/**
 * Created by slavik on 21.01.2017.
 */
public class EditDataController extends EditDataGUI {

    public EditDataController(){}

    private ObservableList<String> typeDataObservList = FXCollections.observableList(instEditDataService.getTypeList());
    private ObservableList<String> optionsObservList = FXCollections.observableList(instEditDataService.getOptionList());

    private static String choose;

    @FXML
    protected ComboBox cbType;

    @FXML
    protected ComboBox cbOption;

    @FXML
    public void initialize() {
        if(choose != null){cbType.setValue(choose);}
        cbType.setItems(typeDataObservList);
        cbOption.setItems(optionsObservList);
    }

    @FXML
    public void chooseType(){
        if(cbType.getValue() != null) {
            choose = cbType.getValue().toString();
            if ((getMapPaneFXML().containsKey(choose)) && (getMapTableModel().containsKey(choose))) {
                loadEditDataPane(choose);
            }
        }
    }

    @FXML
    public void chooseOption(){
        if(cbOption.getValue() != null) {
            String optionValue = cbOption.getValue().toString();
            if (optionValue.equals(instEditDataService.getInsertNewOptionStr())) {
                if ((choose != null) && (getMapModulePaneFXML().containsKey(choose))) {
                    boolean check = new OptionPaneGUI().displayOptionPaneInsert(optionValue, getMapModulePaneFXML().get(choose));
                    if (check) {
                        loadEditDataPane(choose);
                    }
                }
            } else if ((optionValue.equals(instEditDataService.getEditOptionStr())) || (optionValue.equals(instEditDataService.getDeleteOptionStr()))) {
                VBox vbox = (VBox) cbType.getParent().getParent().getParent();
                BorderPane borderPane = (BorderPane) vbox.getChildren().get(1);
                TableView tableView = (TableView) borderPane.getChildren().get(0);
                Entity instEntity = (Entity) tableView.getSelectionModel().getSelectedItem();
                if ((choose != null) && (getMapModulePaneFXML().containsKey(choose)) && (instEntity != null)) {
                    boolean check = new OptionPaneGUI().displayOptionPaneEdit(optionValue, getMapModulePaneFXML().get(choose), instEntity);
                    if (check) {
                        loadEditDataPane(choose);
                    }
                }
            }
        }
    }

}



