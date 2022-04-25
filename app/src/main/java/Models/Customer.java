package Models;

public class Customer {

    String id;
    String Email;
    String Fullname;
    String ShippingAddress;
    String Type;

    public Customer() {
    }

    public Customer(String id, String email, String FullName, String shippingAddress, String type) {
        this.id = id;
        Email = email;
        Fullname = FullName;
        ShippingAddress = shippingAddress;
        Type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFullName() {
        return Fullname;
    }

    public void setFullName(String fullName) {
        Fullname = fullName;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
