import java.util.ArrayList;
import java.util.List;

public class User {
    String username, email, password;
    List<Product> cart = new ArrayList<>();
    List<Integer> cartQuantity = new ArrayList<>(); // Quantity per product in cart

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
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
        if (product.stock >= quantity) {
            cart.add(product);
            cartQuantity.add(quantity);
            product.stock -= quantity;
            System.out.println(quantity + " x " + product.name + " added to cart.");
        } else {
            System.out.println("Sorry, not enough stock for " + product.name);
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
            System.out.println("- " + p.name + " x " + qty + " = Tk " + (p.price * qty));
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            Product p = cart.get(i);
            int qty = cartQuantity.get(i);
            total += p.price * qty;
        }
        return total;
    }
}
