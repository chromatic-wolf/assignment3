package vsms;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IModel {

    public List<Customer> getAllCustomers();
    public List<Customer> getCustomersByAddress(String address);
    public List<Customer> getCustomersByPhone(String phone);
    public List<Vehicle> getAllVehicles();

    public void addVehicle();
    public void updateVehicle();
    public void deleteVehicle();

    public void addCustomer();
    public void updateCustomer();
    public void deleteCustomer();

    public void addServiceBooking();
    public void updateServiceBooking();
    public void deleteServiceBooking();


    public List<ServiceBooking> getAllServiceBookings();
    public List<ServiceBooking> getServiceBookingByVR();


    public void close();

}
