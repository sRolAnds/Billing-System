package com.roland;

public class DiscountCard {

    private int id;
    private double discountSize;

    public DiscountCard(int id, double discountSize) {
        this.id = id;
        this.discountSize = discountSize;
    }

    public double getDiscount(double sum) {
        return sum * discountSize / 100;
    }

    public int getId() {
        return id;
    }

    public double getDiscountSize() {
        return discountSize;
    }
}
