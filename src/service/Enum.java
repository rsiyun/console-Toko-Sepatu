package service;

public class Enum {
    public enum StatusUsers {
        Active(1),Unactive(0);

        public final int value;

        private StatusUsers(int value) {
            this.value = value;
        }
    }
    public enum RoleUsers {
        Users("User",0),Admin("Admin",1),superAdmin("Super Admin",2);

        public final int value;
        public final String label;

        private RoleUsers(String label, int value) {
            this.value = value;
            this.label = label;
        }
    }
    public enum StatusTransaksi {
        dipesan(0),dibayar(1), dikonfirmasi(2), dibatalkan(3);

        public final int value;

        private StatusTransaksi(int value) {
            this.value = value;
        }
    }
}
