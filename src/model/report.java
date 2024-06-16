package model;

public class report {
    private int report_id;
    private int user_id;
    private int verification_id;
    private String input_shoe_model;
    private String purchase_date;
    private String type_seller;

    public report(int report_id, int user_id, int verification_id, String input_shoe_model, String purchase_date, String type_seller) {
        this.report_id = report_id;
        this.user_id = user_id;
        this.verification_id = verification_id;
        this.input_shoe_model = input_shoe_model;
        this.purchase_date = purchase_date;
        this.type_seller = type_seller;
    }

    public report(int user_id, int verification_id, String input_shoe_model, String purchase_date, String type_seller) {
        this.user_id = user_id;
        this.verification_id = verification_id;
        this.input_shoe_model = input_shoe_model;
        this.purchase_date = purchase_date;
        this.type_seller = type_seller;
    }

    public report() {
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVerification_id() {
        return verification_id;
    }

    public void setVerification_id(int verification_id) {
        this.verification_id = verification_id;
    }

    public String getInput_shoe_model() {
        return input_shoe_model;
    }

    public void setInput_shoe_model(String input_shoe_model) {
        this.input_shoe_model = input_shoe_model;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getType_seller() {
        return type_seller;
    }

    public void setType_seller(String type_seller) {
        this.type_seller = type_seller;
    }
}
