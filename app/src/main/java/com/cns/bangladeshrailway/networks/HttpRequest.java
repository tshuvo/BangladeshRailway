package com.cns.bangladeshrailway.networks;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.cns.bangladeshrailway.utils.Common;

import java.util.Map;

public class HttpRequest {

    public static void GET(Context context, String url, Map<String, String> queryParams, final HttpResponse httpResponse) {
        if (Common.isNetworkAvailable(context)) {
            if (queryParams != null) {
                url += "?" + mapToQueryString(queryParams);
            }
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, httpResponse::onSuccess, error -> {
                // HttpUtil.parseVolleyError(error);
                httpResponse.onError(error.getMessage());
            });
            SingletonRequestQueue.getInstance(context).addToRequestQueue(stringRequest);
        }
    }

    private static String mapToQueryString(Map<String, String> params) {
        int count = 0;
        StringBuilder encodedParams = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            count++;
            encodedParams.append(entry.getKey());
            encodedParams.append('=');
            encodedParams.append(entry.getValue());
            if (count < params.size()) {
                encodedParams.append('&');
            }
        }
        return encodedParams.toString();
    }

    public static void POST(Context context, String url, final Map<String, String> requestData, final HttpResponse httpResponse) {
        if (Common.isNetworkAvailable(context)) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, httpResponse::onSuccess, error -> httpResponse.onError(error.getMessage())) {
                @Override
                protected Map<String, String> getParams() {
                    return requestData;
                }
            };

            /* stringRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }
                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }
                @Override
                public void retry(VolleyError error) throws VolleyError {
                }
            }); */

            SingletonRequestQueue.getInstance(context).addToRequestQueue(stringRequest);
        }
    }
}