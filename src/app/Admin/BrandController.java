package app.Admin;

import java.util.ArrayList;
import java.util.Scanner;
import entity.Brand;
import service.AllSql;

public class BrandController extends AllSql{
    public void brandAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. menambah data");
        System.out.println("2. menampilkan data");
        System.out.println("3. update data");
        System.out.println("4. delete data");
        System.out.print("pilih pilihan anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                create();
                break;
            
            case "2":
                select();
                break;
            case "3":
                select();
                update();
                break;

            case "4":
                select();
                delete();
                break;
            default:
                break;
        }
        scanner.close();
    }
    public void create(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Nama Brand: ");
        String namabrand = scanner.nextLine();
        String sql = "INSERT INTO brand (brand) VALUES ('"+namabrand+"')";
        this.sqlexupdate(sql);
        scanner.close();
    }
    public void update(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("data mana yang mau di update: ");
        String idUpdate = scanner.nextLine();
        System.out.print("masukkan data brand yang baru: ");
        String newbrand = scanner.nextLine();
        String sql = "UPDATE brand SET brand = '"+newbrand+"' WHERE id_brand= "+idUpdate+"";
        this.sqlexupdate(sql);
        scanner.close();
    }
    public void delete() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print(" data mana yang mau di delete ? ");
        String idDelete = scanner.nextLine();
        System.out.print("apakah anda yakin ? Product dengan category tersebut akan terhapus [y/n]");
        String pil = scanner.nextLine();
        if (pil.equals("y") || pil.equals("Y")) {
            String sql = "DELETE FROM brand WHERE id_brand= "+idDelete+"";
            this.sqlexupdate(sql);
            String sqlpro = "DELETE FROM product WHERE id_brand= "+idDelete+"";
            this.sqlexupdate(sqlpro);
        }
        scanner.close();
    }
    public void select(){
        try {
            ArrayList<Brand> list = this.selectBrand();
            for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getIdBrand() +" "+list.get(i).getBrand());
        }
        } catch (Exception e) {
            System.out.print("Something wrong in this area");
        }

    }
}
