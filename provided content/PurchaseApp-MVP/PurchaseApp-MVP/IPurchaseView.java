//defintion of interface
//

public interface IPurchaseView
{
    public void bind(PurchasePresenter p);

    public void displayCustomerEntry(Customers c);
    public void displayOrderEntries(Orders o);
    public void displayDataTextArea(String s);

    public void setLatestCustomerID(int id);
    public void setCustomerPhone(String p);

    public void setBrowsing(boolean flag);
    public void displayMaxAndCurrentIndex(int m, int c);
}
