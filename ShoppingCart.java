package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ShoppingCart {
	// List to store cart items
	public ArrayList<CartItem> cartItems;
	// Map for quick lookup by name
	private HashMap<String, CartItem> cartMap;
	// Getter to expose cartItems
	public ArrayList<CartItem> getCartItems()	{
		return cartItems;
	}

	// Constructor
	public ShoppingCart() {
		cartItems = new ArrayList<>();
		cartMap = new HashMap<>();
	}

	// Method to add product to the cart
	public void addProduct(CartItem item) {
		// Adds input as case insensitive to avoid separate listed items of the same thing
		String productName = item.getName().toLowerCase();

		// If product already exists, increase quantity
		if (cartMap.containsKey(productName)) {
			CartItem existingItem = cartMap.get(productName);
			existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
		} else {
			cartItems.add(item);
			cartMap.put(productName, item);
		}
	}

	// Method to remove product from the cart
	public boolean removeProduct(String productName) {
	    productName = productName.toLowerCase();  // Normalize to lowercase

	    if (cartMap.containsKey(productName)) {
	        CartItem item = cartMap.get(productName);
	        int currentQty = item.getQuantity();
	        if (currentQty > 1) {
	            item.setQuantity(currentQty - 1);
	        } else {
	            cartItems.remove(item);
	            cartMap.remove(productName);
	        }
	        return true;
	    }
	    return false;
	}

	// View all cart items
	public void viewCart() {
		if (cartItems.isEmpty()) {
			System.out.println("Your cart is empty.");
			return;
		}
		System.out.println("Shopping Cart:");
		for (CartItem item : cartItems) {
			System.out.println(item);
		}
	}
	
	// Calculate total price
    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

	// Sort cart items by price (low to high) using Bubble sort
    public void sortByPrice() {
    	// Outer loop to compare adjacent items
        for (int i = 0; i < cartItems.size() - 1; i++) {
            for (int j = 0; j < cartItems.size() - i - 1; j++) {
            	// Compare the price of current item j and the next item (j + 1)
                if (cartItems.get(j).getPrice() > cartItems.get(j + 1).getPrice()) {
                	// If the current item is more expensive, swap them
                    CartItem temp = cartItems.get(j); // Saves current item in a temp variable
                    cartItems.set(j, cartItems.get(j + 1)); // Replace current item with the next one
                    cartItems.set(j + 1, temp); // Replace next item w the saved current item
                }
            }
        }
        System.out.println("\nCart sorted by price (Bubble Sort).");
    }

	// Sort cart items by quantity (high to low) 
	public void sortByQuantity() {
		Collections.sort(cartItems, (a, b) -> b.getQuantity() - a.getQuantity());
		System.out.println("\nCart sorted by quantity.");
	}

	// Sort cart items by product name (alphabetical) (Java's built in comparator)
	public void sortByName() {
		Collections.sort(cartItems, Comparator.comparing(CartItem::getName));
		System.out.println("\nCart sorted by name.");
	}
	public void clearCart() {
	    cartItems.clear();
	    cartMap.clear();
	    System.out.println("Cart has been cleared.");
	}
	
}
