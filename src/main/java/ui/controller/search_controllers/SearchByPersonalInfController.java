package ui.controller.search_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import service.PassengersSearchService;
import ui.view.SearchPassengersGUI;

/**
 * Created by slavik on 20.01.2017.
 */
public class SearchByPersonalInfController extends SearchPassengersGUI{

    public SearchByPersonalInfController(){}

    private PassengersSearchService instPassengersSearchService = new PassengersSearchService();

    private ObservableList<String> getLastNamesObservList(Object firstNameValue, Object passportValue, Object countryValue){
        return FXCollections.observableList(instPassengersSearchService.getListLastNames(firstNameValue, passportValue, countryValue));
    }

    private ObservableList<String> getFirstNamesObservList(Object lastNameValue, Object passportValue, Object countryValue){
        return FXCollections.observableList(instPassengersSearchService.getListFirstNames(lastNameValue, passportValue, countryValue));
    }

    private ObservableList<String> getPassportObservList(Object lastNameValue, Object firstNameValue, Object countryValue){
        return FXCollections.observableList(instPassengersSearchService.getListPassports(lastNameValue, firstNameValue, countryValue));
    }

    private ObservableList<String> getCountryObservList(Object lastNameValue, Object firstNameValue, Object passportValue){
        return FXCollections.observableList(instPassengersSearchService.getListCountry(lastNameValue, firstNameValue, passportValue));
    }

    @FXML
    private ComboBox cbLastName;

    @FXML
    private ComboBox cbFirstName;

    @FXML
    private ComboBox cbPassport;

    @FXML
    private ComboBox cbCountry;

    @FXML
    public void initialize(){
            cbLastName.setItems(getLastNamesObservList(null, null, null));
            new AutoCompleteComboBoxListener<>(cbLastName);
            cbFirstName.setItems(getFirstNamesObservList(null, null, null));
            new AutoCompleteComboBoxListener<>(cbFirstName);
            cbPassport.setItems(getPassportObservList(null, null, null));
            new AutoCompleteComboBoxListener<>(cbPassport);
            cbCountry.setItems(getCountryObservList(null, null, null));
            new AutoCompleteComboBoxListener<>(cbCountry);
    }

    @FXML
    public void chooseLastName(){
        cbFirstName.setItems(getFirstNamesObservList(cbLastName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
        cbPassport.setItems(getPassportObservList(cbLastName.getValue(),cbFirstName.getValue(),cbCountry.getValue()));
        cbCountry.setItems(getCountryObservList(cbLastName.getValue(),cbFirstName.getValue(),cbPassport.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListOfPassengersSortedByPersonalInf(cbLastName.getValue(),
                cbFirstName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
    }

    @FXML
    public void chooseFirstName(){
        cbLastName.setItems(getLastNamesObservList(cbFirstName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
        cbPassport.setItems(getPassportObservList(cbLastName.getValue(),cbFirstName.getValue(),cbCountry.getValue()));
        cbCountry.setItems(getCountryObservList(cbLastName.getValue(),cbFirstName.getValue(),cbPassport.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListOfPassengersSortedByPersonalInf(cbLastName.getValue(),
                cbFirstName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
    }

    @FXML
    public void choosePassport(){
        cbLastName.setItems(getLastNamesObservList(cbFirstName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
        cbFirstName.setItems(getFirstNamesObservList(cbLastName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
        cbCountry.setItems(getCountryObservList(cbLastName.getValue(),cbFirstName.getValue(),cbPassport.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListOfPassengersSortedByPersonalInf(cbLastName.getValue(),
                cbFirstName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
    }

    @FXML
    public void chooseCountry(){
        cbLastName.setItems(getLastNamesObservList(cbFirstName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
        cbFirstName.setItems(getFirstNamesObservList(cbLastName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
        cbPassport.setItems(getPassportObservList(cbLastName.getValue(),cbFirstName.getValue(),cbCountry.getValue()));
        loadPassengersSearchTable(instPassengersSearchService.makeListOfPassengersSortedByPersonalInf(cbLastName.getValue(),
                cbFirstName.getValue(),cbPassport.getValue(),cbCountry.getValue()));
    }

    @FXML
    public void chooseRefresh(){loadSearchPassengersByPersonalInf();}

    @FXML
    public void showSearchByPersonalInf(){
        loadSearchPassengersByPersonalInf();
    }

    @FXML
    public void showSearchByFlightInf(){
        loadSearchPassengersByFlightInf();
    }
}
