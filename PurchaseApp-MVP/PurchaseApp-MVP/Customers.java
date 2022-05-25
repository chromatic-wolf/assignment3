// Customers class defintion
//

public class Customers
{
   private int customerID;
   private String firstName;
   private String lastName;
   private String phone;

   public Customers()
   {
   }

   public Customers(int id, String first, String last, String p)
   {
	   customerID=id;
	   firstName=first;
	   lastName=last;
	   phone=p;
   }

   //mutator
   public void setCustomerID(int id)
   {
	   customerID=id;
   }

   //accessor
   public int getCustomerID()
   {
	   return customerID;
   }

   public void setFirstName(String first)
   {
	   firstName=first;
   }

   public String getFirstName()
   {
	   return firstName;
   }

   public void setLastName(String last)
   {
	   lastName=last;
   }

   public String getLastName()
   {
	   return lastName;
   }

   public void setPhone(String p)
   {
	   phone=p;
   }

   public String getPhone()
   {
	   return phone;
   }

}//end of class