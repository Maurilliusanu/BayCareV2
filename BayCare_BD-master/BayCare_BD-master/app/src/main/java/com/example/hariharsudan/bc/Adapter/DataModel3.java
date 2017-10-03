package com.example.hariharsudan.bc.Adapter;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class DataModel3 {

    String doctor_name;
    String doctor_age;
    String doctor_contact;
    int image;


    public DataModel3(String doctor_name, String doctor_age, String doctor_contact, int image) {
        this.doctor_name = doctor_name;
        this.doctor_age = doctor_age;
        this.doctor_contact = doctor_contact;
        this.image=image;
    }

    public String getdoctorName() {
        return doctor_name;
    }

    public String getdoctorAge() {
        return doctor_age;
    }

    public int getImage() {
        return image;
    }



    public String getdoctorContact() {
        return doctor_contact;
    }
}