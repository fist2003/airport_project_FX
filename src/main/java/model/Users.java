package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ПК on 22.12.2016.
 */
public class Users extends Entity {

    private SimpleIntegerProperty id;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty sex;
    private SimpleStringProperty email;
    private SimpleIntegerProperty isAdmin;

    public Users() {}

    public Users(int id, String login, String password, String firstName, String lastName, String sex, String email, int isAdmin) {
        this.id = new SimpleIntegerProperty(id);
        this.login = new SimpleStringProperty(login);
        this.password =  new SimpleStringProperty(password);
        this.firstName =  new SimpleStringProperty(firstName);
        this.lastName =  new SimpleStringProperty(lastName);
        this.sex =  new SimpleStringProperty(sex);
        this.email =  new SimpleStringProperty(email);
        this.isAdmin = new SimpleIntegerProperty(isAdmin);
    }

    public int getId() {return id.get();}
    public SimpleIntegerProperty idProperty() {return id;}
    public void setId(int id) {this.id = new SimpleIntegerProperty(id);}

    public String getLogin() {return login.get();}
    public SimpleStringProperty loginProperty() {return login;}
    public void setLogin(String login) {this.login = new SimpleStringProperty(login);}

    public String getPassword() {return password.get();}
    public SimpleStringProperty passwordProperty() {return password;}
    public void setPassword(String password) {this.password =  new SimpleStringProperty(password);}

    public String getFirstName() {return firstName.get();}
    public SimpleStringProperty firstNameProperty() {return firstName;}
    public void setFirstName(String firstName) {this.firstName =  new SimpleStringProperty(firstName);}

    public String getLastName() {return lastName.get();}
    public SimpleStringProperty lastNameProperty() {return lastName;}
    public void setLastName(String lastName) {this.lastName =  new SimpleStringProperty(lastName);}

    public String getSex() {return sex.get();}
    public SimpleStringProperty sexProperty() {return sex;}
    public void setSex(String sex) {this.sex =  new SimpleStringProperty(sex);}

    public String getEmail() {return email.get();}
    public SimpleStringProperty emailProperty() {return email;}
    public void setEmail(String email) {this.email =  new SimpleStringProperty(email);}

    public int getIsAdmin() {return isAdmin.get();}
    public SimpleIntegerProperty isAdminProperty() {return isAdmin;}
    public void setIsAdmin(int isAdmin) {this.isAdmin = new SimpleIntegerProperty(isAdmin);}
}
