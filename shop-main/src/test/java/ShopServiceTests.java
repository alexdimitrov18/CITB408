import org.example.entities.*;
import org.example.exceptions.ItemOutOfStockException;
import org.example.services.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.of;
import static org.example.helpers.LocalDateToDate.convert;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ShopServiceTests {

    private ShopService shopService;

    private Shop shop;

    @BeforeEach
    void setup() {
        shop = new Shop();
        shopService = spy(new ShopService(shop, new Cashier()));
    }

    @Test
    public void when_multiple_cashiers_present_then_total_salaries_calculated_correctly() {
        // Arrange
        shop.setCashiers(List.of(
                new Cashier(1, "John", 1000.0),
                new Cashier(2, "Jane", 1500.0)
        ));

        // Act
        double totalSalaries = shopService.getCashierSalaries();

        // Assert
        assertEquals(2500.0, totalSalaries);
    }

    @Test
    public void when_no_cashiers_then_total_salaries_is_zero() {
        // Arrange
        shop.setCashiers(new ArrayList<>());

        // Act
        double totalSalaries = shopService.getCashierSalaries();

        // Assert
        assertEquals(0.0, totalSalaries);
    }

    //
    @Test
    public void when_multiple_items_delivered_then_total_expense_is_correct() {
        // Arrange
        Item item1 = new Item(1, "Item1", 10.0, null, null);
        Item item2 = new Item(2, "Item2", 20.0, null, null);
        shop.getDeliveredItems().put(1, new ItemQuantity(item1, 2));
        shop.getDeliveredItems().put(2, new ItemQuantity(item2, 3));

        // Act
        double expense = shopService.getDeliveredItemsExpense();

        // Assert
        assertEquals(80.0, expense);
    }

    @Test
    public void when_no_delivered_items_then_total_expense_is_zero() {
        // Act
        double expense = shopService.getDeliveredItemsExpense();

        // Assert
        assertEquals(0.0, expense);
    }

    @Test
    public void when_calculating_monthly_income_then_correct_income_is_returned() {
        // Arrange
        doReturn(100000.0).when(shopService).getSoldItemsIncome();
        doReturn(30000.0).when(shopService).getDeliveredItemsExpense();
        doReturn(10000.0).when(shopService).getCashierSalaries();

        // Act & Assert
        assertEquals(60000.0, shopService.getShopMonthlyIncome());
    }

    @Test
    public void when_valid_receipt_added_then_receipt_is_stored_in_shop() {
        // Arrange
        Receipt receipt = new Receipt(1, new Cashier(), shop);

        // Act
        shopService.addReceiptToShop(receipt);

        // Assert
        assertEquals(1, shop.getReceipts().size());
        assertEquals(receipt, shop.getReceipts().get(0));
    }

    @Test
    public void when_receipt_added_then_sold_items_are_updated() {
        // Arrange
        Cashier cashier = new Cashier();
        Item item1 = new Item(1, "Item1", 10.0, null, null);
        Item item2 = new Item(2, "Item2", 20.0, null, null);
        List<ItemQuantity> itemQuantities = List.of(new ItemQuantity(item1, 2),
                new ItemQuantity(item2, 3));
        Receipt receipt = new Receipt(1, cashier, LocalDateTime.now(), itemQuantities, shop);

        // Act
        shopService.addSoldItemsToShop(receipt);

        // Assert
        assertEquals(2, shop.getSoldItems().get(1).getQuantity());
        assertEquals(3, shop.getSoldItems().get(2).getQuantity());
    }


    @Test
    public void when_multiple_item_quantities_processed_then_quantities_are_updated_correctly() {
        // Arrange
        List<ItemQuantity> itemQuantities = List.of(
                new ItemQuantity(new Item(1, "Item1", 10.0, ItemCategory.FOOD,
                        convert(of(2024, 10, 15))), 5),
                new ItemQuantity(new Item(2, "Item2", 15.0, ItemCategory.NON_FOOD,
                        convert(of(2024, 10, 15))), 3)
        );

        // Act
        shopService.processDelivery(itemQuantities);

        // Assert
        assertEquals(5, shop.getDeliveredItems().get(1).getQuantity());
        assertEquals(3, shop.getDeliveredItems().get(2).getQuantity());
    }

    @Test
    public void when_existing_item_quantity_updated_then_new_quantity_is_correct() {
        // Arrange
        Item item = new Item(1, "Item2", 15.0, ItemCategory.NON_FOOD,
                convert(of(2024, 10, 15)));
        shop.getDeliveredItems().put(1, new ItemQuantity(item, 5));
        List<ItemQuantity> itemQuantities = List.of(
                new ItemQuantity(item, 3)
        );

        // Act
        shopService.processDelivery(itemQuantities);

        // Assert
        assertEquals(8, shop.getDeliveredItems().get(1).getQuantity());
    }

    @Test
    public void when_sell_item_and_current_receipt_initialized_then_same_receipt_is_returned() {
        // Arrange
        doNothing().when(shopService).sellItem(any(ItemQuantity.class));
        Item item = new Item(1, "Test Item", 10.0, ItemCategory.FOOD, new Date());
        ItemQuantity itemQuantity = new ItemQuantity(item, 1);

        // Act
        shopService.sell(itemQuantity);
        Receipt firstReceipt = shopService.getCurrentReceipt();
        shopService.sell(itemQuantity);

        // Assert
        assertEquals(firstReceipt, shopService.getCurrentReceipt());
    }

    @Test
    public void when_item_quantity_added_to_current_receipt_then_receipt_contains_item_quantity() {
        // Arrange
        Item item = new Item(1, "Test Item", 10.0, ItemCategory.FOOD, new Date());
        ItemQuantity itemQuantity = new ItemQuantity(item, 1);
        doNothing().when(shopService).sellItem(itemQuantity);

        // Act
        shopService.sell(itemQuantity);

        // Assert
        assertTrue(shopService.getCurrentReceipt().getItemQuantities().contains(itemQuantity));
    }

    @Test
    public void when_sell_called_then_sell_item_method_is_invoked() {
        // Arrange
        Item item = new Item(1, "Test Item", 10.0, ItemCategory.FOOD, new Date());
        ItemQuantity itemQuantity = new ItemQuantity(item, 1);
        doNothing().when(shopService).sellItem(itemQuantity);

        // Act
        shopService.sell(itemQuantity);

        // Assert
        verify(shopService, times(1)).sellItem(itemQuantity);
    }

    @Test
    public void when_sell_item_multiple_times_then_quantity_is_reduced_successfully() {
        // Arrange
        Item item = new Item(1, "Test Item", 10.0, ItemCategory.FOOD, new Date());
        ItemQuantity itemQuantity = new ItemQuantity(item, 10);
        shop.getDeliveredItems().put(1, itemQuantity);

        // Act
        shopService.sellItem(new ItemQuantity(item, 3));
        shopService.sellItem(new ItemQuantity(item, 3));

        // Assert
        assertEquals(4, shop.getDeliveredItems().get(1).getQuantity());
    }

    //
    @Test
    public void when_sell_item_with_invalid_id_then_item_out_of_stock_exception_is_thrown() {
        // Act & Assert
        assertThrows(ItemOutOfStockException.class, () -> {
            shopService.sellItem(new ItemQuantity(new Item(), 3));
        });
    }

    @Test
    public void when_sell_item_exceeding_stock_then_item_out_of_stock_exception_is_thrown() {
        // Arrange
        Item item = new Item(1, "Test Item", 10.0, ItemCategory.FOOD, new Date());
        shop.getDeliveredItems().put(1, new ItemQuantity(item, 5));

        // Act & Assert
        assertThrows(ItemOutOfStockException.class, () -> {
            shopService.sellItem(new ItemQuantity(item, 10));
        });
    }

    @Test
    public void when_new_item_is_sold_then_it_is_added_to_sold_items() {
        // Arrange
        Item item = new Item(1, "Apple", 0.5, ItemCategory.FOOD, new Date());
        ItemQuantity itemQuantity = new ItemQuantity(item, 10);

        // Act
        shopService.addSoldItem(itemQuantity);

        // Assert
        assertTrue(shop.getSoldItems().containsKey(item.getId()));
        assertEquals(10, shop.getSoldItems().get(item.getId()).getQuantity());
    }

    @Test
    public void when_existing_sold_item_quantity_increased_then_quantity_is_correct() {
        // Arrange
        Item item = new Item(1, "Apple", 0.5, ItemCategory.FOOD, new Date());
        ItemQuantity initialQuantity = new ItemQuantity(item, 5);
        shopService.addSoldItem(initialQuantity);
        ItemQuantity additionalQuantity = new ItemQuantity(item, 10);

        // Act
        shopService.addSoldItem(additionalQuantity);

        // Assert
        assertEquals(15, shop.getSoldItems().get(item.getId()).getQuantity());
    }


}
