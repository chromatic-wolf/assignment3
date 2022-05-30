/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author caleb
 */
public class DataBaseLoginController implements Initializable {

    //This binds the java code to the ui and initalises the UI components
    @FXML
    Button ui_connect_btn;
    @FXML
    TextField ui_address_txt;
    @FXML
    TextField ui_password_txt;

    //This runs when the window first opens so putting any event listeners here
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connect button listener
        ui_connect_btn.setOnAction((ActionEvent e) -> {
            //code to run goes here
            //Creates a database connection object
            DataBaseManager database = new DataBaseManager();
            //Attempts to connect to the database, using default username for now maybe add ui component to change username later.
            if (database.connect(ui_address_txt.getText(), "root", ui_password_txt.getText(), "purchase")) {
                //Database connect
                //Open next window
            } else {
                //failed to connect
                //display error message
                JOptionPane.showMessageDialog(null, "Error cannot connect", "InfoBox: " + "Error", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

}
