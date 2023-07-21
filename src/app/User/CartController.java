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
            System.out.println("2. Ubah Jumlah Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Checkout");
            System.out.println("5. back");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    showCart();
                    break;
                case "2":
                    showCart();
                    updateQuantity();
                    break;
                case "3":
                    showCart();
                    deleteSomeProduct();
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
        System.out.println("Pemesanan dibatalkan");
        return;
        
    }
    private void transaction(CartData cartData){
        BaseAuth baseAuth = BaseAuth.getInstance();
        if (!checkAllStock()) {
            return;
        }
        cartData = CartData.getInstance();
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            this.insertTransaksi(baseAuth.getUser().getIdUser(), jumlahHarga(cartData), StatusTransaksi.dipesan.value, cartData);
        }
        cartData.truncateCarts();
    }
    private float jumlahHarga(CartData cartData){
        float totalHarga = 0;
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            totalHarga += cartData.getCarts().get(i).getProdukDetail().getProduk().getHarga() * cartData.getCarts().get(i).getQuantity();
        }
        return totalHarga;
    }

    private boolean checkAllStock(){
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
        table.setHeaders("No", "Nama Produk", "Nama Brand", "Ukuran", "Warna", "Total Harga", "quantity");
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            float totalHarga = cartData.getCarts().get(i).getProdukDetail().getProduk().getHarga() * cartData.getCarts().get(i).getQuantity();
            table.addRow(i+1+"", cartData.getCarts().get(i).getProdukDetail().getProduk().getNamaProduct(),cartData.getCarts().get(i).getProdukDetail().getProduk().getBrand().getBrand(),cartData.getCarts().get(i).getProdukDetail().getUkuran()+"",cartData.getCarts().get(i).getProdukDetail().getWarna() , totalHarga+"",  cartData.getCarts().get(i).getQuantity()+"");
        }
        table.print();
        System.out.println("Total Harga : " + jumlahHarga(cartData));
    }
    private void updateQuantity(){
        CartData cartData = CartData.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomer produk yang ingin diubah : ");
        String noProduk = scanner.nextLine();
        if (cartData.getCarts().size() < Integer.parseInt(noProduk) || 1 > Integer.parseInt(noProduk)) {
            System.out.println("Masukkan nomer Produk dengan benar");
            return;
        }
        // stock checker
        if (cartData.getCarts().get(Integer.parseInt(noProduk)-1).getProdukDetail().getStock() == 0) {
            System.out.println("\n Stock Habis, Harap hapus barang \n");
            return;
        }
        int quantity = -1;
        while (quantity == -1) {
            quantity = quantityForm(scanner, cartData.getCarts().get(Integer.parseInt(noProduk)-1).getProdukDetail());
        }
        cartData.changeQuantitiy(Integer.parseInt(noProduk)-1, quantity);
        System.out.println("Berhasil diubah");
    }
    private void deleteSomeProduct(){
        CartData cartData = CartData.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomer produk yang ingin dihapus : ");
        String noProduk = scanner.nextLine();
        if (cartData.getCarts().size() < Integer.parseInt(noProduk) || 1 > Integer.parseInt(noProduk)) {
            System.out.println("Masukkan nomer Produk dengan benar");
            return;
        }
        System.out.println("Apakah anda yakin ingin menghapus produk ini ? [y/n] : ");
        String pilihan = scanner.nextLine();
        if (pilihan.equals("Y") || pilihan.equals("y")) {
            cartData.deleteCart(Integer.parseInt(noProduk)-1);
            System.out.println("Berhasil dihapus");
        }
        if (cartData.getCarts().isEmpty()) {
            // Redirect Jika cart kosong
            UserView userView = new UserView();
            try {
                System.out.println("\nCart Kosong");
                System.out.println("Redirect to User ... \n");
                Thread.sleep(2000);
                userView.menu();   
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    private int quantityForm(Scanner scanner, ProdukDetail pd){
        System.out.print("Masukkan Jumlah barang : ");
        String quantity = scanner.nextLine();
        if (pd.getStock() < Integer.parseInt(quantity)) {
            System.out.println("\n Stock Tidak Mencukupi\n");
            return -1;
        }else if(Integer.parseInt(quantity) <= 0){
            System.out.println("\n Jumlah harus melebihi 1 \n");
            return -1;
        }
        return Integer.parseInt(quantity);
    }
}
