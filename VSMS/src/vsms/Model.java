/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author caleb
 */
public class Model implements IModel {

    Connection database;
    private ObservableList<Customer> custList = FXCollections.observableArrayList();
    private ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    Model(Connection database) {
        this.database = database;
    }

    public void setDataBase(Connection database) {
        this.database = database;
    }

    private void updateCustList(ResultSet rs) throws SQLException {
        //Loop through returned results
        custList.clear();
        while (rs.next()) {
            //add found customer to list
            custList.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
    }

    private void updateVehicleList(ResultSet rs) throws SQLException {
        vehicleList.clear();
        while (rs.next()) {
            //add found customer to list
            vehicleList.add(new Vehicle(rs.getInt(1), rs.getInt(7), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
        }
    }

    public ObservableList<Customer> searchCust(String firstName, String lastName, String address, String phone) throws SQLException {
        String sql = "SELECT * FROM carservicedb.customers WHERE CUSTOMERID LIKE NULL OR FIRSTNAME LIKE ? AND LASTNAME LIKE ? AND ADDRESS LIKE ? AND PHONE LIKE ?;";

        //create statement 
        PreparedStatement searchCustomer = database.prepareStatement(sql);

        //set variables
        searchCustomer.setString(1, firstName + '%');
        searchCustomer.setString(2, lastName + '%');
        searchCustomer.setString(3, address + '%');
        searchCustomer.setString(4, phone + '%');
        //execute and grab result
        ResultSet rs = searchCustomer.executeQuery();

        updateCustList(rs);
        return custList;
    }

    public ObservableList<Vehicle> searchVehicle(int customerid, String rego, String make, String model, String manufactureYear, int odometer) throws SQLException {
        String sql = "SELECT * FROM carservicedb.vehicles WHERE VEHICLEID LIKE NULL OR REGISTRATION LIKE ? AND MAKE LIKE ? AND MODEL LIKE ? AND YEAR LIKE ? AND KILOMETERS LIKE ? AND CUSTOMERID LIKE ?;";

        //create statement 
        PreparedStatement searchCustomer = database.prepareStatement(sql);

        //set variables
        searchCustomer.setString(1, rego + '%');
        searchCustomer.setString(2, make + '%');
        searchCustomer.setString(3, model + '%');
        searchCustomer.setString(4, manufactureYear + '%');

        searchCustomer.setInt(5, odometer + '%');

        searchCustomer.setInt(6, customerid + '%');

        //execute and grab result
        ResultSet rs = searchCustomer.executeQuery();

        updateVehicleList(rs);
        return vehicleList;
    }

    public ObservableList<Customer> getCustList() {
        return custList;
    }

    public ObservableList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void addCustomer(Customer cust) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (FIRSTNAME,LASTNAME, ADDRESS,PHONE) VALUES (?,?,?,?);";

        PreparedStatement addCust = database.prepareStatement(sql);
        addCust.setString(1, cust.getFirstName());
        addCust.setString(2, cust.getLastName());
        addCust.setString(3, cust.getAddress());
        addCust.setString(4, cust.getPhone());
        addCust.executeUpdate();
    }

    public int getidbyrego(String Rego) throws SQLException {
        String sql = "SELECT VEHICLEID FROM vehicles WHERE REGISTRATION = (?)";
        ResultSet resultSet = null;
        int id = 0;
        PreparedStatement getid = database.prepareStatement(sql);
        getid.setString(1, Rego);
        resultSet = getid.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt(1);

        }

        return id;
    }

    public void addService(String desc, Date Date, int Price, String Rego, int Vehicleid) throws SQLException {
        String sql = "INSERT INTO SERVICES (DESCRIPTION,DATE, PRICE,REGISTRATION,VEHICLEID) VALUES (?,?,?,?,?);";
        PreparedStatement addSer = database.prepareStatement(sql);
        addSer.setString(1, desc);
        addSer.setDate(2, Date);
        addSer.setInt(3, Price);
        addSer.setString(4, Rego);
        addSer.setInt(5, Vehicleid);

        addSer.executeUpdate();
    }

    public void removeService(int Serviceid) throws SQLException {
        String sql = "DELETE FROM services WHERE SERVICENUMBER = (?);";
        PreparedStatement remSer = database.prepareStatement(sql);
        remSer.setInt(1, Serviceid);

        remSer.executeUpdate();
    }

    public List<ServiceBooking> searchServices(String Rego) throws SQLException {
        String sql = "SELECT * FROM services WHERE REGISTRATION = (?) ";
        PreparedStatement searchservices = database.prepareStatement(sql);
        searchservices.setString(1, Rego);
        ResultSet resultSet = searchservices.executeQuery();
        List<ServiceBooking> results = new ArrayList<ServiceBooking>();
        while (resultSet.next()) { // adds Services into a list
            results.add(new ServiceBooking(
                    resultSet.getInt("SERVICENUMBER"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getDate("DATE"),
                    resultSet.getInt("PRICE"),
                    resultSet.getString("REGISTRATION"),
                    resultSet.getInt("VEHICLEID")
            ));
        }
        resultSet.close();

        return results;
    }

    public ArrayList<String> getstats() throws SQLException {
        ArrayList<String> stats = new ArrayList<String>();
        String sql = "SELECT MIN(PRICE),MAX(PRICE),AVG(PRICE) FROM services";
        PreparedStatement getstats = database.prepareStatement(sql);
        ResultSet results = getstats.executeQuery();

        while (results.next()) {
            stats.add("Min Price of Services $:" + results.getString("MIN(PRICE)"));
            stats.add("     Max Price of Services  $:" + results.getString("MAX(PRICE)"));
            stats.add("     Avg Price of Services  $:" + results.getString("AVG(PRICE)"));

        }
        results.close();

        return stats;
    }

    public ArrayList<String> getmakestats() throws SQLException {
        ArrayList<String> stats = new ArrayList<String>();
        String sql = "SELECT MAKE, COUNT(*) FROM vehicles as V, services as S WHERE S.VEHICLEID = V.VEHICLEID GROUP BY V.MAKE";
        PreparedStatement getmakestats = database.prepareStatement(sql);
        ResultSet results = getmakestats.executeQuery();

        while (results.next()) {
            stats.add("Make: " + results.getString("MAKE")+"\n");
            stats.add("Amount of Brand Serviced: " + results.getString("COUNT(*)")+"\n");
          

        }
        results.close();

        return stats;
    }
    public LinkedList<String> getbarstats() throws SQLException {
        LinkedList<String> barstats = new LinkedList<String>();
        String sql = "SELECT  MAKE, COUNT(*) FROM vehicles as V, services as S WHERE S.VEHICLEID = V.VEHICLEID GROUP BY V.MAKE ORDER BY COUNT(*) desc LIMIT 3";
        PreparedStatement getmakestats = database.prepareStatement(sql);
        ResultSet results = getmakestats.executeQuery();

        while (results.next()) {
            barstats.add(results.getString("MAKE"));
            barstats.add(results.getString("COUNT(*)"));
          

        }
        results.close();

        return barstats;
    }
    public List<ServiceBooking> getAllServices() throws SQLException {
        String sql = "SELECT * FROM services";
        PreparedStatement getservices = database.prepareStatement(sql);
        ResultSet resultSet = getservices.executeQuery();
        List<ServiceBooking> results = new ArrayList<ServiceBooking>();
        while (resultSet.next()) { // adds Services into a list
            results.add(new ServiceBooking(
                    resultSet.getInt("SERVICENUMBER"),
                    resultSet.getString("DESCRIPTION"),
                    resultSet.getDate("DATE"),
                    resultSet.getInt("PRICE"),
                    resultSet.getString("REGISTRATION"),
                    resultSet.getInt("VEHICLEID")
            ));
        }
        resultSet.close();

        return results;
    }
}
