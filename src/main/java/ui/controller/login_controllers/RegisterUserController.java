package ui.controller.login_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import service.EditDataService;
import service.UsersService;
import ui.controller.editdata_controllers.OptionPaneUserController;
import ui.controller.main_controllers.MainPageController;
import ui.view.LoginPaneGUI;
import ui.view.MainPage;


/**
 * Created by slavik on 24.01.2017.
 */
public class RegisterUserController extends LoginPaneGUI{

    public RegisterUserController(){}

    private ObservableList<String> sexObservList = FXCollections.observableList(new EditDataService().getSexList());
    private UsersService instUsersService = new UsersService();
    private final String checkloginStr = "User with this login or email is already registered";

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;

    @FXML
    private ComboBox cbSex;

    @FXML
    private Label labelLogin;
    @FXML
    private Label labelPassword;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelFirstName;
    @FXML
    private Label labelSex;

    @FXML
    public void initialize() {
        cbSex.setItems(sexObservList);
        tfEmail.setPromptText("Maile@mail.com");
    }

    @FXML
    public void checkLogin(){
        new OptionPaneUserController().isInputCorrect(tfLogin,labelLogin);
    }

    @FXML
    public void checkPassword(){
        new OptionPaneUserController().isPasswordCorrect(pfPassword,labelPassword);
    }

    @FXML
    public void checkEmail(){
        new OptionPaneUserController().isEmailCorrect(tfEmail,labelEmail);
    }

    @FXML
    public void checkLastName(){
        new OptionPaneUserController().isInputCorrect(tfLastName,labelLastName);
    }

    @FXML
    public void checkFirstName(){
        new OptionPaneUserController().isInputCorrect(tfFirstName,labelFirstName);
    }

    @FXML
    public void chooseOk(){
        Users instance = new Users();
        instance.setLogin(tfLogin.getText());
        instance.setEmail(tfEmail.getText());
        boolean isDataExistInDB = new EditDataService().isDataAlreadyExist(new EditDataService().getUserssTypeStr(),instance);
        if((isDataExistInDB)||(!isAllFieldsInputCorrect())){
            if(isDataExistInDB){ labelLogin.setText(checkloginStr);labelEmail.setText(checkloginStr);}
            return;


        }
        else if((!isDataExistInDB)&&(isAllFieldsInputCorrect())){
                instUsersService.insertNewService(new Users(0, tfLogin.getText(), pfPassword.getText(),tfFirstName.getText(),
                        tfLastName.getText(),cbSex.getValue().toString(), tfEmail.getText(),0));
            MainPage.setIsLogined(true);
        }
        MainPage.setIsAdmin(false);
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void chooseCancel(){
        MainPage.setIsLogined(false);
        MainPage.setIsAdmin(false);
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private boolean isAllFieldsInputCorrect(){
        boolean check = true;
        boolean login = new OptionPaneUserController().isInputCorrect(tfLogin,labelLogin);
        boolean password = new OptionPaneUserController().isPasswordCorrect(pfPassword,labelPassword);
        boolean email = new OptionPaneUserController().isEmailCorrect(tfEmail,labelEmail);
        boolean lastName = new OptionPaneUserController().isInputCorrect(tfLastName,labelLastName);
        boolean firstName = new OptionPaneUserController().isInputCorrect(tfFirstName,labelFirstName);
        boolean sex = new OptionPaneUserController().isComboBoxValueChoosed(cbSex,labelSex);
        boolean[] arr = {login,password,email,lastName,firstName,sex};
        for (Boolean value:arr){
            if(!value){check = false;}
        }
        return check;
    }

}
