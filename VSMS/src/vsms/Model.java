/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

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
    
        public ObservableList<Vehicle> searchVehicle(int customerid, String rego, String make, String model, String manufactureYear, int odometer) throws SQLException
        {
            String sql = "SELECT * FROM carservicedb.vehicles WHERE VEHICLEID LIKE NULL OR REGISTRATION LIKE ? AND MAKE LIKE ? AND MODEL LIKE ? AND YEAR LIKE ? AND KILOMETERS LIKE ? AND CUSTOMERID LIKE ?;";

        //create statement 
        PreparedStatement searchCustomer = database.prepareStatement(sql);

        //set variables
        searchCustomer.setString(1, rego + '%');
        searchCustomer.setString(2, make + '%');
        searchCustomer.setString(3, model + '%');
        searchCustomer.setString(4, manufactureYear + '%');               
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

}
