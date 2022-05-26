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
public class ServiceBooking {
    
    private int bookingNumber;
    private String vehicleType;
    private String registration;
    
    public ServiceBooking( int BN, String vehichleType, String registration )
    {
        setBookingNumber( BN );
        setVehicleType( vehicleType );
        setRegistration( registration );
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
    
    
}
