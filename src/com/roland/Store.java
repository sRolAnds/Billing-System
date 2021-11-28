package com.roland;

import com.roland.exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Store {

    private static final String INVENTORY_PATH = "src/com/roland/files/products.txt";
    private static final String CARDS_PATH = "src/com/roland/files/cards.txt";

    private final String name;
    private final String address;
    private final String phoneNumber;
    private List<Product> inventory;
    private List<DiscountCard> discountCards;
    private List<Cashier> cashiers = new ArrayList<>(List.of(
            new Cashier(1234)
    ));

    public Store(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.inventory = readProductsFromFile();
        this.discountCards = readDiscountCardsFromFile();
    }

    public Bill takeOrder(Order order) {
        Bill bill = new Bill(this);
        for (Map.Entry<Integer, Integer> entry : order.getItems().entrySet()) {
            try {
                Product product = getProductById(entry.getKey());
                int quantity = entry.getValue();
                bill.addItem(quantity, product);
            } catch (UnknownProductException exception) {
                System.out.println(exception.getMessage());
            }
        }
        if (order.isDiscountCardUsed()) {
            try {
                DiscountCard discountCard = getDiscountCardById(order.getDiscountCardId());
                bill.setDiscountCard(discountCard);
            } catch (UnknownDiscountCardException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return bill;
    }

    private static List<String> readDataFromFile(String filePath) {
        List<String> data = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNextLine()) {
                data.add(reader.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<Product> readProductsFromFile() {
        List<Product> products = new ArrayList<>();
        for (String item : readDataFromFile(INVENTORY_PATH)) {
            Product product = parseProduct(item);
            products.add(product);
        }
        return products;
    }

    public List<DiscountCard> readDiscountCardsFromFile() {
        List<DiscountCard> cards = new ArrayList<>();
        for (String item : readDataFromFile(CARDS_PATH)) {
            DiscountCard discountCard = parseCard(item);
            cards.add(discountCard);
        }
        return cards;
    }

    private static Product parseProduct(String data) {
        String[] s = data.split(",");
        int productId = Integer.parseInt(s[0]);
        String productName = s[1];
        double productPrice = Double.parseDouble(s[2]);
        return new Product(productId, productName, productPrice);
    }

    private static DiscountCard parseCard(String data) {
        String[] s = data.split(",");
        int cardId = Integer.parseInt(s[0]);
        double cardDiscount = Double.parseDouble(s[1]);
        return new DiscountCard(cardId, cardDiscount);
    }

    public void printInfo(FileWriter fileWriter) throws IOException {
        fileWriter.write(String.format("%35s\n", "CASH RECEIPT"));
        fileWriter.write(String.format("%37s\n", "SUPERMARKET " + name));
        fileWriter.write(String.format("%45s\n", address));
        fileWriter.write(String.format("%37s\n\n", "Tel: " + phoneNumber));
    }

    private Product getProductById(int id) throws UnknownProductException {
        for (Product product : inventory) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new UnknownProductException("No such product in store with id = " + id);
    }

    public void setDiscountPriceToProduct(int productId, double discountSize) {
        try {
            Product product = getProductById(productId);
            product.setPromotional(true);
            product.setPrice(product.getPrice() * (100 - discountSize) / 100);
        } catch (UnknownProductException e) {
            System.out.println("We cannot set another price to product, which does not exist");
        }
    }

    private DiscountCard getDiscountCardById(int id) throws UnknownDiscountCardException {
        for (DiscountCard discountCard : discountCards) {
            if (discountCard.getId() == id) {
                return discountCard;
            }
        }
        throw new UnknownDiscountCardException("Store does not support discount card with id = " + id);
    }

//    public void addProductToInventory(int id, String description, double price) {
//        inventory.add(new Product(id, description, price));
//    }

    public List<Product> getInventory() {
        return inventory;
    }

    public List<DiscountCard> getDiscountCards() {
        return discountCards;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
