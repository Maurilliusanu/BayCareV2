package com.example.hariharsudan.bc.Adapter;

/**
 * Created by Hariharsudan on 2/13/2017.
 */

public class DataModel2 {

    String patient_name;
    String patient_age;
    String patient_contact;
    int image;
    String patient_blood;


    public DataModel2(String patient_name, String patient_age, String patient_contact, int image, String patient_blood) {
        this.patient_name = patient_name;
        this.patient_age = patient_age;
        this.patient_contact = patient_contact;
        this.image=image;
        this.patient_blood=patient_blood;
    }

    public String getPatientName() {
        return patient_name;
    }

    public String getPatientAge() {
        return patient_age;
    }

    public int getImage() {
        return image;
    }

    public String getPatientBlood() {
        return patient_blood;
    }

    public String getPatientContact() {
        return patient_contact;
    }
}