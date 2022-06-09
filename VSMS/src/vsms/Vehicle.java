package vsms;

import java.sql.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
    private SimpleIntegerProperty vehicleID;
    private SimpleIntegerProperty customerid;
    private SimpleStringProperty rego;
    private SimpleStringProperty make;
    private SimpleStringProperty model;
    private SimpleStringProperty manufactureYear;
    private SimpleIntegerProperty odometer;

    public Vehicle(int vehicleID, int customerid, String rego, String make, String model, String manufactureYear, int odometer) {
        this.vehicleID = new SimpleIntegerProperty(vehicleID);
        this.customerid = new SimpleIntegerProperty(customerid);
        this.rego = new SimpleStringProperty(rego);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.manufactureYear = new SimpleStringProperty(manufactureYear);
        this.odometer = new SimpleIntegerProperty(odometer);
    }

    public int getVehicleID()
    {
        return vehicleID.get();
    }
    
    public void setVehicleID(int id)
    {
        this.vehicleID = new SimpleIntegerProperty(id);
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

public void printAll()
{
    System.out.println("----------Vehicle------------");
        System.out.println("vehicleID:  " + vehicleID);
        System.out.println("customerid:  " + customerid);
        System.out.println("rego:  " + rego);
        System.out.println("make:  " + make);
        System.out.println("model:  " + model);
        System.out.println("manufactureYear:  " + manufactureYear);
        System.out.println("odometer:  " + odometer);
}



}
