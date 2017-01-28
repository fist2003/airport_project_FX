package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Flights;
import service.AirplanesService;
import service.FlightsService;
import ui.view.OptionPaneGUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by slavik on 25.01.2017.
 */
public class OptionPaneFlightController extends OptionPaneGUI {

    public OptionPaneFlightController(){}

    private ObservableList<String> airplaneNumbersObservList = FXCollections.observableList(new AirplanesService().getListOfNumbers());
    private ObservableList<String> statusObservList = FXCollections.observableList(instEditDataService.getStatusList());
    private ObservableList<String> gatesObservList = FXCollections.observableList(instEditDataService.getGatesList());
    private FlightsService instFlightsService = new FlightsService();
    private Flights instFlight = new Flights();

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    @FXML
    private DatePicker dateDepart;
    @FXML
    private DatePicker dateArrive;

    @FXML
    private ComboBox cbAirplanes;
    @FXML
    private ComboBox cbStatusFlight;
    @FXML
    private ComboBox cbGate;

    @FXML
    private TextField tfFlight;
    @FXML
    private TextField tfPortDepart;
    @FXML
    private TextField tfPortDestin;
    @FXML
    private TextField tfTimeDepart;
    @FXML
    private TextField tfTimeArrive;
    @FXML
    private TextField tfPriceEconom;
    @FXML
    private TextField tfPriceBusiness;
    @FXML
    private TextField tfCurrentTime;

    @FXML
    private Label labelFlight;
    @FXML
    private Label labelPortDepart;
    @FXML
    private Label labelPortDestin;
    @FXML
    private Label labelDateDepart;
    @FXML
    private Label labelDateArrive;
    @FXML
    private Label labelTimeDepart;
    @FXML
    private Label labelTimeArrive;
    @FXML
    private Label labelPriceEconom;
    @FXML
    private Label labelPriceBusiness;
    @FXML
    private Label labelAirplane;
    @FXML
    private Label labelStatusFlight;
    @FXML
    private Label labelGate;
    @FXML
    private Label labelTimeCurrent;

    @FXML
    public void initialize() {
        setupDatePicker(dateDepart);
        setupDatePicker(dateArrive);
        cbAirplanes.setItems(airplaneNumbersObservList);
        cbStatusFlight.setItems(statusObservList);
        cbGate.setItems(gatesObservList);
        tfTimeDepart.setPromptText("hh:mm:ss");
        tfTimeArrive.setPromptText("hh:mm:ss");
        tfCurrentTime.setPromptText("hh:mm:ss");
        dateDepart.setPromptText("yyyy-MM-dd");
        dateArrive.setPromptText("yyyy-MM-dd");
        if (getEntity() != null){
            instFlight = (Flights) getEntity();
            tfFlight.setText(instFlight.getNumber());
            tfPortDepart.setText(instFlight.getDepartPort());
            tfPortDestin.setText(instFlight.getDestinationPort());
            dateDepart.setValue(instEditDataService.convertStringToLocalDate(instFlight.getDateDepart()));
            dateArrive.setValue(instEditDataService.convertStringToLocalDate(instFlight.getDateDestin()));
            tfTimeDepart.setText(instFlight.getTimeDepart());
            tfTimeArrive.setText(instFlight.getTimeDestin());
            tfPriceEconom.setText(String.valueOf(instFlight.getPriceEconom()));
            tfPriceBusiness.setText(String.valueOf(instFlight.getPriceBusiness()));
            cbAirplanes.setValue(instFlight.getAirplane_number());
            cbStatusFlight.setValue(instFlight.getStatusOfFlight());
            cbGate.setValue(instFlight.getGateName());
            tfCurrentTime.setText(instFlight.getCurrentTime());
        }
    }

