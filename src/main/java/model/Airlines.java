package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ПК on 22.12.2016.
 */
public class Airlines extends Entity{

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty adress;
    private SimpleStringProperty telephone;
    private SimpleStringProperty website;

    public Airlines() {}

    public Airlines(int id, String name, String adress,String telephone, String website) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.adress = new SimpleStringProperty(adress);
        this.telephone = new SimpleStringProperty(telephone);
        this.website = new SimpleStringProperty(website);
    }

    public int getId() {return id.get();}
    public SimpleIntegerProperty idProperty() {return id;}
    public void setId(int id) {this.id = new SimpleIntegerProperty(id);}

    public String getName() {return name.get();}
    public SimpleStringProperty nameProperty() {return name;}
    public void setName(String name) {this.name = new SimpleStringProperty(name);}

    public String getAdress() {return adress.get();}
    public SimpleStringProperty adressProperty() {return adress;}
    public void setAdress(String adress) { this.adress = new SimpleStringProperty(adress);}

    public String getTelephone() {return telephone.get();}
    public SimpleStringProperty telephoneProperty() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = new SimpleStringProperty(telephone);}

    public String getWebsite() {return website.get();}
    public SimpleStringProperty websiteProperty() {return website;}
    public void setWebsite(String website) {this.website = new SimpleStringProperty(website);}
}
