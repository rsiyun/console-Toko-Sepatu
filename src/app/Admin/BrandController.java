package app.Admin;

import java.util.ArrayList;
import java.util.Scanner;
import entity.Brand;
import service.AllSql;
import service.CommandLineTable;

public class BrandController extends AllSql{
    public void brandAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean endwhile = true;
        while(endwhile) {
            System.out.println("1. Menampilkan data");
            System.out.println("2. Menambahkan data");
            System.out.println("3. Mengubah data");
            System.out.println("4. Menghapus data");
            System.out.println("5. back");
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
                    if(select())
                        update();
                    break;
    
                case "4":
                    if(select())
                        delete();
                    break;
                case "5":
                    endwhile = false;
                    Admin adminView = new Admin();
                    adminView.menu();
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }
    public void create(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Nama Brand: ");
        String namabrand = scanner.nextLine();
        String sql = "INSERT INTO brand (brand) VALUES ('"+namabrand+"')";
        this.sqlexupdate(sql);
        // scanner.close();
    }
    public void update() throws Exception{
        ArrayList<Brand> list = this.selectBrand();
        Scanner scanner = new Scanner(System.in);
        System.out.print("data mana yang mau di update: ");
        String idbrand = scanner.nextLine();
        if (!checkBrand(list, idbrand)) {
            System.out.println("Tolong Input dengan benar");
            // scanner.close();
            return;
        }
        System.out.print("masukkan data brand yang baru: ");
        String newbrand = scanner.nextLine();
        String sql = "UPDATE brand SET brand = '"+newbrand+"' WHERE id_brand= "+idbrand+"";
        this.sqlexupdate(sql);
        // scanner.close();
    }
    public void delete() throws Exception{
        ArrayList<Brand> list = this.selectBrand();
        Scanner scanner = new Scanner(System.in);
        System.out.print(" data mana yang mau di delete ? ");
        String idDelete = scanner.nextLine();
        if (!checkBrand(list, idDelete)) {
            System.out.println("Tolong Input dengan benar");
            // scanner.close();
            return;
        }
        System.out.print("apakah anda yakin ? Product dengan category tersebut akan terhapus [y/n]");
        String pil = scanner.nextLine();
        if (pil.equals("y") || pil.equals("Y")) {
            String sql = "DELETE FROM brand WHERE id_brand= "+idDelete+"";
            this.sqlexupdate(sql);
        }
        // scanner.close();
    }
    public boolean select(){
        try {
            ArrayList<Brand> list = this.selectBrand();
            if (list.size() == 0) {
                System.out.println("Data Brand kosong, silahkan input data terlebih dahulu");
                return false;
            }
            CommandLineTable table = new CommandLineTable();
            table.setShowVerticalLines(true);
            table.setHeaders("Id Brand", "Nama Brand");
            for (int i = 0; i < list.size(); i++) {
                table.addRow(list.get(i).getIdBrand()+"", list.get(i).getBrand()+"");
            }
            table.print();
            return true;
        } catch (Exception e) {
            System.out.print("Something wrong in this area");
            return false;
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
