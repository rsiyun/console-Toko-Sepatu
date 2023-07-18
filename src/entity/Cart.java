package entity;

public class Cart {
    private int idProdukDetail;
    private int quantity;
    private ProdukDetail produkDetail;

    public Cart(int idProdukDetail, int quantity, ProdukDetail produkDetail){
        this.idProdukDetail = idProdukDetail;
        this.quantity = quantity;
        this.produkDetail = produkDetail;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getIdProdukDetail() {
        return idProdukDetail;
    }
    public void setIdProdukDetail(int idProdukdDetail) {
        this.idProdukDetail = idProdukdDetail;
    }
    public ProdukDetail getProdukDetail() {
        return this.produkDetail;
    }
    public void setProdukDetail(ProdukDetail produkDetail) {
        this.produkDetail = produkDetail;
        this.idProdukDetail = produkDetail.getIdProdukDetail(); // set idTransaksi
    }
    
}
