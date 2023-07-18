package service;

import entity.Cart;
import java.util.ArrayList;

public class CartData {
    private static CartData instance;
    private ArrayList<Cart> carts = new ArrayList<>();

    private CartData() {
    }

    public static synchronized CartData getInstance() {
        if (instance == null) {
            instance = new CartData();
        }
        return instance;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }
}
