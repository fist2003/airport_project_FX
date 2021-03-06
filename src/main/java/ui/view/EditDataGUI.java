package ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import service.EditDataService;
import ui.model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by slavik on 21.01.2017.
 */
public class EditDataGUI extends MainPage {

    public EditDataGUI(){
        this.instEditDataService = new EditDataService();
        mapPaneFXML.put(instEditDataService.getAirlinesTypeStr(),editDataAirlinePaneUrl);
        mapPaneFXML.put(instEditDataService.getAirplanesTypeStr(),editDataAirplanePaneUrl);
        mapPaneFXML.put(instEditDataService.getFlightsTypeStr(),editDataFlightsPaneUrl);
        mapPaneFXML.put(instEditDataService.getPassengersTypeStr(),editDataPassengersPaneUrl);
        mapPaneFXML.put(instEditDataService.getUserssTypeStr(),editDataUsersPaneUrl);

        mapTableModel.put(instEditDataService.getAirlinesTypeStr(),new AirlinesTableModel());
        mapTableModel.put(instEditDataService.getAirplanesTypeStr(),new AirplanesTableModel());
        mapTableModel.put(instEditDataService.getFlightsTypeStr(),new FlightsTableModel());
        mapTableModel.put(instEditDataService.getPassengersTypeStr(),new EditPassengersTableModel());
        mapTableModel.put(instEditDataService.getUserssTypeStr(),new UsersTableModel());

        mapModulePaneFXML.put(instEditDataService.getAirlinesTypeStr(),airlineModuleUrl);
        mapModulePaneFXML.put(instEditDataService.getAirplanesTypeStr(),airplaneModuleUrl);
        mapModulePaneFXML.put(instEditDataService.getFlightsTypeStr(),flightModuleUrl);
        mapModulePaneFXML.put(instEditDataService.getPassengersTypeStr(),passengerModuleUrl);
        mapModulePaneFXML.put(instEditDataService.getUserssTypeStr(),userModuleUrl);
    }

    protected EditDataService instEditDataService;

    private Map<String,String> mapPaneFXML = new HashMap<>();
    private Map<String,TableModelInterface> mapTableModel = new HashMap<>();
    private Map<String,String> mapModulePaneFXML = new HashMap<>();

    protected Map<String, String> getMapPaneFXML() {return mapPaneFXML;}
    protected Map<String, TableModelInterface> getMapTableModel() {return mapTableModel;}
    public Map<String, String> getMapModulePaneFXML() {return mapModulePaneFXML;}

    protected final String editDataAirlinePaneUrl = "/fxml/edit/editDataAirline.fxml";
    protected final String airlineModuleUrl = "/fxml/edit/moduleAirline.fxml";

    protected final String editDataAirplanePaneUrl = "/fxml/edit/editDataAirplane.fxml";
    protected final String airplaneModuleUrl = "/fxml/edit/moduleAirplane.fxml";

    protected final String editDataFlightsPaneUrl = "/fxml/edit/editDataFlights.fxml";
    protected final String flightModuleUrl = "/fxml/edit/moduleFlight.fxml";

    protected final String editDataPassengersPaneUrl = "/fxml/edit/editDataPassenger.fxml";
    protected final String passengerModuleUrl = "/fxml/edit/modulePassengers.fxml";

    protected final String editDataUsersPaneUrl = "/fxml/edit/editDataUser.fxml";
    protected final String userModuleUrl = "/fxml/edit/moduleUser.fxml";

    public VBox getEastPane() {
        loadEditMenu();
        return eastPane;
    }

    protected BorderPane tablePane;

    protected void loadEditMenu(){
        if (eastPane != null){eastPane.getChildren().clear();}
        String eastPaneFxmlFile = "/fxml/edit/editDataEastPane.fxml";
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(eastPaneFxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = getEmptyTablePane(tablePane);
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        if (eastPane != null){
            eastPane.getChildren().add(pane);
            eastPane.setVgrow(pane, Priority.ALWAYS);}
        else eastPane = pane;
    }

    public void loadEditDataPane(String key) {
        if (eastPane != null){eastPane.getChildren().clear();}
        FXMLLoader loaderEastPane = new FXMLLoader();
        VBox pane = new VBox();
        try {
            pane = (VBox) loaderEastPane.load(getClass().getResourceAsStream(mapPaneFXML.get(key)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tablePane = mapTableModel.get(key).getTablePane();
        pane.getChildren().remove(1);
        pane.getChildren().add(1,tablePane);
        pane.setVgrow(tablePane, Priority.ALWAYS);
        if (eastPane != null){
            eastPane.getChildren().add(pane);
            eastPane.setVgrow(pane, Priority.ALWAYS);}
        else eastPane = pane;
    }

}
