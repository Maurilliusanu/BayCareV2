package com.example.hariharsudan.bc.Adapter;

import java.io.Serializable;

/**
 * Created by Subash on 06-02-2017.
 */

public class Loginnext implements Serializable
{
    public Boolean islog;
    public String user;
    public Loginnext(String user, Boolean islog)
    {
        this.user = user;
        this.islog = islog;
    }
}
