import com.stocksync.model.Product;

public class TestValidation {
    public static void main(String[] args) {
        System.out.println("Test 1: Valid product");
        try {
            Product p = Product.builder()
                .id(1)
                .sku("VALID-001")
                .name("Valid Product")
                .quantity(10)
                .price(99.99)
                .build();
            System.out.println("✅ Created: " + p);
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        System.out.println("\nTest 2: Empty SKU (should fail)");
        try {
            Product p = Product.builder()
                .id(2)
                .sku("")  // ← Should throw exception HERE
                .name("Test")
                .build();
            System.out.println("❌ ERROR: Should have thrown exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught as expected: " + e.getMessage());
        }
        
        System.out.println("\nTest 3: Negative price (should fail)");
        try {
            Product p = Product.builder()
                .id(3)
                .sku("TEST-003")
                .name("Test")
                .price(-10.0)  // ← Should throw exception HERE
                .build();
            System.out.println("❌ ERROR: Should have thrown exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Caught as expected: " + e.getMessage());
        }
    }
}
