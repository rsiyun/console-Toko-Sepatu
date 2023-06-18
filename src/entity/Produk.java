package entity;

public class Produk {
    private int id_produk;
    private int id_brand; // foreign key Brand
    private String nama_product;
    private float harga;

    public Produk(int id_produk, int id_brand, String nama_product, float harga) {
        this.id_produk = id_produk;
        this.id_brand = id_brand;
        this.nama_product = nama_product;
        this.harga = harga;
    }

    public int getId_Produk() {
        return id_produk;
    }

    public void setId_Produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getId_Brand() {
        return id_brand;
    }

    public void setId_Brand(int id_brand) {
        this.id_brand = id_brand;
    }

    public String getNama_Product() {
        return nama_product;
    }

    public void setNama_Product(String nama_product) {
        this.nama_product = nama_product;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga= harga;
    }
}
