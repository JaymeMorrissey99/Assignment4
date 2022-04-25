package Models;

public class Stock {

    String title;
    Double price;
    String category;
    String image;

    public Stock() {
    }

    public Stock(String title, Double price, String category, String image) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.image = image;
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
