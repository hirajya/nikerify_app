package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class verification {
    private int verification_id;
    private int user_id;
    private String verification_date;
    private String verification_time;
    private int shoe_id;
    private boolean authenticity_result;
    private String serial_number;

    public verification(int verification_id, int user_id, String verification_date, String verification_time, int shoe_id, boolean authenticity_result, String serial_number) {
        this.verification_id = verification_id;
        this.user_id = user_id;
        this.verification_date = verification_date;
        this.verification_time = verification_time;
        this.shoe_id = shoe_id;
        this.authenticity_result = authenticity_result;
        this.serial_number = serial_number;
    }

    public verification(int user_id, String verification_date, String verification_time, int shoe_id, boolean authenticity_result, String serial_number) {
        this.user_id = user_id;
        this.verification_date = verification_date;
        this.verification_time = verification_time;
        this.shoe_id = shoe_id;
        this.authenticity_result = authenticity_result;
        this.serial_number = serial_number;
    }

    public verification() {
    }

    public int getVerification_id() {
        return verification_id;
    }

    public void setVerification_id(int verification_id) {
        this.verification_id = verification_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getVerification_date() {
        return verification_date;
    }

    public void setVerification_date(String verification_date) {
        this.verification_date = verification_date;
    }

    public String getVerification_time() {
        return verification_time;
    }

    public void setVerification_time(String verification_time) {
        this.verification_time = verification_time;
    }

    public int getShoe_id() {
        return shoe_id;
    }

    public void setShoe_id(int shoe_id) {
        this.shoe_id = shoe_id;
    }

    public boolean isAuthenticity_result() {
        return authenticity_result;
    }

    public void setAuthenticity_result(boolean authenticity_result) {
        this.authenticity_result = authenticity_result;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    
}
