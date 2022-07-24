package com.cns.bangladeshrailway.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import com.cns.bangladeshrailway.R;
import com.cns.bangladeshrailway.listener.AppsExistListener;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Common {

    private static final char[] bengaliDigits = {'০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'};

    public static String digitEnglishToBengali(@NonNull String number) {
        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i < number.length(); i++) {
                if (Character.isDigit(number.charAt(i))) {
                    if (((int) (number.charAt(i)) - 48) <= 9) {
                        builder.append(bengaliDigits[(int) (number.charAt(i)) - 48]);
                    } else {
                        builder.append(number.charAt(i));
                    }
                } else {
                    builder.append(number.charAt(i));
                }
            }
        } catch (Exception e) {
            return "";
        }
        return builder.toString();
    }

    public static String numberFormatWithComma(@NonNull String number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##,###");
        return decimalFormat.format(Integer.valueOf(number));
    }

    public static void startWaitingDialog(ProgressDialog pDialog, String msg, boolean cancelable) {
        pDialog.setIndeterminate(true);
        pDialog.setMessage(msg);
        pDialog.setCancelable(cancelable);
        Objects.requireNonNull(pDialog.getWindow()).setBackgroundDrawableResource(R.color.blue_ocean);
        pDialog.show();
    }

    public static void stopWaitingDialog(ProgressDialog pDialog) {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isNetworkAvailable2(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    if (networkCapabilities == null) {
                        return false;
                    }
                    boolean isInternetSuspended = !networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED);
                    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) && !isInternetSuspended;
                } else {
                    return false;
                }
            } else {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                boolean status = networkInfo != null && networkInfo.isConnected();
                if (!status) {
                    Network[] allNetworks = connectivityManager.getAllNetworks();
                    return allNetworks.length > 0;
                } else {
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public static boolean ifInternetIsOn(Context context, AppsExistListener appsExistListener) {
        if (!isNetworkAvailable(context)) {
            SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
            dialog.setTitleText(context.getString(R.string.txt_no_internet_bn)).setContentText(context.getString(R.string.txt_no_internet2_bn)).setCustomImage(R.drawable.ic_wifi_disconnected);
            dialog.setCancelable(false);
            dialog.show();
            dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackgroundColor(Color.RED);
            dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setOnClickListener(view -> {
                appsExistListener.appsExist();
            });
            dialog.setConfirmText(context.getString(R.string.txt_ok_bn));
            return false;
        } else {
            return true;
        }
    }

    public static void serverOff(Context context, AppsExistListener appsExistListener) {
        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        dialog.setContentText(context.getString(R.string.txt_no_server_bn)).setCustomImage(R.drawable.ic_server_disconnected);
        dialog.setCancelable(false);
        dialog.show();
        dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackgroundColor(Color.RED);
        dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setOnClickListener(view -> {
            appsExistListener.appsExist();
        });
        dialog.setConfirmText(context.getString(R.string.txt_ok_bn));
    }

    @SuppressLint({"SimpleDateFormat", "NewApi"})
    public static String formatApiDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = dateFormat.parse(date);
            Locale locale = Locale.forLanguageTag("bn-BD");
            assert d != null;
            return new SimpleDateFormat("dd MMMM yyyy", locale).format(d);
        } catch (ParseException e) {
            e.printStackTrace();
            Locale locale = Locale.forLanguageTag("bn-BD");
            return new SimpleDateFormat("dd MMMM yyyy", locale).format(new Date());
        }
    }

    @SuppressLint({"SimpleDateFormat", "NewApi"})
    public static String currentTime() {
        Locale locale = Locale.forLanguageTag("bn-BD");
        return new SimpleDateFormat("HH:mm", locale).format(new Date());
    }
}