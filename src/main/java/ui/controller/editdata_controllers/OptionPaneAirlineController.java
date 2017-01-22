package ui.controller.editdata_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
           Airlines inst = (Airlines)getEntity();
           tfName.setText(inst.getName());
           tfAdress.setText(inst.getAdress());
           tfTelephone.setText(inst.getTelephone());
           tfWebsite.setText(inst.getWebsite());
       }
    }

    @FXML
    public void chooseOk(){
        if(getOptionStr().equals(instEditDataService.getInsertNewOptionStr())){
           instAirlineService.insertNewService(new Airlines(0,tfName.getText(),tfAdress.getText(),tfTelephone.getText(),tfWebsite.getText()));
        }
        else if(getOptionStr().equals(instEditDataService.getEditOptionStr())){
            instAirlineService.editDataService(new Airlines(getEntity().getId(),tfName.getText(),tfAdress.getText(),tfTelephone.getText(),tfWebsite.getText()));
        }
        else if(getOptionStr().equals(instEditDataService.getDeleteOptionStr())){
            instAirlineService.deleteDataService((Airlines)getEntity());
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



}
