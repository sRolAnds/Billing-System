package com.roland;

public class Product {

    private final int id;
    private String description;
    private double price;
    private boolean isPromotional = false;

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Product(int id, String description, double price, boolean isPromotional) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isPromotional = isPromotional;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            return;
        }
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPromotional() {
        return isPromotional;
    }

    public void setPromotional(boolean promotional) {
        isPromotional = promotional;
    }
}
