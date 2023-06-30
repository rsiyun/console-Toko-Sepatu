package app.Admin;

import java.util.ArrayList;

import entity.TransaksiDetail;
import service.AllSql;

public class TransaksiDetailController extends AllSql{
    public void showTransaksiDetail() throws Exception{
        ArrayList<TransaksiDetail> list = this.selectTransaksiDetail();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getIdDetailTransaksi() + " "+ list.get(i).getTransaksi().getIdTransaksi() +" "+ list.get(i).getProdukDetail().getIdProdukDetail() +" "+list.get(i).getHarga()+" "+ list.get(i).getQuantity());
        }
    }
}
