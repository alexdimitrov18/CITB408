import org.example.entities.*;
import org.example.exceptions.ItemOutOfDateException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.example.helpers.LocalDateToDate.convert;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class ReceiptTest {

    @Test
    public void when_add_single_item_quantity_to_empty_receipt_then_item_is_added() {
        // Arrange
        Receipt receipt = new Receipt();
        Item item = new Item(1, "Item1", 10.0, null, null);
        ItemQuantity itemQuantity = new ItemQuantity(item, 1.0);

        // Act
        receipt.addItems(itemQuantity, receipt);

        // Assert
        assertEquals(1, receipt.getItemQuantities().size());
        assertEquals(item, receipt.getItemQuantities().get(0).getItem());
    }





    @Test
    public void when_multiple_items_in_receipt_then_total_cost_is_calculated_correctly() {
        // Arrange
        Item item1 = new Item(1, "Apple", 1.0, ItemCategory.FOOD,
                convert(of(2024, 10, 20)));
        Item item2 = new Item(2, "Banana", 0.5, ItemCategory.FOOD,
                convert(of(2024, 10, 20)));
        ItemQuantity itemQuantity1 = new ItemQuantity(item1, 2);
        ItemQuantity itemQuantity2 = new ItemQuantity(item2, 3);
        List<ItemQuantity> itemQuantities = Arrays.asList(itemQuantity1, itemQuantity2);

        Receipt receipt = spy(new Receipt(1, new Cashier(), of(2024, 10, 20).atStartOfDay(),
                itemQuantities, new Shop()));
        doReturn(10.0, 20.0)
                .when(receipt)
                .getItemPrice(any(ItemQuantity.class), any(Receipt.class));

        // Act
        double totalCost = receipt.getTotalCost(receipt);

        // Assert
        assertEquals(80.0, totalCost);
    }



    @Test
    public void when_food_category_item_has_past_expiration_date_then_item_out_of_date_exception_is_thrown() {
        // Arrange
        Item item = new Item(5, "Milk", 1.5, ItemCategory.FOOD,
                convert(of(2024, 10, 15)));
        ItemQuantity itemQuantity = new ItemQuantity(item, 1);
        Receipt receipt = new Receipt(5, new Cashier(),
                of(2024, 10, 20).atStartOfDay(), List.of(itemQuantity), new Shop());


        // Act & Assert
        assertThrows(ItemOutOfDateException.class, () -> {
            receipt.getItemPrice(itemQuantity, receipt);
        });
    }

    @Test
    public void when_food_category_item_has_valid_expiration_date_then_price_is_calculated_correctly() {
        // Arrange
        Item item = new Item(1, "Apple", 10.0,
                ItemCategory.FOOD, convert(of(2024, 10, 20)));
        ItemQuantity itemQuantity = new ItemQuantity(item, 1);
        Shop shop = new Shop();
        shop.setFoodMarkupPercent(10);
        shop.setFoodDiscountPercent(1);
        Receipt receipt = new Receipt(1, new Cashier(), of(2024, 10, 15).atStartOfDay(),
                List.of(itemQuantity), shop);


        // Act
        double price = receipt.getItemPrice(itemQuantity, receipt);

        // Assert
        assertEquals(10.5, price);
    }
}