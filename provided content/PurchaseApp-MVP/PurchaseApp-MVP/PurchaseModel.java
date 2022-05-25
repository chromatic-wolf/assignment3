//data model- PurchaseModel class definition
//considering the 1:m relationship between Customers and Orders
//more comments see the word document
//

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * Author : Michael Li
 File Name:
 Date :20/05/201
 Purpose : DataModel

 */

public class PurchaseModel implements IPurchaseModel
{
    private static final String URL = "jdbc:mysql://localhost/Purchase"; //MySQL, database name: Purchase
    private static final String USERNAME = "root";//
    private static final String PASSWORD = "**********";//set YOUR password

    private Connection connection = null; // declare a connection object

    private PreparedStatement getAllCustomer = null; //
    private PreparedStatement saveCustomer = null; // add a new customer

    private PreparedStatement addOrderToCustomer = null;//
    private PreparedStatement searchCustomerAndOrderByName = null;//
    private PreparedStatement searchCustomerAndOrderByPhone = null;//

    private PreparedStatement displayCustomerAndOrder = null;
    private PreparedStatement statistics = null;//

    private int lastInsertedCustomerID=0; //latest customer ID after inserted a new cusoter

    //constructor
    public PurchaseModel()
    {
        try {
            connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);

            // create query that selects all entries in the table
            getAllCustomer = connection.prepareStatement("SELECT * FROM Customers");

            saveCustomer=connection.prepareStatement("INSERT INTO Customers "
                    + "(FirstName, LastName, Phone)" + "VALUES (?, ?, ?)");

            // create insert that adds a new entry into the database
            addOrderToCustomer = connection.prepareStatement("INSERT INTO Orders "
                    + "(OrderID,Price,Shipper,CustomerID)" + "VALUES (?, ?, ?, ?)");

           searchCustomerAndOrderByName=connection.prepareStatement("SELECT * FROM Customers As C left Join Orders As O on C.CustomerID=O.CustomerID where FirstName=? and LastName=?");//

           //searchCustomerAndOrderByPhone=connection.prepareStatement("SELECT * FROM Customers As C, Orders As O where C.CustomerID=O.CustomerID and Phone=?");//
           searchCustomerAndOrderByPhone=connection.prepareStatement("SELECT * FROM Customers As C left Join Orders As O on C.CustomerID=O.CustomerID where Phone=?");//

          //displayCustomerAndOrder=connection.prepareStatement("SELECT * FROM Customers As C, Orders As O where C.CustomerID=O.CustomerID");

           displayCustomerAndOrder=connection.prepareStatement("SELECT * FROM Customers As C Left Join Orders As O on C.CustomerID=O.CustomerID");

           statistics=connection.prepareStatement("SELECT C.CustomerID, FirstName, LastName, count(*) As NumOfOrders FROM Customers AS C, Orders AS O WHERE C.CustomerID=O.CustomerID GROUP BY C.CustomerID, FirstName, LastName;");

        } catch (Exception e) {
            //print out in the system if there is no database
            System.out.println("Database does not exist and program terminating");
            System.exit(0);//close the system
        }//end catch

    }//end constructor

    // get the latest customerID after inserting a new customer
    public int getLastInsertedCustomerID()
    {
		getAllCustomer();
		return lastInsertedCustomerID;
	}

    //select all of the customes in the database-
    public void getAllCustomer()
    {
        ResultSet resultSet = null;

        try {
            resultSet = getAllCustomer.executeQuery();//execute query

            int customerID=0;

            while (resultSet.next()) {
              customerID=resultSet.getInt(1);
            }

            lastInsertedCustomerID=customerID;// latest customerID

            resultSet.close();
        } // end try
        catch (SQLException e)
        {
           e.printStackTrace();
        }

    } // end method

    //save customer, add a new customer to DB
    public int saveCustomer(String firstName, String lastName, String phone)
    {
		int result=-1;
		try{
		    saveCustomer.setString(1, firstName);
		    saveCustomer.setString(2,lastName);
		    saveCustomer.setString(3,phone);

            result = saveCustomer.executeUpdate();
        } catch (SQLException e) {//handle error
            e.printStackTrace();
        } // end catch

		return result;
	}

	 //add order to A customer
	 public int addOrderToCustomer(String orderID, int price, String shipper, int customerID)
	 {
			int result=-1;
			try{
			    addOrderToCustomer.setString(1, orderID);
			    addOrderToCustomer.setInt(2,price);
			    addOrderToCustomer.setString(3,shipper);
	            addOrderToCustomer.setInt(4, customerID);//fk

	            result = addOrderToCustomer.executeUpdate();
	        } catch (SQLException e) {//handle error
	            e.printStackTrace();
	        } // end catch

			return result;
	 }// end of the method

     //search by phone
     public List<Orders> searchCustomerAndOrderByPhone(String p)
     {
		    ResultSet resultSet = null;
		    List<Orders> result=new ArrayList<>();

			try{
                 searchCustomerAndOrderByPhone.setString(1,p);
                 resultSet = searchCustomerAndOrderByPhone.executeQuery();//execute query

                  while (resultSet.next())
                  {
				 	   int customerID = resultSet.getInt(1);//get customer id
				 	   String firstName=resultSet.getString(2);
				 	   String lastName=resultSet.getString(3);
				 	   String phone=resultSet.getString(4);

					   Customers c=new Customers(customerID,firstName, lastName,phone);

				 	   String orderID=resultSet.getString(5);
				 	   int price = resultSet.getInt(6);
				 	   String shipper = resultSet.getString(7);

				 	   //int customerIDFK=  resultSet.getInt(8);

 					   Orders o=new Orders(orderID,price,shipper,c);
					   result.add(o);

		          } // end while

	        } catch (SQLException e) {//handle error
	            e.printStackTrace();
	        } // end catch

	        return result;
	 }

     //search by name
	 public List<Orders> searchCustomerAndOrderByName(String first, String last)
	 {
			ResultSet resultSet = null;
            List<Orders> result=new ArrayList<>();

		    try{
	             searchCustomerAndOrderByName.setString(1,first);
	             searchCustomerAndOrderByName.setString(2,last);

	             resultSet = searchCustomerAndOrderByName.executeQuery();//execute query

	             while (resultSet.next())
	             {
					 int customerID = resultSet.getInt(1);//
					 String firstName=resultSet.getString(2);
					 String lastName=resultSet.getString(3);
					 String phone=resultSet.getString(4);

                     Customers c=new Customers(customerID,firstName, lastName,phone);

					 String orderID=resultSet.getString(5);
					 int price = resultSet.getInt(6);
					 String shipper = resultSet.getString(7);

					 Orders o=new Orders(orderID,price,shipper,c);
					 result.add(o);
			       } // end while

		        } catch (SQLException e) {//handle error
		            e.printStackTrace();
		        } // end catch
		        return result;
	 }//end

	 //display all Oreders and associed customers who placed orders
	 public List<Orders> displayCustomerAndOrder()
	 {
            List<Orders> result=new ArrayList<>();
	 		ResultSet resultSet = null;

	 	    try{
	              resultSet = displayCustomerAndOrder.executeQuery();//execute query
	              while (resultSet.next())
	              {
	 				  int customerID = resultSet.getInt(1);//
	 				  String firstName=resultSet.getString(2);
	 				  String lastName=resultSet.getString(3);
	 				  String phone=resultSet.getString(4);

                      Customers c=new Customers(customerID,firstName, lastName,phone);

	 				  String orderID=resultSet.getString(5);
	 				  int price = resultSet.getInt(6);
	 				  String shipper = resultSet.getString(7);

                      Orders o=new Orders(orderID,price,shipper,c);
					  result.add(o);

	 		       } // end while

	 	      } catch (SQLException e) {//handle error
	 	            e.printStackTrace();
	 	        } // end catch
	 	        return result;
	 }//end

    //STATISTICS, Depend on query details
	 public List<String> statistics()
	 {
		    List<String> result=new ArrayList<>();
	 		ResultSet resultSet = null;

	 	    try{
	              resultSet = statistics.executeQuery();//execute query

	              while (resultSet.next())
	              {
	 				  int customerID = resultSet.getInt(1);//
	 				  String firstName=resultSet.getString(2);
	 				  String lastName=resultSet.getString(3);

	 				  int numOfOrders=  resultSet.getInt(4);

	                  String s=customerID+"\t"+firstName+"\t"+lastName+"\t"+numOfOrders;

	                  result.add(s);
	 		       } // end while

	 	      } catch (SQLException e) {
	 	            e.printStackTrace();
	 	      } // end catch
	 	      return result;
	 }//end

    //close db connection
    public void close()
    {
        try {
            connection.close();//close connection
            System.exit(0);
        } // end try
        catch (SQLException e) {
            System.out.println("Error to closing database");
        }
    } // end method close

    //main method for test
    public static void main(String [] args)
	{   /*
        PurchaseModel p=new PurchaseModel();
        List<Orders> result=new ArrayList<>();

        int j=p.getLastInsertedCustomerID();
        System.out.println(j);

        //int result=p.addOrderToCustomer("A006",300,"Toll",2);
        //result=p.searchCustomerAndOrderByName("Michael","Li");

        //result=p.searchCustomerAndOrderByPhone("0401283900");
        result2=p.statistics();
        //p.displayCustomerAndOrder();
        */
	}

} // end class


