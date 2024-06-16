package model;

public class user {
    private int user_id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String contact_number;
    private String email;
    private String password;

    public user(int user_id, String first_name, String middle_name, String last_name, String contact_number, String email, String password) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
        this.email = email;
        this.password = password;
    }

    public user(String first_name, String middle_name, String last_name, String contact_number, String email, String password) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.contact_number = contact_number;
        this.email = email;
        this.password = password;
    }

    public user(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public user() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
