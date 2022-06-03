package vsms;

/**
 *
 * @author Lenovo
 */
public class ServiceBooking {

    private int bookingNumber;
    private Vehicle vehicle;

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "ServiceBooking{" +
                "bookingNumber=" + bookingNumber +
                ", vehicle=" + vehicle +
                '}';
    }

    public ServiceBooking(int bookingNumber, Vehicle vehicle) {
        this.bookingNumber = bookingNumber;
        this.vehicle = vehicle;


    }


}
