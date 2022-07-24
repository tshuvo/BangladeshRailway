package com.cns.bangladeshrailway.networks;

public interface ApiUrl {
    // String BASE_URL = "http://210.4.76.133:5121/";
    String BASE_URL = "http://202.51.187.59:90/";
    String API_URL = "api/";

    String URL_DEVICE_AUTHORIZATION = BASE_URL + API_URL + "divice-insert";
    String URL_RAIL_INFO = BASE_URL + API_URL + "rail-info";
}