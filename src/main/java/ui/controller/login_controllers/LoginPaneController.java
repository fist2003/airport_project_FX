package ui.controller.login_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;
import service.UsersService;
import ui.view.LoginPaneGUI;
import ui.view.MainPage;

/**
 * Created by slavik on 26.01.2017.
 */
public class LoginPaneController extends LoginPaneGUI {
    public LoginPaneController(){}

    private final String checkInputStr = "You entered wrong login or password ";

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelPassword;

    @FXML
    public void initialize(){}

    @FXML
    public void chooseLoginBtn(){
        Users user = new Users();
        user.setLogin(tfLogin.getText());
        user.setPassword(pfPassword.getText());
        Users checkedUser = new UsersService().checkInputUser(user);
        if (checkedUser != null){
            UsersService.setUserLoginedName(checkedUser.getLogin());
            MainPage.setIsLogined(true);
            switch (checkedUser.getIsAdmin()){
                case 1:MainPage.setIsAdmin(true);
                break;
                default:MainPage.setIsAdmin(false);
                break;
            }
            Stage stage = (Stage) tfLogin.getScene().getWindow();
            stage.close();
        }
        else {
            labelLogin.setText(checkInputStr);
            MainPage.setIsLogined(false);
            MainPage.setIsAdmin(false);
        }
    }

    @FXML
    public void chooseregisterBtn(){
        loadRegisterPane(tfLogin);
    }
}
