package com.example.hariharsudan.bc.Adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Subash on 16-02-2017.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Pillsadapter2 implements Serializable
{

    public ArrayList<Tablets> pills;
    public String tmp;

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public Pillsadapter2(){}
    public ArrayList<Tablets> getPills() {
        return pills;
    }

    public void setPills(ArrayList<Tablets> pills) {
        this.pills = pills;
    }
}
