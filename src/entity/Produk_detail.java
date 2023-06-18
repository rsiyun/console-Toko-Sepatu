package entity;

public class Produk_detail {
    private int id_produk_detail;
    private int id_produk; // foreign key Produk
    private Produk produk; // reference
    private int ukuran;
    private String warna;
    private int stock;

    public Produk_detail(int id_produk_detail, int id_produk, int ukuran, String warna, int stock, Produk produk) {
        this.id_produk_detail = id_produk_detail;
        this.id_produk = produk.getId_Produk();
        this.ukuran = ukuran;
        this.warna = warna;
        this.stock = stock;
        this.produk = produk;
    }
    
    public int getId_Produk_detail() {
        return id_produk_detail;
    }

    public void setId_Produk_detail(int id_produk_detail) {
        this.id_produk_detail = id_produk_detail;
    }

    public int getId_Produk() {
        return id_produk;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk= produk;
        this.id_produk = produk.getId_Produk();
    }

    public int getUkuran() {
        return ukuran;
    }

    public void setUkuran(int ukuran) {
        this.ukuran= ukuran;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna= warna;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock= stock;
    }
}
