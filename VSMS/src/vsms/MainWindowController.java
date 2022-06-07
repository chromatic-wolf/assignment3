package vsms;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    Connection database;
        ObservableList<Customer> list = FXCollections.observableArrayList();


    public void initData(DataBaseManager database) {
        this.database = database.getConnectionObject();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui_search_btn.setOnAction((ActionEvent e) -> {
            //Call search function/ search logic here
           
             String sql = "SELECT * FROM purchase.customers WHERE CUSTOMERID LIKE NULL OR FIRSTNAME LIKE ? AND LASTNAME LIKE ? AND PHONE LIKE ?;";
        try {
            PreparedStatement searchCustomer = database.prepareStatement(sql);

            searchCustomer.setString(1, ui_first_name_field.getText() + '%');
            searchCustomer.setString(2, ui_last_name_field.getText() + '%');
            searchCustomer.setString(3, ui_address_field.getText() + '%');
            searchCustomer.setString(4, ui_phone_field.getText() + '%');


            ResultSet rs = searchCustomer.executeQuery();
            int index = 1;
            list.clear();
            while (rs.next()) {
                /*
                if (rs.getString(1).compareToIgnoreCase("Test") == 0) {
                   
                }
                 */
                list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

                index++;

            }
            for (int i = 0; i < list.size(); i++) {
                list.get(i).printAll();
            }
            //ui_table.setItems(list);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
            
            
        });
        
        ui_add_cust_btn.setOnAction((ActionEvent e) -> {
            //Call insert function/ insert logic here
        });
    }

}
