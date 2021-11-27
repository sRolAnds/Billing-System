package com.roland;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bill {

    private List<Item> items = new ArrayList<>();
    private DiscountCard discountCard;
    private double totalSum;

    private static final double DISCOUNT_10 = 0.1;
    private static final int DISCOUNT_QNT = 6;

    public Bill() {}

    public void addItem(int quantity, Product product) {
        Item item = new Item(quantity, product);
        items.add(item);
    }

    public void displayInfo(Store store) {
        store.displayInfo();
        System.out.print("CASHIER: #" + store.getCashiers().get(0).getId());
        displayCurrentDateTime();
        displayItems();
        displaySumDetails();
    }

    private void displaySumDetails() {
        System.out.printf("SUBTOTAL %45s\n", String.format("$%.2f", totalSum));
        if (discountCard != null) {
            double discount = discountCard.getDiscount(totalSum);
            System.out.printf("Discount by card %37s\n", String.format("$%.2f", discount));
            totalSum -= discount;
        } else {
            System.out.println("Discount card was not used.");
        }
        System.out.printf("TOTAL %48s\n", String.format("$%.2f", totalSum));
    }

    private void displayItems() {
        System.out.printf("%5s\t%-20s\t%10s\t%10s\n\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL");
        for (Item item : items) {
            double itemDiscount = 0.0;
            String priceFormat = String.format("$%.2f", item.product.getPrice());
            String totalPriceFormat = String.format("$%.2f", item.total);
            System.out.printf("%4d\t%-20s\t%10s\t%10s\n", item.quantity, item.product.getDescription(),
                    priceFormat, totalPriceFormat);
            if (item.product.isPromotional() && item.quantity >= DISCOUNT_QNT) {
                itemDiscount = item.total * DISCOUNT_10;
                System.out.printf("%30s%24s\n", "promotional item", String.format("-$%.2f", itemDiscount));
            }
            totalSum = totalSum + item.total - itemDiscount;
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
    }

    private void displayCurrentDateTime() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.printf("%40s\n", "DATE: " + date.format(dateFormatter));
        System.out.printf("%52s\n", "TIME: " + time.format(timeFormatter));
        System.out.println("-------------------------------------------------------");
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    private static class Item {

        private final int quantity;
        private final Product product;
        private final double total;

        public Item(int quantity, Product product) {
            this.quantity = quantity;
            this.product = product;
            total = this.quantity * this.product.getPrice();
        }
    }
}
