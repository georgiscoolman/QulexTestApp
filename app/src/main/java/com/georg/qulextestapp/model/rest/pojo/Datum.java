package com.georg.qulextestapp.model.rest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by georg on 23.09.2016.
 */

public class Datum {

    @SerializedName("images")
    @Expose
    public Images images;

}
