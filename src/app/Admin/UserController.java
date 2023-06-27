package app.Admin;
import java.util.Scanner;
import java.util.ArrayList;
import entity.User;
import service.AllSql;
import service.CommandLineTable;

public class UserController extends AllSql{
    public void UserAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Menampilkan User");
        System.out.println("2. Ban/UnBan User");
        System.out.println("Pilih Pilihan anda: ");
        int pilihan = scanner.nextInt();
        switch (pilihan) {
            case 1:
                this.ShowUser();
                break;
            case 2:
                this.ShowUser();
                this.UpdateUser();
                break;
            default:
                System.out.println("Pilihan tidak ada");
                break;
        }
        scanner.close();
    }

    public void UpdateUser() throws Exception{
        ArrayList<User> listUser = this.selectUser();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan id user yang ingin di ban/unban: ");
        int idUser = scanner.nextInt();
        if(!checkUser(listUser, idUser)){
            System.out.println("Tolong Input id dengan benar");
            scanner.close();
            return;
        }
        int status = checkStatus(listUser, idUser);
        if(status == 0){
            System.out.println("User tidak aktif, ingin diaktifkan? (y/n)");
            String pilihan = scanner.next();
            if(pilihan.equals("y") || pilihan.equals("Y")){
                status = 1;
            } else {
                scanner.close();
                return;
            }
        } else if (status == 1){
            System.out.println("User aktif, ingin di nonaktifkan? (y/n)");
            String pilihan = scanner.next();
            if(pilihan.equals("y") || pilihan.equals("Y")){
                status = 0;
            } else {
                scanner.close();
                return;
            }
        }
        String sql = "UPDATE users SET active = "+status+" WHERE id_user = "+idUser+";";
        this.sqlexupdate(sql);
        System.out.println("Berhasil mengubah status user");
        scanner.close();
    }

    private void ShowUser() throws Exception{
        try{
            ArrayList<User> listUser = this.selectUser();
            CommandLineTable cmd = new CommandLineTable();
            cmd.setShowVerticalLines(true);
            cmd.setHeaders("ID User", "Username", "Role", "Status");
            for(User user : listUser){
                String status = "";
                if(user.getActive() == 0){
                    status = "Unactive";
                } else if(user.getActive() == 1){
                    status = "Active";
                }
                cmd.addRow(String.valueOf(user.getIdUser()), user.getUsername(), String.valueOf(user.getRole()), status);
            }
            cmd.print();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private boolean checkUser(ArrayList<User> list, int id) throws Exception{
        for(User user : list){
            if(user.getIdUser() == id){
                return true;
            }
        }
        return false;
    } 
    private int checkStatus(ArrayList<User> list, int id) throws Exception{
        int status = 0; // 0 = unactive, 1 = active. with default value unactive
        for(User user : list){
            if(user.getIdUser() == id){
                status = user.getActive();
            }
        }
        return status;
    }
}
