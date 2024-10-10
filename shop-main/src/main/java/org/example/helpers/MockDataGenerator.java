package org.example.helpers;

import org.example.entities.*;

import java.time.LocalDate;
import java.util.*;

public class MockDataGenerator {

    public static List<Shop> generateMockShop() {
        List<Shop> shops = new ArrayList<>();

        // Shop 1
        Shop shop1 = new Shop();
        shop1.setId(1);
        shop1.setName("Lidl");
        shop1.setFoodMarkupPercent(10.0);
        shop1.setFoodDiscountPercent(0.05);
        shop1.setNonFoodMarkupPercent(15.0);
        shop1.setNonFoodDiscountPercent(0.07);

        List<Cashier> cashiers1 = new ArrayList<>();
        cashiers1.add(new Cashier(1, "John Doe", 3000));
        cashiers1.add(new Cashier(2, "Jane Smith", 3200));
        shop1.setCashiers(cashiers1);


        shops.add(shop1);

        // Shop 2
        Shop shop2 = new Shop();
        shop2.setId(2);
        shop2.setName("Walmart");
        shop2.setFoodMarkupPercent(12.0);
        shop2.setFoodDiscountPercent(0.04);
        shop2.setNonFoodMarkupPercent(18.0);
        shop2.setNonFoodDiscountPercent(0.08);

        List<Cashier> cashiers2 = new ArrayList<>();
        cashiers2.add(new Cashier(3, "Alice Johnson", 3100));
        cashiers2.add(new Cashier(4, "Bob Williams", 3300));
        shop2.setCashiers(cashiers2);
        shops.add(shop2);

        // Shop 3
        Shop shop3 = new Shop();
        shop3.setId(3);
        shop3.setName("Aldi");
        shop3.setFoodMarkupPercent(8.5);
        shop3.setFoodDiscountPercent(0.06);
        shop3.setNonFoodMarkupPercent(14.0);
        shop3.setNonFoodDiscountPercent(0.05);

        List<Cashier> cashiers3 = new ArrayList<>();
        cashiers3.add(new Cashier(5, "Charlie Brown", 2900));
        cashiers3.add(new Cashier(6, "Eve Davis", 3000));
        shop3.setCashiers(cashiers3);

        shops.add(shop3);

        // Shop 4
        Shop shop4 = new Shop();
        shop4.setId(4);
        shop4.setName("Target");
        shop4.setFoodMarkupPercent(11.0);
        shop4.setFoodDiscountPercent(0.05);
        shop4.setNonFoodMarkupPercent(16.0);
        shop4.setNonFoodDiscountPercent(0.06);

        List<Cashier> cashiers4 = new ArrayList<>();
        cashiers4.add(new Cashier(7, "Frank Miller", 3400));
        cashiers4.add(new Cashier(8, "Grace Lee", 3500));
        shop4.setCashiers(cashiers4);


        shops.add(shop4);

        // Shop 5
        Shop shop5 = new Shop();
        shop5.setId(5);
        shop5.setName("Tesco");
        shop5.setFoodMarkupPercent(9.0);
        shop5.setFoodDiscountPercent(0.045);
        shop5.setNonFoodMarkupPercent(15.5);
        shop5.setNonFoodDiscountPercent(0.007);

        List<Cashier> cashiers5 = new ArrayList<>();
        cashiers5.add(new Cashier(9, "Hank Harris", 3200));
        cashiers5.add(new Cashier(10, "Ivy Walker", 3300));
        shop5.setCashiers(cashiers5);


        shops.add(shop5);

        return shops;
    }

    public static List<ItemQuantity> generateMockDeliveredItems(int shopId) {
        List<ItemQuantity> deliveredItems = new ArrayList<>();

        deliveredItems.add(new ItemQuantity(
                new Item(shopId , "Milk", 5.0, ItemCategory.FOOD,
                        LocalDateToDate.convert(LocalDate.of(2024, 10, 15))),
                100));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId, "Bread", 2.0, ItemCategory.FOOD,
                        LocalDateToDate.convert(LocalDate.of(2024, 10, 30))),
                200));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId , "Shampoo", 7.5, ItemCategory.NON_FOOD,
                        LocalDateToDate.convert(LocalDate.of(2025, 2, 15))),
                50));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId, "Soap", 3.0, ItemCategory.NON_FOOD,
                        LocalDateToDate.convert(LocalDate.of(2025, 1, 5))),
                150));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId , "Eggs", 4.0, ItemCategory.FOOD,
                        LocalDateToDate.convert(LocalDate.of(2024, 10, 10))),
                250));
        deliveredItems.add(new ItemQuantity(
                new Item(shopId, "Cheese", 2.5, ItemCategory.FOOD,
                        LocalDateToDate.convert(LocalDate.of(2024, 10, 10))),
                220));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId, "Juice", 1.0, ItemCategory.FOOD,
                        LocalDateToDate.convert(LocalDate.of(2024, 11, 22))),
                350));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId, "Conditioner", 4.5, ItemCategory.NON_FOOD,
                        LocalDateToDate.convert(LocalDate.of(2025, 3, 10))),
                100));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId, "Toothpaste", 2.0, ItemCategory.NON_FOOD,
                        LocalDateToDate.convert(LocalDate.of(2025, 1, 15))),
                140));

        deliveredItems.add(new ItemQuantity(
                new Item(shopId, "Pasta", 0.75, ItemCategory.FOOD,
                        LocalDateToDate.convert(LocalDate.of(2024, 12, 5))),
                300));
        return deliveredItems;
    }

}


