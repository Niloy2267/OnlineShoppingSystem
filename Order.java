import java.util.List;

public class Order {
    User user;
    List<Product> products;
    List<Integer> quantities;
    String status;

    public Order(User user, List<Product> products, List<Integer> quantities) {
        this.user = user;
        this.products = products;
        this.quantities = quantities;
        this.status = "Pending";
    }

    public void placeOrder() {
        status = "Placed";
        System.out.println("Order placed for " + user.username + " with " + products.size() + " products.");
    }

    public void trackOrder() {
        System.out.println("Order status: " + status);
    }
}
