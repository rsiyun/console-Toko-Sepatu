package entity;

import java.sql.Date;

public class Transaksi {
    private int idTransaksi;
    private int idUser; // foreign key User
    private String usernameAdmin;
    private float totalHarga;
    private Date tglTransaksi;
    private int status;
    private User user; // reference

    public Transaksi(int idTransaksi, int idUser, float totalHarga, Date tglTransaksi, int status, User user) {
        this.idTransaksi = idTransaksi;
        this.idUser = user.getIdUser();
        this.totalHarga = totalHarga;
        this.tglTransaksi = tglTransaksi;
        this.status = status;
        this.user = user;
    }

    public Transaksi(int idTransaksi, int idUser, String usernameAdmin, float totalHarga, Date tglTransaksi, int status, User user) {
        this.idTransaksi = idTransaksi;
        this.idUser = user.getIdUser();
        this.usernameAdmin = usernameAdmin;
        this.totalHarga = totalHarga;
        this.tglTransaksi = tglTransaksi;
        this.status = status;
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
    public String getUsernameAdmin(){
        return usernameAdmin;
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
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.idUser = user.getIdUser(); // set idUser
    }

}
