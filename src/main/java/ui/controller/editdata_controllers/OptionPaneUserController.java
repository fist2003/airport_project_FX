package ui.controller.editdata_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import service.UsersService;
import ui.view.OptionPaneGUI;

/**
 * Created by slavik on 24.01.2017.
 */
public class OptionPaneUserController extends OptionPaneGUI {

    public OptionPaneUserController(){}

    private ObservableList<String> typeUsersObservList = FXCollections.observableList(instEditDataService.getTypeUsersList());
    private ObservableList<String> sexObservList = FXCollections.observableList(instEditDataService.getSexList());
    private UsersService instUsersService = new UsersService();

    private final String lengthPassword = "Password must be min 5 characters length!";

    private Users instUsers = new Users();

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
    private ComboBox cbTypeUser;

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
    private Label labelTypeUser;

    @FXML
    public void initialize() {
        cbTypeUser.setItems(typeUsersObservList);
        cbSex.setItems(sexObservList);
        tfEmail.setPromptText("Maile@mail.com");
        if (getEntity() != null){
            instUsers = (Users) getEntity();
            tfLogin.setText(instUsers.getLogin());
            pfPassword.setText(instUsers.getPassword());
            tfEmail.setText(instUsers.getEmail());
            tfLastName.setText(instUsers.getLastName());
            tfFirstName.setText(instUsers.getFirstName());
            cbSex.setValue(instUsers.getSex());
            cbTypeUser.setValue(instUsers.getTypeUser());
        }
    }

    @FXML
    public void checkLogin(){
        isInputCorrect(tfLogin,labelLogin);
    }

    @FXML
    public void checkPassword(){
        isPasswordCorrect(pfPassword,labelPassword);
    }

    @FXML
    public void checkEmail(){
        isEmailCorrect(tfEmail,labelEmail);
    }

    @FXML
    public void checkLastName(){
        isInputCorrect(tfLastName,labelLastName);
    }

    @FXML
    public void checkFirstName(){
        isInputCorrect(tfFirstName,labelFirstName);
    }

    @FXML
    public void chooseOk(){
        boolean isInsertNewSelected = getOptionStr().equals(instEditDataService.getInsertNewOptionStr());
        boolean isEditSelected = getOptionStr().equals(instEditDataService.getEditOptionStr());
        boolean isDeleteSelected = getOptionStr().equals(instEditDataService.getDeleteOptionStr());
        Users instance = new Users();
        instance.setLogin(tfLogin.getText());
        instance.setEmail(tfEmail.getText());
        boolean isDataExistInDB = instEditDataService.isDataAlreadyExist(instEditDataService.getUserssTypeStr(),instance);
        if((isInsertNewSelected)&&((isDataExistInDB)||(!isAllFieldsInputCorrect()))){
            if(isDataExistInDB){ labelLogin.setText(getValueAlreadyExist());labelEmail.setText(getValueAlreadyExist());}
            return;


        }
        else if((isInsertNewSelected)&&(!isDataExistInDB)&&(isAllFieldsInputCorrect())){
                instUsersService.insertNewService(new Users(0, tfLogin.getText(), pfPassword.getText(),tfFirstName.getText(),
                        tfLastName.getText(),cbSex.getValue().toString(), tfEmail.getText(),
                        instUsersService.getIsAdminFromTypeUser(cbTypeUser.getValue().toString())));
        }
        if ((isEditSelected)&&(!isAllFieldsInputCorrect())){return;}
        else if((isEditSelected)&&(isAllFieldsInputCorrect())){
            boolean isEditedLogin = !instUsers.getLogin().toLowerCase().equals(tfLogin.getText().toLowerCase());
            boolean isEditedEmail = !instUsers.getEmail().toLowerCase().equals(tfEmail.getText().toLowerCase());
            if((isEditedLogin)&&(isEditedEmail)&&(isDataExistInDB)) {
                labelEmail.setText(getValueAlreadyExist());
                labelLogin.setText(getValueAlreadyExist());
                return;
            }
            else {
                    instUsersService.editDataService(new Users(getEntity().getId(), tfLogin.getText(), pfPassword.getText(),tfFirstName.getText(),
                            tfLastName.getText(),cbSex.getValue().toString(), tfEmail.getText(),
                            instUsersService.getIsAdminFromTypeUser(cbTypeUser.getValue().toString())));
                }
        }

        if(isDeleteSelected){
            instUsers = (Users)getEntity();
            if(!instUsers.getLogin().toLowerCase().equals(UsersService.getUserLoginedName().toLowerCase())){
                instUsersService.deleteDataService((Users) getEntity());
            }
            else {
                displayErrorDialog("This user is already logined. Before delete you must logout");
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

    public boolean isPasswordCorrect(PasswordField pfPassword,Label label){
        if((pfPassword.getText().length() >= 5) && (isInputCorrect(pfPassword,label))){
            label.setText("");
            return true;
        }
        else if((pfPassword.getText().length() < 5)&&(pfPassword.getText().length() > 0)){
            label.setText(lengthPassword);
            return false;
        }
        else return false;
    }

    public boolean isEmailCorrect(TextField tfEmail,Label labelEmail){
        if(instUsersService.validateEmail(tfEmail.getText())){
            labelEmail.setText("");
            return true;
        }
        else if (tfEmail.getText().length() > 49){
            labelEmail.setText(getInputIsTooLong());
            return false;
        }
        else labelEmail.setText(getInputIsIncorect());
        return false;
    }

    private boolean isAllFieldsInputCorrect(){
        boolean check = true;
        boolean login = isInputCorrect(tfLogin,labelLogin);
        boolean password = isPasswordCorrect(pfPassword,labelPassword);
        boolean email = isEmailCorrect(tfEmail,labelEmail);
        boolean lastName = isInputCorrect(tfLastName,labelLastName);
        boolean firstName = isInputCorrect(tfFirstName,labelFirstName);
        boolean sex = isComboBoxValueChoosed(cbSex,labelSex);
        boolean typeuser = isComboBoxValueChoosed(cbTypeUser,labelTypeUser);
        boolean[] arr = {login,password,email,lastName,firstName,sex,typeuser};
        for (Boolean value:arr){
            if(!value){check = false;}
        }
        return check;
    }

}
