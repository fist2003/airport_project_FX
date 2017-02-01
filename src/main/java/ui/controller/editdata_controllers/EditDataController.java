package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.Entity;
import service.EditDataSortService;
import ui.view.EditDataGUI;
import ui.view.OptionPaneGUI;

/**
 * Created by slavik on 21.01.2017.
 */
public class EditDataController extends EditDataGUI {

    public EditDataController(){}

    protected EditDataSortService instEditDataSortService = new EditDataSortService();

    protected ObservableList<String> typeDataObservList = FXCollections.observableList(instEditDataService.getTypeList());
    protected ObservableList<String> optionsObservList = FXCollections.observableList(instEditDataService.getOptionList());

    protected static String choose;

    public static void setChoose(String choose) {
        EditDataController.choose = choose;
    }

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
    private void chooseOption(){
       chooseOptionAll();
    }

    protected void chooseOptionAll(){
        if((cbOption.getValue() != null)&&(choose != null)) {
            String optionValue = cbOption.getValue().toString();
            if (optionValue.equals(instEditDataService.getInsertNewOptionStr())) {
                if ((choose != null) && (getMapModulePaneFXML().containsKey(choose))) {
                    boolean check = new OptionPaneGUI().displayOptionPaneInsert(optionValue, getMapModulePaneFXML().get(choose));
                    if (check) {
                        loadEditDataPane(choose);
                    }
                }
            } else if ((optionValue.equals(instEditDataService.getEditOptionStr())) || (optionValue.equals(instEditDataService.getDeleteOptionStr()))) {

                tablePane = (BorderPane)eastPane.getChildren().get(1);
                TableView tableView = (TableView) tablePane.getChildren().get(0);
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



