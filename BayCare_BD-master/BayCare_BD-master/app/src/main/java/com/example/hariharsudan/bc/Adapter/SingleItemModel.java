package com.example.hariharsudan.bc.Adapter;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

/**
 * Created by pratap.kesaboyina on 01-12-2015.
 */
public class SingleItemModel {


    private String name;
    private String url;
    private String description;
    private Uri uri;
    private StorageReference storageReference;

    public StorageReference getStorageReference() {
        return storageReference;
    }

    public void setStorageReference(StorageReference storageReference) {
        this.storageReference = storageReference;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public SingleItemModel() {
    }

    public SingleItemModel(String name, String url,StorageReference storageReference) {
        this.name = name;
        this.url = url;
        this.storageReference=storageReference;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
