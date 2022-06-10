package vsms;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    TableView<Customer> ui_cust_table;
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

    @FXML
    TextField ui_registration_field;
    @FXML
    TextField ui_make_field;
    @FXML
    TextField ui_model_field;
    @FXML
    TextField ui_yeah_field;
    @FXML
    TextField ui_kilometers_field;
    @FXML
    TableView<Vehicle> ui_vehicle_table;

    @FXML
    TableColumn<Vehicle, String> ui_registration_column;
    @FXML
    TableColumn<Vehicle, String> ui_make_column;
    @FXML
    TableColumn<Vehicle, String> ui_model_column;
    @FXML
    TableColumn<Vehicle, String> ui_year_column;
    @FXML
    TableColumn<Vehicle, Integer> ui_kilometers_column;

    @FXML
    Button ui_search_vehicle_btn;
    @FXML
    Button ui_add_vehicle_btn;
    @FXML
    Button ui_view_selected_cust_vehicles_btn;

    Model model;
    @FXML
    private Button btnaddService;
    @FXML
    private Button btnsearchservice;
    @FXML
    private Button btndeleteservice;
    @FXML
    private TextField txtrego;
    @FXML
    private DatePicker txtservicedate;
    @FXML
    private TextField txtprice;
    @FXML
    private TextArea txtdescription;
    @FXML
    private Button btndisplayall;
    @FXML
    private Button btnstatistics;
    @FXML
    private Button btnclear;
    @FXML
    private TextArea txtinfofield;
    @FXML
    private BarChart<String, Number> barchart;
    @FXML
    private TextField txtdeleteservice;
    @FXML
    private TextField txtsearch;

    List<ServiceBooking> service;
    int numberOfEnteries;
    int currentEntryIndex;
    ServiceBooking currentEntry;
    XYChart.Series series1 = new XYChart.Series();

    public void initData(DataBaseManager database) {
        //Grab pure connection object from database manager
        model = new Model(database.getConnectionObject());
        //Set table to watch the list of customers
        ui_cust_table.setItems(model.getCustList());
        ui_vehicle_table.setItems(model.getVehicleList());
        //This section will bind the table columns to the data in the Customer class
        ui_customerid_column.setCellValueFactory(cellData -> cellData.getValue().customerID());
        ui_firstName_column.setCellValueFactory(cellData -> cellData.getValue().firstName());
        ui_lastName_column.setCellValueFactory(cellData -> cellData.getValue().lastName());
        ui_address_column.setCellValueFactory(cellData -> cellData.getValue().address());
        ui_phoneNum_column.setCellValueFactory(cellData -> cellData.getValue().phone());

        ui_registration_column.setCellValueFactory(cellData -> cellData.getValue().rego());
        ui_make_column.setCellValueFactory(cellData -> cellData.getValue().make());
        ui_model_column.setCellValueFactory(cellData -> cellData.getValue().model());
        ui_year_column.setCellValueFactory(cellData -> cellData.getValue().manufactureYear());
        ui_kilometers_column.setCellValueFactory(cellData -> cellData.getValue().odometer());
        barchart.setAnimated(false);

    }

    void searchCurrentEnteredCust() throws SQLException {
        model.searchCust(ui_first_name_field.getText(), ui_last_name_field.getText(), ui_address_field.getText(), ui_phone_field.getText());
    }

    boolean checkCustBlankFields() {
        if (ui_first_name_field.getText() == null || ui_first_name_field.getText().equals("") || ui_last_name_field.getText() == null || ui_last_name_field.getText().equals("") || ui_address_field.getText() == null || ui_address_field.getText().equals("") || ui_phone_field.getText() == null || ui_phone_field.getText().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkVehicleBlankFields() {

        if (ui_registration_field.getText() == null || ui_registration_field.getText().equals("") || ui_make_field.getText() == null || ui_make_field.getText().equals("") || ui_model_field.getText() == null || ui_model_field.getText().equals("") || ui_yeah_field.getText() == null || ui_yeah_field.getText().equals("") || ui_kilometers_field.getText() == null || ui_kilometers_field.getText().equals("")) {
            return true;
        }
        return false;
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

            if (checkCustBlankFields()) {
                JOptionPane.showMessageDialog(null, "Error please enter all fields", "Error: " + "Blank fields", JOptionPane.ERROR_MESSAGE);
            } else {
                try {

                    model.searchCust(ui_first_name_field.getText(), ui_last_name_field.getText(), "", "");
                    //Create a dummy customer using info entered

                    Customer currentCust = new Customer(0, ui_first_name_field.getText(), ui_last_name_field.getText(), ui_address_field.getText(), ui_phone_field.getText());

                    //There is a customer with some matching details
                    //If first and last name match show error but allow 'overide' if some of the other details dont match.
                    //if only first name or last name match (not both) then add customer
                    boolean custFound = false;
                    for (int i = 0; i < model.getCustList().size(); i++) {
                        //Check results and see if all details match if all details match do not allow adding customer
                        if (model.getCustList().get(i).compare(currentCust)) {
                            JOptionPane.showMessageDialog(null, "Error Customer already exists, see table of custs.", "Error: " + "Duplicate cust", JOptionPane.ERROR_MESSAGE);
                            custFound = true;
                        } else if (model.getCustList().get(i).getCustomerID() == currentCust.getCustomerID()) {
                            JOptionPane.showMessageDialog(null, "Error Customer ID already exists", "Error: " + "Duplicate cust", JOptionPane.ERROR_MESSAGE);
                            custFound = true;

                        } else if (currentCust.getFirstName().equals(model.getCustList().get(i).getFirstName()) && currentCust.getLastName().equals(model.getCustList().get(i).getLastName())) {

                            //First and last name exists display message box asking if customer still wants to add (overide)
                            int option = JOptionPane.showOptionDialog(null, "Customer first and last name in system", "Name Match", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                            //0 = yes 1 = no 2 = cancel

                            if (option == 0) {
                                custFound = false;
                            } else if (option == 1) {
                                custFound = true;
                            } else if (option == 2) {
                                custFound = true;
                            }

                        }

                    }
                    searchCurrentEnteredCust();
                    if (!custFound) {
                        if (model.getCustList().isEmpty()) {
                            //customer doesnt exist
                            System.out.println("List empty adding cust");
                            model.addCustomer(currentCust);
                            searchCurrentEnteredCust();
                            JOptionPane.showMessageDialog(null, "Added customer", "Added: " + "OK", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        });

        ui_view_selected_cust_vehicles_btn.setOnAction((ActionEvent e) -> {
            //check if cust is selected
            if (ui_cust_table.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Error Please select a customer", "Error: " + "No Cust Selected", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    model.searchVehicle(ui_cust_table.getSelectionModel().getSelectedItem().getCustomerID());
                    for (int i = 0; i < model.getVehicleList().size(); i++) {
                        model.getVehicleList().get(i).printAll();
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });

        ui_add_vehicle_btn.setOnAction((ActionEvent e) -> {
            //Check if blank
            if (checkVehicleBlankFields()) {
                JOptionPane.showMessageDialog(null, "Error Please enter all fields", "Error: " + "Empty fields", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("not blank");
            }
            //Check if no customer is selected
            if (ui_cust_table.getSelectionModel().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Error Please select a customer", "Error: " + "No Cust Selected", JOptionPane.ERROR_MESSAGE);
            }
        });

        ui_search_vehicle_btn.setOnAction((ActionEvent e) -> {

        });
    }

    @FXML
    private void addservice(ActionEvent event) {
        try {

            String Rego = txtrego.getText();
            Date Date = java.sql.Date.valueOf(txtservicedate.getValue());
            System.out.println(txtprice.toString());
            int Price = Integer.parseInt(txtprice.getText());
            String desc = txtdescription.getText();
            int Vehicleid = model.getidbyrego(Rego);
            System.out.println(Vehicleid);

            if (Vehicleid > 0) {
                model.addService(desc, Date, Price, Rego, Vehicleid);
                JOptionPane.showMessageDialog(null, "Added Service", "Added: " + "OK", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error Invalid Rego", "Error: " + "Invalid Rego", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Formatting", "Error: " + "Formatting", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    @FXML
    private void searchService(ActionEvent event) {
        try {
            String Rego = txtsearch.getText();
            service = model.searchServices(Rego);

            numberOfEnteries = service.size();
            if (numberOfEnteries == 0) {
                JOptionPane.showMessageDialog(null, "Enter a valid Rego with a service attached", "Error: " + "Formatting", JOptionPane.ERROR_MESSAGE);
            }
            if (numberOfEnteries != 0) {
                currentEntryIndex = 0;
                txtinfofield.clear();
                while (currentEntryIndex < numberOfEnteries) {
                    currentEntry = service.get(currentEntryIndex);
                    txtinfofield.appendText(currentEntry.toString());
                    currentEntryIndex += 1;
                }

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void deleteService(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtdeleteservice.getText());
            model.removeService(id);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Enter a valid ID", "Error: " + "Formatting", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @FXML
    private void displayall(ActionEvent event) {
        try {
            service = model.getAllServices();

            numberOfEnteries = service.size();
            if (numberOfEnteries == 0) {
                JOptionPane.showMessageDialog(null, "DATABASE FOR SERVICES IS EMPTY", "Error: " + "DATABASE EMPTY", JOptionPane.ERROR_MESSAGE);
                //This should ONLY HAPPEN IF THAT DATABASE IS EMPTY
            }
            if (numberOfEnteries != 0) {
                currentEntryIndex = 0;
                txtinfofield.clear();
                while (currentEntryIndex < numberOfEnteries) {
                    currentEntry = service.get(currentEntryIndex);
                    txtinfofield.appendText(currentEntry.toString());
                    currentEntryIndex += 1;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void statistics(ActionEvent event) {
        try {
            txtinfofield.clear();
            ArrayList<String> stats = new ArrayList<String>();
            ArrayList<String> brandstats = new ArrayList<String>();
            LinkedList<String> barstats = new LinkedList<String>();
            stats = model.getstats();
            txtinfofield.appendText(stats.toString());
            txtinfofield.appendText("\n");
            brandstats = model.getmakestats();
            txtinfofield.appendText(brandstats.toString());
            barstats = model.getbarstats();

            System.out.println(barstats.get(0));
            System.out.println(barstats.get(2));
            series1.setName("Top Car Brands");
            series1.getData().add(new XYChart.Data(barstats.get(0), Integer.parseInt(barstats.get(1))));
            series1.getData().add(new XYChart.Data(barstats.get(2), Integer.parseInt(barstats.get(3))));
            series1.getData().add(new XYChart.Data(barstats.get(4), Integer.parseInt(barstats.get(5))));
            barchart.getData().addAll(series1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clear(ActionEvent event) {

        txtrego.clear();
        txtprice.clear();
        txtdescription.clear();
        txtsearch.clear();
        txtdeleteservice.clear();
        txtinfofield.clear();
        barchart.getData().removeAll(series1);
    }

}
