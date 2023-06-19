package entity;

public class ProdukDetail {
    private int idProdukDetail;
    private int idProduk; // foreign key Produk
    private Produk produk; // reference
    private int ukuran;
    private String warna;
    private int stock;

    public ProdukDetail(int idProdukDetail, int idProduk, int ukuran, String warna, int stock, Produk produk) {
        this.idProdukDetail = idProdukDetail;
        this.idProduk = produk.getIdProduk();
        this.ukuran = ukuran;
        this.warna = warna;
        this.stock = stock;
        this.produk = produk;
    }
    
    public int getIdProdukDetail() {
        return idProdukDetail;
    }

    public void setidProdukDetail(int idProdukDetail) {
        this.idProdukDetail = idProdukDetail;
    }

    public int getidProduk() {
        return idProduk;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk= produk;
        this.idProduk = produk.getIdProduk();
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
