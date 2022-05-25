// main method of the application
//using MVP design pattern

public class PurchaseApp {

    public static void main(String[] args) {

        //create an instance of data model
        IPurchaseModel ipm=new PurchaseModel();

        //create an instance of view
        IPurchaseView  ipv=new PurchaseView();

        // create an instance of presenter
        PurchasePresenter pp=new PurchasePresenter(ipv,ipm);

        // assign view access to PurchasePresenter
        ipv.bind(pp);
    }

}
