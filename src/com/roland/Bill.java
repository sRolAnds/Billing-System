package com.roland;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bill {

    private Store store;
    private List<Item> items = new ArrayList<>();
    private DiscountCard discountCard;
    private double totalSum;

    private static final String CASH_RECEIPT_PATH = "src/com/roland/files/cash_receipt.txt";
    private static final double DISCOUNT_10 = 0.1;
    private static final int DISCOUNT_QNT = 6;

    public Bill(Store store) {
        this.store = store;
    }

    public void addItem(int quantity, Product product) {
        Item item = new Item(quantity, product);
        items.add(item);
    }

    public void printToFile() {
        try (FileWriter myWriter = new FileWriter(CASH_RECEIPT_PATH)) {
            store.printInfo(myWriter);
            myWriter.write("CASHIER: #" + store.getCashiers().get(0).getId());
            printCurrentDateTime(myWriter);
            printItems(myWriter);
            printSumDetails(myWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printSumDetails(FileWriter fileWriter) throws IOException {
        fileWriter.write(String.format("SUBTOTAL %45s\n", String.format("$%.2f", totalSum)));
        if (discountCard != null) {
            double discount = discountCard.getDiscount(totalSum);
            fileWriter.write(String.format("Discount by card %37s\n", String.format("$%.2f", discount)));
            totalSum -= discount;
        } else {
            fileWriter.write("Discount card was not used.\n");
        }
        fileWriter.write(String.format("TOTAL %48s\n", String.format("$%.2f", totalSum)));
    }

    private void printItems(FileWriter fileWriter) throws IOException {
        fileWriter.write(String.format("%5s\t%-20s\t%10s\t%10s\n\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL"));
        for (Item item : items) {
            double itemDiscount = 0.0;
            String priceFormat = String.format("$%.2f", item.product.getPrice());
            String totalPriceFormat = String.format("$%.2f", item.total);
            fileWriter.write(String.format("%4d\t%-20s\t%10s\t%10s\n", item.quantity, item.product.getDescription(),
                    priceFormat, totalPriceFormat));
            if (item.product.isPromotional() && item.quantity >= DISCOUNT_QNT) {
                itemDiscount = item.total * DISCOUNT_10;
                fileWriter.write(String.format("%30s%24s\n", "promotional item", String.format("-$%.2f", itemDiscount)));
            }
            totalSum = totalSum + item.total - itemDiscount;
        }
        fileWriter.write("-------------------------------------------------------\n");
        fileWriter.write("-------------------------------------------------------\n");
    }

    private void printCurrentDateTime(FileWriter fileWriter) throws IOException {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        fileWriter.write(String.format("%40s\n", "DATE: " + date.format(dateFormatter)));
        fileWriter.write(String.format("%52s\n", "TIME: " + time.format(timeFormatter)));
        fileWriter.write("-------------------------------------------------------\n");
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
