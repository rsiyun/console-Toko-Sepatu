package app.User;

import java.util.ArrayList;
import java.util.Scanner;

import entity.ProdukDetail;
import service.AllSql;
import service.BaseAuth;
import service.CartData;
import service.CommandLineTable;
import service.Enum.StatusTransaksi;

public class CartController  extends AllSql {

    public void mainCart()  throws Exception{
        CartData cartData = CartData.getInstance();
        boolean endwhile = true;
        if (cartData.getCarts().isEmpty()) {
            System.out.println("\n Cart Masih Kosong \n");
            System.out.println("Await for second ...");
            Thread.sleep(2000);
            UserView userView = new UserView();
            userView.menu();
            return;
        }
        while(endwhile){
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Tampilkan Semua cart");
            System.out.println("2. Ubah Quantity");
            System.out.println("3. Hapus Cart");
            System.out.println("4. Checkout");
            System.out.println("5. back");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    showCart();
                    break;
                case "2":
                    System.out.print("Ubah Quantity");
                    break;
                case "3":
                    System.out.print("Hapus Cart");
                    break;
                case "4" :
                    // showCart();
                    cartData = CartData.getInstance();
                    processTransaction();
                    break;
                case "5":
                    endwhile = false;
                    UserView userView = new UserView();
                    userView.menu();
                    break;
                default:
                    System.out.println("Pilihan tidak ada");
                    break;
            }
        }
    }
    private void processTransaction(){
        CartData cartData = CartData.getInstance();
        System.out.print("Apakah anda yakin ingin membeli ini ? [y/n] *pembayaran berada pada halaman transaksi : ");
        Scanner scanner = new Scanner(System.in);
        String pilihan = scanner.nextLine();
        if (pilihan.equals("Y") || pilihan.equals("y")) {
            transaction(cartData);
            return;
        }
        System.out.println("Pembayaran dibatalkan");
        return;
        
    }
    private void transaction(CartData cartData){
        BaseAuth baseAuth = BaseAuth.getInstance();
        if (!checkStock()) {
            return;
        }
        cartData = CartData.getInstance();
        float allHarga = 0;
        
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            allHarga += cartData.getCarts().get(i).getProdukDetail().getProduk().getHarga();
        }
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            this.insertTransaksi(baseAuth.getUser().getIdUser(), allHarga, StatusTransaksi.dipesan.value, cartData);
        }
        cartData.truncateCarts();
        

    }
    private boolean checkStock(){
        try {
            perbaruiStock();
        } catch (Exception e) {
            System.out.println(e);
        }
        boolean quantityR = true;
        
        CartData cartData = CartData.getInstance();
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            if (cartData.getCarts().get(i).getProdukDetail().getStock() < cartData.getCarts().get(i).getQuantity()) {
                System.out.println("\n Stock tidak mencukupi \n");
                System.out.println("\n Stock" + cartData.getCarts().get(i).getProdukDetail().getIdProdukDetail() + " "+ cartData.getCarts().get(i).getProdukDetail().getProduk().getNamaProduct() + " tolong diubah\n");
                quantityR = false;
            }         
        }
       return quantityR;
    }
    private void perbaruiStock() throws Exception{
        CartData cartData = CartData.getInstance();
        ArrayList<ProdukDetail> produkDetail = this.selectProdukDetail();
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            for (int j = 0; j < produkDetail.size(); j++) {
                if (cartData.getCarts().get(i).getIdProdukDetail() == produkDetail.get(j).getIdProdukDetail()) {
                    cartData.getCarts().get(i).getProdukDetail().setStock(produkDetail.get(i).getStock());
                }
            }
        }
    }
    private void showCart(){
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
