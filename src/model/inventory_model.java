package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class inventory_model {
    private String shoe_ID;
    private String model_ID;
    private String verification_serial_ID;
    private String unit_color;
    private String manufacturing_location;
    private Year manufacturing_year;
    private String batch_number;
    private Date last_scan_date;

    public inventory_model(String shoe_ID, String model_ID, String verification_serial_ID, String unit_color, String manufacturing_location, Year manufacturing_year, String batch_number, Date last_scan_date) {
        this.shoe_ID = shoe_ID;
        this.model_ID = model_ID;
        this.verification_serial_ID = verification_serial_ID;
        this.unit_color = unit_color;
        this.manufacturing_location = manufacturing_location;
        this.manufacturing_year = manufacturing_year;
        this.batch_number = batch_number;
        this.last_scan_date = last_scan_date;
    }

    public inventory_model(String model_ID, String verification_serial_ID, String unit_color, String manufacturing_location, Year manufacturing_year, String batch_number, Date last_scan_date) {
        this.model_ID = model_ID;
        this.verification_serial_ID = verification_serial_ID;
        this.unit_color = unit_color;
        this.manufacturing_location = manufacturing_location;
        this.manufacturing_year = manufacturing_year;
        this.batch_number = batch_number;
        this.last_scan_date = last_scan_date;
    }

    public String getShoe_ID() {
        return shoe_ID;
    }

    public void setShoe_ID(String shoe_ID) {
        this.shoe_ID = shoe_ID;
    }

    public String getModel_ID() {
        return model_ID;
    }

    public void setModel_ID(String model_ID) {
        this.model_ID = model_ID;
    }

    public String getVerification_serial_ID() {
        return verification_serial_ID;
    }

    public void setVerification_serial_ID(String verification_serial_ID) {
        this.verification_serial_ID = verification_serial_ID;
    }

    public String getUnit_color() {
        return unit_color;
    }

    public void setUnit_color(String unit_color) {
        this.unit_color = unit_color;
    }

    public String getManufacturing_location() {
        return manufacturing_location;
    }

    public void setManufacturing_location(String manufacturing_location) {
        this.manufacturing_location = manufacturing_location;
    }

    public Year getManufacturing_year() {
        return manufacturing_year;
    }

    public void setManufacturing_year(Year manufacturing_year
    ) {
        this.manufacturing_year = manufacturing_year;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public Date getLast_scan_date() {
        return last_scan_date;
    }

    public void setLast_scan_date(Date last_scan_date) {
        this.last_scan_date = last_scan_date;
    }

    

    


    

    






}
