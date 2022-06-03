package vsms;

import java.sql.Date;

public class Vehicle {

    String rego;
    String make;
    String model;
    Date manufactureYear;
    int odometer;


    public Vehicle(String rego, String make, String model, Date manufactureYear, int odometer) {
        this.rego = rego;
        this.make = make;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.odometer = odometer;
    }

    public String getRego() {
        return rego;
    }

    public void setRego(String rego) {
        this.rego = rego;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Date manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }



}