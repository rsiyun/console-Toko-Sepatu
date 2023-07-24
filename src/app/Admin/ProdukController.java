package app.Admin;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Brand;
import entity.Produk;
import entity.ProdukDetail;
import service.AllSql;
import service.CommandLineTable;

public class ProdukController extends AllSql{
    public void ProductAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean endwhile = true;
        while (endwhile) {
            System.out.println("1. Menampilkan data");
            System.out.println("2. Menambah data");
            System.out.println("3. Mengubah data");
            System.out.println("4. Menghapus data");
            System.out.println("5. Detail Produk");
            System.out.println("6. back");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    showProduk();
                    break;
            
                case "2":
                    createProduk();
                    break;
            
                case "3":
                    if(showProduk())
                        updateProduk();
                    break;
            
                case "4":
                    if(showProduk())
                        deleteProduk();
                    break;
    
                case "5":
                    if(showProduk())
                        produkDetailAdmin();
                    break;
            
                case "6":
                    endwhile = false;
                    Admin adminView = new Admin();
                    adminView.menu();
                    break;
                default:
                    System.out.println("Pilihan tidak ada");
                    break;
            }
        }
        scanner.close();
    }

    private void updateProduk() throws Exception{
        ArrayList<Produk> list = this.selectProduk();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih id produk: ");
        // CHECK ADA TIDAK ID yang diinputkan user
        String idproduk = scanner.nextLine();
        if (!checkProduk(list, idproduk)) { 
            System.out.print("Tolong Input dengan benar");
            scanner.close();
            return;
        }
        // PEMILIHAN TABLE BRAND
        ArrayList<Brand> listB = this.selectBrand();
        for (int i = 0; i < listB.size(); i++) {
            System.out.println(listB.get(i).getIdBrand() +" "+listB.get(i).getBrand());
        }
        // CHECK ADA TIDAK ID yang diinputkan user
        System.out.print("Pilih id idBrand: ");
        String idbrand = scanner.nextLine();
        if (!checkBrand(listB, idbrand)) { 
            System.out.print("Tolong Input dengan benar");
            // scanner.close();
            return;
        }

        // AMBIL OLD DATANYA
        Produk oldData = getProdukbyid(list, idproduk);
        System.out.print("Nama Produk: ");
        String namaproduk = scanner.nextLine();
        System.out.print("masukkan harga: ");
        String harga = scanner.nextLine();
        // JIKA YANG DIISI KOSONG MAKA GUNAKAN DATA OLD
        String sql = "UPDATE produk SET id_brand = "+(idbrand.isEmpty() ? oldData.getidBrand() : idbrand)+", nama_product = '"+(namaproduk.isEmpty() ? oldData.getNamaProduct() : namaproduk)+"', harga = "+String.valueOf(harga.isEmpty() ? oldData.getHarga() : harga)+" WHERE id_produk ="+idproduk+"";
        this.sqlexupdate(sql);
        // scanner.close(); 
    }

    private void deleteProduk()throws Exception{
        ArrayList<Produk> list = this.selectProduk();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih id produk: ");
        // CHECK ADA TIDAK ID yang diinputkan user
        String idproduk = scanner.nextLine();
        if (!checkProduk(list, idproduk)) { 
            System.out.print("Tolong Input dengan benar");
            // scanner.close();
            return;
        }
        System.out.print("apakah anda yakin ? Product Detail dengan Product tersebut akan terhapus [y/n]");
        String pil = scanner.nextLine();
        if (pil.equals("y") || pil.equals("Y")) {
            String sql = "DELETE FROM produk WHERE id_produk = "+idproduk+";";
            this.sqlexupdate(sql);
        }
        // scanner.close(); 
    }

    public void createProduk()throws Exception{
        CommandLineTable table = new CommandLineTable();
        ArrayList<Brand> list = this.selectBrand();
        if (list.size() == 0) {
            System.out.println("Data Brand Kosong, silahkan input data Brand terlebih dahulu");
            return;
        }
        table.setShowVerticalLines(true);
        table.setHeaders("Id Brand", "Nama Brand");
        for (int i = 0; i < list.size(); i++) {
            table.addRow(list.get(i).getIdBrand()+"", list.get(i).getBrand()+"");
        }
        table.print();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih id brand: ");
        String idbrand = scanner.nextLine();
        if (!checkBrand(list, idbrand)) { 
            System.out.print("Tolong Input dengan benar");
            // scanner.close();
            return;
        }
        System.out.print("Nama Produk: ");
        String namaproduk = scanner.nextLine();
        System.out.print("masukkan harga: ");
        String harga = scanner.nextLine();
        String sql = "INSERT INTO produk (id_brand, nama_product, harga) VALUES ("+idbrand+",'"+namaproduk+"',"+String.valueOf(harga)+")";
        this.sqlexupdate(sql);
        // scanner.close(); 
    }
    private boolean showProduk(){
        try {
            ArrayList<Produk> list = this.selectProduk();
            if (list.size() == 0) {
                System.out.println("Data Produk Kosong, silahkan input data terlebih dahulu");
                return false;
            }
            CommandLineTable st = new CommandLineTable();
            st.setShowVerticalLines(true);
            st.setHeaders("ID Produk", "Nama Produk", "ID Brand", "Nama Brand", "Harga");
            for (int i = 0; i < list.size(); i++) {
                st.addRow(list.get(i).getIdProduk()+"", list.get(i).getNamaProduct(), list.get(i).getidBrand()+"", list.get(i).getBrand().getBrand(), list.get(i).getHarga()+"");
            }
            st.print();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }
    public void produkDetailAdmin() throws Exception{
        ArrayList<Produk> list = this.selectProduk();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih produk yang ingin dilihat detailnya: ");
        String idproduk = scanner.nextLine();
        if (!checkProduk(list, idproduk)) { 
            System.out.print("Tolong Input dengan benar");
            // scanner.close();
            return;
        }
        System.out.println("1. Menampilkan data");
        System.out.println("2. Menambah data");
        System.out.println("3. Mengubah data");
        System.out.println("4. Menghapus data");
        System.out.println("5. Back");
        System.out.print("pilih pilihan anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                showProdukDetail(Integer.parseInt(idproduk));
                break;
            
            case "2":
                createProdukDetail(Integer.parseInt(idproduk));
                break;
            case "3":
                if(showProdukDetail(Integer.parseInt(idproduk)))
                    updateProdukDetail(Integer.parseInt(idproduk));
                break;

            case "4":
                if(showProdukDetail(Integer.parseInt(idproduk)))
                    deleteProdukDetail(Integer.parseInt(idproduk));
                break;
            case "5":
                ProductAdmin();
                break;
            default:
                break;
        }
        // scanner.close();
    }
    public void createProdukDetail(int idproduk){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Ukuran Produk: ");
        String ukuranproduk = scanner.nextLine();
        System.out.print("Masukkan Warna Produk: ");
        String warnaproduk = scanner.nextLine();
        System.out.print("Masukkan Stok Produk: ");
        String stokproduk = scanner.nextLine();
        String sql = "INSERT INTO produk_detail (id_produk, ukuran, warna, stock) VALUES ("+idproduk+", "+ukuranproduk+", '"+warnaproduk+"', "+stokproduk+")";
        this.sqlexupdate(sql);
        // scanner.close();
    }
    public void updateProdukDetail(int newidproduk) throws Exception{
        Scanner scanner = new Scanner(System.in);
        ArrayList<ProdukDetail> list = this.selectProdukDetailbyIdproduk(newidproduk);
        System.out.print("data mana yang mau di update: ");
        String idUpdate = scanner.nextLine();
        if (!checkProdukDetail(list, idUpdate)) {
            System.out.print("Tolong Input dengan benar");
            // scanner.close();
            return;
        }
        ProdukDetail oldData =  getProdukDetailById(list, idUpdate);
        System.out.print("masukkan data ukuran produk yang baru: ");
        String newukuranproduk = scanner.nextLine();
        System.out.print("masukkan data warna produk yang baru: ");
        String newwarnaproduk = scanner.nextLine();
        System.out.print("masukkan data stok produk yang baru: ");
        String newstokproduk = scanner.nextLine();
        String sql = "UPDATE produk_detail SET id_produk = "+newidproduk+", ukuran = "+(newukuranproduk.isEmpty() ? oldData.getUkuran() : newukuranproduk)+", warna = '"+(newwarnaproduk.isEmpty() ? oldData.getWarna() : newwarnaproduk)+"', stock = "+(newstokproduk.isEmpty() ? oldData.getStock() : newstokproduk)+" WHERE id_produk_detail= "+idUpdate+"";
        this.sqlexupdate(sql);
        // scanner.close();
    }
    public void deleteProdukDetail(int idproduk) throws Exception{
        ArrayList<ProdukDetail> list = this.selectProdukDetailbyIdproduk(idproduk);
        Scanner scanner = new Scanner(System.in);
        System.out.print(" data mana yang mau di delete ? ");
        String idDelete = scanner.nextLine();
        if (!checkProdukDetail(list, idDelete)) {
            System.out.print("Tolong Input dengan benar");
            // scanner.close();
            return;
        }
        String sql = "DELETE FROM produk_detail WHERE id_produk_detail = "+idDelete+"";
        this.sqlexupdate(sql);
        // scanner.close();
    }
    public boolean showProdukDetail(int id) throws Exception{
        ArrayList<ProdukDetail> list = this.selectProdukDetailbyIdproduk(id);
        if (list.size() == 0) {
            System.out.println("Data Produk Detail Kosong, silahkan input data terlebih dahulu");
            return false;
        }
        CommandLineTable cmd = new CommandLineTable();
        cmd.setShowVerticalLines(true);
        cmd.setHeaders("ID Produk Detail", "ID Produk", "Nama Produk", "Nama Brand", "Harga", "Ukuran", "Warna", "Stok");
        for (int i = 0; i < list.size(); i++) {
            cmd.addRow(list.get(i).getIdProdukDetail()+"", list.get(i).getidProduk()+"", list.get(i).getProduk().getNamaProduct(), list.get(i).getProduk().getBrand().getBrand(), list.get(i).getProduk().getHarga()+"", list.get(i).getUkuran()+"", list.get(i).getWarna(), list.get(i).getStock()+"");
        }
        cmd.print();
        return true;
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
    private boolean checkProduk(ArrayList<Produk> list, String idProduk){
        boolean t = false;
        for (int i = 0; i < list.size(); i++) {            
            if (idProduk.equals(Integer.toString(list.get(i).getIdProduk()))) {
                t = true;
            }
        }
        return t;
    }
    private boolean checkProdukDetail(ArrayList<ProdukDetail> list, String idProdukDetail){
        boolean t = false;
        for (int i = 0; i < list.size(); i++) {            
            if (idProdukDetail.equals(Integer.toString(list.get(i).getIdProdukDetail()))) {
                t = true;
            }
        }
        return t;
    }
    private Produk getProdukbyid(ArrayList<Produk> list, String idproduk){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdProduk() == Integer.parseInt(idproduk)) {
                Produk oldData = new Produk(list.get(i).getIdProduk(), list.get(i).getidBrand(), list.get(i).getNamaProduct(), list.get(i).getHarga(), list.get(i).getBrand());
                return oldData;
            }
        }
        return null;
    }
    private ProdukDetail getProdukDetailById(ArrayList<ProdukDetail> list, String idprodukdetail){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdProdukDetail() == Integer.parseInt(idprodukdetail)) {
                ProdukDetail oldData = new ProdukDetail(list.get(i).getIdProdukDetail(), list.get(i).getidProduk(), list.get(i).getUkuran(), list.get(i).getWarna(), list.get(i).getStock(), list.get(i).getProduk());
                return oldData;
            }
        }
        return null;
    }
}
