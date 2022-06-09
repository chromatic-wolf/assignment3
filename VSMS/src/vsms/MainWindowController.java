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

    Model model;

    public void initData(DataBaseManager database) {
        //Grab pure connection object from database manager
        model = new Model(database.getConnectionObject());
        //Set table to watch the list of customers
        ui_table.setItems(model.getCustList());

        //This section will bind the table columns to the data in the Customer class
        ui_customerid_column.setCellValueFactory(cellData -> cellData.getValue().customerID());
        ui_firstName_column.setCellValueFactory(cellData -> cellData.getValue().firstName());
        ui_lastName_column.setCellValueFactory(cellData -> cellData.getValue().lastName());
        ui_address_column.setCellValueFactory(cellData -> cellData.getValue().address());
        ui_phoneNum_column.setCellValueFactory(cellData -> cellData.getValue().phone());

    }

    void searchCurrentEnteredCust() throws SQLException {
        model.searchCust(ui_first_name_field.getText(), ui_last_name_field.getText(), ui_address_field.getText(), ui_phone_field.getText());
    }

    boolean checkBlankFields() {
        if (ui_first_name_field.getText() == null || ui_first_name_field.getText().equals("") || ui_last_name_field.getText() == null || ui_last_name_field.getText().equals("") || ui_address_field.getText() == null || ui_address_field.getText().equals("") || ui_phone_field.getText() == null || ui_phone_field.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui_search_btn.setOnAction((ActionEvent e) -> {
            try {
                //Call search function/ search logic here

                searchCurrentEnteredCust();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error sql error", "Error: " + "SQL error", JOptionPane.ERROR_MESSAGE);
            }

            //Clear table
            //check if list is empty
            if (model.getCustList().isEmpty()) {
                //display error if no customers found
                System.out.println("No Customers Found");
                JOptionPane.showMessageDialog(null, "Error No Customers found please check cust info", "Error: " + "No customers found", JOptionPane.ERROR_MESSAGE);
            }

            //Debug print all infor on customers
            for (int i = 0; i < model.getCustList().size(); i++) {
                model.getCustList().get(i).printAll();
            }

        });

        ui_add_cust_btn.setOnAction((ActionEvent e) -> {
            //Call insert function/ insert logic here
            //first check if all info is entered

            if (checkBlankFields()) {
                JOptionPane.showMessageDialog(null, "Error please enter all fields", "Error: " + "Blank fields", JOptionPane.ERROR_MESSAGE);
            } else {
                try {

                    searchCurrentEnteredCust();
                    //Create a dummy customer using info entered

                    Customer currentCust = new Customer(0, ui_first_name_field.getText(), ui_last_name_field.getText(), ui_address_field.getText(), ui_phone_field.getText());

                    ObservableList<Customer> list = model.getCustList();
                    if (model.getCustList().isEmpty()) {
                        //customer doesnt exist
                        model.addCustomer(currentCust);
                        JOptionPane.showMessageDialog(null, "Added customer", "Added: " + "OK", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        //There is a customer with some matching details
                        //Check results and see if all details match if all details match do not allow adding customer
                        //If first and last name match show error but allow 'overide' if some of the other details dont match.
                        //if only first name or last name match (not both) then add customer

                        int i = 0;
                        while (i < list.size()) {
                            if (list.get(i).compare(currentCust)) {
                                JOptionPane.showMessageDialog(null, "Error Customer already exists, see table of custs.", "Error: " + "Duplicate cust", JOptionPane.ERROR_MESSAGE);

                            } else if (list.get(i).getCustomerID() == currentCust.getCustomerID()) {
                                JOptionPane.showMessageDialog(null, "Error Customer ID already exists", "Error: " + "Duplicate cust", JOptionPane.ERROR_MESSAGE);

                            } else if (currentCust.getFirstName().equals(list.get(i).getFirstName()) && currentCust.getLastName().equals(list.get(i).getLastName())) {
                                //First and last name exists display message box asking if customer still wants to add (overide)
                            }
                            i++;
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        });
    }
}
