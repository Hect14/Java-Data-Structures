package GUI;

import Model.CartItem;
import Model.ShoppingCart;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CartGUI {
	// ShoppingCart to hold cart items
    private ShoppingCart cart = new ShoppingCart();

    public static void main(String[] args) {
        // Launch the GUI in the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new CartGUI().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // Creating the JFrame (main window)
        JFrame frame = new JFrame("E-Cart GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create components for displaying and interacting with the cart
        JTextArea cartDisplay = new JTextArea();  // Display cart content
        cartDisplay.setEditable(false);  // Make display area non-editable
        JScrollPane scrollPane = new JScrollPane(cartDisplay);  // Scroll area for cart items

        // Input field for items (name, price, quantity)
        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(5);
        JTextField quantityField = new JTextField(5);

        // Create buttons for adding, removing, and viewing cart items
        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton totalButton = new JButton("Show Total");
        JButton viewButton = new JButton("View Cart");
        JButton sortPriceButton = new JButton("Sort by Price");
        JButton sortQuantityButton = new JButton("Sort by Quantity");
        JButton sortNameButton = new JButton("Sort by Name");

        // Panel to hold input fields and the "Add Item" button
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(quantityField);
        inputPanel.add(addButton);

        // Panel for control buttons (Remove, View, Show Total)
        JPanel controlPanel = new JPanel();
        controlPanel.add(removeButton);
        controlPanel.add(viewButton);
        controlPanel.add(totalButton);
        controlPanel.add(sortPriceButton);
        controlPanel.add(sortQuantityButton);
        controlPanel.add(sortNameButton);
        
        // Adding panels to the JFrame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Button action for adding an item to the cart
        addButton.addActionListener(e -> {
            try {
                // Validate Name: Check if name is not empty
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Product name can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;  // Exit if name is empty
                }

                // Validate Price: Ensure price is a positive number
                double price = Double.parseDouble(priceField.getText());
                if (price <= 0) {
                    JOptionPane.showMessageDialog(frame, "Price must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate Quantity: Ensure quantity is a positive integer
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(frame, "Quantity must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add the new product to the cart
                cart.addProduct(new CartItem(name, price, quantity));
                cartDisplay.append("Added: " + name + " ($" + price + " x " + quantity + ")\n");
            } catch (NumberFormatException ex) {
                // Catch any errors if price or quantity are not valid numbers
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for price and quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button action for removing an item from the cart
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter item name to remove
                String name = JOptionPane.showInputDialog(frame, "Enter item to remove:");

                // Check if the input is valid
                if (name != null && !name.trim().isEmpty()) {
                    name = name.trim().toLowerCase();  // Normalize input to lowercase

                    // Try to remove the item from the cart
                    boolean removed = cart.removeProduct(name);

                    // Show confirmation or error message
                    if (removed) {
                        cartDisplay.append("Removed: " + name + "\n");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Item not found in cart: " + name, 
                                                      "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Display error if no valid name is entered
                    JOptionPane.showMessageDialog(frame, "Please enter a valid item name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Button action for viewing all items in the cart
        viewButton.addActionListener(e -> {
            cartDisplay.setText("");  // Clear the display area
            for (CartItem item : cart.getCartItems()) {
                cartDisplay.append(item + "\n");  // List all cart items
            }
        });

        // Button action for calculating and displaying the total price of the cart
        totalButton.addActionListener(e -> {
            double total = cart.calculateTotal();  // Calculate total
            cartDisplay.append("Total: $" + total + "\n");  // Display total in the cart
        });

     // Button action for sorting the cart by price
        sortPriceButton.addActionListener(e -> {
            cart.sortByPrice();  // Sort the cart items by price using Bubble Sort
            cartDisplay.setText("");  // Clear the display area
            for (CartItem item : cart.getCartItems()) {
                cartDisplay.append(item + "\n");  // Display sorted cart items
            }
        });
        
     // Button action for sorting the cart by quantity
        sortQuantityButton.addActionListener(e -> {
            cart.sortByQuantity();  // Sort the cart items by quantity (high to low)
            cartDisplay.setText("");  
            for (CartItem item : cart.getCartItems()) {
                cartDisplay.append(item + "\n");  
            }
        });
        
     // Button action for sorting the cart by name
        sortNameButton.addActionListener(e -> {
            cart.sortByName();  // Sort the cart items by name (alphabetical)
            cartDisplay.setText("");  
            for (CartItem item : cart.getCartItems()) {
                cartDisplay.append(item + "\n");  // Display sorted cart items
            }
        });
        
        // Make the frame visible
        frame.setVisible(true);  
    }
}


