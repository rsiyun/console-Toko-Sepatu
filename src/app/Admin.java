package app;

import java.util.Scanner;

public class Admin {
    public void menu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat Datang Admin: ");
        System.out.println("1. List Brand");
        System.out.println("2. List Product");
        System.out.println("3. List Transaksi");
        System.out.println("4. List User");
        System.out.println("Pilih Pilihan anda");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                System.out.println("Brand");
                break;
        
            case "2":
                System.out.println("product");
                break;
        
            case "3":
                System.out.println("transaksi");
                break;
        
            case "4":
                System.out.println("User");
                break;
        
            default:
                break;
        }
        scanner.close();
    }
}
