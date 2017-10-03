package com.example.hariharsudan.bc.Adapter;

/**
 * Created by subash on 16/3/17.
 */

public class DataModel4
{
    int img;
    public String aadhar,disease,age,name;



    public DataModel4(String aadhar, String disease, String age, String name)
    {
        this.aadhar = aadhar;
        this.disease = disease;
        this.age = age;
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
