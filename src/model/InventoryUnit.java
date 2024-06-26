package model;

public class InventoryUnit {
    // Properties
    private int shoeID;
    private String verificationSerialID;
    private String unitColor;
    private int manufactureYear;
    private int scans;

    // Constructors
    public InventoryUnit(int shoeID, String verificationSerialID, String unitColor, int manufactureYear, int scans) {
        this.shoeID = shoeID;
        this.verificationSerialID = verificationSerialID;
        this.unitColor = unitColor;
        this.manufactureYear = manufactureYear;
        this.scans = scans;
    }

    // Getters and Setters (Encapsulation)
    public int getShoeID() {
        return shoeID;
    }

    public void setShoeID(int shoeID) {
        this.shoeID = shoeID;
    }

    public String getVerificationSerialID() {
        return verificationSerialID;
    }

    public void setVerificationSerialID(String verificationSerialID) {
        this.verificationSerialID = verificationSerialID;
    }

    public String getUnitColor() {
        return unitColor;
    }

    public void setUnitColor(String unitColor) {
        this.unitColor = unitColor;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public int getScans() {
        return scans;
    }

    public void setScans(int scans) {
        this.scans = scans;
    }
}