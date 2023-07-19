package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Brand;
import entity.Produk;
import entity.ProdukDetail;
import entity.Transaksi;
import entity.TransaksiDetail;
import entity.User;

public class AllSql extends ExecuteSql{
    // User
    public ArrayList<User> selectUser() throws Exception{
        String selectUser = "SELECT id_user, username, role, active FROM users";
        ResultSet rs = this.sqlquerry(selectUser);
        ArrayList<User> list = new ArrayList<User>();
        while (rs.next()) {
            User user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getInt("role"), rs.getInt("active"));
            list.add(user);
        }
        return list;
    }
    // Brand
    public ArrayList<Brand> selectBrand() throws Exception{
        String select = "SELECT id_brand, brand FROM brand";
        ResultSet rs = this.sqlquerry(select);
        ArrayList<Brand> list = new ArrayList<Brand>();
        while(rs.next()){
            Brand brand = new Brand(rs.getInt("id_brand"),rs.getString("brand"));
            list.add(brand);
        }
        return list;
    }
    // Produk detail
    public ArrayList<ProdukDetail> selectProdukDetailbyIdproduk(int id) throws Exception{
        String sql = "SELECT produk_detail.id_produk_detail, produk_detail.id_produk, produk_detail.ukuran, produk_detail.warna, produk_detail.stock, produk.id_brand, produk.nama_product, produk.harga, brand.brand FROM produk_detail INNER JOIN produk ON produk_detail.id_produk = produk.id_produk INNER JOIN brand ON produk.id_brand = brand.id_brand WHERE produk_detail.id_produk = "+id+";";
        ResultSet rs = this.sqlquerry(sql);
        ArrayList<ProdukDetail> list = new ArrayList<ProdukDetail>();
        while(rs.next()){
            Brand brand = new Brand(rs.getInt("id_brand"), rs.getString("brand"));
            Produk produk = new Produk(rs.getInt("id_produk"), rs.getInt("id_brand"), rs.getString("nama_product"), rs.getFloat("harga"), brand);
            ProdukDetail produkDetail = new ProdukDetail(rs.getInt("id_produk_detail"), rs.getInt("id_produk"), rs.getInt("ukuran"), rs.getString("warna"), rs.getInt("stock"), produk);
            list.add(produkDetail);
        }
        return list;
    }
    public ArrayList<ProdukDetail> selectProdukDetail() throws Exception{
        String sql = "SELECT produk_detail.id_produk_detail, produk_detail.id_produk, produk_detail.ukuran, produk_detail.warna, produk_detail.stock, produk.id_brand, produk.nama_product, produk.harga, brand.brand FROM produk_detail INNER JOIN produk ON produk_detail.id_produk = produk.id_produk INNER JOIN brand ON produk.id_brand = brand.id_brand;";
        ResultSet rs = this.sqlquerry(sql);
        ArrayList<ProdukDetail> list = new ArrayList<ProdukDetail>();
        while(rs.next()){
            Brand brand = new Brand(rs.getInt("id_brand"), rs.getString("brand"));
            Produk produk = new Produk(rs.getInt("id_produk"), rs.getInt("id_brand"), rs.getString("nama_product"), rs.getFloat("harga"), brand);
            ProdukDetail produkDetail = new ProdukDetail(rs.getInt("id_produk_detail"), rs.getInt("id_produk"), rs.getInt("ukuran"), rs.getString("warna"), rs.getInt("stock"), produk);
            list.add(produkDetail);
        }
        return list;
    }
    // Produk
    public ArrayList<Produk> selectProduk() throws Exception{
        String sql = "SELECT id_produk, produk.id_brand, nama_product, harga, brand.brand FROM produk INNER JOIN brand ON produk.id_brand = brand.id_brand;";
        ResultSet rs = this.sqlquerry(sql);
        ArrayList<Produk> list = new ArrayList<Produk>();
        while (rs.next()) {
            Brand brand = new Brand(rs.getInt("id_brand"), rs.getString("brand"));
            Produk produk = new Produk(rs.getInt("id_produk"), rs.getInt("id_brand"), rs.getString("nama_product"), rs.getFloat("harga"), brand);
            list.add(produk);
        }
        return list;
    }
    public ArrayList<TransaksiDetail> selectTransaksiDetailbyIdtransaksi(int id) throws Exception {
        String sql = "SELECT id_detail_transaksi, detail_transaksi.id_transaksi, detail_transaksi.id_produk_detail, detail_transaksi.harga, quantity, transaksi.id_user, transaksi.total_harga, transaksi.tgl_transaksi, transaksi.status, produk_detail.id_produk, produk_detail.ukuran, produk_detail.warna, produk_detail.stock, produk.id_brand, produk.nama_product, brand.brand, users.username, users.active FROM detail_transaksi INNER JOIN transaksi ON detail_transaksi.id_transaksi = transaksi.id_transaksi INNER JOIN produk_detail ON detail_transaksi.id_produk_detail = produk_detail.id_produk_detail INNER JOIN produk ON produk_detail.id_produk = produk.id_produk INNER JOIN brand ON produk.id_brand = brand.id_brand INNER JOIN users ON transaksi.id_user = users.id_user WHERE detail_transaksi.id_transaksi = "+id+";";
        ResultSet rs = this.sqlquerry(sql);
        ArrayList<TransaksiDetail> list = new ArrayList<TransaksiDetail>();
        while (rs.next()){
            User user = new User(rs.getInt("id_user"), rs.getString("username"), "", 0, rs.getInt("active"));//user
            Brand brand = new Brand(rs.getInt("id_brand"), rs.getString("brand"));
            Produk produk = new Produk(rs.getInt("id_produk"), rs.getInt("id_brand"), rs.getString("nama_product"), 0, brand); //produk
            ProdukDetail produkDetail = new ProdukDetail(rs.getInt("id_produk_detail"), rs.getInt("id_produk"), rs.getInt("ukuran"), rs.getString("warna"), rs.getInt("stock"), produk);//produk detail
            Transaksi transaksi = new Transaksi(rs.getInt("id_transaksi"), rs.getInt("id_user"), rs.getFloat("total_harga"), rs.getDate("tgl_transaksi"), rs.getInt("status"), user);//transaksi
            TransaksiDetail transaksiDetail = new TransaksiDetail(rs.getInt("id_detail_transaksi"), rs.getInt("id_transaksi"), rs.getInt("id_produk_detail"), rs.getFloat("harga"), rs.getInt("quantity"), transaksi, produkDetail);
            list.add(transaksiDetail);
        }
        return list;
    }
    public ArrayList<TransaksiDetail> selectTransaksiDetailbyIdtransaksi(int idTransaksi, int idUser) throws Exception {
        String sql = "SELECT id_detail_transaksi, detail_transaksi.id_transaksi, detail_transaksi.id_produk_detail, detail_transaksi.harga, quantity, transaksi.id_user, transaksi.total_harga, transaksi.tgl_transaksi, transaksi.status, produk_detail.id_produk, produk_detail.ukuran, produk_detail.warna, produk_detail.stock, produk.id_brand, produk.nama_product, brand.brand, users.username, users.active FROM detail_transaksi INNER JOIN transaksi ON detail_transaksi.id_transaksi = transaksi.id_transaksi INNER JOIN produk_detail ON detail_transaksi.id_produk_detail = produk_detail.id_produk_detail INNER JOIN produk ON produk_detail.id_produk = produk.id_produk INNER JOIN brand ON produk.id_brand = brand.id_brand INNER JOIN users ON transaksi.id_user = users.id_user WHERE detail_transaksi.id_transaksi = "+idTransaksi+" AND transaksi.id_user = "+idUser+";";
        ResultSet rs = this.sqlquerry(sql);
        ArrayList<TransaksiDetail> list = new ArrayList<TransaksiDetail>();
        while (rs.next()){
            User user = new User(rs.getInt("id_user"), rs.getString("username"), "", 0, rs.getInt("active"));//user
            Brand brand = new Brand(rs.getInt("id_brand"), rs.getString("brand"));
            Produk produk = new Produk(rs.getInt("id_produk"), rs.getInt("id_brand"), rs.getString("nama_product"), 0, brand); //produk
            ProdukDetail produkDetail = new ProdukDetail(rs.getInt("id_produk_detail"), rs.getInt("id_produk"), rs.getInt("ukuran"), rs.getString("warna"), rs.getInt("stock"), produk);//produk detail
            Transaksi transaksi = new Transaksi(rs.getInt("id_transaksi"), rs.getInt("id_user"), rs.getFloat("total_harga"), rs.getDate("tgl_transaksi"), rs.getInt("status"), user);//transaksi
            TransaksiDetail transaksiDetail = new TransaksiDetail(rs.getInt("id_detail_transaksi"), rs.getInt("id_transaksi"), rs.getInt("id_produk_detail"), rs.getFloat("harga"), rs.getInt("quantity"), transaksi, produkDetail);
            list.add(transaksiDetail);
        }
        return list;
    }
    public ArrayList<Transaksi> selectTransaksi() throws Exception{
        String sql = "SELECT id_transaksi, transaksi.id_user, total_harga, tgl_transaksi, status, users.username, users.active FROM transaksi INNER JOIN users ON transaksi.id_user = users.id_user;";
        ResultSet rs = this.sqlquerry(sql);
        ArrayList<Transaksi> list = new ArrayList<Transaksi>();
        while (rs.next()) {
            User user = new User(rs.getInt("id_user"), rs.getString("username"), "", 0, rs.getInt("active"));
            Transaksi transaksi = new Transaksi(rs.getInt("id_transaksi"), rs.getInt("id_user"), rs.getFloat("total_harga"), rs.getDate("tgl_transaksi"), rs.getInt("status"), user);
            list.add(transaksi);
        }
        return list;
    }

    public ArrayList<Transaksi> selectTransaksiByIdUser(int idUser) throws Exception{
        String sql = "SELECT id_transaksi, transaksi.id_user, total_harga, tgl_transaksi, status, users.username, users.active FROM transaksi INNER JOIN users ON transaksi.id_user = users.id_user WHERE users.id_user = "+idUser+";";
        ResultSet rs = this.sqlquerry(sql);
        ArrayList<Transaksi> list = new ArrayList<Transaksi>();
        while (rs.next()) {
            User user = new User(rs.getInt("id_user"), rs.getString("username"), "", 0, rs.getInt("active"));
            Transaksi transaksi = new Transaksi(rs.getInt("id_transaksi"), rs.getInt("id_user"), rs.getFloat("total_harga"), rs.getDate("tgl_transaksi"), rs.getInt("status"), user);
            list.add(transaksi);
        }
        return list;
    }

    public void insertTransaksi(int idUser, float totalHargaOrder, int orderstts, CartData cartData){
        ResultSet rs = null;
        Conn conn = null;
        try {
            conn = new Conn();
            PreparedStatement stmtTransaksi;
            PreparedStatement stmtDetailTransaksi = null;
            
            // insert data transaksi
            String sqlTransaksi = "INSERT INTO transaksi (id_user, total_harga, tgl_transaksi, status) VALUES (?, ?, (SELECT CURRENT_TIMESTAMP), ?)";
            stmtTransaksi = conn.getConnection().prepareStatement(sqlTransaksi, Statement.RETURN_GENERATED_KEYS);
            stmtTransaksi.setInt(1,idUser);
            stmtTransaksi.setDouble(2, totalHargaOrder);
            stmtTransaksi.setInt(3, orderstts);
            stmtTransaksi.executeUpdate();

            rs = stmtTransaksi.getGeneratedKeys();
            if (rs.next()) {
                int idTransaksi = rs.getInt(1);
                for (int i = 0; i < cartData.getCarts().size(); i++) {        
                    float totalHarga = cartData.getCarts().get(i).getProdukDetail().getProduk().getHarga() * cartData.getCarts().get(i).getQuantity();            
                    String sqlDetailTransaksi = "INSERT INTO detail_transaksi (id_transaksi, id_produk_detail, harga, quantity) VALUES (?, ?, ?, ?)";
                    stmtDetailTransaksi = conn.getConnection().prepareStatement(sqlDetailTransaksi);
                    stmtDetailTransaksi.setInt(1, idTransaksi);
                    stmtDetailTransaksi.setInt(2, cartData.getCarts().get(i).getIdProdukDetail());
                    stmtDetailTransaksi.setFloat(3, totalHarga);
                    stmtDetailTransaksi.setInt(4, cartData.getCarts().get(i).getQuantity());
                    stmtDetailTransaksi.executeUpdate();
                }
            }

            // tutup koneksi
            rs.close();
            stmtTransaksi.close();
            stmtDetailTransaksi.close();
            conn.close();
        } catch (SQLException e) {
            // rollback transaksi jika terjadi error
            try {
                if (conn.getConnection() != null) {
                    conn.getConnection().rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
