public class Payment {
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
