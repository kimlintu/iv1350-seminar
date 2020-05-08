package model.pos;

import java.util.ArrayList;
import java.util.List;

import integration.dbhandler.InventorySystem;
import integration.dbhandler.data.ItemDescription;
import model.dto.PurchasedItemInformation;
import model.dto.Receipt;
import model.dto.SaleInformation;
import model.util.Amount;

/**
 * This class represents the ongoing sale. It has an internal array list 
 * that holds the sold items.
 */
public class Sale {
	private List<Item> itemList;
	private TotalPrice totalPrice;

	/**
	 * Creates a new instance. The instance will have a list that contains the items
	 * that's being purchased and the total price of the sale
	 */
	public Sale() {
		itemList = new ArrayList<>();
		totalPrice = new TotalPrice();
	}

	/**
	 * Creates a new {@link Item} object containing the <code>itemDescription</code>
	 * and the <code>quantity</code> and adds it to the internal item list. If an
	 * identical item already exists in the list, the program will instead update
	 * the quantity and price of that item.
	 * 
	 * @param itemDescription   description of the item that's stored in the
	 *                          {@link InventorySystem}.
	 * @param purchasedQuantity Amount of the corresponding item being purchased.
	 * 
	 * @return Information about the most recently purchased item.
	 */
	public PurchasedItemInformation addItemToSale(ItemDescription itemDescription, int purchasedQuantity) {
		Item purchasedItem = new Item(itemDescription, purchasedQuantity);

		if (itemList.contains(purchasedItem)) {
			Item itemInList = getItemFromList(purchasedItem);
			
			updateQuantityOfItemInList(itemInList, purchasedQuantity);
		} else {
			addItemToList(purchasedItem);
		}
 
		totalPrice.addToTotalPrice(purchasedItem);
		return purchasedItem.getItemInformation();
	}

	/**
	 * Returns a new {@link SaleInformation} object created by this instance.
	 * 
	 * @return A <code>SaleInformation</code> object containing data from this
	 *         <code>Sale</code>.
	 */
	public SaleInformation getSaleInformation() {
		return new SaleInformation(totalPrice.getPriceInfo(), itemList);
	}

	/**
	 * Complete the sale and create a receipt containing the complete
	 * sale information.
	 * 
	 * @param saleInfo       The information about the sale specified by
	 *                       {@link SaleInformation}.
	 * @param amountPaid     The amount paid by the customer.
	 * @param amountOfChange The amount of change that the customer should receive.
	 * @return
	 */
	public Receipt processSale(SaleInformation saleInfo, Amount amountPaid, Amount amountOfChange) {
		Receipt receipt = new Receipt(saleInfo, amountPaid, amountOfChange);

		return receipt;
	}

	private void updateQuantityOfItemInList(Item item, int quantity) {
		item.addToQuantity(quantity);
	}

	private Item getItemFromList(Item item) {
		return itemList.get(itemList.indexOf(item)); 
	}

	private void addItemToList(Item item) {
		itemList.add(item);
	}
}
