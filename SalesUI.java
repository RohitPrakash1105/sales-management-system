import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class SalesUI {
    private JFrame frame;
    private JTextField salesIdField, quantityField;
    private JButton processSaleButton;
    private JLabel resultLabel;

    public SalesUI() {
        frame = new JFrame("Sales Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to Sales Management System");
        welcomeLabel.setBounds(10, 10, 300, 25);
        panel.add(welcomeLabel);

        JLabel salesIdLabel = new JLabel("Enter Sales ID:");
        salesIdLabel.setBounds(10, 50, 80, 25);
        panel.add(salesIdLabel);

        salesIdField = new JTextField(20);
        salesIdField.setBounds(100, 50, 165, 25);
        panel.add(salesIdField);

        JLabel quantityLabel = new JLabel("Enter Quantity:");
        quantityLabel.setBounds(10, 80, 100, 25);
        panel.add(quantityLabel);

        quantityField = new JTextField(20);
        quantityField.setBounds(100, 80, 165, 25);
        panel.add(quantityField);

        processSaleButton = new JButton("Process Sale");
        processSaleButton.setBounds(10, 120, 150, 25);
        panel.add(processSaleButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 150, 300, 25);
        panel.add(resultLabel);

        processSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });
    }

    private void processSale() {
        // Create a product (this would usually come from a product list)
        Product product = new Product(101, "Laptop", 1500.00);

        // Get sales information from user
        int salesId = Integer.parseInt(salesIdField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        // Create Sales object
        Sales sale = new Sales(salesId, quantity, new Date(), product);

        // Display sale details
        resultLabel.setText("Sale processed successfully! " + sale.toString());
    }

    public static void main(String[] args) {
        new SalesUI();
    }
}
