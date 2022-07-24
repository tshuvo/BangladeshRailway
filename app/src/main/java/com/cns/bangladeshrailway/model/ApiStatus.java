package com.cns.bangladeshrailway.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiStatus implements Serializable {

    @SerializedName("status")
    private boolean responseStatus;

    @SerializedName("code")
    private int responseCode;

    @SerializedName("msg")
    private String responseMessage;

    public boolean isResponseStatus() {
        return responseStatus;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}