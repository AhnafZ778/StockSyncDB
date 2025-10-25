
package com.stocksync;

import com.stocksync.model.Product;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Immutable Product with Convenience Methods ===\n");

        // Create initial product
        Product original = Product.builder()
                .id(1)
                .sku("WDG-001")
                .name("Super Widget")
                .quantity(100)
                .price(29.99)
                .build();

        System.out.println("Original product:");
        System.out.println(original);
        System.out.println();

        // Scenario 1: Customer buys 10 units
        System.out.println("Scenario 1: Customer buys 10 units");
        Product afterSale = original.decrementQuantity(10);
        System.out.println("After sale: " + afterSale);
        System.out.println("Original unchanged: " + original);
        System.out.println();

        // Scenario 2: Restock 50 units
        System.out.println("Scenario 2: Restock 50 units");
        Product afterRestock = afterSale.incrementQuantity(50);
        System.out.println("After restock: " + afterRestock);
        System.out.println();

        // Scenario 3: 20% discount sale
        System.out.println("Scenario 3: 20% discount sale");
        Product onSale = afterRestock.decreasePrice(20);
        System.out.println("On sale: " + onSale);
        System.out.println();

        // Scenario 4: Price increase after discount ends
        System.out.println("Scenario 4: 15% price increase");
        Product afterIncrease = onSale.increasePrice(15);
        System.out.println("After increase: " + afterIncrease);
        System.out.println();

        // Scenario 5: Try to oversell (should fail)
        System.out.println("Scenario 5: Try to sell 200 units (only 140 available)");
        try {
            Product invalid = afterRestock.decrementQuantity(200);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught expected error: " + e.getMessage());
        }
        System.out.println();

        // Scenario 6: Chain multiple operations
        System.out.println("Scenario 6: Chain operations");
        Product finalProduct = original
                .decrementQuantity(20)  // Sell 20
                .incrementQuantity(10)  // Restock 10
                .decreasePrice(10);     // 10% discount
        System.out.println("After chaining: " + finalProduct);
        System.out.println("Original still unchanged: " + original);
    }
}
