package com.example.hariharsudan.bc.Adapter;

import java.io.Serializable;

/**
 * Created by Subash on 16-02-2017.
 */

public class Tablets implements Serializable
{
    public String name;
    public Boolean mrng,mrngt,noon,noont,night,nightt;
    public Integer tcount;
    public Tablets()
    {

    }
    public Tablets(String name,Boolean mrng,Boolean noon,Boolean night,Boolean mrngt,Boolean noont,Boolean nightt,Integer tcount)
    {
        this.name = name;
        this.mrng = mrng;
        this.mrngt = mrngt;
        this.noon = noon;
        this.noont = noont;
        this.night = night;
        this.nightt = nightt;
        this.tcount = tcount;

    }
}
