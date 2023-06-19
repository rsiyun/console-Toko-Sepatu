package entity;

import java.sql.Date;

public class Transaksi {
    private int idTransaksi;
    private int idUser;
    private float totalHarga;
    private Date tglTransaksi;
    private int stock;
    private User user;

    public Transaksi(int idTransaksi, int idUser, float totalHarga, Date tglTransaksi, int stock, User user) {
        this.idTransaksi = idTransaksi;
        this.idUser = user.getIdUser();
        this.totalHarga = totalHarga;
        this.tglTransaksi = tglTransaksi;
        this.stock = stock;
        this.user = user;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }
    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }
    public int getIdUser() {
        return idUser;
    }
    public float getTotalHarga() {
        return totalHarga;
    }
    public void setTotalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
    }
    public Date getTglTransaksi() {
        return tglTransaksi;
    }
    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.idUser = user.getIdUser(); // set idUser
    }

}
