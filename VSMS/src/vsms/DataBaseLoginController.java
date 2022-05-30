/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author caleb
 */
public class DataBaseLoginController implements Initializable {

    //This binds the java code to the ui and initalises the UI components
    @FXML
    Button ui_connect_btn;
    TextField ui_address_txt;
    TextField ui_password_txt;
    
    //This runs when the window first opens so putting any event listeners here
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
