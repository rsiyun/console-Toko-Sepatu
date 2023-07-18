package app.User;

import service.CartData;
import service.CommandLineTable;

public class CartController {
    public void showCart(){
        CartData cartData = CartData.getInstance();
        CommandLineTable table = new CommandLineTable();
        table.setShowVerticalLines(true);
        table.setHeaders("Nama Produk", "Ukuran", "Warna", "Total Harga", "quantity");
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            float totalHarga = cartData.getCarts().get(i).getProdukDetail().getProduk().getHarga() * cartData.getCarts().get(i).getQuantity();
            table.addRow(cartData.getCarts().get(i).getProdukDetail().getProduk().getNamaProduct(),cartData.getCarts().get(i).getProdukDetail().getUkuran()+"",cartData.getCarts().get(i).getProdukDetail().getWarna() , totalHarga+"",  cartData.getCarts().get(i).getQuantity()+"");
        }
        table.print();
    }

}
