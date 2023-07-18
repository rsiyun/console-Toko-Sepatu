package app.User;

import java.util.Scanner;
import service.CommandLineCleaner;

public class UserView {
    Scanner scanner;
    public void menu() throws Exception{
        CommandLineCleaner clClear = new CommandLineCleaner();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat Datang User: ");
        System.out.println("1. List Product");
        System.out.println("2. History Transaksi");
        System.out.println("3. Keranjang");
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
                System.out.println("Keranjang");
                
                break;
        
            default:
                System.out.println("Pilihan tidak ada");
                menu();
                break;
        }
        scanner.close();   
    }
}

