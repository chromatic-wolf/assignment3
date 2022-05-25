//defintion of Orders class
//


public class Orders
{
	private String orderID;
	private int price;
	private String shipper;
	private Customers c; //composition of Customer object

	public Orders()
	{
	}

    //constructor
    public Orders(String id, int p, String s, Customers c1)
    {
		orderID=id;
		shipper=s;
		price=p;
		c=c1;
	}

    //mutator method
    public void setOrderID(String id)
    {
		orderID=id;
	}

    //accessor method
	public String getOrderID()
	{
		return orderID;
	}

    public void setPrice(int p)
    {
		price=p;
	}

    public int getPrice()
    {
		return price;
	}

	public void setShipper(String s)
	{
		shipper=s;
    }

    public String getShipper()
    {
		return shipper;
	}

    public Customers getCustomers()
    {
		return c;
	}

    public void setCustomers(Customers c1)
    {
		c=c1;
	}

	public String toString()
	{
		String s="";

		if(orderID==null)
		   s=c.getCustomerID()+"\t"+c.getFirstName()+"\t"+c.getLastName();
		else
		   s=c.getCustomerID()+"\t"+c.getFirstName()+"\t"+c.getLastName()+"\t"+orderID+"\t"+price+ "\t"+shipper;

		return s;
	}

    //main method for test
	public static void main(String [] args)
	{
		Customers c=new Customers(1,"john","smith","0455666123");
		Orders o1=new Orders("A001",100,"AustPost",c);
		Orders o2=new Orders("A002",200,"Toll",c);
        System.out.println(o1.toString());
	}

}