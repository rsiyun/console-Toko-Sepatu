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

    public void truncateCarts(){
        this.carts.clear();
    }

    public void changeQuantitiy(int index, int quantity){
        for (int i = 0; i < this.carts.size(); i++) {
            if (index == i) {
                this.carts.get(i).setQuantity(quantity);
            }            
        }
    }

    public void deleteCart(int index){
        for (int i = 0; i < this.carts.size(); i++) {
            if (index == i) {
                this.carts.remove(i);
            }            
        }
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }
}
