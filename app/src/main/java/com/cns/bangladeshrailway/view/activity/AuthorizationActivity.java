package com.cns.bangladeshrailway.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.widget.ContentLoadingProgressBar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cns.bangladeshrailway.Apps;
import com.cns.bangladeshrailway.DetailsActivity;
import com.cns.bangladeshrailway.R;
import com.cns.bangladeshrailway.listener.AppsExistListener;
import com.cns.bangladeshrailway.model.ApiStatus;
import com.cns.bangladeshrailway.networks.HttpRequest;
import com.cns.bangladeshrailway.networks.HttpResponse;
import com.cns.bangladeshrailway.utils.Common;
import com.cns.bangladeshrailway.utils.SharedPreferencesManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.lang.System;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.provider.Settings.*;
import static com.cns.bangladeshrailway.networks.ApiUrl.URL_DEVICE_AUTHORIZATION;

public class AuthorizationActivity extends AppCompatActivity implements AppsExistListener {

    private AuthorizationActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        uiInit();
    }

    private void uiInit() {

        this.context = AuthorizationActivity.this;
        AppCompatEditText acEtAuthorizationCode = findViewById(R.id.ac_et_authorization_code);
        FloatingActionButton fabNextToAuthorization = findViewById(R.id.fab_next_to_authorization);
        ContentLoadingProgressBar clProgressBarNext = findViewById(R.id.cl_progress_bar_next);

        fabNextToAuthorization.setOnClickListener(view -> {
            String authCode = Objects.requireNonNull(acEtAuthorizationCode.getText()).toString();
            if (TextUtils.isEmpty(authCode)) {
                Apps.snackBarMsg(fabNextToAuthorization, getString(R.string.txt_empty_auth_code_bn), 0, true);
            } else {
                if (Common.ifInternetIsOn(context, AuthorizationActivity.this)) {

                    clProgressBarNext.setVisibility(View.VISIBLE);
                    fabNextToAuthorization.setEnabled(false);

                    //================== DEVICE ID ==================
                    @SuppressLint("HardwareIds") String deviceId = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
                    // Toast.makeText(context, "deviceId " + deviceId, Toast.LENGTH_LONG).show();
                    //================== DEVICE ID ==================

                    Map<String, String> params = new HashMap<>();
                    params.put("divice_pin", authCode);
                    params.put("divice_mac", deviceId);

                    HttpRequest.POST(context, URL_DEVICE_AUTHORIZATION, params, new HttpResponse() {
                        @Override
                        public void onSuccess(String response) {
                            try {
                                ApiStatus apiStatus = new Gson().fromJson(response, ApiStatus.class);
                                if (apiStatus.getResponseCode() == 1) {
                                    boolean status = SharedPreferencesManager.getInstance(context).saveToSession(authCode, deviceId);
                                    if (status) {
                                        Apps.redirect(context, DetailsActivity.class, true);
                                    } else {
                                        Apps.snackBarMsg(fabNextToAuthorization, getString(R.string.error_msg_bn), 0, true);
                                    }
                                } else {
                                    Apps.snackBarMsg(fabNextToAuthorization, apiStatus.getResponseMessage(), 0, true);
                                }
                                if (clProgressBarNext.getVisibility() == View.VISIBLE) {
                                    clProgressBarNext.setVisibility(View.INVISIBLE);
                                }
                                fabNextToAuthorization.setEnabled(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(String error) {
                            if (clProgressBarNext.getVisibility() == View.VISIBLE) {
                                clProgressBarNext.setVisibility(View.INVISIBLE);
                            }
                            fabNextToAuthorization.setEnabled(true);
                            Apps.snackBarMsg(fabNextToAuthorization, getString(R.string.error_msg_bn), 0, true);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void appsExist() {
        finish();
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}