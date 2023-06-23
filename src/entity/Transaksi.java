package entity;

import java.sql.Date;

public class Transaksi {
    private int idTransaksi;
    private int idUser;
    private float totalHarga;
    private Date tglTransaksi;
    private User user;

    public Transaksi(int idTransaksi, int idUser, float totalHarga, Date tglTransaksi, User user) {
        this.idTransaksi = idTransaksi;
        this.idUser = user.getIdUser();
        this.totalHarga = totalHarga;
        this.tglTransaksi = tglTransaksi;
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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.idUser = user.getIdUser(); // set idUser
    }

}
