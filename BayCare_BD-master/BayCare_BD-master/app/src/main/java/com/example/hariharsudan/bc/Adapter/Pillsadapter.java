package com.example.hariharsudan.bc.Adapter;

import java.util.ArrayList;

/**
 * Created by Subash on 16-02-2017.
 */

public class Pillsadapter
{
   public  Pillsadapter(){
       pills = new ArrayList<>();
   }
    public static ArrayList<Tablets> pills;
    public static ArrayList<Tablets> pillsp;


    public static ArrayList<Tablets> getPills() {
        return pills;
    }

    public static void addPills(Tablets pills) {
        Pillsadapter.pills.add(pills);
    }
}
