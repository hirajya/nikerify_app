package model;

public class location {
    private int store_location_id;
    private String street_number;
    private String block_number;
    private String barangay;
    private String city;

    public location(int store_location_id, String street_number, String block_number, String barangay, String city) {
        this.store_location_id = store_location_id;
        this.street_number = street_number;
        this.block_number = block_number;
        this.barangay = barangay;
        this.city = city;
    }

    public location(String street_number, String block_number, String barangay, String city) {
        this.street_number = street_number;
        this.block_number = block_number;
        this.barangay = barangay;
        this.city = city;
    }

    public location() {
    }

    public int getStore_location_id() {
        return store_location_id;
    }

    public void setStore_location_id(int store_location_id) {
        this.store_location_id = store_location_id;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getBlock_number() {
        return block_number;
    }

    public void setBlock_number(String block_number) {
        this.block_number = block_number;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
