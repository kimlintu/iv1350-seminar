package model.dto;

import model.util.Amount;

/**
 * This is a data container for the total price and the total VAT tax for the current sale.
 * @author kim
 *
 */
public class PriceInformation {
	private Amount totalPrice;
	private Amount totalVat;
	
	/**
	 * Creates an instance and stores the total price and total VAT tax of the sale.
	 * @param totalPrice The total price of the sale.
	 * @param totalVat The total VAT tax of the sale.
	 */
	public PriceInformation(Amount totalPrice, Amount totalVat) {
		this.totalPrice = totalPrice;
		this.totalVat = totalVat;
	}
	
	/**
	 * Returns the total price stored in this object.
	 * @return The total price as an <code>Amount</code>.
	 */
	public Amount getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Returns the total VAT tax stored in this object.
	 * @return The total VAT tax as an <code>Amount</code>.
	 */
	public Amount getTotalVat() {
		return totalVat;
	}
}
