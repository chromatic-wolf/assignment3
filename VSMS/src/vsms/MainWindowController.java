package vsms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author caleb
 */
public class MainWindowController implements Initializable {

    @FXML
    Button ui_customer_management_btn;
    @FXML
    Button ui_vehicle_management_btn;
    @FXML
    Button ui_service_management_btn;

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
