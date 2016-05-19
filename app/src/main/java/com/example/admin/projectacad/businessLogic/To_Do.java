package com.example.admin.projectacad.businessLogic;

/**
 * Created by admin on 13-05-2016.
 */
public class To_Do {
    private int ID;
    private String Title;

    public To_Do(int ID, String title, String description, String date, int status) {
        this.ID = ID;
        Title = title;
        Description = description;
        Date = date;
        Status = status;
    }

    private String Description;
    private String Date;
    private int Status;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public To_Do() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
