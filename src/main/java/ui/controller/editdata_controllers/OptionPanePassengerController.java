package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Passengers;
import service.FlightsService;
import service.PassengersService;
import ui.controller.search_controllers.AutoCompleteComboBoxListener;
import ui.view.OptionPaneGUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by slavik on 25.01.2017.
 */
public class OptionPanePassengerController extends OptionPaneGUI {

    public OptionPanePassengerController(){}

    private ObservableList<String> flightNumbersObservList = FXCollections.observableList(new FlightsService().getListFlightNumbers());
    private ObservableList<String> sexObservList = FXCollections.observableList(instEditDataService.getSexList());
    private ObservableList<String> ticketObservList = FXCollections.observableList(instEditDataService.getTicketList());
    private PassengersService instPassengersService = new PassengersService();
    private Passengers instPassenger = new Passengers();

    private final String passportIsAlreadyRegistered = "This passport is already registered in this flight";
    private final String thereisntFreePlace = "There isn`t free places in this flight and class";

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private ComboBox cbFlights;
    @FXML
    private ComboBox cbSex;
    @FXML
    private ComboBox cbTicket;

    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfPassport;
    @FXML
    private TextField tfCountry;

    @FXML
    private Label labelFlights;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelFirstName;
    @FXML
    private Label labelPassport;
    @FXML
    private Label labelSex;
    @FXML
    private Label labelBirthday;
    @FXML
    private Label labelCountry;
    @FXML
    private Label labelTicket;

    @FXML
    public void initialize() {
        setupDatePicker(birthdayPicker);
        cbFlights.setItems(flightNumbersObservList);
        new AutoCompleteComboBoxListener<>(cbFlights);
        cbSex.setItems(sexObservList);
        cbTicket.setItems(ticketObservList);
        birthdayPicker.setPromptText("yyyy-MM-dd");
        if (getEntity() != null){
            instPassenger = (Passengers) getEntity();
            tfLastName.setText(instPassenger.getLastName());
            tfFirstName.setText(instPassenger.getFirstName());
            tfPassport.setText(instPassenger.getPassportNumber());
            tfCountry.setText(instPassenger.getCountry());
            birthdayPicker.setValue(instEditDataService.convertStringToLocalDate(instPassenger.getBirtday()));
            cbFlights.setValue(instPassenger.getFlight_number());
            cbSex.setValue(instPassenger.getSex());
            cbTicket.setValue(instPassenger.getClassTicket());
        }
    }

    @FXML
    public void checkLastName(){isInputCorrect(tfLastName,labelLastName);}
    @FXML
    public void checkFirstName(){isInputCorrect(tfFirstName,labelFirstName);}
    @FXML
    public void checkPassport(){isInputCorrect(tfPassport,labelPassport);}
    @FXML
    public void checkCountry(){isInputCorrect(tfCountry,labelCountry);}
    @FXML
    public void checkBirthday(){isDateCorrect();}

    @FXML
    public void chooseOk(){
        boolean isInsertNewSelected = getOptionStr().equals(instEditDataService.getInsertNewOptionStr());
        boolean isEditSelected = getOptionStr().equals(instEditDataService.getEditOptionStr());
        boolean isDeleteSelected = getOptionStr().equals(instEditDataService.getDeleteOptionStr());
        int flight_id = 0;
        if(cbFlights.getValue() != null) {
            flight_id = new FlightsService().getFlightIdByNumber(cbFlights.getValue().toString());
        }
        Passengers instance = new Passengers();
        instance.setFlight_id(flight_id);
        instance.setPassportNumber(tfPassport.getText());
        boolean isDataExistInDB = instEditDataService.isDataAlreadyExist(instEditDataService.getPassengersTypeStr(),instance);
        if((isInsertNewSelected)&&((isDataExistInDB)||(!isAllFieldsInputCorrect())||(!isFreePlace()))){
            if(isDataExistInDB) labelPassport.setText(passportIsAlreadyRegistered);
            return;
        }
        else if((isInsertNewSelected)&&(!isDataExistInDB)&&(isAllFieldsInputCorrect())&&(isFreePlace())){
            flight_id = new FlightsService().getFlightIdByNumber(cbFlights.getValue().toString());
            if (flight_id > 0) {
                instPassengersService.insertNewService(new Passengers(0, tfLastName.getText(),tfFirstName.getText(),
                        tfPassport.getText(),cbSex.getValue().toString(),birthdayPicker.getValue().toString(),tfCountry.getText(),
                        cbTicket.getValue().toString(),flight_id,cbFlights.getValue().toString()));
            }
        }

        if ((isEditSelected)&&((!isAllFieldsInputCorrect())||(!isFreePlace()))){return;}
        else if((isEditSelected)&&(isAllFieldsInputCorrect()&&(isFreePlace()))){
            boolean isEditedNumber = !instPassenger.getPassportNumber().toLowerCase().equals(tfPassport.getText().toLowerCase());
            if((isEditedNumber)&&(isDataExistInDB)) {
                labelPassport.setText(passportIsAlreadyRegistered);
                return;
            }
            else {
                flight_id = new FlightsService().getFlightIdByNumber(cbFlights.getValue().toString());
                if (flight_id > 0) {
                    instPassengersService.editDataService(new Passengers(getEntity().getId(), tfLastName.getText(),tfFirstName.getText(),
                            tfPassport.getText(),cbSex.getValue().toString(),birthdayPicker.getValue().toString(),tfCountry.getText(),
                            cbTicket.getValue().toString(),flight_id,cbFlights.getValue().toString()));
                }
            }
        }

        if(isDeleteSelected){
            instPassengersService.deleteDataService((Passengers) getEntity());
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

    private boolean isDateCorrect() {
        if (birthdayPicker.getValue() == null){
            labelBirthday.setText(getEnterSomeValue());
            return false;
        }
        else {labelBirthday.setText("");}
        return true;
    }

    private boolean isFreePlace(){
        boolean tickeChoosed = isComboBoxValueChoosed(cbTicket,labelTicket);
        boolean flightChoosed = isComboBoxValueChoosed(cbFlights,labelFlights);
        if ((tickeChoosed)&&(flightChoosed)){
            int flight_id = new FlightsService().getFlightIdByNumber(cbFlights.getValue().toString());
            Passengers passenger = new Passengers();
            passenger.setFlight_id(flight_id);
            passenger.setClassTicket(cbTicket.getValue().toString());
            int freePlaces = instPassengersService.getQuantityFreePlaces(passenger);
            if(freePlaces > 0){
                labelTicket.setText("");
                labelFlights.setText("");
                return true;
            }
            else {
                labelTicket.setText(thereisntFreePlace);
                labelFlights.setText(thereisntFreePlace);
            }
        }
        return false;
    }

    private boolean isAllFieldsInputCorrect(){
        boolean check = true;
        boolean last = isInputCorrect(tfLastName,labelLastName);
        boolean first = isInputCorrect(tfFirstName,labelFirstName);
        boolean passport = isInputCorrect(tfPassport,labelPassport);
        boolean country = isInputCorrect(tfCountry,labelCountry);
        boolean date = isDateCorrect();
        boolean flight = isComboBoxValueChoosed(cbFlights,labelFlights);
        boolean sex = isComboBoxValueChoosed(cbSex,labelSex);
        boolean ticket = isComboBoxValueChoosed(cbTicket,labelTicket);
        boolean freePlace = isFreePlace();
        boolean[] arr = {last,first,passport,country,date,flight,sex,ticket,freePlace};
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

}
