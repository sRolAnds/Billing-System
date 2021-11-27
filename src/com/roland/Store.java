package com.roland;

import com.roland.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {

    private final String name;
    private final String address;
    private final String phoneNumber;
    private List<Product> inventory = new ArrayList<>(List.of(
            new Product(1, "Loren ipsun", 1.55),
            new Product(2, "Dolor", 2.34),
            new Product(3, "Sir amet", 3.32),
            new Product(4, "Consectelur adiping", 10.5),
            new Product(5, "Elit", 4.56),
            new Product(6, "Integer", 2.67),
            new Product(7, "Placerat massa", 37.64),
            new Product(8, "At velit", 17.80),
            new Product(9, "Nisi id", 0.54),
            new Product(10, "Sictum lacus", 53.70)
    ));
    private List<Cashier> cashiers = new ArrayList<>(List.of(new Cashier(1234)));
    private List<DiscountCard> validDiscountCards = List.of(
            new DiscountCard(1234, 5.0),
            new DiscountCard(2345, 7.0),
            new DiscountCard(3456, 10.0)
    );

    public Store(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Bill takeOrder(Order order) {
        Bill bill = new Bill();
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

    public void displayInfo() {
        System.out.printf("%35s\n", "CASH RECEIPT");
        System.out.printf("%37s\n", "SUPERMARKET " + name);
        System.out.printf("%45s\n", address);
        System.out.printf("%37s\n\n", "Tel: " + phoneNumber);
    }

    private Product getProductById(int id) throws UnknownProductException {
        for (Product product : inventory) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new UnknownProductException("No such product in store with id = " + id);
    }

    public void setDiscountPriceToProduct(int productId, double discountSize) throws UnknownProductException {
        Product product = getProductById(productId);
        product.setPromotional(true);
        product.setPrice(product.getPrice() * (100 - discountSize) / 100);
    }

    private DiscountCard getDiscountCardById(int id) throws UnknownDiscountCardException {
        for (DiscountCard discountCard : validDiscountCards) {
            if (discountCard.getId() == id) {
                return discountCard;
            }
        }
        throw new UnknownDiscountCardException("Store does not support discount card with id = " + id);
    }

    public void addProductToInventory(int id, String description, double price) {
        inventory.add(new Product(id, description, price));
    }

    public List<Product> getInventory() {
        return inventory;
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
