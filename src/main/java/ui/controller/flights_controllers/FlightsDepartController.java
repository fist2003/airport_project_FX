package ui.controller.flights_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import service.FlightsDepartService;
import ui.view.FlightsGUI;

import java.time.LocalDate;

/**
 * Created by slavik on 11.01.2017.
 */
public class FlightsDepartController extends FlightsGUI {

    public FlightsDepartController(){}

    private ObservableList<LocalDate> cbDateDepartObservList = FXCollections.observableList(new FlightsDepartService().getListOfDatesForComboBox());

    @FXML
    private Slider sliderDepart;

    @FXML
    protected ComboBox cbDateDepart;

    @FXML
    public void initialize(){
        for(int i = 0; i < cbDateDepartObservList.size(); i++){
            if(cbDateDepartObservList.get(i).equals(getShoowingDate())){
                cbDateDepart.setValue(cbDateDepartObservList.get(i));
            }
        }
        cbDateDepart.setItems(cbDateDepartObservList);
    }

    @FXML
    public void cbDateDepartController(){
        int timeFrame = (int) Math.round(sliderDepart.getValue());
        loadDepartureTable( timeFrame , (LocalDate) cbDateDepart.getValue());
    }

    @FXML
    public void sliderDepartController(){
        int timeFrame = (int) Math.round(sliderDepart.getValue());
        loadDepartureTable( timeFrame ,getShoowingDate());
    }

    @FXML
    public void showArrivals(){
        loadArrivalEastPane();
    }

    @FXML
    public void showDepartures(){
        loadDepartureEastPane();
    }

    @FXML
    public void showSearch(){
        loadSearchEastPane();
    }

}
