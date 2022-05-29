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
    private String vehicleRegistration;
    private int bookingNumber;

    public Customer(int ID, String address, String phone, String VR, int BN) {
        setCustomerID(ID);
        setAddress(address);
        setPhone(phone);
        setVehicleRegistration(VR);
        setBookingNumber(BN);
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

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

}
