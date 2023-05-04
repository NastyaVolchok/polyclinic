package controllers;

public class RecorsSec implements BulderRecords{
    @Override
    public void setType(String type) {
        this.type=type;
    }

    @Override
    public void setSeats(int seats) {
        this.seast=seats;
    }

    @Override
    public void setRecrods(String date) {
        this.date=date;
    }

    String  type;
    int seast;
    String date;
}
