package by.itnik.model.beans;

import java.sql.Date;

//Класс postings
public class postings {
    private double mat_Doc;
    private int item;
    private Date doc_Date;
    private Date pstng_Date;
    private String  material_Description;
    private int quantity;
    private String bUn;
    private double amount_LC;
    private String crcy;
    private String  user_Name;
    private boolean isAuthDelivery=false;

    public postings() {
    }

    public postings(double mat_Doc, int item, Date doc_Date, Date pstng_Date, String material_Description, int quantity, String bUn, double amount_LC, String crcy, String user_Name, boolean isAuthDelivery) {
        this.mat_Doc = mat_Doc;
        this.item = item;
        this.doc_Date = doc_Date;
        this.pstng_Date = pstng_Date;
        this.material_Description = material_Description;
        this.quantity = quantity;
        this.bUn = bUn;
        this.amount_LC = amount_LC;
        this.crcy = crcy;
        this.user_Name = user_Name;
        this.isAuthDelivery = isAuthDelivery;
    }

    public double getMat_Doc() {
        return mat_Doc;
    }

    public void setMat_Doc(double mat_Doc) {
        this.mat_Doc = mat_Doc;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Date getDoc_Date() {
        return doc_Date;
    }

    public void setDoc_Date(Date doc_Date) {
        this.doc_Date = doc_Date;
    }

    public Date getPstng_Date() {
        return pstng_Date;
    }

    public void setPstng_Date(Date pstng_Date) {
        this.pstng_Date = pstng_Date;
    }

    public String getMaterial_Description() {
        return material_Description;
    }

    public void setMaterial_Description(String material_Description) {
        this.material_Description = material_Description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getbUn() {
        return bUn;
    }

    public void setbUn(String bUn) {
        this.bUn = bUn;
    }

    public double getAmount_LC() {
        return amount_LC;
    }

    public void setAmount_LC(double amount_LC) {
        this.amount_LC = amount_LC;
    }

    public String getCrcy() {
        return crcy;
    }

    public void setCrcy(String crcy) {
        this.crcy = crcy;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public boolean getIsAuthDelivery() {
        return isAuthDelivery;
    }

    public void setIsAuthDelivery(boolean authDelivery) {
        isAuthDelivery = authDelivery;
    }

    @Override
    public String toString() {
        return "postings{" +
                "mat_Doc=" + mat_Doc +
                ", item=" + item +
                ", doc_Date=" + doc_Date +
                ", pstng_Date=" + pstng_Date +
                ", material_Description='" + material_Description + '\'' +
                ", quantity=" + quantity +
                ", bUn='" + bUn + '\'' +
                ", amount_LC=" + amount_LC +
                ", crcy='" + crcy + '\'' +
                ", user_Name='" + user_Name + '\'' +
                ", isAuthDelivery=" + isAuthDelivery +
                '}';
    }
}

