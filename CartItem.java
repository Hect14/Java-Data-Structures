package Model;

public class CartItem {
	// Attributes
	private String name;
	private double price;
	private int quantity;

	// Constructor init
	public CartItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	// Getters (read)
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	// Setters (update)
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		if (price >= 0) {
			this.price = price;
		}
	}

	public void setQuantity(int quantity) {
		if (quantity > 0) {
			this.quantity = quantity;
		}
	}

	// toString() method to display item details
	@Override
    public String toString() {
        return name + " - $" + price + " (Qty: " + quantity + ")";
}
}
