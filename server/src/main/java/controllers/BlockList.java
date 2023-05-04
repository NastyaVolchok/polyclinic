package controllers;

public class BlockList {
    int id;
    int id_user;
    String reason;
    String dateBlock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(String dateBlock) {
        this.dateBlock = dateBlock;
    }
}
