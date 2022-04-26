package Models;

public class Review extends Stock{

    String itemID;
    String itemName;
    String quantity;

    public Review(){

    }

    public Review(String category, String itemID, String itemName, String itemprice, String manufacturer, String image, String quantity, String itemID1, String itemName1) {
        super(category, itemID, itemName, itemprice, manufacturer, image, quantity);
        this.itemID = itemID1;
        this.itemName = itemName1;
    }

    @Override
    public String getItemID() {
        return itemID;
    }

    @Override
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
