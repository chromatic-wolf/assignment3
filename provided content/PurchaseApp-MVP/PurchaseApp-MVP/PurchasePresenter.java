//presenter class defintion
//
//

import java.util.List;
import java.util.ArrayList;

public class PurchasePresenter
{
    IPurchaseView view;
    IPurchaseModel model;

    List<Orders> results;// list
    int currentEntryIndex;
    int numberOfEntries;
    Orders currentEntry;

    //constructor
    public PurchasePresenter(IPurchaseView ipv, IPurchaseModel ipm) {
	    view = ipv;
	    model = ipm;
	    currentEntryIndex = 0;
	    numberOfEntries = 0;
	    results = null;//new ArrayList<>();
	    currentEntry = null;
	}

	   // handles call when previousButton is clicked
	   public void showPrevious() {
	      currentEntryIndex--;

	      if ( currentEntryIndex < 0 )
	         currentEntryIndex = numberOfEntries - 1;

	      currentEntry = results.get( currentEntryIndex );
	      view.displayOrderEntries(currentEntry);
	      view.displayMaxAndCurrentIndex(numberOfEntries,currentEntryIndex);
	   }

	   // handles call when nextButton is clicked
	   public void showNext() {
	      currentEntryIndex++;

	      if ( currentEntryIndex >= numberOfEntries )
	         currentEntryIndex = 0;

	      currentEntry = results.get( currentEntryIndex );
	      view.displayOrderEntries(currentEntry);
	      view.displayMaxAndCurrentIndex(numberOfEntries,currentEntryIndex);
	   }

	   // handles call search by phone
	   public void performQueryByPhone(String phone) {

	      results = model.searchCustomerAndOrderByPhone(phone);

	      numberOfEntries = results.size();
	      if ( numberOfEntries != 0 ) {
	         currentEntryIndex = 0;
	         currentEntry = results.get( currentEntryIndex );

	         Customers c=currentEntry.getCustomers();

             if(currentEntry.getOrderID()==null && numberOfEntries!=0)
             {
                 view.displayDataTextArea("The customer has not placed an order yet");
                 view.setLatestCustomerID(c.getCustomerID());
                 view.setCustomerPhone(c.getPhone());
                 return;
             }
	         view.displayOrderEntries(currentEntry);
             view.displayCustomerEntry(c);
             view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
	         view.setBrowsing(true);
	      }
	      else
	        view.displayDataTextArea("customer Not found");
	   }

        //handle method call- search by name
	    public void performQueryByName(String first, String last) {

	      results = model.searchCustomerAndOrderByName(first,last);

	      numberOfEntries = results.size();
	      if ( numberOfEntries != 0 ) {
	         currentEntryIndex = 0;
	         currentEntry = results.get( currentEntryIndex );

             Customers c=currentEntry.getCustomers();

             if(currentEntry.getOrderID()==null && numberOfEntries!=0)
             {
                 view.displayDataTextArea("The customer has not placed an order yet");
                 view.setLatestCustomerID(c.getCustomerID());
                 view.setCustomerPhone(c.getPhone());
                 return;
             }
	         view.displayOrderEntries(currentEntry);
             view.displayCustomerEntry(c);
             view.displayMaxAndCurrentIndex(numberOfEntries, currentEntryIndex);
	         view.setBrowsing(true);
	      }
	      else
	        view.displayDataTextArea("customer Not found");
	   }

	   // handles call-all cusomer and orders
	   public void displayAllCustomerAndOrder() {
	      try {
	         results = model.displayCustomerAndOrder();//

	         numberOfEntries = results.size();
	         if(numberOfEntries ==0)
	             view.displayDataTextArea("No records found");

             String display="CustomerID\tFirstName\tLastName\tOrderID\tPrice($)\tShipper\n";
			 display=display+"--------------------------------------------------------------------------------------------------------------------------\n";

	         if ( numberOfEntries != 0 ) {
                for(int i=0;i<numberOfEntries;i++)
			    {
			 		Orders o=results.get(i);
			 		display=display+"\n"+o.toString();
			 	}
	         }
		     view.displayDataTextArea(display);
	      }//end try

	      catch ( Exception e ) {
	         e.printStackTrace();
	      }
	   }//end

	   // handles call when save button is clicked- insert a new customer
	   public void insertNewCustomer(String first,String last,String phone) {

	      int result = model.saveCustomer(first,last,phone);
          int latestCustomerID=model.getLastInsertedCustomerID();

	      if ( result == 1 )
          {
	          view.displayDataTextArea("customer added");
              view.setLatestCustomerID(latestCustomerID);
          }
	      else
	          view.displayDataTextArea("customer not added");
	   }//end

	   //add order to a customer
       public void addOrderToCustomer(String orderID,int price,String shipper, int customerID) {

	      int result = model.addOrderToCustomer(orderID,price, shipper,customerID);
	      if ( result == 1 )
	          view.displayDataTextArea("order added");
	      else
	          view.displayDataTextArea("order not added");
	   }//END

	   //statistics
       public void statistics()
       {
		   List<String> result2=new ArrayList<>();//

           result2=model.statistics();

	       int size=result2.size();
		   String s="CustomerID\tFirstName\tLastName\tNumOfOrders\n";
           s=s+"------------------------------------------------------------------------------------\n";
		   for(int i=0;i<size;i++)
		   {
			  s=s+"\n"+result2.get(i);
		   }
           view.displayDataTextArea(s);
	   }

	   // handles window closure
	   public void close() {
	      model.close();
   }

}//end of class