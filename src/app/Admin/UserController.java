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
        System.out.println("2. Mengubah User");
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
        System.out.print("Masukkan id user yang ingin diubah : ");
        int idUser = scanner.nextInt();
        if(!checkUser(listUser, idUser)){
            System.out.println("Tolong Input id dengan benar");
            scanner.close();
            return;
        }

        System.out.println("Apa yang mau diubah?");
        System.out.println("1. Mengubah Role User");    
        System.out.println("2. Mengubah Status User");
        System.out.print("Pilih Pilihan anda: ");
        int pilihan = scanner.nextInt();
        switch (pilihan) {
            case 1:
                this.UpdateRoleUser(listUser, idUser);
                break;
            case 2:
                this.UpdateStatusUser(listUser, idUser);
                break;
            default:
                System.out.println("Pilihan tidak ada");
                break;
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
                cmd.addRow(String.valueOf(user.getIdUser()), user.getUsername(), getRole(user), getStatus(user));
            }
            cmd.print();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void UpdateRoleUser(ArrayList<User> listUser, int idUser) throws Exception{
        int role = checkRole(listUser, idUser);
        role = setRole(role);
        String sql = "UPDATE users SET role = "+role+" WHERE id_user = "+idUser+" AND role != "+role+";";
        if (sqlexupdate(sql) != 0) {
            System.out.println("Berhasil mengubah role user");
        }
    }
    private void UpdateStatusUser(ArrayList<User> listUser, int idUser) throws Exception{
        int status = checkStatus(listUser, idUser);
        status = setStatus(status);
        String sql = "UPDATE users SET active = "+status+" WHERE id_user = "+idUser+" AND active != "+status+";";
        if (sqlexupdate(sql) != 0) {
            System.out.println("Berhasil mengubah status user");
        }
    }

    private String getStatus(User user){
        if(user.getActive() == Enum.StatusUsers.Unactive.value){
            return "Unactive";
        }
        return "Active";
    }
    private int setStatus(int status){
        if(status == Enum.StatusUsers.Unactive.value){
            System.out.print("User tidak aktif, ingin diaktifkan? (y/n)");
            return chooseStatus(status, 1);
        }
        System.out.print("User aktif, ingin di nonaktifkan? (y/n)");
        return chooseStatus(status, 0);
    }
    private int chooseStatus(int oldEnum, int newEnum){
        Scanner scanner = new Scanner(System.in);
        String pilihan = scanner.next();
        if(pilihan.equals("y") || pilihan.equals("Y")){
            scanner.close();
            return newEnum;
        }
        scanner.close();
        return oldEnum;
    }
    private String getRole(User user){
        if(user.getRole() == Enum.RoleUsers.Users.value){
            return Enum.RoleUsers.Users.label;
        }else if(user.getRole() == Enum.RoleUsers.Admin.value){
            return Enum.RoleUsers.Admin.label;
        }
        return Enum.RoleUsers.superAdmin.label;
    }
    private int setRole(int role){
        Scanner scanner = new Scanner(System.in);
        if(role == Enum.RoleUsers.Users.value){
            System.out.println("User adalah USER, ingin dijadikan ADMIN? (y/n)");
            System.out.println("1. SUPER ADMIN");
            System.out.println("2. ADMIN");
            System.out.println("Pilih Pilihan anda: ");
            int pilihan = scanner.nextInt();
            scanner.close();
            if (pilihan == 1)
                return Enum.RoleUsers.superAdmin.value;
            else if (pilihan == 2)
                return Enum.RoleUsers.Admin.value;        
        }else if(role == Enum.RoleUsers.Admin.value){
            System.out.println("User adalah ADMIN, ingin dirubah menjadi?");
            System.out.println("1. SUPER ADMIN");
            System.out.println("2. USER");
            System.out.println("Pilih Pilihan anda: ");
            int pilihan = scanner.nextInt();
            scanner.close();
            if (pilihan == 1)
                return Enum.RoleUsers.superAdmin.value;
            else if (pilihan == 2)
                return Enum.RoleUsers.Users.value;
        }else if (role == Enum.RoleUsers.superAdmin.value){
            System.out.println("User adalah SUPER ADMIN, ingin dijadikan ADMIN? (y/n)");
            System.out.println("1. ADMIN");
            System.out.println("2. USER");
            System.out.println("Pilih Pilihan anda: ");
            int pilihan = scanner.nextInt();
            scanner.close();
            if (pilihan == 1)
                return Enum.RoleUsers.Admin.value;
            else if (pilihan == 2)
                return Enum.RoleUsers.Users.value;
        }
        scanner.close();
        return role;
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
        int status = 0; //default active
        for(User user : list){
            if(user.getIdUser() == id){
                status = user.getActive();
            }
        }
        return status;
    }
    private int checkRole(ArrayList<User> list, int id) throws Exception{
        int role = 0; //default user
        for(User user : list){
            if(user.getIdUser() == id){
                role = user.getRole();
            }
        }
        return role;
    }
}
