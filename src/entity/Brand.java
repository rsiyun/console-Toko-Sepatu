package entity;

public class Brand {
    private int id_brand;
    private String brand;

    public Brand(int id_brand, String brand) {
        this.id_brand = id_brand;
        this.brand = brand;
    }

    public int getId_Brand() {
        return id_brand;
    }

    public void setId_Brand(int id_brand) {
        this.id_brand = id_brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand= brand;
    }
}
