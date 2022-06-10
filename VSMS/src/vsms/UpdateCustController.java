/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author caleb
 */
public class UpdateCustController implements Initializable {

    Model model;
    Customer oldCust;
    @FXML
    TextField ui_first_name_field;
    @FXML
    TextField ui_last_name_field;
    @FXML
    TextField ui_address_field;
    @FXML
    TextField ui_phone_field;
    @FXML
    Button ui_update_btn;
    @FXML
    Button ui_cancel_btn;

    boolean checkCustBlankFields() {
        if (ui_first_name_field.getText() == null || ui_first_name_field.getText().equals("") || ui_last_name_field.getText() == null || ui_last_name_field.getText().equals("") || ui_address_field.getText() == null || ui_address_field.getText().equals("") || ui_phone_field.getText() == null || ui_phone_field.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public void initData(Customer cust, Model model) {
        ui_first_name_field.setText(cust.getFirstName());
        ui_last_name_field.setText(cust.getLastName());
        ui_address_field.setText(cust.getAddress());
        ui_phone_field.setText(cust.getPhone());
        this.model = model;
        this.oldCust = cust;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ui_cancel_btn.setOnAction((ActionEvent e) -> {
            Stage stage = (Stage) ui_cancel_btn.getScene().getWindow();
            stage.close();
        });

        ui_update_btn.setOnAction((ActionEvent e) -> {
            if (checkCustBlankFields()) {
                JOptionPane.showMessageDialog(null, "Error please fill in all info", "Error: " + "Blank fields", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    model.updateCustomer(oldCust, new Customer(-1, ui_first_name_field.getText(), ui_last_name_field.getText(), ui_address_field.getText(), ui_phone_field.getText()));
                    JOptionPane.showMessageDialog(null, "Customer updated succesfully", "OK" + "Success", JOptionPane.INFORMATION_MESSAGE);
                    model.searchCust(ui_first_name_field.getText(), ui_last_name_field.getText(), ui_address_field.getText(), ui_phone_field.getText());
                    Stage stage = (Stage) ui_update_btn.getScene().getWindow();

                    stage.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateCustController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
