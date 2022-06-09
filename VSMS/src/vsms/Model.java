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

    Model(Connection database)
    {
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
        if (!rs.isBeforeFirst()) {
            //display error if no customers found
            System.out.println("No Customers Found");
            JOptionPane.showMessageDialog(null, "Error No Customers found please check cust info", "Error: " + "No customers found", JOptionPane.ERROR_MESSAGE);
        } else {
            updateCustList(rs);
            return custList;

        }
        return null;
    }

    public ObservableList<Customer> getCustList() {
        return custList;
    }

    public void addCustomer(Customer cust) throws SQLException{
        String sql = "INSERT INTO CUSTOMERS (FIRSTNAME,LASTNAME, ADDRESS,PHONE) VALUES (?,?,?,?);";

        PreparedStatement addCust = database.prepareStatement(sql);
        addCust.setString(1, cust.getFirstName());
        addCust.setString(2, cust.getLastName());
        addCust.setString(3, cust.getAddress());
        addCust.setString(4, cust.getPhone());
        addCust.executeQuery();
    }


}
