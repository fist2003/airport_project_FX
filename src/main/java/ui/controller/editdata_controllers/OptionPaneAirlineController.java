package ui.controller.editdata_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Airlines;
import service.AirlinesService;
import ui.view.OptionPaneGUI;

/**
 * Created by slavik on 21.01.2017.
 */
public class OptionPaneAirlineController extends OptionPaneGUI{

    public OptionPaneAirlineController(){
    }
    private AirlinesService instAirlineService = new AirlinesService();
    private Airlines instAirline = new Airlines();

    @FXML
    private Label labelName;

    @FXML
    private Label labelAdress;

    @FXML
    private Label labelTelephone;

    @FXML
    private Label labelWebsite;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAdress;

    @FXML
    private TextField tfTelephone;

    @FXML
    private TextField tfWebsite;

    @FXML
    public void initialize() {
       if (getEntity() != null){
           instAirline = (Airlines)getEntity();
           tfName.setText(instAirline.getName());
           tfAdress.setText(instAirline.getAdress());
           tfTelephone.setText(instAirline.getTelephone());
           tfWebsite.setText(instAirline.getWebsite());
       }
    }

    @FXML
    public void checkName(){
        isInputCorrect(tfName,labelName);
    }

    @FXML
    public void checkAdress(){
        isInputCorrect(tfAdress,labelAdress,100);
    }

    @FXML
    public void checkTelephone(){
       isTelephoneCorrect();
    }

    @FXML
    public void checkWebsite(){
        isWebsiteCorrect();
    }

    @FXML
    public void chooseOk(){
        boolean isInsertNewSelected = getOptionStr().equals(instEditDataService.getInsertNewOptionStr());
        boolean isEditSelected = getOptionStr().equals(instEditDataService.getEditOptionStr());
        boolean isDeleteSelected = getOptionStr().equals(instEditDataService.getDeleteOptionStr());
        Airlines instance = new Airlines();
        instance.setName(tfName.getText());
        boolean isDataExistInDB = instEditDataService.isDataAlreadyExist(instEditDataService.getAirlinesTypeStr(),instance);
        if((isInsertNewSelected)&&((isDataExistInDB)||(!isAllFieldsInputCorrect()))){
            if(isDataExistInDB)labelName.setText(getValueAlreadyExist());
            return;
        }
        else if((isInsertNewSelected)&&(!isDataExistInDB)&&(isAllFieldsInputCorrect())){
           instAirlineService.insertNewService(new Airlines(0,tfName.getText(),tfAdress.getText(),tfTelephone.getText(),
                   tfWebsite.getText()));
        }
        if ((isEditSelected)&&(!isAllFieldsInputCorrect())){return;}
        else if((isEditSelected)&&(isAllFieldsInputCorrect())){
            boolean isEditedName = !instAirline.getName().toLowerCase().equals(tfName.getText().toLowerCase());
            if((isEditedName)&&(isDataExistInDB)) {
                labelName.setText(getValueAlreadyExist());
                return;
            }
            else instAirlineService.editDataService(new Airlines(getEntity().getId(), tfName.getText(), tfAdress.getText(),
                    tfTelephone.getText(), tfWebsite.getText()));
        }
        if(isDeleteSelected){
            instAirline.setId(getEntity().getId());
            if(instEditDataService.isSafeDelete(instEditDataService.getAirlinesTypeStr(),instAirline)){
                instAirlineService.deleteDataService((Airlines) getEntity());
            }
            else {
                displayErrorDialog("You can`t delete this airline while there are registered airplanes on it!");
                return;
            }
        }
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
        setFlag(true);
        setEntity(null);
    }

    @FXML
    public void chooseCancel(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        setFlag(false);
        setEntity(null);
    }

    private boolean isAllFieldsInputCorrect(){
        boolean check = true;
        boolean name = isInputCorrect(tfName,labelName);
        boolean adress  = isInputCorrect(tfAdress,labelAdress,100);
        boolean telephone = isTelephoneCorrect();
        boolean website = isWebsiteCorrect();
        boolean[] arr = {name,adress,telephone,website};
        for (Boolean value:arr){
            if(!value){check = false;}
        }
        return check;
    }

    private boolean isTelephoneCorrect(){
        if (tfTelephone.getText().length() == 0){
            labelTelephone.setText("");
            return true;
        }
        else if((isInputCorrect(tfTelephone,labelTelephone)&&(instEditDataService.isInputNumber(tfTelephone.getText())))){
            return true;
        }
        else if(!instEditDataService.isInputNumber(tfTelephone.getText())){
            labelTelephone.setText("Wrong telephone number");
        }
        return false;
    }

    private boolean isWebsiteCorrect(){
        if(tfWebsite.getText().length() == 0){
            labelWebsite.setText("");
            return true;
        }
        else if((isInputCorrect(tfWebsite,labelWebsite)&&(instEditDataService.isWebsite(tfWebsite.getText())))){
            return true;
        }
        else if(!instEditDataService.isWebsite(tfWebsite.getText())){
            labelWebsite.setText("Wrong website");
        }
        return false;
    }



}
