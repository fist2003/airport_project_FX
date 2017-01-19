package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ПК on 22.12.2016.
 */
public class Passengers extends Entity {

    private SimpleIntegerProperty id;
    private SimpleStringProperty lastName;
    private SimpleStringProperty firstName;
    private SimpleStringProperty passportNumber;
    private SimpleStringProperty sex;
    private SimpleStringProperty birtday;
    private SimpleStringProperty country;
    private SimpleStringProperty classTicket;
    private SimpleIntegerProperty flight_id;
    private SimpleStringProperty flight_number;

    public Passengers() {}

    public Passengers(int id, String lastName, String firstName, String passportNumber, String sex, String birtday,
                      String country, String classTicket, int flight_id,String flight_number) {
        this.id = new SimpleIntegerProperty(id);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.passportNumber = new SimpleStringProperty(passportNumber);
        this.sex = new SimpleStringProperty(sex);
        this.birtday = new SimpleStringProperty(birtday);
        this.country = new SimpleStringProperty(country);
        this.classTicket = new SimpleStringProperty(classTicket);
        this.flight_id = new SimpleIntegerProperty(flight_id);
        this.flight_number = new SimpleStringProperty(flight_number);
    }

    @Override
    public int getId() {return id.get();}
    public SimpleIntegerProperty idProperty() {return id;}
    public void setId(int id) {this.id = new SimpleIntegerProperty(id);}

    public String getLastName() {return lastName.get();}
    public SimpleStringProperty lastNameProperty() {return lastName;}
    public void setLastName(String lastName) {this.lastName = new SimpleStringProperty(lastName);}

    public String getFirstName() {return firstName.get();}
    public SimpleStringProperty firstNameProperty() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = new SimpleStringProperty(firstName);}

    public String getPassportNumber() {return passportNumber.get();}
    public SimpleStringProperty passportNumberProperty() {return passportNumber;}
    public void setPassportNumber(String passportNumber) {this.passportNumber = new SimpleStringProperty(passportNumber);}

    public String getSex() {return sex.get();}
    public SimpleStringProperty sexProperty() {return sex;}
    public void setSex(String sex) {this.sex = new SimpleStringProperty(sex);}

    public String getBirtday() {return birtday.get();}
    public SimpleStringProperty birtdayProperty() {return birtday;}
    public void setBirtday(String birtday) {this.birtday = new SimpleStringProperty(birtday);}

    public String getCountry() {return country.get();}
    public SimpleStringProperty countryProperty() {return country;}
    public void setCountry(String country) {this.country = new SimpleStringProperty(country);}

    public String getClassTicket() {return classTicket.get();}
    public SimpleStringProperty classTicketProperty() {return classTicket;}
    public void setClassTicket(String classTicket) {this.classTicket = new SimpleStringProperty(classTicket);}

    public int getFlight_id() {return flight_id.get();}
    public SimpleIntegerProperty flight_idProperty() {return flight_id;}
    public void setFlight_id(int flight_id) {this.flight_id = new SimpleIntegerProperty(flight_id);}

    public String getFlight_number() {return flight_number.get();}
    public SimpleStringProperty flight_numberProperty() {return flight_number;}
    public void setFlight_number(String flight_number) {this.flight_number = new SimpleStringProperty(flight_number);}
}
