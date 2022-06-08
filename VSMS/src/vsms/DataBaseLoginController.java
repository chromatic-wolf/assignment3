/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author caleb
 */
public class DataBaseLoginController implements Initializable {

    //This binds the java code to the ui and initalises the UI components
    //Make sure the names of the variablees here match exactly to the fx:id in the fxml file
    @FXML
    Button ui_connect_btn;
    @FXML
    TextField ui_address_txt;
    @FXML
    TextField ui_password_txt;
    @FXML
    TextField ui_username_txt;

    //This runs when the window first opens so putting any event listeners here
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connect button listener
        ui_connect_btn.setOnAction((ActionEvent e) -> {
            //code to run goes here
            //Creates a database connection object
            DataBaseManager database = new DataBaseManager();
            //Attempts to connect to the database, using default username for now maybe add ui component to change username later.
            if (database.connect(ui_address_txt.getText(), ui_username_txt.getText(), ui_password_txt.getText(), "carservicedb")) {
                //Database connect
                //Open next window
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource(
                                    "MainWindow.fxml"
                            )
                    );

                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setScene(
                            new Scene(loader.load())
                    );

                    MainWindowController controller = loader.getController();
                    controller.initData(database);
                    stage.show();

                    Stage rootStage = (Stage) ui_username_txt.getScene().getWindow();
                    // do what you have to do
                    rootStage.close();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "FATAL Error, please check your java install", "InfoBox: " + "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println(ex);
                }
            } else {
                //failed to connect
                //display error message
                JOptionPane.showMessageDialog(null, "Error cannot connect", "InfoBox: " + "Error", JOptionPane.ERROR_MESSAGE);

            }
        });
    }

}
