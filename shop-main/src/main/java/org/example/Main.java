package org.example;

import org.example.entities.Item;

import org.example.entities.ItemQuantity;
import org.example.entities.Shop;
import org.example.helpers.MockDataGenerator;
import org.example.helpers.ReceiptPrinter;
import org.example.services.ShopService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Shop magazin = MockDataGenerator.generateMockShop().get(2);


        ShopService kasa1 = new ShopService(magazin, magazin.getCashiers().get(0));
        ShopService kasa2 = new ShopService(magazin, magazin.getCashiers().get(1));


        List<ItemQuantity> itemQuantities = MockDataGenerator.generateMockDeliveredItems(1);


        kasa1.processDelivery(itemQuantities);
        kasa2.processDelivery(itemQuantities);

// 1-Milk  2-Bread  3-Shampoo 4-Soap  5-Eggs  6- cheese  7- juice 8-conditioner 9-toothpaste 10-pasta

        Item milk = itemQuantities.get(0).getItem();
        kasa1.sell(new ItemQuantity(milk, 5));
        Item shampoo = itemQuantities.get(2).getItem();
        kasa1.sell(new ItemQuantity(shampoo, 3));
        kasa1.pay();

// 1-Milk  2-Bread  3-Shampoo 4-Soap  5-Eggs  6- cheese  7- juice 8-conditioner 9-toothpaste 10-pasta
        Item milk2 = itemQuantities.get(0).getItem();
        kasa2.sell(new ItemQuantity(milk2, 5));
        Item eggs = itemQuantities.get(4).getItem();
        kasa2.sell(new ItemQuantity(eggs, 4));
        Item bread = itemQuantities.get(1).getItem();
        kasa2.sell(new ItemQuantity(bread, 1));
        kasa2.pay();

// 0-Milk  1-Bread  2-Shampoo 3-Soap  4-Eggs  5- cheese  6- juice 7-conditioner 8-toothpaste 9-pasta
        Item milk3 = itemQuantities.get(0).getItem();
        kasa2.sell(new ItemQuantity(milk3, 5));

        Item eggs3 = itemQuantities.get(4).getItem();
        kasa2.sell(new ItemQuantity(eggs3, 4));

        Item bread3 = itemQuantities.get(1).getItem();
        kasa2.sell(new ItemQuantity(bread3, 1));

        Item soap3 = itemQuantities.get(3).getItem();
        kasa2.sell(new ItemQuantity(soap3, 1));

        Item cheese3 = itemQuantities.get(5).getItem();
        kasa2.sell(new ItemQuantity(cheese3, 1));

        Item juice3 = itemQuantities.get(6).getItem();
        kasa2.sell(new ItemQuantity(juice3, 1));
        Item conditioner3 = itemQuantities.get(7).getItem();
        kasa2.sell(new ItemQuantity(conditioner3, 1));
        Item toothpase3 = itemQuantities.get(8).getItem();
        kasa2.sell(new ItemQuantity(toothpase3, 1));
        Item pasta3 = itemQuantities.get(9).getItem();
        kasa2.sell(new ItemQuantity(pasta3, 1));
        kasa2.pay();

        ReceiptPrinter.printReceipts(magazin.getReceipts());

        System.out.println(kasa1.getShopMonthlyIncome());
        System.out.println(kasa1.getCashierSalaries());
        System.out.println(kasa1.getSoldItemsIncome());
    }

}