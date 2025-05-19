public class Product {
    String name, category;
    double price;
    int stock;

    public Product(String name, double price, String category, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public void displayProduct(int index) {
        System.out.println(index + ". " + name + " | Tk " + price + " | Stock: " + stock);
    }
}
