package entity;

public class Brand {
    private int idBrand;
    private String brand;

    public Brand(int idBrand, String brand) {
        this.idBrand = idBrand;
        this.brand = brand;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand= brand;
    }
}
