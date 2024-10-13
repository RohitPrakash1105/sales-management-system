import javax.swing.*;
import java.awt.event.*;
import java.util.*;

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

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: $" + price;
    }
}

class Sales {
    private int salesId;
    private int quantity;
    private Date date;
    private Product product;

    public Sales(int salesId, int quantity, Date date, Product product) {
        this.salesId = salesId;
        this.quantity = quantity;
        this.date = date;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Sales ID: " + salesId + ", Quantity: " + quantity + ", Date: " + date + ", Product: [" + product + "]";
    }
}

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

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available Products:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}

public class SalesUI {
    private JFrame frame;
    private JButton processSaleButton, addProductButton, viewProductsButton;
    private JLabel resultLabel;
    private ProductManager productManager;

    public SalesUI() {
        productManager = new ProductManager();
        
        frame = new JFrame("Sales Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to Sales Management System");
        welcomeLabel.setBounds(10, 10, 400, 25);
        panel.add(welcomeLabel);

        processSaleButton = new JButton("Process Sale");
        processSaleButton.setBounds(10, 50, 150, 25);
        panel.add(processSaleButton);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(170, 50, 150, 25);
        panel.add(addProductButton);

        viewProductsButton = new JButton("View Products");
        viewProductsButton.setBounds(330, 50, 150, 25);
        panel.add(viewProductsButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 90, 400, 25);
        panel.add(resultLabel);

        processSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });
    }

    private void addProduct() {
        try {
            int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter Product ID:"));
            String productName = JOptionPane.showInputDialog("Enter Product Name:");
            double productPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter Product Price:"));

            Product product = new Product(productId, productName, productPrice);
            productManager.addProduct(product);
            resultLabel.setText("Product added successfully!");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid product details.");
        }
    }

    private void processSale() {
        try {
            int salesId = Integer.parseInt(JOptionPane.showInputDialog("Enter Sales ID:"));
            int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter Product ID for sale:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter Quantity:"));

            Product product = productManager.findProductById(productId);
            if (product != null) {
                Sales sale = new Sales(salesId, quantity, new Date(), product);
                resultLabel.setText("Sale processed successfully! " + sale.toString());
            } else {
                resultLabel.setText("Product not found.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    private void viewProducts() {
        productManager.displayProducts();
    }

    public static void main(String[] args) {
        new SalesUI();
    }
}
