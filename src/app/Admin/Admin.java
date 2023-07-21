package app.Admin;
import java.util.Scanner;

import app.AuthView;
import service.BaseAuth;
import service.CommandLineCleaner;

public class Admin {
    public void menu() throws Exception {
        BaseAuth baseAuth = BaseAuth.getInstance();
        CommandLineCleaner clClear = new CommandLineCleaner();
        Scanner scanner = new Scanner(System.in);
            System.out.println("Selamat Datang Admin: "+ baseAuth.getUser().getUsername());
            System.out.println("1. List Brand");
            System.out.println("2. List Product");
            System.out.println("3. List Transaksi");
            System.out.println("4. List User");
            System.out.println("5. Logout");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
            clClear.clear();
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
            
                case "5":
                    baseAuth.setUser(null);
                    AuthView av = new AuthView();
                    av.View();
                default:
                    System.out.println("Pilihan tidak ada");
                    menu();
                    break;
            }
        // scanner.close();
    }
}
