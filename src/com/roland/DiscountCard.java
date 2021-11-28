package com.roland;

public class DiscountCard {

    private int id;
    private double discountSize;

    public DiscountCard(int id, double discountSize) {
        this.id = id;
        this.discountSize = discountSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DiscountCard{");
        sb.append("id=").append(id);
        sb.append(", discountSize=").append(discountSize);
        sb.append('}');
        return sb.toString();
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
