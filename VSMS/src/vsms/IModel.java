package vsms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public interface IModel {

    public void setDataBase(Connection database);

    public int getidbyrego(String Rego) throws SQLException;

    public ObservableList<Customer> searchCust(String firstName, String lastName, String address, String phone) throws SQLException;

    public ObservableList<Vehicle> searchVehicle(int customerid, String rego, String make, String model, String manufactureYear, int odometer) throws SQLException;

    public ObservableList<Vehicle> searchVehicle(int customerID) throws SQLException;

    public void addVehicle(Vehicle vehicle) throws SQLException;

    public ObservableList<Customer> getCustList();

    public ObservableList<Vehicle> getVehicleList();

    public void addCustomer(Customer cust) throws SQLException;

}
