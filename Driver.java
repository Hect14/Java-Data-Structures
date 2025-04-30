package ECartFinal;

import GUI.CartGUI;

public class Driver {
    public static void main(String[] args) {
        CartGUI gui = new CartGUI();
        gui.createAndShowGUI();
    }
}

/**
 * Conclusion:
 *
 * This project implements an E-Cart system that allows users to add, remove, and view items in their cart, 
 * calculate the total price, and sort the cart by price, quantity, or name. 
 * 
 * Key Features:
 * - **Add/Remove Items**: Supports case-insensitive item names and handles invalid inputs.
 * - **View Cart & Total**: Displays items with details and calculates the total cost.
 * - **Sorting**: Allows sorting by price, quantity, and name using different algorithms.
 * - **Input Validation**: Ensures valid price and quantity inputs.
 * 
 * Data Structures:
 * - **ArrayList** for storing items.
 * - **HashMap** for fast lookups and removals.
 * 
 * This system provides essential cart management with a simple GUI, and can be enhanced with features like discounts, gui visual improvements like images, drag and drop, etc,
 */
