/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

/**
 *
 * @author Lenovo
 */
public class Customer {

    private Integer customerID;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    //Having a single vehicle registration might not work customers can have multiple vehicles
    private String vehicleRegistration;
    private int bookingNumber;

    public Customer(int ID, String firstName, String lastName, String address, String phone) {
        this.customerID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;

    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    //debug function to dump info to terminal
    public void printAll()
    {
        System.out.println("----------Customer------------");
        System.out.println("First:  " + firstName);
        System.out.println("Last:  " + lastName);
        System.out.println("Address:  " + address);
        System.out.println("Phone:  " + phone);
    }


}
