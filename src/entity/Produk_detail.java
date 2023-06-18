package entity;

public class Produk_detail {
    private int id_produk_detail;
    private int id_produk; // foreign key Produk
    private int ukuran;
    private String warna;
    private int stock;

    public Produk_detail(int id_produk_detail, int id_produk, int ukuran, String warna, int stock) {
        this.id_produk_detail = id_produk_detail;
        this.id_produk = id_produk;
        this.ukuran = ukuran;
        this.warna = warna;
        this.stock = stock;
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

    public void setId_Produk(int id_produk) {
        this.id_produk = id_produk;
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
