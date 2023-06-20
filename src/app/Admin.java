package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import connection.Conn;
import entity.Brand;

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
                brand();
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
    public void brand() throws Exception {
        Conn connection = new Conn();
        String select = "SELECT*FROM brand";
        ResultSet rs = connection.getConnection().createStatement().executeQuery(select);
        ArrayList<Brand> list = new ArrayList<Brand>();
        while(rs.next()){
            Brand brand = new Brand(rs.getInt("id_brand"),rs.getString("brand"));
            list.add(brand);
            // System.out.println(rs.getString("id_brand")+" "+rs.getString("brand"));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. menambah data");
        System.out.println("2. menampilkan data");
        System.out.println("pilih pilihan anda");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
            System.out.print("masukkan nama brand");
            String namabrand = scanner.nextLine();  
            String insert = "INSERT INTO brand (brand) VALUES ('"+namabrand+"')";
            connection.getConnection().createStatement().executeUpdate(insert);
                break;
            
            case "2":
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getIdBrand() +" "+list.get(i).getBrand());
            }
            System.out.println(" ingin delete/update? [1,2]");
            String action = scanner.nextLine();
            System.out.println(action);
            if (action.equals("1")){
                System.out.println(" data mana yang mau di delete?");
                String id = scanner.nextLine();
                String Query = "DELETE FROM brand WHERE id_brand= "+id+"";
                 connection.getConnection().createStatement().executeUpdate(Query);
            } else if (action.equals("2")){
                System.out.println("data mana yang mau di update?");
                String id = scanner.nextLine();
                System.out.println("masukkan data brand yang baru");
                String newbrand = scanner.nextLine();
                String Query = "UPDATE brand SET brand = '"+newbrand+"' WHERE id_brand= "+id+"";
                connection.getConnection().createStatement().executeUpdate(Query);
            }
            
                break;
            default:
                break;
        }  
    }
}
