package com.stocksync.model;

public class Product{
    private final int id;
    private final String sku;
    private final String name;
    private final int quantity;
    private final double price;

    public Product(Builder builder){
        this.id = builder.id;
        this.sku = builder.sku;
        this.name = builder.name;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getId() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }

    public String getSku() {
        return sku;
    }
    public Product withQuantity(int quantity){
        return Product.builder()
                .id(this.id)
                .sku(this.sku)
                .name(this.name)
                .price(this.price)
                .quantity(quantity)
                .build();

    }
    public Product withPrice(double newPrice) {
        return Product.builder()
                .id(this.id)
                .sku(this.sku)
                .name(this.name)
                .quantity(this.quantity)
                .price(newPrice)
                .build();
    }
    public Product decrementQuantity(int amount){
        if (amount < 0){
            throw new IllegalStateException("Amount cannot be negative");
        }
        if (this.quantity < amount){
            throw new IllegalStateException(String.format("Insufficient quantity Available:%d Requested: %d", this.quantity, amount));
        }
        return withQuantity(this.quantity-amount);
    }
    public Product incrementQuantity(int amount){
        if (amount < 0){
            throw new IllegalStateException("Amount cannot be negative");
        }
        return withQuantity(this.quantity+amount);
    }
    public Product decreasePrice(double percentage){
        if (percentage < 0 || percentage > 100){
            throw new IllegalStateException("percentage must be between 0 and 100");
        }
        double newPrice = price * (1 - percentage / 100.0);
        return withPrice(newPrice);
    }
    public Product increasePrice(double percentage){
        if (percentage < 0 || percentage > 100){
            throw new IllegalStateException("Percentage must be between 0 and 100");
        }
        double newPrice = price * (1 + percentage / 100);
        return withPrice(newPrice);
    }

    @Override
    public String toString() {
        return String.format("Product: {id= %d, sku= '%s', name= '%s', qty= %d, price= $%.2f}",
                id, sku, name, quantity, price);
    }
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int id;
        private String sku;
        private String name;
        private int quantity;
        private double price;
        public Builder id(int id){
            this.id = id;
            return this;
        }
        public Builder sku(String sku){
            this.sku = sku;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder quantity(int quantity){
            this.quantity = quantity;
            return this;
        }
        public Builder price(double price){
            this.price = price;
            return this;
        }
        public Product build(){
            if (sku == null || name == null){
                throw new IllegalStateException("SKU and Name are required fields");
            }
            return new Product(this);
        }

    }


    public static void main(String[] args) {
        Product product = Product.builder()
                        .id(1)
                        .name("Hershey's Chocolate")
                        .sku("HC-012")
                        .quantity(500)
                        .price(60.00)
                        .build();
        System.out.println(product);
    }
}
