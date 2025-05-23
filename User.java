import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private String password;
    protected List<Product> cart = new ArrayList<>();
    protected List<Integer> cartQuantity = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and setters (Encapsulation)
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Welcome, " + username + "!");
            return true;
        }
        System.out.println("Invalid login credentials.");
        return false;
    }

    public void addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            cart.add(product);
            cartQuantity.add(quantity);
            product.setStock(product.getStock() - quantity);
            System.out.println(quantity + " x " + product.getName() + " added to cart.");
        } else {
            System.out.println("Sorry, not enough stock for " + product.getName());
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Your Cart:");
        for (int i = 0; i < cart.size(); i++) {
            Product p = cart.get(i);
            int qty = cartQuantity.get(i);
            System.out.println("- " + p.getName() + " x " + qty + " = Tk " + (p.getPrice() * qty));
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            Product p = cart.get(i);
            int qty = cartQuantity.get(i);
            total += p.getPrice() * qty;
        }
        return total;
    }
}

// Inheritance: AdminUser extends User
class AdminUser extends User {
    public AdminUser(String username, String email, String password) {
        super(username, email, password);
    }

    public void addProduct(List<Product> productList, Product product) {
        productList.add(product);
        System.out.println("Product " + product.getName() + " added successfully.");
    }

    public void removeProduct(List<Product> productList, Product product) {
        productList.remove(product);
        System.out.println("Product " + product.getName() + " removed successfully.");
    }
}