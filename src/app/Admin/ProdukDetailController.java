package app.Admin;

import java.util.ArrayList;

import entity.ProdukDetail;
import service.AllSql;

public class ProdukDetailController extends AllSql{
    
    public void showProductDetail() throws Exception{
        ArrayList<ProdukDetail> list = this.selectProdukDetail();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getIdProdukDetail() + " "+ list.get(i).getProduk().getBrand().getBrand() +" "+ list.get(i).getProduk().getNamaProduct() +" "+ list.get(i).getUkuran() + " " +list.get(i).getWarna() + " " + list.get(i).getStock());
        }
    }
}
    