package entity;

public class TransaksiDetail {
    private int idDetailTransaksi;
    private int idTransaksi; // foreign key Transaksi
    private int idProdukDetail; // foreign key ProdukDetail
    private float harga;
    private int quantity;
    private Transaksi transaksi; // reference
    private ProdukDetail produkDetail; // reference

    public TransaksiDetail(int idDetailTransaksi, int idTransaksi, int idProdukDetail, float harga, int quantity, Transaksi transaksi, ProdukDetail produkDetail){
        this.idDetailTransaksi = idDetailTransaksi;
        this.idTransaksi = transaksi.getIdTransaksi();
        this.idProdukDetail = produkDetail.getIdProdukDetail();
        this.harga = harga;
        this.quantity = quantity;
        this.transaksi = transaksi;
        this.produkDetail = produkDetail;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
