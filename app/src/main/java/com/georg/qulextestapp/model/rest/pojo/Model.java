package com.georg.qulextestapp.model.rest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by georg on 23.09.2016.
 */

public class Model {

    @SerializedName("data")
    @Expose
    public List<Datum> data = new ArrayList<Datum>();

}
