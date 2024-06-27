package model;

public class InventoryUnit {
    private String shoeID;
    private String verificationSerialID;
    private String unitColor;
    private int manufacturingYear; // Keep this one, as it's being used
    private int numberOfScans;

    public InventoryUnit(String shoeID, String verificationSerialID, String unitColor, int manufacturingYear,
            int numberOfScans) {
        this.shoeID = shoeID;
        this.verificationSerialID = verificationSerialID;
        this.unitColor = unitColor;
        this.manufacturingYear = manufacturingYear;
        this.numberOfScans = numberOfScans;
    }

    // Getters and Setters for all properties
    public String getShoeID() {
        return shoeID;
    }

    public void setShoeID(String shoeID) {
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

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public int getNumberOfScans() {
        return numberOfScans;
    }

    public void setNumberOfScans(int numberOfScans) {
        this.numberOfScans = numberOfScans;
    }
}