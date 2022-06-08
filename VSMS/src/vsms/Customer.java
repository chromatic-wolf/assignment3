/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lenovo
 */
public class Customer {

    private SimpleIntegerProperty customerID;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty address;
    private SimpleStringProperty phone;
    //Having a single vehicle registration might not work customers can have multiple vehicles
    private String vehicleRegistration;
    private int bookingNumber;

    public Customer(int ID, String firstName, String lastName, String address, String phone) {
        this.customerID = new SimpleIntegerProperty(ID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);

    }

    public int getCustomerID() {
        return customerID.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public void setCustomerID(int customerID) {
        this.customerID = new SimpleIntegerProperty(customerID);
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);

    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public void setPhone(String phone) {
        this.phone = new SimpleStringProperty(phone);
    }

     public final ObjectProperty<Integer> customerID() {
        return new SimpleObjectProperty<>(this.customerID.get());
    }

    public final ObjectProperty<String> firstName() {
        return new SimpleObjectProperty<>(this.firstName.get());
    }

    public final ObjectProperty<String> lastName() {
        return new SimpleObjectProperty<>(this.lastName.get());
    }

    public final ObjectProperty<String> address() {
        return new SimpleObjectProperty<>(this.address.get());
    }

    public final ObjectProperty<String> phone() {
        return new SimpleObjectProperty<>(this.phone.get());
    }


    //debug function to dump info to terminal
    public void printAll() {
        System.out.println("----------Customer------------");
        System.out.println("First:  " + firstName);
        System.out.println("Last:  " + lastName);
        System.out.println("Address:  " + address);
        System.out.println("Phone:  " + phone);
    }

}
