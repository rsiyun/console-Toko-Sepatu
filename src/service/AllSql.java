package service;

import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Brand;
import entity.Produk;
import entity.ProdukDetail;

public class AllSql extends ExecuteSql{
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
}
