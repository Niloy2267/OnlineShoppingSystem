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
                new ElectronicProduct("Laptop", 50000, "Electronics", 10, 12),
                new ElectronicProduct("Power Bank", 2500, "Electronics", 10, 6),
                new ElectronicProduct("Router", 3500, "Electronics", 12, 12),
                new ElectronicProduct("Smartphone", 30000, "Electronics", 5, 24),
                new ElectronicProduct("Smartwatch", 8000, "Electronics", 8, 12)));

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
        System.out.println("Total amount: Tk " + total);

        Order order = new Order(loggedUser, loggedUser.cart, loggedUser.cartQuantity);

        System.out.println("Select payment method:");
        System.out.println("1. bKash");
        System.out.println("2. Nagad");
        System.out.println("3. Cash on Delivery");
        int paymentChoice = scanner.nextInt();

        Payment payment = null;
        switch (paymentChoice) {
            case 1:
                payment = new BkashPayment(order);
                break;
            case 2:
                payment = new NagadPayment(order);
                break;
            case 3:
                payment = new CashOnDeliveryPayment(order);
                break;
            default:
                System.out.println("Invalid payment option.");
                System.exit(0);
        }

        payment.processPayment();
        order.trackOrder();

        System.out.println("Thank you for shopping with us!");
    }

    private static void displayProductsAndAddToCart(Scanner scanner, User user, List<Product> products) {
        System.out.println("Available products:");
        for (int i = 0; i < products.size(); i++) {
            products.get(i).displayProduct(i + 1);
        }

        System.out.print("Select product number to add to cart (0 to cancel): ");
        int prodChoice = scanner.nextInt();
        if (prodChoice > 0 && prodChoice <= products.size()) {
            Product selected = products.get(prodChoice - 1);
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            user.addToCart(selected, quantity);
        }
    }
}
