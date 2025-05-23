public abstract class Payment {
    protected Order order;

    public Payment(Order order) {
        this.order = order;
    }

    // Abstract method (Abstraction)
    public abstract void processPayment();
}

class BkashPayment extends Payment {
    public BkashPayment(Order order) {
        super(order);
    }

    @Override
    public void processPayment() {
        System.out.println("Processing bKash payment...");
        System.out.println("Payment successful!");
        order.setStatus("Paid");
    }
}

class NagadPayment extends Payment {
    public NagadPayment(Order order) {
        super(order);
    }

    @Override
    public void processPayment() {
        System.out.println("Processing Nagad payment...");
        System.out.println("Payment successful!");
        order.setStatus("Paid");
    }
}

class CashOnDeliveryPayment extends Payment {
    public CashOnDeliveryPayment(Order order) {
        super(order);
    }

    @Override
    public void processPayment() {
        System.out.println("Cash on Delivery selected.");
        order.setStatus("Pending Payment");
    }
}
