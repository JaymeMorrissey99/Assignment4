package Models;

public class Cart {

    String CustomerID;
    String cartitemID;
    String category;
    String itemName;
    String itemprice;
    String itemquantity;
    String manufacturer;

    public Cart() {
    }

    public Cart(String customerID, String cartitemID, String category, String itemName, String itemprice, String itemquantity, String manufacturer) {
        CustomerID = customerID;
        this.cartitemID = cartitemID;
        this.category = category;
        this.itemName = itemName;
        this.itemprice = itemprice;
        this.itemquantity = itemquantity;
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCartitemID() {
        return cartitemID;
    }

    public void setCartitemID(String cartitemID) {
        this.cartitemID = cartitemID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(String itemquantity) {
        this.itemquantity = itemquantity;
    }

//    public String getMaufacturer() {
//        return maufacturer;
//    }
//
//    public void setMaufacturer(String maufacturer) {
//        this.maufacturer = maufacturer;
//    }
}
