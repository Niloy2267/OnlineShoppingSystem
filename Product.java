public class Product {
    private String name;
    private String category;
    private double price;
    private int stock;

    public Product(String name, double price, String category, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    // Getters and Setters (Encapsulation)
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void displayProduct(int index) {
        System.out.println(index + ". " + name + " | Tk " + price + " | Stock: " + stock);
    }
}

// Inheritance example: Subclass of Product
class ElectronicProduct extends Product {
    private int warrantyMonths;

    public ElectronicProduct(String name, double price, String category, int stock, int warrantyMonths) {
        super(name, price, category, stock);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void displayProduct(int index) {
        super.displayProduct(index);
        System.out.println("   Warranty: " + warrantyMonths + " months");
    }
}
