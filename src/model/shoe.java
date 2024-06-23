package model;

public class shoe {
    private int shoe_id;
    private String serial_number;
    private String manufacture_year;
    private String shoe_color;
    private String batch_number;
    private String shoe_model;

    public shoe(int shoe_id, String serial_number, String manufacture_year, String batch_number, String shoe_model) {
        this.shoe_id = shoe_id;
        this.serial_number = serial_number;
        this.manufacture_year = manufacture_year;
        this.batch_number = batch_number;
        this.shoe_model = shoe_model;
    }

    public shoe(String serial_number, String manufacture_year, String batch_number, String shoe_model) {
        this.serial_number = serial_number;
        this.manufacture_year = manufacture_year;
        this.batch_number = batch_number;
        this.shoe_model = shoe_model;
    }

    public shoe() {
    }

    public int getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(int shoe_id) {
        this.shoe_id = shoe_id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getManufacture_year() {
        return manufacture_year;
    }

    public void setManufacture_year(String manufacture_year) {
        this.manufacture_year = manufacture_year;
    }

    public void setShoe_color(String shoe_color) {
        this.shoe_color = shoe_color;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getShoe_model() {
        return shoe_model;
    }

    public void setShoe_model(String shoe_model) {
        this.shoe_model = shoe_model;
    }
}
