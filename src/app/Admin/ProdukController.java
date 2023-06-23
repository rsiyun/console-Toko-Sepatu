package app.Admin;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Produk;
import service.AllSql;
import service.CommandLineTable;

public class ProdukController extends AllSql{
    public void ProductAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Menampilkan data");
        System.out.println("2. Menambah data");
        System.out.println("3. Mengubah data");
        System.out.println("4. Menghapus data");
        System.out.print("Pilih Pilihan anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                showProduk();
                break;
        
            case "2":
                    
                break;
        
            case "3":
                System.out.println("Menghapus");
                break;
        
            case "4":
                System.out.println("User");
                break;
        
            default:
                break;
        }
        scanner.close();
    }

    private void showProduk(){
        try {
            ArrayList<Produk> list = this.selectProduk();
            CommandLineTable st = new CommandLineTable();
            st.setShowVerticalLines(true);
            st.setHeaders("id produk", "Brand", "Nama Produk", "Harga");
            for (int i = 0; i < list.size(); i++) {
                st.addRow(Integer.toString(list.get(i).getIdProduk()), list.get(i).getBrand().getBrand(), list.get(i).getNamaProduct(), String.valueOf(list.get(i).getHarga()));
            }
            st.print();

        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
