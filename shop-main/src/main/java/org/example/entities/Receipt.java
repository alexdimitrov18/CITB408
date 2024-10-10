package org.example.entities;

import org.example.exceptions.ItemOutOfDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private int id;

    private Cashier cashier;

    private LocalDateTime purchaseDate;

    private List<ItemQuantity> itemQuantities = new ArrayList<>();

    private Shop shop;

    public Receipt() {
    }

    public Receipt(int id, Cashier cashier, Shop shop) {
        this.id = id;
        this.cashier = cashier;
        this.shop = shop;
    }

    public Receipt(int id, Cashier cashier, LocalDateTime purchaseDate,
                   List<ItemQuantity> itemQuantities, Shop shop) {
        this.id = id;
        this.cashier = cashier;
        this.purchaseDate = purchaseDate;
        this.itemQuantities = itemQuantities;
        this.shop = shop;
    }

    public List<ItemQuantity> getItemQuantities() {
        return itemQuantities;
    }

    public void setItemQuantities(List<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Calculates the total cost of all items in a given receipt, taking into account the price of each item and its quantity.
     *
     * @param receipt the {@link Receipt} containing the list of item quantities and purchase details.
     * @return the total cost of all items in the receipt.
     */
    public double getTotalCost(Receipt receipt) {
        double totalCost = 0;
        for (ItemQuantity itemQuantity : receipt.getItemQuantities()) {
            totalCost += getItemPrice(itemQuantity, receipt) * itemQuantity.getQuantity();
        }
        return totalCost;
    }

    /**
     * Calculates the price of an item based on its delivery price,
     * markup, and any discounts due to the
     * time since the item's expiration date.
     *
     * @param itemQuantity the {@link ItemQuantity} containing the item whose price is to be calculated.
     * @param receipt the {@link Receipt} that contains the purchase date.
     * @return the calculated price of the item after applying markup and discounts.
     * @throws ItemOutOfDateException if the item's expiration date is in the past relative to the purchase date.
     */
    public double getItemPrice(ItemQuantity itemQuantity, Receipt receipt) {
        Item item = itemQuantity.getItem();
        LocalDate expirationDate = item.getExpirationDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        long daysBetween = -1 * ChronoUnit.DAYS.between(expirationDate, receipt.getPurchaseDate());
        if (daysBetween < 0) {
            throw new ItemOutOfDateException(item.getName());
        }
        double markedUpPrice = item.getDeliveryPrice() + round(item.getDeliveryPrice() * getMarkup(receipt, item), 2);
        double priceDiscount = round(daysBetween * getDiscount(receipt, item) * item.getDeliveryPrice(), 2);
        return markedUpPrice - priceDiscount;
    }

    private static double getMarkup(Receipt receipt, Item item) {
        return item.getCategory() == ItemCategory.FOOD ?
                receipt.getShop().getFoodMarkupPercent() : receipt.getShop().getNonFoodMarkupPercent();
    }

    private static double getDiscount(Receipt receipt, Item item) {
        return item.getCategory() == ItemCategory.FOOD ?
                receipt.getShop().getFoodDiscountPercent() : receipt.getShop().getNonFoodDiscountPercent();
    }

    public static double round(double value, int precision) {
        return Math.round(value * Math.pow(10.0, precision)) / Math.pow(10.0, precision);
    }


    public void addItems(ItemQuantity itemQuantity, Receipt receipt) {
        receipt.getItemQuantities().add(itemQuantity);
    }

}
