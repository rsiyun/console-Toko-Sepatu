package app.User;
import java.util.ArrayList;
import java.util.Scanner;
import entity.Produk;
import entity.ProdukDetail;
import entity.Brand;
import entity.Cart;
import service.AllSql;
import service.CartData;
import service.CommandLineTable;

public class ProdukController extends AllSql{
    ArrayList<ProdukDetail> list = new ArrayList<ProdukDetail>();
    public void ProdukUser()throws Exception{
        boolean endwhile = true;
        while(endwhile){
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Tampilkan Semua Produk");
            System.out.println("2. Cari Produk");
            System.out.println("3. back");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    showProduk();
                    ProdukDetailUser();
                    break;
                case "2":
                    searchProduk();
                    break;
                case "3":
                    endwhile = false;
                    UserView userView = new UserView();
                    userView.menu();
                    break;
                default:
                    System.out.println("Pilihan tidak ada");
                    break;
            }
        }
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
            return;
        }
        if(!checkProdukDetailbyIdProduk(Integer.parseInt(idProduk))){
            System.out.println("\n Detail Produk belum ada \n");
            return;
        }
        // DI EDIT SEPERTI searchProduk
        Produk produkTerpilih = getProdukbyid(listProduk, idProduk);
        showProdukTerpilih(produkTerpilih);
        
    }

    private boolean checkProdukDetailbyIdProduk(int idProduk) throws Exception{
        list = this.selectProdukDetailbyIdproduk(idProduk);
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }

    private ProdukDetail checkProdukDetail(ArrayList<ProdukDetail> list, int idProdukDetail){
        ProdukDetail result = null;
             for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIdProdukDetail() == idProdukDetail) {
                    result = list.get(i);
                    return result;
                }
             }
        return result;
    }

    private void showProdukTerpilih(Produk produkTerpilih){
        try{
            Produk produk = produkTerpilih;
            Brand brand = getBrandByProduk(produk);
            CommandLineTable tableProduk = new CommandLineTable();
            tableProduk.setShowVerticalLines(true);
            tableProduk.setHeaders("Nama Produk", "Nama Brand", "Harga");
            tableProduk.addRow(produk.getNamaProduct(), brand.getBrand(), String.valueOf(produk.getHarga()));
            tableProduk.print();
            showProdukDetail(list);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void showProdukDetail(ArrayList<ProdukDetail> list) throws Exception{
        CommandLineTable tableProdukDetail = new CommandLineTable();
        tableProdukDetail.setShowVerticalLines(true);
        tableProdukDetail.setHeaders("Id Produk Detail", "Ukuran", "Warna", "Stok");
        for (ProdukDetail produkDetail : list) {
            tableProdukDetail.addRow(String.valueOf(produkDetail.getIdProdukDetail()), String.valueOf(produkDetail.getUkuran()), produkDetail.getWarna(), String.valueOf(produkDetail.getStock()));
        }
        tableProdukDetail.print();
    }
    public void searchProduk() throws Exception{
        CartData cartData = CartData.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama produk yang ingin dicari : ");
        String namaProduk = scanner.nextLine();
        ArrayList<Produk> listProduk = this.listProdukbySearch(namaProduk);
        if (listProduk.size() == 0 ) {
            System.out.println("\n Yang anda cari tidak ditemukan \n");
            return;
        }
        CommandLineTable table = new CommandLineTable();
        table.setShowVerticalLines(true);
        table.setHeaders("Id Produk", "Nama Produk", "Nama Brand","Harga");
        for (Produk produk : listProduk) {
            Brand brand = getBrandByProduk(produk);
            table.addRow(String.valueOf(produk.getIdProduk()), produk.getNamaProduct(), brand.getBrand(), String.valueOf(produk.getHarga()));
        }
        table.print();
        System.out.print("Masukkan id produk yang ingin di lihat detailnya : ");
        String idProduk = scanner.nextLine();
        if(!checkProduk(listProduk, idProduk)){
            System.out.println("Tolong Input id dengan benar");
            return;
        }
        if(!checkProdukDetailbyIdProduk(Integer.parseInt(idProduk))){
            System.out.println("\n Detail Produk belum ada \n");
            return;
        }
        Produk produkTerpilih = getProdukbyid(listProduk, idProduk);
        showProdukTerpilih(produkTerpilih);
        System.out.print("Masukkan id produk detail yang ingin dibeli : ");
        String idProdukDetail = scanner.nextLine();
        ProdukDetail pd = checkProdukDetail(list, Integer.parseInt(idProdukDetail));
        if (pd == null) {
            System.out.println("\n Tolong masukkan id produk detail dengan benar \n");
            return;
        }
        int quantityR = -1;
        while (quantityR == -1) {
            quantityR = quantityForm(scanner, pd);
        }
        for (int i = 0; i < cartData.getCarts().size(); i++) {
            if(cartData.getCarts().get(i).getIdProdukDetail() == Integer.parseInt(idProdukDetail)){
                cartData.getCarts().get(i).setQuantity(cartData.getCarts().get(i).getQuantity()+quantityR);
                return;
            }   
        }
        Cart cart = new Cart(Integer.parseInt(idProdukDetail), quantityR, pd);
        cartData.addCart(cart);
        

    }

    private int quantityForm(Scanner scanner, ProdukDetail pd){
        System.out.print("Masukkan Jumlah barang : ");
        String quantity = scanner.nextLine();
        if (pd.getStock() < Integer.parseInt(quantity)) {
            System.out.println("\n Stock Tidak Mencukupi\n");
            return -1;
        }else if(Integer.parseInt(quantity) <= 0){
            System.out.println("\n Jumlah harus melebihi 1 \n");
            return -1;
        }
        return Integer.parseInt(quantity);
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
