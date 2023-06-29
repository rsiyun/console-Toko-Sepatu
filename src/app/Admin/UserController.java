package app.Admin;
import java.util.Scanner;
import java.util.ArrayList;
import entity.User;
import service.AllSql;
import service.CommandLineTable;
import service.Enum;

public class UserController extends AllSql{
    public void UserAdmin() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Menampilkan User");
        System.out.println("2. Ban/UnBan User");
        System.out.print("Pilih Pilihan anda: ");
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
        System.out.print("Masukkan id user yang ingin di ban/unban: ");
        int idUser = scanner.nextInt();
        if(!checkUser(listUser, idUser)){
            System.out.println("Tolong Input id dengan benar");
            scanner.close();
            return;
        }
        int status = checkStatus(listUser, idUser);
        status = setStatus(status);
        String sql = "UPDATE users SET active = "+status+" WHERE id_user = "+idUser+" AND active != "+status+";";
        if (sqlexupdate(sql) != 0) {
            System.out.println("Berhasil mengubah status user");
        }
        scanner.close();
    }

    private void ShowUser() throws Exception{
        try{
            ArrayList<User> listUser = this.selectUser();
            CommandLineTable cmd = new CommandLineTable();
            cmd.setShowVerticalLines(true);
            cmd.setHeaders("ID User", "Username", "Role", "Status");
            for(User user : listUser){
                cmd.addRow(String.valueOf(user.getIdUser()), user.getUsername(), String.valueOf(user.getRole()), getstatus(user));
            }
            cmd.print();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // Siyun (add new method)

    private String getstatus(User user){
        if(user.getActive() == Enum.StatusUsers.Unactive.value){
            return "Unactive";
        }
        return "Active";
    }
    private int choose(int status, int newStatus){
        Scanner scanner = new Scanner(System.in);
        String pilihan = scanner.next();
        if(pilihan.equals("y") || pilihan.equals("Y")){
            scanner.close();
            return newStatus;
        }
        scanner.close();
        return status;
    }
    private int setStatus(int status){
        if(status == Enum.StatusUsers.Unactive.value){
            System.out.print("User tidak aktif, ingin diaktifkan? (y/n)");
            return choose(status, 1);
        }
        System.out.print("User aktif, ingin di nonaktifkan? (y/n)");
        return choose(status, 0);
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
        int status = 0;
        for(User user : list){
            if(user.getIdUser() == id){
                status = user.getActive();
            }
        }
        return status;
    }
}
