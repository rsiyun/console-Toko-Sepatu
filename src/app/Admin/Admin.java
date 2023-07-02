package app.Admin;
import java.util.Scanner;

import entity.Produk;

public class Admin {
    public void menu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat Datang Admin: ");
        System.out.println("1. List Brand");
        System.out.println("2. List Product");
        System.out.println("3. List Transaksi");
        System.out.println("4. List User");
        System.out.print("Pilih Pilihan anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                BrandController bc = new BrandController();
                bc.brandAdmin();
                break;
        
            case "2":
                ProdukController pdc = new ProdukController();
                pdc.ProductAdmin();
                break;
        
            case "3":
                TransaksiController tc = new TransaksiController();
                tc.TransaksiAdmin();
                break;
        
            case "4":
                UserController uc = new UserController();
                uc.UserAdmin();
                break;
        
            default:
                break;
        }
        scanner.close();
    }
}
