import java.util.List;

public class Order {
    private User user;
    private List<Product> products;
    private List<Integer> quantities;
    private String status;

    public Order(User user, List<Product> products, List<Integer> quantities) {
        this.user = user;
        this.products = products;
        this.quantities = quantities;
        this.status = "Pending";
    }

    // Getters and setters (Encapsulation)
    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void placeOrder() {
        status = "Placed";
        System.out.println("Order placed for " + user.getUsername() + " with " + products.size() + " products.");
    }

    public void trackOrder() {
        System.out.println("Order status: " + status);
    }
}
