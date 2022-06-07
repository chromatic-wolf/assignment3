package vsms;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
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
    Connection database;

    public void initData(DataBaseManager database)
    {
        this.database = database.getConnectionObject();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //There are 3 main windows which will allow you to basically view the contents of a table in the database.
        // There will be more windows that will allow you to view data linked to other data for example you can click on a customer and view their vehicles or services.
        ui_customer_management_btn.setOnAction((ActionEvent e) -> {
            //open customer management window
        });
        ui_vehicle_management_btn.setOnAction((ActionEvent e) -> {
            //open vehicle management window
        });
        ui_service_management_btn.setOnAction((ActionEvent e) -> {
            //open service management window
        });

    }

}
