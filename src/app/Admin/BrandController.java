package app.Admin;

import java.util.ArrayList;
import java.util.Scanner;
import entity.Brand;
import service.AllSql;

public class BrandController extends AllSql{
    public void brandAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Menampilkan data");
        System.out.println("2. Menambahkan data");
        System.out.println("3. Mengubah data");
        System.out.println("4. Menghapus data");
        System.out.print("pilih pilihan anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                select();
            break;
            
            case "2":
                create();
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
    public void update() throws Exception{
        ArrayList<Brand> list = this.selectBrand();
        Scanner scanner = new Scanner(System.in);
        System.out.print("data mana yang mau di update: ");
        String idbrand = scanner.nextLine();
        if (!checkBrand(list, idbrand)) {
            System.out.print("Tolong Data dengan benar");
            scanner.close();
            return;
        }
        // BELUM ADA PENGECHECKAN MENGENAI DATA YANG DIPILIH OLEH USER
        System.out.print("masukkan data brand yang baru: ");
        String newbrand = scanner.nextLine();
        String sql = "UPDATE brand SET brand = '"+newbrand+"' WHERE id_brand= "+idbrand+"";
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
    private boolean checkBrand(ArrayList<Brand> list, String idbrand){
        boolean t = false;
        for (int i = 0; i < list.size(); i++) {            
            if (idbrand.equals(Integer.toString(list.get(i).getIdBrand()))) {
                t = true;
            }
        }
        return t;
    }
}
