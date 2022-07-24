package com.cns.bangladeshrailway.utils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cns.bangladeshrailway.DetailsActivity;
import com.cns.bangladeshrailway.R;

public class InternetChangeReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        if (DetailsActivity.acIvSignalInternet != null) {
            if (Common.isNetworkAvailable(context)) {
                DetailsActivity.acIvSignalInternet.setImageResource(R.drawable.ic_wifi_connected);
            } else {
                DetailsActivity.acIvSignalInternet.setImageResource(R.drawable.ic_wifi_disconnected);
            }
        }
    }
}