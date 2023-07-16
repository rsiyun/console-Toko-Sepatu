package entity;

public class Cart {
    private int cartId;
    private int idProdukDetail;
    private int quantity;
    private ProdukDetail produkDetail;

    public Cart(int cartId, int idProdukDetail, int quantity, ProdukDetail produkDetail){
        this.cartId = cartId;
        this.idProdukDetail = idProdukDetail;
        this.quantity = quantity;
        this.produkDetail = produkDetail;
    }
    public int getCartId() {
        return cartId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
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
