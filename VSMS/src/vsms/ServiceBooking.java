package vsms;

/**
 *
 * @author Lenovo
 */
public class ServiceBooking {

    private int bookingNumber;
    private String vehicleType;
    private String vehicleRegistration;

    public ServiceBooking(int BN, String vehichleType, String VR) {
        setBookingNumber(BN);
        setVehicleType(vehicleType);
        setVehicleRegistration(VR);
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

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

}
