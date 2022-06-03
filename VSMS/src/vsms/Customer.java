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

    private int customerID;
    private String address;
    private String phone;
    //Having a single vehicle registration might not work customers can have multiple vehicles
    private String vehicleRegistration;
    private int bookingNumber;

    public Customer(int ID, String address, String phone, String VR, int BN) {
        this.customerID = ID;
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


}
