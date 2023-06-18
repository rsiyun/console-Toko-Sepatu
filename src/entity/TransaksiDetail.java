package entity;

public class TransaksiDetail {
    private int id_detail_transaksi;
    private int id_transaksi;
    private int id_produk_detail;
    private float harga;
    private Transaksi transaksi;

    public TransaksiDetail(int id_detail_transaksi, int id_transaksi, int id_produk_detail, float harga, Transaksi transaksi){
        this.id_detail_transaksi = id_detail_transaksi;
        this.id_transaksi = transaksi.getId_transaksi();
        this.id_produk_detail = id_produk_detail;
        this.harga = harga;
        this.transaksi = transaksi;
    }

    public int getId_detail_transaksi() {
        return id_detail_transaksi;
    }
    public void setId_detail_transaksi(int id_detail_transaksi) {
        this.id_detail_transaksi = id_detail_transaksi;
    }
    public int getId_transaksi() {
        return id_transaksi;
    }
    public int getId_produk_detail() {
        return id_produk_detail;
    }
    public void setId_produk_detail(int id_produk_detail) {
        this.id_produk_detail = id_produk_detail;
    }
    public float getHarga() {
        return harga;
    }
    public void setHarga(float harga) {
        this.harga = harga;
    }
    public Transaksi getTransaksi() {
        return transaksi;
    }
    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
        this.id_transaksi = transaksi.getId_transaksi(); // set id_transaksi
    }
    


}
