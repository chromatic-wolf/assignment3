package vsms;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class ServiceBooking {

    private int bookingNumber;
    private String rego;
    private int price;
    private String serviceDescription;
    private Date serviceDate;
    private int vehicleid;

    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }
    

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getRego() {
        return rego;
    }

    public void setRego(String rego) {
        this.rego = rego;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public ServiceBooking(int bookingNumber,  String serviceDescription, Date serviceDate, int price, String rego,int vehicleid) {
        this.bookingNumber = bookingNumber;
        this.rego = rego;
        this.price = price;
        this.serviceDescription = serviceDescription;
        this.serviceDate = serviceDate;
        this.vehicleid = vehicleid;
    }

    @Override
    public String toString() {
        return"Service Number:   " + bookingNumber + ", Rego:   " + rego + ", Price:   " + price + ", Service Description:   " + serviceDescription + ", ServiceDate:   " + serviceDate + ", Vehicle id:   " + vehicleid + '\n';
    } 
    
}
