package vsms;

import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
    private SimpleIntegerProperty customerid;
    private SimpleStringProperty rego;
    private SimpleStringProperty make;
    private SimpleStringProperty model;
    private SimpleStringProperty manufactureYear;
    private SimpleIntegerProperty odometer;

    public Vehicle(int customerid, String rego, String make, String model, String manufactureYear, int odometer) {
        this.customerid = new SimpleIntegerProperty(customerid);
        this.rego = new SimpleStringProperty(rego);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.manufactureYear = new SimpleStringProperty(manufactureYear);
        this.odometer = new SimpleIntegerProperty(odometer);
    }

    public int getCustomerid() {
        return customerid.get();
    }

    public void setCustomerid(int customerid) {
        this.customerid = new SimpleIntegerProperty(customerid);
    }

    public String getRego() {
        return rego.get();
    }

    public void setRego(String rego) {
        this.rego = new SimpleStringProperty(rego);
    }

    public String getMake() {
        return make.get();
    }

    public void setMake(String make) {
        this.make = new SimpleStringProperty(make);
    }

    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model = new SimpleStringProperty(model);
    }

    public String getManufactureYear() {
        return manufactureYear.get();
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = new SimpleStringProperty(manufactureYear);
    }

    public int getOdometer() {
        return odometer.get();
    }

    public void setOdometer(int odometer) {
        this.odometer = new SimpleIntegerProperty(odometer);
    }





}