    @FXML
    public void checkNumber(){isInputCorrect(tfFlight,labelFlight);}
    @FXML
    public void checkPortDepart(){isInputCorrect(tfPortDepart,labelPortDepart);}
    @FXML
    public void checkPortDestin(){isInputCorrect(tfPortDestin,labelPortDestin);}
    @FXML
    public void checkDateDepart(){isDepartDateCorrect();}
    @FXML
    public void checkDateArrive(){isArriveDateCorrect();}
    @FXML
    public void checkTimeDepart(){isInputTimeCorrect(tfTimeDepart,labelTimeDepart);}
    @FXML
    public void checkTimeArrive(){isInputTimeCorrect(tfTimeArrive,labelTimeArrive);}
    @FXML
    public void checkTimeCurrent(){isInputCurrentTimeCorrect();}
    @FXML
    public void checkPriceEconom(){isInputPriceCorrect(tfPriceEconom,labelPriceEconom);}
    @FXML
    public void checkPriceBusiness(){isInputPriceCorrect(tfPriceBusiness,labelPriceBusiness);}

    @FXML
    public void chooseOk(){
        boolean isInsertNewSelected = getOptionStr().equals(instEditDataService.getInsertNewOptionStr());
        boolean isEditSelected = getOptionStr().equals(instEditDataService.getEditOptionStr());
        boolean isDeleteSelected = getOptionStr().equals(instEditDataService.getDeleteOptionStr());
        Flights instance = new Flights();
        instance.setNumber(tfFlight.getText());
        boolean isDataExistInDB = instEditDataService.isDataAlreadyExist(instEditDataService.getFlightsTypeStr(),instance);
        if((isInsertNewSelected)&&((isDataExistInDB)||(!isAllFieldsInputCorrect()))){
            if(isDataExistInDB) labelFlight.setText(getValueAlreadyExist());
            return;
        }
        else if((isInsertNewSelected)&&(!isDataExistInDB)&&(isAllFieldsInputCorrect())){
            int airplane_id = new AirplanesService().getAirplaneIdByName(cbAirplanes.getValue().toString());
            if (airplane_id > 0) {
                int priceEconom = instEditDataService.convertStringToInt(tfPriceEconom.getText());
                int priceBusiness = instEditDataService.convertStringToInt(tfPriceBusiness.getText());
                String status = getComboBoxStringValue(cbStatusFlight);
                String gate = getComboBoxStringValue(cbGate);
                String current = getCurrentTimeValue();
                instFlightsService.insertNewService(new Flights(0, tfFlight.getText(),tfPortDepart.getText(),
                        tfPortDestin.getText(),dateDepart.getValue().toString(), dateArrive.getValue().toString(),
                        tfTimeDepart.getText(),tfTimeArrive.getText(),priceEconom, priceBusiness,airplane_id,status,
                        gate,current));
            }
        }

        if ((isEditSelected)&&(!isAllFieldsInputCorrect())){return;}
        else if((isEditSelected)&&(isAllFieldsInputCorrect())){
            boolean isEditedNumber = !instFlight.getNumber().toLowerCase().equals(tfFlight.getText().toLowerCase());
            if((isEditedNumber)&&(isDataExistInDB)) {
                labelFlight.setText(getValueAlreadyExist());
                return;
            }
            else {
                int airplane_id = new AirplanesService().getAirplaneIdByName(cbAirplanes.getValue().toString());
                if (airplane_id > 0) {
                    int priceEconom = instEditDataService.convertStringToInt(tfPriceEconom.getText());
                    int priceBusiness = instEditDataService.convertStringToInt(tfPriceBusiness.getText());
                    String status = getComboBoxStringValue(cbStatusFlight);
                    String gate = getComboBoxStringValue(cbGate);
                    String current = getCurrentTimeValue();
                    instFlightsService.editDataService(new Flights(getEntity().getId(), tfFlight.getText(),tfPortDepart.getText(),
                            tfPortDestin.getText(),dateDepart.getValue().toString(), dateArrive.getValue().toString(),
                            tfTimeDepart.getText(),tfTimeArrive.getText(),priceEconom, priceBusiness,airplane_id,status,
                            gate,current));
                }
            }
        }
        if(isDeleteSelected){
            instFlight.setId(getEntity().getId());
            if(instEditDataService.isSafeDelete(instEditDataService.getAirplanesTypeStr(),instFlight)){
                instFlightsService.deleteDataService((Flights) getEntity());
            }
            else {
                displayErrorDialog("You can`t delete this flight while there are registered passengers on it!");
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

    private void setupDatePicker(DatePicker datePicker){
        datePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(instEditDataService.getDateFormater());
            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }
            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });
    }

