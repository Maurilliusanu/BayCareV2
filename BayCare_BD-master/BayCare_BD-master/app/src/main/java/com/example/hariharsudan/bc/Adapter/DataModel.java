package com.example.hariharsudan.bc.Adapter;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class DataModel {

    String medication;
    String prescribedby;
    String date;
    String expiry;
    int image;
    String intake;

    public String getDate() {
        return date;
    }

    public DataModel(String medication, String prescribedby, String date, String expiry, int image, String intake) {
        this.medication = medication;
        this.prescribedby = prescribedby;
        this.expiry = expiry;
        this.image=image;
        this.intake=intake;
        this.date=date;
    }

    public String getMedication() {
        return medication;
    }

    public String getPrescribedby() {
        return prescribedby;
    }

    public int getImage() {
        return image;
    }

    public String getIntake() {
        return intake;
    }

    public String getExpiry() {
        return expiry;
    }
}