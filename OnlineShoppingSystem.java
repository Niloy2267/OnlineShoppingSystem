import java.util.*;

// Product Class
class Product {
    String name, category;
    double price;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void displayProduct(int index) {
        System.out.println(index + ". Product: " + name + ", Category: " + category + ", Price: " + price);
    }
}

// User Class
class User {
    String username, email, password;
    List<Product> cart = new ArrayList<>();

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

    public void addToCart(Product product) {
        cart.add(product);
        System.out.println(product.name + " has been added to your cart.");
    }

    public void viewCart() {
        System.out.println("Your cart contains:");
        for (Product product : cart) {
            System.out.println("- " + product.name + ", Price: " + product.price);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.price;
        }
        return total;
    }
}

// Order Class
class Order {
    User user;
    List<Product> products;
    String status;

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
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

// Payment Class
class Payment {
    Order order;

    public Payment(Order order) {
        this.order = order;
    }

    public void processPayment() {
        System.out.println("Processing payment...");
        System.out.println("Payment successful!");
        order.status = "Paid";
    }
}

// Main Class
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User user1 = new User("John Doe", "john@example.com", "password123");
        List<Product> products = Arrays.asList(
                new Product("Laptop", 50000, "Electronics"),
                new Product("Smartphone", 30000, "Electronics"),
                new Product("Headphones", 3000, "Accessories"),
                new Product("Smartwatch", 8000, "Electronics"),
                new Product("Camera", 20000, "Electronics"),
                new Product("TV", 40000, "Electronics"),
                new Product("Keyboard", 1500, "Accessories"),
                new Product("Mouse", 800, "Accessories"),
                new Product("Router", 3500, "Electronics"),
                new Product("Power Bank", 2500, "Electronics"));

        // Display products with index
        System.out.println("Available products:");
        for (int i = 0; i < products.size(); i++) {
            products.get(i).displayProduct(i + 1);
        }

        // Login
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (user1.login(email, password)) {
            // Allow user to choose products
            boolean continueShopping = true;
            while (continueShopping) {
                System.out.print("Enter product number to add to cart or 0 to finish: ");
                int productNumber = scanner.nextInt();

                if (productNumber == 0) {
                    continueShopping = false;
                } else if (productNumber > 0 && productNumber <= products.size()) {
                    Product selectedProduct = products.get(productNumber - 1);
                    user1.addToCart(selectedProduct);
                } else {
                    System.out.println("Invalid product number.");
                }
            }

            // View cart and calculate total
            user1.viewCart();
            double total = user1.calculateTotal();
            System.out.println("Total amount before discount: " + total);

            // Apply promo code
            System.out.print("Enter promo code: ");
            String promoCode = scanner.next();

            double discount = 0;
            if (promoCode.equals("niloy")) {
                discount = total * 0.15;
                System.out.println("Promo code applied! You get 15% discount: " + discount);
            } else {
                System.out.println("Invalid promo code.");
            }

            double finalTotal = total - discount;
            System.out.println("Total after discount: " + finalTotal);

            // Place order and process payment
            Order order1 = new Order(user1, user1.cart);
            order1.placeOrder();

            Payment payment1 = new Payment(order1);
            payment1.processPayment();

            order1.trackOrder();
        }
        scanner.close();
    }
}