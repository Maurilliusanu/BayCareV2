package com.example.hariharsudan.bc.Adapter;

import java.io.Serializable;

/**
 * Created by Subash on 07-02-2017.
 */

public class OrgLoginNext implements Serializable
{
    public Boolean islog;
    public String IRDA;
    public OrgLoginNext(String IRDA, Boolean islog)
    {
        this.IRDA = IRDA;
        this.islog = islog;
    }
}
