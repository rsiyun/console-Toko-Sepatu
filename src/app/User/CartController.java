package app.User;

import entity.Cart;
import service.CartData;

public class CartController {
    
    public void showCart(){
        CartData cartData = CartData.getInstance();
        Cart cart = new Cart(1, 1, 1, null);

        cartData.addCart(cart);
    }
}
