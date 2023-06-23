package entity;

public class Produk {
    private int idProduk;
    private int idBrand; // foreign key Brand
    private Brand brand; // reference
    private String namaProduct;
    private float harga;

    public Produk(int idProduk, int idBrand, String namaProduct, float harga, Brand brand) {
        this.idProduk = idProduk;
        this.idBrand = brand.getIdBrand();
        this.namaProduct = namaProduct;
        this.harga = harga;
        this.brand = brand;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public int getidBrand() {
        return idBrand;
    }
    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }


    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand= brand;
        this.idBrand = brand.getIdBrand();
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga= harga;
    }
}
