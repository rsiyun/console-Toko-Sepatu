package app.User;
import java.util.ArrayList;
import java.util.Scanner;
import entity.Produk;
import entity.ProdukDetail;
import entity.Brand;
import service.AllSql;
import service.CommandLineTable;

public class ProdukController extends AllSql{
    public void ProdukUser()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Menampilkan data Produk");
        System.out.println("2. Cari Produk");
        System.out.print("Pilih Pilihan anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                showProduk();
                ProdukDetailUser();
                break;
            case "2":
                searchProduk();
                ProdukDetailUser();
                break;
            default:
                System.out.println("Pilihan tidak ada");
                break;
        }
        scanner.close();
    }
    public void showProduk() throws Exception{
        try{
            ArrayList<Produk> listProduk = this.selectProduk();
            CommandLineTable table = new CommandLineTable();
            table.setShowVerticalLines(true);
            table.setHeaders("Id Produk", "Nama Produk", "Nama Brand", "Harga");
            for (Produk produk : listProduk) {
                Brand brand = getBrandByProduk(produk);
                table.addRow(String.valueOf(produk.getIdProduk()), produk.getNamaProduct(), brand.getBrand(), String.valueOf(produk.getHarga()));
            }
            table.print();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void ProdukDetailUser() throws Exception{
        ArrayList<Produk> listProduk = this.selectProduk();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan id produk yang ingin di lihat detailnya : ");
        String idProduk = scanner.nextLine();
        if(!checkProduk(listProduk, idProduk)){
            System.out.println("Tolong Input id dengan benar");
            scanner.close();
            return;
        }
        scanner.close();
        Produk produkTerpilih = getProdukbyid(listProduk, idProduk);
        showProdukTerpilih(produkTerpilih);
    }

    private void showProdukTerpilih(Produk produkTerpilih) throws Exception{
        try{
            Produk produk = produkTerpilih;
            Brand brand = getBrandByProduk(produk);
            CommandLineTable tableProduk = new CommandLineTable();
            tableProduk.setShowVerticalLines(true);
            tableProduk.setHeaders("Nama Produk", "Nama Brand", "Harga");
            tableProduk.addRow(produk.getNamaProduct(), brand.getBrand(), String.valueOf(produk.getHarga()));
            tableProduk.print();
            showProdukDetail(produk.getIdProduk());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void showProdukDetail(int idProduk) throws Exception{
        ArrayList<ProdukDetail> listProdukDetail = this.selectProdukDetail();
        CommandLineTable tableProdukDetail = new CommandLineTable();
        tableProdukDetail.setShowVerticalLines(true);
        tableProdukDetail.setHeaders("Id Produk Detail", "Ukuran", "Warna", "Stok");
        for (ProdukDetail produkDetail : listProdukDetail) {
            if (produkDetail.getidProduk() == idProduk) {
                tableProdukDetail.addRow(String.valueOf(produkDetail.getIdProdukDetail()), String.valueOf(produkDetail.getUkuran()), produkDetail.getWarna(), String.valueOf(produkDetail.getStock()));
            }
        }
        tableProdukDetail.print();
    }
    public void searchProduk() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama produk yang ingin dicari : ");
        String namaProduk = scanner.nextLine();
        ArrayList<Produk> listProduk = this.listProdukbySearch(namaProduk);
        CommandLineTable table = new CommandLineTable();
        table.setShowVerticalLines(true);
        table.setHeaders("Id Produk", "Nama Produk", "Nama Brand","Harga");
        for (Produk produk : listProduk) {
            Brand brand = getBrandByProduk(produk);
            table.addRow(String.valueOf(produk.getIdProduk()), produk.getNamaProduct(), brand.getBrand(), String.valueOf(produk.getHarga()));
        }
        table.print();
        scanner.close();
    }
    public ArrayList<Produk> listProdukbySearch(String namaProduk) throws Exception{
        ArrayList<Produk> listProduk = this.selectProduk();
        ArrayList<Produk> listProdukbySearch = new ArrayList<Produk>();
        for (Produk produk : listProduk) {
            if (produk.getNamaProduct().toLowerCase().contains(namaProduk.toLowerCase())) {
                listProdukbySearch.add(produk);
            }
        }
        return listProdukbySearch;
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
    private Produk getProdukbyid(ArrayList<Produk> list, String idproduk){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdProduk() == Integer.parseInt(idproduk)) {
                Produk oldData = new Produk(list.get(i).getIdProduk(), list.get(i).getidBrand(), list.get(i).getNamaProduct(), list.get(i).getHarga(), list.get(i).getBrand());
                return oldData;
            }
        }
        return null;
    }
    private Brand getBrandByProduk(Produk produk) throws Exception{
        ArrayList<Brand> listBrand = this.selectBrand();
        for (int i = 0; i < listBrand.size(); i++) {
            if (listBrand.get(i).getIdBrand() == produk.getidBrand()) {
                Brand brand = new Brand(listBrand.get(i).getIdBrand(), listBrand.get(i).getBrand());
                return brand;
            }
        }
        return null;
    }
}
