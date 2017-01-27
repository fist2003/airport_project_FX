package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

/**
 * Created by ПК on 22.12.2016.
 */
public class Flights extends Entity{

    private SimpleIntegerProperty id;
    private SimpleStringProperty number;
    private SimpleStringProperty departPort;
    private SimpleStringProperty destinationPort;
    private SimpleStringProperty dateDepart;
    private SimpleStringProperty dateDestin;
    private SimpleStringProperty timeDepart;
    private SimpleStringProperty timeDestin;
    private SimpleIntegerProperty priceEconom;
    private SimpleIntegerProperty priceBusiness;
    private SimpleIntegerProperty airplane_id;
    private SimpleStringProperty statusOfFlight;
    private SimpleStringProperty gateName;
    private SimpleStringProperty currentTime;
    private SimpleStringProperty airplane_number;

    public Flights() {}

    public Flights(int id, String number, String departPort, String destinationPort, String dateDepart, String dateDestin,
                   String timeDepart, String timeDestin, int priceEconom, int priceBusiness, int airplane_id) {
        this.id = new SimpleIntegerProperty(id);
        this.number = new SimpleStringProperty(number);
        this.departPort = new SimpleStringProperty(departPort);
        this.destinationPort = new SimpleStringProperty(destinationPort);
        this.dateDepart = new SimpleStringProperty(dateDepart);
        this.dateDestin = new SimpleStringProperty(dateDestin);
        this.timeDepart = new SimpleStringProperty(timeDepart);
        this.timeDestin = new SimpleStringProperty(timeDestin);
        this.priceEconom = new SimpleIntegerProperty(priceEconom);
        this.priceBusiness = new SimpleIntegerProperty(priceBusiness);
        this.airplane_id = new SimpleIntegerProperty(airplane_id);
        this.statusOfFlight =   new SimpleStringProperty(null);
        this.currentTime =   new SimpleStringProperty(null);
        this.gateName =   new SimpleStringProperty(null);
    }

    public Flights(int id, String number, String departPort, String destinationPort, String dateDepart, String dateDestin,
                   String timeDepart, String timeDestin, int priceEconom, int priceBusiness, int airplane_id,
                   String statusOfFlight,String gateName, String currentTime) {
        this.id = new SimpleIntegerProperty(id);
        this.number = new SimpleStringProperty(number);
        this.departPort = new SimpleStringProperty(departPort);
        this.destinationPort = new SimpleStringProperty(destinationPort);
        this.dateDepart = new SimpleStringProperty(dateDepart);
        this.dateDestin = new SimpleStringProperty(dateDestin);
        this.timeDepart = new SimpleStringProperty(timeDepart);
        this.timeDestin = new SimpleStringProperty(timeDestin);
        this.priceEconom = new SimpleIntegerProperty(priceEconom);
        this.priceBusiness = new SimpleIntegerProperty(priceBusiness);
        this.airplane_id = new SimpleIntegerProperty(airplane_id);
        this.statusOfFlight =   new SimpleStringProperty(statusOfFlight);
        this.currentTime =   new SimpleStringProperty(currentTime);
        this.gateName =   new SimpleStringProperty(gateName);
    }

    public int getId() {return id.get();}
    public SimpleIntegerProperty idProperty() {return id;}
    public void setId(int id) {this.id = new SimpleIntegerProperty(id);}

    public String getNumber() {return number.get();}
    public SimpleStringProperty numberProperty() {return number;}
    public void setNumber(String number) {this.number = new SimpleStringProperty(number);}

    public String getDepartPort() {return departPort.get();}
    public SimpleStringProperty departPortProperty() {return departPort;}
    public void setDepartPort(String departPort) {this.departPort = new SimpleStringProperty(departPort);}

    public String getDestinationPort() {return destinationPort.get();}
    public SimpleStringProperty destinationPortProperty() {return destinationPort;}
    public void setDestinationPort(String destinationPort) {this.destinationPort = new SimpleStringProperty(destinationPort);}

    public String getDateDepart() {return dateDepart.get();}
    public SimpleStringProperty dateDepartProperty() {return dateDepart;}
    public void setDateDepart(String dateDepart) {this.dateDepart = new SimpleStringProperty(dateDepart);}

    public String getDateDestin() {return dateDestin.get();}
    public SimpleStringProperty dateDestinProperty() {return dateDestin;}
    public void setDateDestin(String dateDestin) {this.dateDestin = new SimpleStringProperty(dateDestin);}

    public String getTimeDepart() {return timeDepart.get();}
    public SimpleStringProperty timeDepartProperty() {return timeDepart;}
    public void setTimeDepart(String timeDepart) {this.timeDepart = new SimpleStringProperty(timeDepart);}

    public String getTimeDestin() {return timeDestin.get();}
    public SimpleStringProperty timeDestinProperty() {return timeDestin;}
    public void setTimeDestin(String timeDestin) {this.timeDestin = new SimpleStringProperty(timeDestin);}

    public int getPriceEconom() {return priceEconom.get();}
    public SimpleIntegerProperty priceEconomProperty() {return priceEconom;}
    public void setPriceEconom(int priceEconom) {this.priceEconom = new SimpleIntegerProperty(priceEconom);}

    public Integer getPriceBusiness() {return priceBusiness.get();}
    public SimpleIntegerProperty priceBusinessProperty() {return priceBusiness;}
    public void setPriceBusiness(int priceBusiness) {this.priceBusiness = new SimpleIntegerProperty(priceBusiness);}

    public int getAirplane_id() {return airplane_id.get();}
    public SimpleIntegerProperty airplane_idProperty() {return airplane_id;}
    public void setAirplane_id(int airplane_id) {this.airplane_id = new SimpleIntegerProperty(airplane_id);}

    public String getStatusOfFlight() {return statusOfFlight.get();}
    public SimpleStringProperty statusOfFlightProperty() {return statusOfFlight;}
    public void setStatusOfFlight(String statusOfFlight) {this.statusOfFlight = new SimpleStringProperty(statusOfFlight);}

    public String getCurrentTime() {return currentTime.get();}
    public SimpleStringProperty currentTimeProperty() {return currentTime;}
    public void setCurrentTime(String currentTime) {this.currentTime =   new SimpleStringProperty(currentTime);}

    public String getGateName() {return gateName.get();}
    public SimpleStringProperty gateNameProperty() {return gateName;}
    public void setGateName(String gateName) {this.gateName =   new SimpleStringProperty(gateName);}

    public String getAirplane_number() {return airplane_number.get();}
    public SimpleStringProperty airplane_numberProperty() {return airplane_number;}
    public void setAirplane_number(String airplane_number) {this.airplane_number = new SimpleStringProperty(airplane_number);}
}
