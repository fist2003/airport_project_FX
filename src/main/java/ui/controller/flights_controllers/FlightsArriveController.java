package ui.controller.flights_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import service.FlightsArrivalService;
import ui.view.FlightsGUI;

import java.time.LocalDate;

/**
 * Created by ПК on 22.12.2016.
 */

public class FlightsArriveController extends FlightsGUI {

    public FlightsArriveController(){}

    private ObservableList<LocalDate> cbDateArriveObservList = FXCollections.observableList(new FlightsArrivalService().getListOfDatesForComboBox());

    @FXML
    private Slider sliderArrival;

    @FXML
    protected ComboBox cbDateArrival;

    @FXML
    public void initialize(){
        for(int i = 0; i < cbDateArriveObservList.size(); i++){
            if(cbDateArriveObservList.get(i).equals(getShoowingDate())){
                cbDateArrival.setValue(cbDateArriveObservList.get(i));
            }
        }
        cbDateArrival.setItems(cbDateArriveObservList);
    }

    @FXML
    public void cbDateArrivalController(){
        int timeFrame = (int) Math.round(sliderArrival.getValue());
        loadArrivalTable( timeFrame , (LocalDate) cbDateArrival.getValue());
    }

    @FXML
    public void sliderArrivalController(){
        int timeFrame = (int) Math.round(sliderArrival.getValue());
        loadArrivalTable( timeFrame ,getShoowingDate());
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
