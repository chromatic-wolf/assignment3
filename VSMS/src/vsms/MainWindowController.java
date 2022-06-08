package vsms;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author caleb
 */
public class MainWindowController implements Initializable {

    @FXML
    TextField ui_first_name_field;
    @FXML
    TextField ui_last_name_field;
    @FXML
    TextField ui_address_field;
    @FXML
    TextField ui_phone_field;
    @FXML
    Button ui_search_btn;
    @FXML
    Button ui_add_cust_btn;
    @FXML
    TableView ui_table;
    @FXML
    TableColumn<Customer, Integer> ui_customerid_column;
    @FXML
    TableColumn<Customer, String> ui_firstName_column;
    @FXML
    TableColumn<Customer, String> ui_lastName_column;
    @FXML
    TableColumn<Customer, String> ui_address_column;
    @FXML
    TableColumn<Customer, String> ui_phoneNum_column;

    Connection database;
    ObservableList<Customer> list = FXCollections.observableArrayList();

    public void initData(DataBaseManager database) {
        //Grab pure connection object from database manager
        this.database = database.getConnectionObject();
        //Set table to watch the list of customers
        ui_table.setItems(list);
        list.clear();

        //This section will bind the table columns to the data in the Customer class
        ui_customerid_column.setCellValueFactory(cellData -> cellData.getValue().customerID());
        ui_firstName_column.setCellValueFactory(cellData -> cellData.getValue().firstName());
        ui_lastName_column.setCellValueFactory(cellData -> cellData.getValue().lastName());
        ui_address_column.setCellValueFactory(cellData -> cellData.getValue().address());
        ui_phoneNum_column.setCellValueFactory(cellData -> cellData.getValue().phone());

    }

    ResultSet searchCust(String firstName, String lastName, String address, String phone) throws SQLException {
        String sql = "SELECT * FROM carservicedb.customers WHERE CUSTOMERID LIKE NULL OR FIRSTNAME LIKE ? AND LASTNAME LIKE ? AND ADDRESS LIKE ? AND PHONE LIKE ?;";

        //create statement 
        PreparedStatement searchCustomer = database.prepareStatement(sql);

        //set variables
        searchCustomer.setString(1, firstName + '%');
        searchCustomer.setString(2, lastName + '%');
        searchCustomer.setString(3, address + '%');
        searchCustomer.setString(4, phone + '%');
        //execute and grab result
        return searchCustomer.executeQuery();

    }
    
    ResultSet searchCurrentEnteredCust() throws SQLException
    {
        return searchCust(ui_first_name_field.getText(), ui_last_name_field.getText(), ui_address_field.getText(), ui_phone_field.getText());
    }
   
    void updateCustList(ResultSet rs) throws SQLException {
        //Loop through returned results
        while (rs.next()) {
            //add found customer to list
            list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui_search_btn.setOnAction((ActionEvent e) -> {
            //Call search function/ search logic here

            ResultSet rs;
            try {
                rs = searchCurrentEnteredCust();

                //Clear table
                list.clear();

                //check if list is empty
                if (!rs.isBeforeFirst()) {
                    //display error if no customers found
                    System.out.println("No Customers Found");
                    JOptionPane.showMessageDialog(null, "Error No Customers found please check cust info", "Error: " + "No customers found", JOptionPane.ERROR_MESSAGE);
                } else {
                    updateCustList(rs);

                }
            } catch (SQLException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Debug print all infor on customers
            for (int i = 0; i < list.size(); i++) {
                list.get(i).printAll();
            }

        });

        ui_add_cust_btn.setOnAction((ActionEvent e) -> {
            //Call insert function/ insert logic here
            try {
                ResultSet rs = searchCurrentEnteredCust();
                if (!rs.isBeforeFirst()) {
                    //customer doesnt exist
                    String sql = "INSERT INTO CUSTOMERS (FIRSTNAME,LASTNAME, ADDRESS,PHONE) VALUES (?,?,?,?);";

                    PreparedStatement addCust = database.prepareStatement(sql);
                    addCust.setString(1, ui_first_name_field.getText());
                    addCust.setString(2, ui_lastName_column.getText());
                    addCust.setString(3, ui_address_column.getText());
                    addCust.setString(4, ui_phoneNum_column.getText());
                    addCust.executeQuery();
                }else{
                    //There is a customer with some matching details
                    //Check results and see if all details match if all details match do not allow adding customer
                    //If first and last name match show error but allow 'overide' if some of the other details dont match.
                    //if only first name or last name match (not both) then add customer
                }
                   


            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
    }

}
