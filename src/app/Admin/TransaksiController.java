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
        System.out.println("1. Menampilkan data Transaksi");
        System.out.println("2. Konfirmasi Transaksi");
        System.out.println("3. Menghapus Transaksi");
        System.out.println("Pilih Pilihan anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1":
                showTransaksi();
                TransaksiDetailAdmin();
                break;
            case "2":
                showOnlyTransaksiDibayar();
                updateTransaksi();
                break;
            case "3":
                showTransaksi();
                deleteTransaksi();
                break;
            default:
                System.out.println("Pilihan tidak ada");
                break;
        }
        scanner.close();
    }
    public void TransaksiDetailAdmin() throws Exception{
        ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan id transaksi yang ingin di lihat detailnya : ");
        String idTransaksi = scanner.nextLine();
        // CHECK ADA TIDAK ID yang diinputkan user
        if(!checkTransaksi(listTransaksi, idTransaksi)){
            System.out.println("Tolong Input id dengan benar");
            scanner.close();
            return;
        }
        // AMBIL OLD DATANYA
        Transaksi oldData = getTransaksibyid(listTransaksi, idTransaksi);
        showTransaksiTerpilih(oldData);
        scanner.close();
    }
    private void updateTransaksi() throws Exception{
        ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan id transaksi yang ingin di konfirmasi : ");
        String idTransaksi = scanner.nextLine();
        // CHECK ADA TIDAK ID yang diinputkan user
        if(!checkTransaksi(listTransaksi, idTransaksi)){
            System.out.println("Tolong Input id dengan benar");
            scanner.close();
            return;
        }
        // AMBIL OLD DATANYA
        Transaksi oldData = getTransaksibyid(listTransaksi, idTransaksi);
        // MENAMPILKAN DATA TRANSAKSI BESERTA DETAIL TRANSAKSI YANG AKAN DIKONFIRMASI
        showTransaksiTerpilih(oldData);
        System.out.println("Apakah anda ingin mengkonfirmasi transaksi ini? (y/n)");
        String pil = scanner.nextLine();
        if(pil.equals("y")||pil.equals("Y")){
            String sql = "UPDATE transaksi SET status = 2 WHERE id_transaksi = "+idTransaksi+";";
            this.sqlexupdate(sql);
            System.out.println("Transaksi telah dikonfirmasi");
        }
        scanner.close();
    }
    private void deleteTransaksi() throws Exception{
        ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan id transaksi yang ingin di hapus: ");
        String idTransaksi = scanner.nextLine();
        if(!checkTransaksi(listTransaksi, idTransaksi)){
            System.out.println("Tolong Input id dengan benar");
            scanner.close();
            return;
        }
        // AMBIL OLD DATANYA
        Transaksi oldData = getTransaksibyid(listTransaksi, idTransaksi);
        // MENAMPILKAN DATA TRANSAKSI BESERTA DETAIL TRANSAKSI YANG AKAN DIHAPUS
        showTransaksiTerpilih(oldData);        
        System.out.println("Apakah anda ingin menghapus transaksi ini? (y/n)");
        String pil = scanner.nextLine();
        if(pil.equals("y")||pil.equals("Y")){
            if(oldData.getStatus() == Enum.StatusTransaksi.dibayar.value){
                String sql2 = "UPDATE produk_detail SET stock = stock + (SELECT quantity FROM transaksi_detail WHERE id_transaksi = "+idTransaksi+" AND id_produk_detail = produk_detail.id_produk_detail) WHERE id_produk_detail = (SELECT id_produk_detail FROM transaksi_detail WHERE id_transaksi = "+idTransaksi+");";
                this.sqlexupdate(sql2); //mengembalikan stock produk, kondisi dibayar tapi dibatalkan oleh admin
            }
            String sql = "DELETE FROM transaksi WHERE id_transaksi = "+idTransaksi+"; DELETE FROM detail_transaksi WHERE id_transaksi = "+idTransaksi+";";
            this.sqlexupdate(sql); //menghapus transaksi beserta transaksi detail terkait
            System.out.println("Transaksi berhasil dihapus");
        }
        scanner.close();
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
    private void showOnlyTransaksiDibayar() throws Exception {
        try {
            ArrayList<Transaksi> listTransaksi = this.selectTransaksi();
            CommandLineTable cmd = new CommandLineTable();
            cmd.setShowVerticalLines(true);
            cmd.setHeaders("ID Transaksi", "ID User", "Total Harga", "Tanggal Transaksi");
            for (Transaksi transaksi : listTransaksi) {
                if(transaksi.getStatus() == Enum.StatusTransaksi.dibayar.value)
                    cmd.addRow(String.valueOf(transaksi.getIdTransaksi()), String.valueOf(transaksi.getIdUser()), String.valueOf(transaksi.getTotalHarga()), String.valueOf(transaksi.getTglTransaksi()));
            }
            System.out.println("Daftar Transaksi yang sudah dibayar :");
            cmd.print();
        }
        catch (Exception e) {
            System.out.println(e);
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
            cmd.setHeaders("ID Detail Transaksi", "ID Transaksi", "ID Produk Detail", "Harga", "Jumlah");
            cmd.setShowVerticalLines(true);
            for(TransaksiDetail transaksiDetail : list){
                cmd.addRow(String.valueOf(transaksiDetail.getIdDetailTransaksi()), String.valueOf(transaksiDetail.getIdTransaksi()), String.valueOf(transaksiDetail.getIdProdukDetail()), String.valueOf(transaksiDetail.getHarga()), String.valueOf(transaksiDetail.getQuantity()));
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
