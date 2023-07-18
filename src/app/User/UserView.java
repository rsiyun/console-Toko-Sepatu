package app.User;

import java.util.Scanner;

import app.AuthView;
import service.BaseAuth;
import service.CommandLineCleaner;

public class UserView {
    Scanner scanner;
    public void menu() throws Exception{
        BaseAuth baseAuth = BaseAuth.getInstance();
        CommandLineCleaner clClear = new CommandLineCleaner();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat Datang User: " + baseAuth.getUser().getUsername());
        System.out.println("1. Belanja Sepatu");
        System.out.println("2. History Transaksi");
        System.out.println("3. Keranjang");
        System.out.println("4. Logout");
        System.out.print("Pilih Pilihan anda: ");
        String pilihan = scanner.nextLine();
        clClear.clear();
        switch (pilihan) {
            case "1":
                ProdukController pdc = new ProdukController();
                pdc.ProdukUser();
                break;
            case "2":
                System.out.println("Transaksi");
                break;
        
            case "3":
                CartController cartCon = new CartController();
                cartCon.showCart();
                break;
            case "4":
                baseAuth.setUser(null);
                AuthView av = new AuthView();
                av.View();
                break;
            default:
                System.out.println("Pilihan tidak ada");
                menu();
                break;
        }
        // scanner.close();   
    }
}

