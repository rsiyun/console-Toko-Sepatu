package app.User;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Transaksi;
import entity.TransaksiDetail;
import service.AllSql;
import service.BaseAuth;
import service.CommandLineCleaner;
import service.CommandLineTable;
import service.Enum.StatusTransaksi;

public class TransaksiController extends AllSql{
    public void mainTransaksi()throws Exception{
        BaseAuth baseAuth  = BaseAuth.getInstance();
        CommandLineCleaner clClear = new CommandLineCleaner();
        boolean endwhile = true;
        while(endwhile){
            Scanner scanner = new Scanner(System.in);
            ArrayList<Transaksi> list = this.selectTransaksiByIdUser(baseAuth.getUser().getIdUser());
            System.out.println("1. Histori Transaksi");
            System.out.println("2. back");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
            clClear.clear();
            switch (pilihan) {
                case "1":
                    if(showTransaction(list))
                        changeStatus(baseAuth.getUser().getIdUser());
                    break;
                case "2":
                    endwhile = false;
                    UserView userView = new UserView();
                    userView.menu();
                    break;
                default:
                    System.out.println("Pilihan tidak ada");
                    break;
            }
        }
    }

    private boolean showTransaction(ArrayList<Transaksi> list)throws Exception{
        if(list.isEmpty()){
            System.out.println("\n\n Belum ada Transaksi \n\n");
            return false;
        }
        CommandLineTable table = new CommandLineTable();
        table.setShowVerticalLines(true);
        table.setHeaders("Id Transaksi", "Tanggal Transaksi", "Total Harga", "Status");
        for (Transaksi transaksi : list) {
            table.addRow(String.valueOf(transaksi.getIdTransaksi()), transaksi.getTglTransaksi()+"", String.valueOf(transaksi.getTotalHarga()), showTransaksiLabel(transaksi.getStatus()));
        }
        table.print();
        return true;
    }
    private void changeStatus(int idUser)throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan id Transaksi Untuk dilihat detailnya : ");
        String idTransaksi = scanner.nextLine();
        try {
            Integer.parseInt(idTransaksi);
        } catch (Exception e) {
            System.out.println("Masukkan id Transaksi dengan benar");
        return;
        }
        // Menampilkan order detail
        ArrayList<TransaksiDetail> listDetail = this.selectTransaksiDetailbyIdtransaksi(Integer.parseInt(idTransaksi), idUser);
        if (listDetail.isEmpty()) {
            System.out.println("Masukkan id Transaksi dengan benar");
            return;
        }
        CommandLineTable table = new CommandLineTable();
        table.setShowVerticalLines(true);
        table.setHeaders( "Nama Produk", "Nama Brand", "Ukuran", "Warna", "Harga", "Jumlah barang");
        for (TransaksiDetail transaksiDetail : listDetail) {
            table.addRow(transaksiDetail.getProdukDetail().getProduk().getNamaProduct(), transaksiDetail.getProdukDetail().getProduk().getBrand().getBrand(), transaksiDetail.getProdukDetail().getUkuran()+"", transaksiDetail.getProdukDetail().getWarna(), transaksiDetail.getHarga()+"", transaksiDetail.getQuantity()+"");
        }
        table.print();
        if (listDetail.get(0).getTransaksi().getStatus() != 0) {
            System.out.println("\n Total Transaksi: "+listDetail.get(0).getTransaksi().getTotalHarga()+""+"\n");
            return;
        }
        if (!checkStock(listDetail)) {
            return;
        }
        System.out.println("\n Total Transaksi: "+listDetail.get(0).getTransaksi().getTotalHarga()+""+"\n");
        System.out.print("Apakah Anda yakin membeli Barang Tersebut ? [y/n]: ");
        String pilihan = scanner.nextLine();
        if (pilihan.equals("y") || pilihan.equals("Y")) {
            String sql = "UPDATE transaksi SET status = 1 WHERE id_transaksi = "+idTransaksi+";";
            sqlexupdate(sql);
            stockBerkurang(listDetail);
            System.out.println("\n Barang Berhasil sudah Dibayar \n");
        }
    }

    private boolean checkStock(ArrayList<TransaksiDetail> list){
        boolean result = true;
        for (int i = 0; i < list.size(); i++) {            
            if (list.get(i).getProdukDetail().getStock() < list.get(i).getQuantity()) {
                result = false;
                System.out.println("\n\nStock tidak mencukupi");
                System.out.print("Jika ingin membayar tolong buat transaksi baru \n\n");
                return result;
            }
        }
        return result;
    }

    private void stockBerkurang(ArrayList<TransaksiDetail> list){
        for (int i = 0; i < list.size(); i++) {
            // Mapping data
            int newStock = list.get(i).getProdukDetail().getStock() - list.get(i).getQuantity();
            
            String sql = "UPDATE produk_detail SET produk_detail.stock = "+newStock+" WHERE produk_detail.id_produk_detail = "+list.get(i).getIdProdukDetail()+";";
            sqlexupdate(sql);
        }
    }

    private String showTransaksiLabel(int vl){
        if (vl == StatusTransaksi.dikonfirmasi.value) {
            return "Di Konfirmasi";
        }else if(vl == StatusTransaksi.dibayar.value){
            return "Di Bayar";
        }
        return "Di Pesan";
    }
}
