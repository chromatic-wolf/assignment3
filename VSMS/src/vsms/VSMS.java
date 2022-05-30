package vsms;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author caleb
 */

//This class just launches the login page it serves no other purpose 
public class VSMS extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("DataBaseLogin.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Database Login");
            primaryStage.show();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "FATAL Error, please check your java install", "InfoBox: " + "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
