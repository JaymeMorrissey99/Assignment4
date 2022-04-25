package Models;

public class Stock {

    String category;
    String itemID;
    String itemName;
    String itemprice;
    String manufacturer;
    String image;
    String quantity;

    public Stock() {
    }


    public Stock(String category, String itemID, String itemName, String itemprice, String manufacturer, String image, String quantity) {
        this.category = category;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemprice = itemprice;
        this.manufacturer = manufacturer;
        this.image = image;
        this.quantity = quantity;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
