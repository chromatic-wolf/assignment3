package vsms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 *
 * @author Lenovo
 */
public interface IModel {
    
    
    public void setDataBase(Connection database);
    
    public ObservableList searchCust(String firstName, String lastName, String address, String phone) throws SQLException;
    
    public ObservableList getCustList();
    
    public void addCustomer(Customer cust) throws SQLException;


}
