package Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {

    String CustomerID;
    String cartitemID;
    String category;
    String itemName;
    String itemprice;
    String itemquantity;
    String manufacturer;
    String itemID;

    public Cart() {
    }

    public Cart(String customerID, String cartitemID, String category, String itemName, String itemprice, String itemquantity, String manufacturer, String itemID) {
        CustomerID = customerID;
        this.cartitemID = cartitemID;
        this.category = category;
        this.itemName = itemName;
        this.itemprice = itemprice;
        this.itemquantity = itemquantity;
        this.manufacturer = manufacturer;
        this.itemID = itemID;
    }

    protected Cart(Parcel in) {
        CustomerID = in.readString();
        cartitemID = in.readString();
        category = in.readString();
        itemName = in.readString();
        itemprice = in.readString();
        itemquantity = in.readString();
        manufacturer = in.readString();
        itemID = in.readString();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemID);
//        parcel.writeString(CustomerID);
//        parcel.writeString(cartitemID);
//        parcel.writeString(category);
//        parcel.writeString(itemName);
//        parcel.writeString(itemprice);
//        parcel.writeString(itemquantity);
//        parcel.writeString(manufacturer);

    }

    @Override
    public String toString() {
        return "Cart{" +
                "CustomerID='" + CustomerID + '\'' +
                ", cartitemID='" + cartitemID + '\'' +
                ", category='" + category + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemprice='" + itemprice + '\'' +
                ", itemquantity='" + itemquantity + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", itemID='" + itemID + '\'' +
                '}';
    }

    //    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(itemID);
//    }

//    public String getMaufacturer() {
//        return maufacturer;
//    }
//
//    public void setMaufacturer(String maufacturer) {
//        this.maufacturer = maufacturer;
//    }
}
