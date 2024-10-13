import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Product class
class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return id;
    }

    public double getPrice() {
        return price; // Added for total calculation
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: $" + price;
    }
}

// Sales class
class Sales {
    private int salesId;
    private List<Product> products;
    private Date date;

    public Sales(int salesId) {
        this.salesId = salesId;
        this.products = new ArrayList<>();
        this.date = new Date();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getSalesId() {
        return salesId;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sales ID: ").append(salesId).append(", Date: ").append(date).append("\nProducts:\n");
        for (Product product : products) {
            sb.append("  ").append(product).append("\n");
        }
        sb.append("Total Price: $").append(getTotalPrice());
        return sb.toString();
    }
}

// ProductManager class
class ProductManager {
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added: " + product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null; // Not found
    }

    public String displayAllProducts() {
        if (products.isEmpty()) {
            return "No products available.";
        } else {
            StringBuilder sb = new StringBuilder("Available Products:\n");
            for (Product product : products) {
                sb.append(product).append("\n");
            }
            return sb.toString();
        }
    }
}

// Main UI class
public class SalesUI extends JFrame {
    private ProductManager productManager;
    private Map<Integer, Sales> salesMap;

    public SalesUI() {
        productManager = new ProductManager();
        salesMap = new HashMap<>();

        setTitle("Sales Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton addProductButton = new JButton("Add Product");
        JButton showProductsButton = new JButton("Show All Products");
        JButton salesInfoButton = new JButton("Sales Info");
        JButton processSaleButton = new JButton("Process Sale");

        add(addProductButton);
        add(showProductsButton);
        add(salesInfoButton);
        add(processSaleButton);

        // Action Listeners
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        showProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllProducts();
            }
        });

        salesInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSalesInfo();
            }
        });

        processSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });
    }

    // Method to add a product
    private void addProduct() {
        JTextField productIdField = new JTextField(10);
        JTextField productNameField = new JTextField(10);
        JTextField productPriceField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Product Name:"));
        panel.add(productNameField);
        panel.add(new JLabel("Product Price:"));
        panel.add(productPriceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(productIdField.getText());
            String name = productNameField.getText();
            double price = Double.parseDouble(productPriceField.getText());
            Product product = new Product(id, name, price);
            productManager.addProduct(product);
        }
    }

    // Method to show all products
    private void showAllProducts() {
        String message = productManager.displayAllProducts();
        JOptionPane.showMessageDialog(null, message, "All Products", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to show sales info by Sales ID
    private void showSalesInfo() {
        JTextField salesIdField = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Sales ID:"));
        panel.add(salesIdField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Sales Info", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int salesId = Integer.parseInt(salesIdField.getText());
            Sales sales = salesMap.get(salesId);
            if (sales != null) {
                JOptionPane.showMessageDialog(null, sales, "Sales Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No sales found for Sales ID: " + salesId);
            }
        }
    }

    // Method to process a sale
    private void processSale() {
        JTextField salesIdField = new JTextField(10);
        JTextField productIdField = new JTextField(10);
        JTextField quantityField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Sales ID:"));
        panel.add(salesIdField);
        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Process Sale", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int salesId = Integer.parseInt(salesIdField.getText());
            int productId = Integer.parseInt(productIdField.getText());
            Product product = productManager.findProductById(productId);
            if (product != null) {
                Sales sales = salesMap.getOrDefault(salesId, new Sales(salesId));
                sales.addProduct(product);
                salesMap.put(salesId, sales);
                JOptionPane.showMessageDialog(null, "Sale processed successfully!\n" + sales);
            } else {
                JOptionPane.showMessageDialog(null, "Product not found. Sale could not be processed.");
            }
        }
    }

    public static void main(String[] args) {
        SalesUI ui = new SalesUI();
        ui.setVisible(true);
    }
}
