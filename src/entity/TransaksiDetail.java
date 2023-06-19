package entity;

public class TransaksiDetail {
    private int idDetailTransaksi;
    private int idTransaksi;
    private int idProdukDetail;
    private float harga;
    private Transaksi transaksi;
    private ProdukDetail produkDetail;

    public TransaksiDetail(int idDetailTransaksi, int idTransaksi, int idProdukDetail, float harga, Transaksi transaksi, ProdukDetail produkDetail){
        this.idDetailTransaksi = idDetailTransaksi;
        this.idTransaksi = transaksi.getIdTransaksi();
        this.idProdukDetail = idProdukDetail;
        this.harga = harga;
        this.transaksi = transaksi;
    }

    public int getIdDetailTransaksi() {
        return idDetailTransaksi;
    }
    public void setIdDetailTransaksi(int idDetailTransaksi) {
        this.idDetailTransaksi = idDetailTransaksi;
    }
    public int getIdTransaksi() {
        return idTransaksi;
    }
    public int getIdProdukDetail() {
        return idProdukDetail;
    }
    public void setIdProdukDetail(int idProdukDetail) {
        this.idProdukDetail = idProdukDetail;
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
        this.idTransaksi = transaksi.getIdTransaksi(); // set idTransaksi
    }
    public ProdukDetail getProdukDetail() {
        return this.produkDetail;
    }
    public void setProdukDetail(ProdukDetail produkDetail) {
        this.produkDetail = produkDetail;
        this.idProdukDetail = produkDetail.getIdProdukDetail(); // set idTransaksi
    }
    


}
