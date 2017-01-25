package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Airplanes;
import model.Flights;
import service.AirlinesService;
import service.AirplanesService;
import ui.view.OptionPaneGUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slavik on 24.01.2017.
 */
public class OptionPaneAirplaneController extends OptionPaneGUI {

    public OptionPaneAirplaneController(){}

    private ObservableList<String> airlinesNamesObservList = FXCollections.observableList(new AirlinesService().getListOfNames());

    private AirplanesService instAirplanesService = new AirplanesService();

    private Airplanes instAirplane = new Airplanes();

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    @FXML
    private TextField tfManufacturer;
    @FXML
    private TextField tfModel;
    @FXML
    private TextField tfNumberISO;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfEconom;
    @FXML
    private TextField tfBusiness;

    @FXML
    private ComboBox cbAirlines;

    @FXML
    private Label labelManufacturer;
    @FXML
    private Label labelModel;
    @FXML
    private Label labelNumberISO;
    @FXML
    private Label labelYear;
    @FXML
    private Label labelEconom;
    @FXML
    private Label labelBusiness;
    @FXML
    private Label labelAirlines;

    @FXML
    public void initialize() {
        cbAirlines.setItems(airlinesNamesObservList);
        tfNumberISO.setPromptText("ISO112233");
        if (getEntity() != null){
            instAirplane = (Airplanes) getEntity();
            tfManufacturer.setText(instAirplane.getManufacturer());
            tfModel.setText(instAirplane.getModel());
            tfNumberISO.setText(instAirplane.getNumberISO());
            tfYear.setText(String.valueOf(instAirplane.getYear()));
            tfEconom.setText(String.valueOf(instAirplane.getEconomPlaces()));
            tfBusiness.setText(String.valueOf(instAirplane.getBusinessPlaces()));
            cbAirlines.setValue(instAirplane.getAirline_name());
        }
    }

    @FXML
    public void checkManufacturer(){
        isInputCorrect(tfManufacturer,labelManufacturer);
    }

    @FXML
    public void checkModel(){
        isInputCorrect(tfModel,labelModel);
    }

    @FXML
    public void checkNumberISO(){
        isInputISONumberCorrect(tfNumberISO,labelNumberISO);
    }

    @FXML
    public void checkYear(){
        isInputYearCorrect(tfYear,labelYear);
    }

    @FXML
    public void checkEconom(){
        isInputPlacesCorrect(tfEconom,labelEconom);
    }

    @FXML
    public void checkBusiness(){
        isInputPlacesCorrect(tfBusiness,labelBusiness);
    }

    @FXML
    public void checkAirlines(){
        isComboBoxValueChoosed(cbAirlines,labelAirlines);
    }

    @FXML
    public void chooseOk(){
        boolean isInsertNewSelected = getOptionStr().equals(instEditDataService.getInsertNewOptionStr());
        boolean isEditSelected = getOptionStr().equals(instEditDataService.getEditOptionStr());
        boolean isDeleteSelected = getOptionStr().equals(instEditDataService.getDeleteOptionStr());
        Airplanes instance = new Airplanes();
        instance.setNumberISO(tfNumberISO.getText());
        boolean isDataExistInDB = instEditDataService.isDataAlreadyExist(instEditDataService.getAirplanesTypeStr(),instance);
        if((isInsertNewSelected)&&((isDataExistInDB)||(!isAllFieldsInputCorrect()))){
            if(isDataExistInDB)labelNumberISO.setText(getValueAlreadyExist());
            return;
        }
        else if((isInsertNewSelected)&&(!isDataExistInDB)&&(isAllFieldsInputCorrect())){
            int airline_id = new AirlinesService().getAirlineIdByName(cbAirlines.getValue().toString());
            if (airline_id > 0) {
                instAirplanesService.insertNewService(new Airplanes(0, tfManufacturer.getText(), tfModel.getText(), tfNumberISO.getText(),
                        instEditDataService.convertStringToInt(tfYear.getText()), instEditDataService.convertStringToInt(tfEconom.getText()),
                        instEditDataService.convertStringToInt(tfBusiness.getText()), airline_id));
            }
        }

        if ((isEditSelected)&&(!isAllFieldsInputCorrect())){return;}
        else if((isEditSelected)&&(isAllFieldsInputCorrect())){
            boolean isEditedNumber = !instAirplane.getNumberISO().toLowerCase().equals(tfNumberISO.getText().toLowerCase());
            if((isEditedNumber)&&(isDataExistInDB)) {
                labelNumberISO.setText(getValueAlreadyExist());
                return;
            }
            else {
                int airline_id = new AirlinesService().getAirlineIdByName(cbAirlines.getValue().toString());
                if (airline_id > 0) {
                    instAirplanesService.editDataService(new Airplanes(getEntity().getId(), tfManufacturer.getText(),
                            tfModel.getText(), tfNumberISO.getText(), instEditDataService.convertStringToInt(tfYear.getText()),
                            instEditDataService.convertStringToInt(tfEconom.getText()),
                            instEditDataService.convertStringToInt(tfBusiness.getText()), airline_id));
                }
            }
        }
        if(isDeleteSelected){
            instAirplane.setId(getEntity().getId());
            if(instEditDataService.isSafeDelete(instEditDataService.getAirplanesTypeStr(),instAirplane)){
                instAirplanesService.deleteDataService((Airplanes) getEntity());
            }
            else {
                displayErrorDialog("You can`t delete this airplane while there are registered flights on it!");
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

    private boolean isInputISONumberCorrect(TextField textField,Label label) {
        Pattern patIso = Pattern.compile("iso\\d{6}");
        Matcher matIso = patIso.matcher(textField.getText().toLowerCase());
        if(matIso.matches()){
            label.setText("");
            return true;}
        else {
            label.setText(getInputIsIncorect() +" Example: ISO012345");
            return true;
        }
    }

    private boolean isInputYearCorrect(TextField textField,Label label){
        if(instEditDataService.checkInputYear(textField.getText())){
            label.setText("");
            return true;
        }
        else {
            label.setText(getInputIsIncorect() + " Example: 2017");
            return false;
        }
    }

    private boolean isInputPlacesCorrect(TextField textField,Label label){
        if(instEditDataService.checkInputQuantityPlaces(textField.getText())) {
            label.setText("");
            return true;
        }
        else {
            label.setText(getInputIsIncorect());
            return false;
        }
    }

    private boolean isAllFieldsInputCorrect(){
        boolean check = true;
        boolean manufacturer = isInputCorrect(tfManufacturer,labelManufacturer);
        boolean model = isInputCorrect(tfModel,labelModel);
        boolean number = isInputISONumberCorrect(tfNumberISO,labelNumberISO);
        boolean year = isInputYearCorrect(tfYear,labelYear);
        boolean econom = isInputPlacesCorrect(tfEconom,labelEconom);
        boolean business = isInputPlacesCorrect(tfBusiness,labelBusiness);
        boolean airline = isComboBoxValueChoosed(cbAirlines,labelAirlines);
        boolean[] arr = {manufacturer,model,number,year,econom,business,airline};
        for (Boolean value:arr){
            if(!value){check = false;}
        }
        return check;
    }


}
