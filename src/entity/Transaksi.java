package entity;

import java.sql.Date;

public class Transaksi {
    private int id_transaksi;
    private int id_user;
    private float total_harga;
    private Date tgl_transaksi;
    private int stock;
    private User user;

    public Transaksi(int id_transaksi, int id_user, float total_harga, Date tgl_transaksi, int stock, User user) {
        this.id_transaksi = id_transaksi;
        this.id_user = user.getId_user();
        this.total_harga = total_harga;
        this.tgl_transaksi = tgl_transaksi;
        this.stock = stock;
        this.user = user;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }
    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }
    public int getId_user() {
        return id_user;
    }
    public float getTotal_harga() {
        return total_harga;
    }
    public void setTotal_harga(float total_harga) {
        this.total_harga = total_harga;
    }
    public Date getTgl_transaksi() {
        return tgl_transaksi;
    }
    public void setTgl_transaksi(Date tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
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
        this.id_user = user.getId_user(); // set id_user
    }

}
