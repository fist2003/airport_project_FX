package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Airplanes;
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
        tfTimeDepart.setPromptText("hh-mm-ss");
        tfTimeArrive.setPromptText("hh-mm-ss");
        tfCurrentTime.setPromptText("hh-mm-ss");
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
    public void checkDateDepart(){
      //  isDepartDateCorrect();
    }
    @FXML
    public void checkDateArrive(){

    }
    @FXML
    public void checkTime(){

    }
    @FXML
    public void checkPrice(){

    }

    @FXML
    public void chooseOk(){
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
//don`t work
/*    private boolean isDepartDateCorrect() {
        boolean flag = false;
        if (!instEditDataService.checkInputDate(dateDepart.getValue().toString())) {
                System.out.println(dateDepart.getValue().toString());
                System.out.println(instEditDataService.checkInputDate(dateDepart.getValue().toString()));
                labelDateDepart.setText("Input date format is wrong");
                flag = false;
            } else {
                labelDateDepart.setText("");
                flag = true;
            }
        if((dateArrive.getValue() != null)&&(dateDepart.getValue() != null)) {
            if (dateDepart.getValue().isAfter(dateArrive.getValue())) {
                labelDateDepart.setText("Date of depart can`t be after date of arrive");
                flag = false;
            }
        }
        return flag;
    }
*/
}
