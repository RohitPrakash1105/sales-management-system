import java.util.Scanner;
import java.util.Date;

// Product class as before
class Product {
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product[ID=" + productId + ", Name=" + name + ", Price=" + price + "]";
    }
}

// Sales class as before
class Sales {
    private int salesId;
    private double price;
    private Date date;
    private int quantity;
    private Product product;

    public Sales(int salesId, int quantity, Date date, Product product) {
        this.salesId = salesId;
        this.quantity = quantity;
        this.date = date;
        this.product = product;
        this.price = product.getPrice() * quantity; // Total price
    }

    public int getSalesId() {
        return salesId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Sales[ID=" + salesId + ", Quantity=" + quantity + ", Total Price=" + price + ", Date=" + date + ", Product=" + product + "]";
    }
}

// UI Class to handle user input and sales processing
class SalesUI {
    private Scanner scanner;

    public SalesUI() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Sales Management System");

        // Create a product (this would usually come from a product list)
        Product product = new Product(101, "Laptop", 1500.00);

        // Get sales information from user
        System.out.print("Enter Sales ID: ");
        int salesId = scanner.nextInt();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        // Create Sales object
        Sales sale = new Sales(salesId, quantity, new Date(), product);

        // Display sale details
        System.out.println("Sale processed successfully!");
        System.out.println(sale);
    }
}

public class Main {
    public static void main(String[] args) {
        // Start the UI
        SalesUI ui = new SalesUI();
        ui.start();
    }
}