    private boolean isDepartDateCorrect() {
        if((dateArrive.getValue() != null)&&(dateDepart.getValue() != null)) {
            if (dateDepart.getValue().isAfter(dateArrive.getValue())) {
                labelDateDepart.setText("Can`t be after arrive date");
                return false;
            }
            else {labelDateDepart.setText("");}
        }
        else if (dateDepart.getValue() == null){
            labelDateDepart.setText(getEnterSomeValue());
            return false;
        }
        else {labelDateDepart.setText("");}
        return true;
    }

    private boolean isArriveDateCorrect() {
        if((dateArrive.getValue() != null)&&(dateDepart.getValue() != null)) {
            if (dateArrive.getValue().isBefore(dateDepart.getValue())) {
                labelDateArrive.setText("Can`t be before depart date");
                return false;
            }
            else {labelDateArrive.setText("");}
        }
        else if (dateArrive.getValue() == null){
            labelDateArrive.setText(getEnterSomeValue());
            return false;
        }
        else {labelDateArrive.setText("");}
        return true;
    }

    private boolean isInputTimeCorrect(TextField time,Label label){
        if(!instEditDataService.checkInputTime(time.getText())){
            label.setText(getInputIsIncorect());
            return false;
        }
        else {
            label.setText("");
            return true;
        }
    }

    private boolean isInputCurrentTimeCorrect(){
        if((tfCurrentTime.getText() != null)&&(tfCurrentTime.getText().length() > 0)){
            return isInputTimeCorrect(tfCurrentTime,labelTimeCurrent);
        }
        else {
            labelTimeCurrent.setText("");
            return true;
        }
    }

    private boolean isInputPriceCorrect(TextField textField,Label label){
        if ((!instEditDataService.isInputNumber(textField.getText()))||(textField.getText().length() > 10)){
            label.setText(getInputIsIncorect());
            return false;
        }
        else {
            label.setText("");
            return true;
        }
    }

    private boolean isAllFieldsInputCorrect(){
        boolean check = true;
        boolean number = isInputCorrect(tfFlight,labelFlight);
        boolean portDepart = isInputCorrect(tfPortDepart,labelPortDepart);
        boolean portDestin = isInputCorrect(tfPortDestin,labelPortDestin);
        boolean dateDepart = isDepartDateCorrect();
        boolean dateDestin = isArriveDateCorrect();
        boolean timeDepart = isInputTimeCorrect(tfTimeDepart,labelTimeDepart);
        boolean timeDestin = isInputTimeCorrect(tfTimeArrive,labelTimeArrive);
        boolean timeCurrent = isInputCurrentTimeCorrect();
        boolean priceEconom = isInputPriceCorrect(tfPriceEconom,labelPriceEconom);
        boolean priceBusiness = isInputPriceCorrect(tfPriceBusiness,labelPriceBusiness);
        boolean airplaneCB = isComboBoxValueChoosed(cbAirplanes,labelAirplane);
        boolean[] arr = {number,portDepart,portDestin,dateDepart,dateDestin,timeDepart,timeDestin,timeCurrent,priceEconom,
        priceBusiness,airplaneCB};
        for (Boolean value:arr){
            if(!value){check = false;}
        }
        return check;
    }

    private String getComboBoxStringValue(ComboBox comboBox){
        String result = "";
        if (comboBox.getValue() != null){
            result = comboBox.getValue().toString();
        }
        return result;
    }
    private String getCurrentTimeValue(){
        String result = null;
        if (tfCurrentTime.getText() != null){
            if(!tfCurrentTime.getText().equals("")){
                result = tfCurrentTime.getText();}
        }
        return result;
    }

}
