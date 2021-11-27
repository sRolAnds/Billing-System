package com.roland;

public class AppRunner {

    public static void main(String[] args) {
        Store store = new Store("Lidl", "14, Stare Mesto, Kosice/Slovakia",
                "123-456-7890");
        store.setDiscountPriceToProduct(2, 10);
        Order order = makeOrder(args);
        Bill bill = store.takeOrder(order);
        bill.displayInfo(store);
    }

    private static Order makeOrder(String[] args) {
        Order order = new Order();
        for (String arg : args) {
            String[] s = arg.split("-");
            if (Character.isDigit(arg.charAt(0))) {
                int productId = Integer.parseInt(s[0]);
                int quantity = Integer.parseInt(s[1]);
                order.addItem(productId, quantity);
            } else {
                order.setDiscountCardId(Integer.parseInt(s[1]));
            }
        }
        return order;
    }
}
