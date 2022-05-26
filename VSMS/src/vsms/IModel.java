package vsms;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IModel {

    public List<Customer> getAllCustomers();

    public List<ServiceBooking> getAllServiceBookings();

    public List<ServiceBooking> getSerivceBookingByVR();

    public int ServiceBooking(int BN, String vehichleType, String VR);

    public int Customer(int ID, String address, String phone, String VR, int BN);

    public void close();

}
