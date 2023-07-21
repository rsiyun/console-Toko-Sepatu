package app.Admin;
import java.util.ArrayList;
import java.util.Scanner;
import entity.Transaksi;
import entity.TransaksiDetail;
import service.AllSql;
import service.CommandLineTable;
import service.Enum;



public class TransaksiController extends AllSql{
    public void TransaksiAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean endwhile = true;
        while(endwhile){
            System.out.println("1. Menampilkan data Transaksi");
            System.out.println("2. Konfirmasi Transaksi");
            System.out.println("3. back");
            System.out.print("Pilih Pilihan anda: ");
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    showTransaksi();
                    TransaksiDetailAdmin();
                    break;
                case "2":
                if (showOnlyTransaksiDibayar()) {
                    updateTransaksi();
                }
                    break;
                case "3":
                    endwhile = false;
                    Admin adminView = new Admin();
                    adminView.menu();
                    break;
                default:
                    System.out.println("Pilihan tidak ada");
                    break;
        }
        }
        scanner.close();
    }
    public void TransaksiDetailAdmin() throws Exception{
        ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan id transaksi yang ingin di lihat detailnya : ");
        String idTransaksi = scanner.nextLine();
        if(!checkTransaksi(listTransaksi, idTransaksi)){
            System.out.println("Tolong Input id dengan benar");
            // scanner.close();
            return;
        }
        // scanner.close();
        Transaksi oldData = getTransaksibyid(listTransaksi, idTransaksi);
        showTransaksiTerpilih(oldData);
    }
    private void updateTransaksi() throws Exception{
        ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan id transaksi yang ingin di konfirmasi : ");
        String idTransaksi = scanner.nextLine();
        if(!checkTransaksi(listTransaksi, idTransaksi)){
            System.out.println("Tolong Input id dengan benar");
            // scanner.close();
            return;
        }
        Transaksi oldData = getTransaksibyid(listTransaksi, idTransaksi);
        showTransaksiTerpilih(oldData);
        System.out.println("Apakah anda ingin mengkonfirmasi transaksi ini? (y/n)");
        String pil = scanner.nextLine();
        if(pil.equals("y")||pil.equals("Y")){
            String sql = "UPDATE transaksi SET status = 2 WHERE id_transaksi = "+idTransaksi+";";
            this.sqlexupdate(sql);
            System.out.println("Transaksi telah dikonfirmasi");
        }
        // scanner.close();
    }
    private void showTransaksi() throws Exception {
        try {
            ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
            CommandLineTable cmd = new CommandLineTable();
            cmd.setShowVerticalLines(true);
            cmd.setHeaders("ID Transaksi", "ID User", "Total Harga", "Tanggal Transaksi", "Status");
            for (Transaksi transaksi : listTransaksi) {
                cmd.addRow(String.valueOf(transaksi.getIdTransaksi()), String.valueOf(transaksi.getIdUser()), String.valueOf(transaksi.getTotalHarga()), String.valueOf(transaksi.getTglTransaksi()), getStatus(transaksi));
            }
            cmd.print();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    private boolean showOnlyTransaksiDibayar() throws Exception {
        try {
            ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
            CommandLineTable cmd = new CommandLineTable();
            boolean stts = false;
            cmd.setShowVerticalLines(true);
            cmd.setHeaders("ID Transaksi", "ID User", "Total Harga", "Tanggal Transaksi");

            for (Transaksi transaksi : listTransaksi) {
                
                if(transaksi.getStatus() == Enum.StatusTransaksi.dibayar.value){
                    stts = true;
                    cmd.addRow(String.valueOf(transaksi.getIdTransaksi()), String.valueOf(transaksi.getIdUser()), String.valueOf(transaksi.getTotalHarga()), String.valueOf(transaksi.getTglTransaksi()));
                }
            }
            if (stts == false) {
                System.out.println("Belum ada transaksi yang dibayar");
                return stts;
            }
            System.out.println("Daftar Transaksi yang sudah dibayar :");
            cmd.print();
            return stts;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    private void showTransaksiTerpilih(Transaksi oldData) throws Exception {
        try {
            Transaksi transaksi = oldData;
            CommandLineTable cmd = new CommandLineTable();
            cmd.setShowVerticalLines(true);
            cmd.setHeaders("ID Transaksi", "ID User", "Total Harga", "Tanggal Transaksi", "Status");
            cmd.addRow(String.valueOf(transaksi.getIdTransaksi()), String.valueOf(transaksi.getIdUser()), String.valueOf(transaksi.getTotalHarga()), String.valueOf(transaksi.getTglTransaksi()), getStatus(transaksi));
            cmd.print();
            showTransaksiDetail(transaksi.getIdTransaksi());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    private void showTransaksiDetail(int id) throws Exception{
        try{
            ArrayList<TransaksiDetail> list = this.selectTransaksiDetailbyIdtransaksi(id);
            CommandLineTable cmd = new CommandLineTable();
            cmd.setHeaders("ID Detail Transaksi", "Produk", "Brand", "Ukuran", "Warna", "Harga", "Jumlah Barang");
            cmd.setShowVerticalLines(true);
            for(TransaksiDetail transaksiDetail : list){
                cmd.addRow(transaksiDetail.getIdDetailTransaksi()+"", transaksiDetail.getProdukDetail().getProduk().getNamaProduct(), transaksiDetail.getProdukDetail().getProduk().getBrand().getBrand(), transaksiDetail.getProdukDetail().getUkuran()+"", transaksiDetail.getProdukDetail().getWarna(), transaksiDetail.getHarga()+"", transaksiDetail.getQuantity()+"");
            }
            cmd.print();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    private boolean checkTransaksi(ArrayList<Transaksi> list, String idTransaksi) throws Exception{
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getIdTransaksi() == Integer.parseInt(idTransaksi)){
                return true;
            }
        }
        return false;
    }
    private Transaksi getTransaksibyid(ArrayList<Transaksi> list, String idTransaksi) throws Exception{
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getIdTransaksi() == Integer.parseInt(idTransaksi)){
                Transaksi oldData = new Transaksi(list.get(i).getIdTransaksi(), list.get(i).getIdUser(), list.get(i).getTotalHarga(), list.get(i).getTglTransaksi(), list.get(i).getStatus(), list.get(i).getUser());
                return oldData;
            }
        }
        return null;
    }
    private String getStatus(Transaksi Transaksi){
        if(Transaksi.getStatus() == Enum.StatusTransaksi.dipesan.value){
            return "Dipesan";
        }else if (Transaksi.getStatus() == Enum.StatusTransaksi.dibayar.value){
            return "Dibayar";
        }
        return "Dikonfirmasi";
    }
}
