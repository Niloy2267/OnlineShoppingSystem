import java.util.*;

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<User> users = new ArrayList<>();
        User loggedUser = null;

        List<Product> accessories = new ArrayList<>(Arrays.asList(
                new Product("Keyboard", 1500, "Accessories", 15),
                new Product("Mouse", 800, "Accessories", 20)));
        List<Product> electronics = new ArrayList<>(Arrays.asList(
                new Product("Laptop", 50000, "Electronics", 10),
                new Product("Power Bank", 2500, "Electronics", 10),
                new Product("Router", 3500, "Electronics", 12),
                new Product("Smartphone", 30000, "Electronics", 5),
                new Product("Smartwatch", 8000, "Electronics", 8)));

        System.out.println("Welcome to Online Shopping!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Select option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            User newUser = new User(name, email, password);
            users.add(newUser);
            System.out.println("Registration successful! Please login now.");
        }

        // Login loop
        while (loggedUser == null) {
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            for (User user : users) {
                if (user.login(email, password)) {
                    loggedUser = user;
                    break;
                }
            }
            if (loggedUser == null) {
                System.out.println("Invalid login. Try again.");
            }
        }

        boolean shopping = true;

        while (shopping) {
            System.out.println("\nAvailable Categories:");
            System.out.println("1. Accessories");
            System.out.println("2. Electronics");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout & Exit");
            System.out.print("Select a category or option: ");
            int catChoice = scanner.nextInt();

            switch (catChoice) {
                case 1:
                    displayProductsAndAddToCart(scanner, loggedUser, accessories);
                    break;
                case 2:
                    displayProductsAndAddToCart(scanner, loggedUser, electronics);
                    break;
                case 3:
                    loggedUser.viewCart();
                    break;
                case 4:
                    shopping = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        double total = loggedUser.calculateTotal();
        System.out.println("\nYour Cart Summary:");
        loggedUser.viewCart();
        System.out.println("Total before discount: Tk " + total);

        System.out.print("Enter promo code (or press Enter to skip): ");
        scanner.nextLine(); // consume leftover newline
        String promoCode = scanner.nextLine();
        double discount = 0;
        if (promoCode.equalsIgnoreCase("niloy")) {
            discount = total * 0.15;
            System.out.println("Promo applied! Tk " + discount + " discount.");
        } else if (!promoCode.isEmpty()) {
            System.out.println("Invalid promo code.");
        }

        double finalTotal = total - discount;
        System.out.println("Total after discount: Tk " + finalTotal);

        System.out.println("Choose payment method:");
        System.out.println("1. bKash");
        System.out.println("2. Nagad");
        System.out.println("3. Cash on Delivery");
        System.out.print("Select payment method: ");
        int paymentChoice = scanner.nextInt();

        Order order = new Order(loggedUser, loggedUser.cart, loggedUser.cartQuantity);
        order.placeOrder();
        Payment payment = new Payment(order);
        payment.processPayment();
        order.trackOrder();

        scanner.close();
    }

    private static void displayProductsAndAddToCart(Scanner scanner, User user, List<Product> products) {
        System.out
                .println("\n--- " + (products.isEmpty() ? "No Products" : products.get(0).category) + " Products ---");
        for (int i = 0; i < products.size(); i++) {
            products.get(i).displayProduct(i + 1);
        }
        while (true) {
            System.out.print("Enter product number to add to cart (0 to go back): ");
            int productNum = scanner.nextInt();
            if (productNum == 0)
                break;
            if (productNum < 1 || productNum > products.size()) {
                System.out.println("Invalid product number.");
                continue;
            }
            Product selectedProduct = products.get(productNum - 1);

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("Quantity must be positive.");
                continue;
            }

            user.addToCart(selectedProduct, quantity);
        }
    }
}
