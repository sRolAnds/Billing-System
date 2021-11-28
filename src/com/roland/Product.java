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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", isPromotional=").append(isPromotional);
        sb.append('}');
        return sb.toString();
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
