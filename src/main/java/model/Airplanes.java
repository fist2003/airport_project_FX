package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ПК on 22.12.2016.
 */
public class Airplanes extends Entity {

    private SimpleIntegerProperty id;
    private SimpleStringProperty manufacturer;
    private SimpleStringProperty model;
    private SimpleStringProperty numberISO;
    private SimpleIntegerProperty year;
    private SimpleIntegerProperty economPlaces;
    private SimpleIntegerProperty businessPlaces;
    private SimpleIntegerProperty airline_id;

    public Airplanes() {}

    public Airplanes(int id, String manufacturer, String model, String numberISO, int year, int economPlaces,
                     int businessPlaces, int airline_id) {
        this.id = new SimpleIntegerProperty(id);
        this.manufacturer = new SimpleStringProperty(manufacturer);
        this.model = new SimpleStringProperty(model);
        this.numberISO =new SimpleStringProperty(numberISO);
        this.year = new SimpleIntegerProperty(year);
        this.economPlaces = new SimpleIntegerProperty(economPlaces);
        this.businessPlaces = new SimpleIntegerProperty(businessPlaces);
        this.airline_id = new SimpleIntegerProperty(airline_id);
    }

    public int getId() {return id.get();}
    public SimpleIntegerProperty idProperty() {return id;}
    public void setId(int id) {this.id = new SimpleIntegerProperty(id);}

    public String getManufacturer() {return manufacturer.get();}
    public SimpleStringProperty manufacturerProperty() {return manufacturer;}
    public void setManufacturer(String manufacturer) {this.manufacturer = new SimpleStringProperty(manufacturer);}

    public String getModel() {return model.get();}
    public SimpleStringProperty modelProperty() {return model;}
    public void setModel(String model) {this.model = new SimpleStringProperty(model);}

    public String getNumberISO() {return numberISO.get();}
    public SimpleStringProperty numberISOProperty() {return numberISO;}
    public void setNumberISO(String numberISO) {this.numberISO =new SimpleStringProperty(numberISO);}

    public int getYear() {return year.get();}
    public SimpleIntegerProperty yearProperty() {return year;}
    public void setYear(int year) {this.year = new SimpleIntegerProperty(year);}

    public int getEconomPlaces() {return economPlaces.get();}
    public SimpleIntegerProperty economPlacesProperty() {return economPlaces;}
    public void setEconomPlaces(int economPlaces) {this.economPlaces = new SimpleIntegerProperty(economPlaces);}

    public int getBusinessPlaces() {return businessPlaces.get();}
    public SimpleIntegerProperty businessPlacesProperty() {return businessPlaces;}
    public void setBusinessPlaces(int businessPlaces) {this.businessPlaces = new SimpleIntegerProperty(businessPlaces);}

    public int getAirline_id() {return airline_id.get();}
    public SimpleIntegerProperty airline_idProperty() {return airline_id;}
    public void setAirline_id(int airline_id) {this.airline_id = new SimpleIntegerProperty(airline_id);}
}
