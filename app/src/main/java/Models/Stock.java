package Models;

public class Stock {

    String title;
    Double price;
    String category;
    String manufacturer;
    String image;
    int stockNo;

    public Stock() {
    }

    public Stock(String title, Double price, String category, String manufacturer, String image, int stockNo) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.manufacturer = manufacturer;
        this.image = image;
        this.stockNo = stockNo;

    }

    public int getStockNo() {
        return stockNo;
    }

    public void setStockNo(int stockNo) {
        this.stockNo = stockNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
