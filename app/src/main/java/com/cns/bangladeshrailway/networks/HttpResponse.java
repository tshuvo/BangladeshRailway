package com.cns.bangladeshrailway.networks;

public interface HttpResponse {
    void onSuccess(String response);

    void onError(String error);
}