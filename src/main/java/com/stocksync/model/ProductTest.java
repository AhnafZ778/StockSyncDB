package com.stocksync.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testValidProductCreation() {
        Product product = Product.builder()
                .id(1)
                .sku("TEST-001")
                .name("Test Product")
                .quantity(5)
                .price(99.99)
                .build();

        assertEquals(1, product.getId());
        assertEquals("TEST-001", product.getSku());
        assertEquals("Test Product", product.getName());
        assertEquals(5, product.getQuantity());
        assertEquals(99.99, product.getPrice(), 0.01);
    }

    @Test
    public void testDecrementQuantity() {
        Product product = Product.builder()
                .id(1)
                .sku("TEST-001")
                .name("Test")
                .quantity(100)
                .price(10.0)
                .build();

        Product after = product.decrementQuantity(30);

        assertEquals(70, after.getQuantity());
        assertEquals(100, product.getQuantity());
    }

    @Test
    public void testIncrementQuantity() {
        Product product = Product.builder()
                .id(1)
                .sku("TEST-001")
                .name("Test")
                .quantity(100)
                .price(10.0)
                .build();

        Product after = product.incrementQuantity(50);

        assertEquals(150, after.getQuantity());
        assertEquals(100, product.getQuantity());
    }

    @Test
    public void testDecrementQuantityInsufficientStock() {
        Product product = Product.builder()
                .id(1)
                .sku("TEST-001")
                .name("Test")
                .quantity(10)
                .price(10.0)
                .build();

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            product.decrementQuantity(20);
        });

        assertTrue(exception.getMessage().contains("Insufficient quantity"));
    }

    @Test
    public void testIncreasePrice() {
        Product product = Product.builder()
                .id(1)
                .sku("TEST-001")
                .name("Test")
                .quantity(10)
                .price(100.0)
                .build();

        Product after = product.increasePrice(10);

        assertEquals(110.0, after.getPrice(), 0.01);
        assertEquals(100.0, product.getPrice(), 0.01);
    }

    @Test
    public void testDecreasePrice() {
        Product product = Product.builder()
                .id(1)
                .sku("TEST-001")
                .name("Test")
                .quantity(10)
                .price(100.0)
                .build();

        Product after = product.decreasePrice(20);

        assertEquals(80.0, after.getPrice(), 0.01);
        assertEquals(100.0, product.getPrice(), 0.01);
    }

    @Test
    public void testChainedOperations() {
        Product product = Product.builder()
                .id(1)
                .sku("TEST-001")
                .name("Test")
                .quantity(100)
                .price(100.0)
                .build();

        Product result = product
                .decrementQuantity(10)
                .incrementQuantity(5)
                .decreasePrice(10);

        assertEquals(95, result.getQuantity());
        assertEquals(90.0, result.getPrice(), 0.01);
        assertEquals(100, product.getQuantity());
        assertEquals(100.0, product.getPrice(), 0.01);
    }

    @Test
    public void testEmptySkuThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Product.builder()
                    .sku("")
                    .name("Test")
                    .build();
        });
        assertEquals("SKU cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testNegativePriceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Product.builder()
                    .sku("TEST-001")
                    .name("Test")
                    .price(-10.0)
                    .build();
        });
        assertEquals("Price cannot be negative", exception.getMessage());
    }
}