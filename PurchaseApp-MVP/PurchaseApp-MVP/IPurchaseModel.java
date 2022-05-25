// interface definition
//

import java.util.List;

public interface IPurchaseModel
{
    public int getLastInsertedCustomerID();

    public int saveCustomer(String firstName,String lastName,String phone);
    public int addOrderToCustomer(String orderID, int price, String shipper, int customerID);

    public List<Orders> searchCustomerAndOrderByPhone(String p);
    public List<Orders> searchCustomerAndOrderByName(String first, String last);
    public List<Orders> displayCustomerAndOrder();

    public List<String> statistics();
    public void close();
}