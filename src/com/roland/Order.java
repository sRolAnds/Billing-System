package com.roland;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<Integer, Integer> items = new HashMap<>();
    private int discountCardId;

    public void addItem(int productId, int quantity) {
        items.put(productId, quantity);
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public boolean isDiscountCardUsed() {
        return discountCardId != 0;
    }

    public int getDiscountCardId() {
        return discountCardId;
    }

    public void setDiscountCardId(int discountCardId) {
        this.discountCardId = discountCardId;
    }
}
