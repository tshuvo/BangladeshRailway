package com.cns.bangladeshrailway;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

public class Apps extends Application {

    private static Apps mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized Apps getMInstance() {
        return mInstance;
    }

    public static synchronized Apps getAppsContext() {
        if (mInstance == null) {
            mInstance = new Apps();
        }
        return mInstance;
    }

    /* int LENGTH_INDEFINITE = -2;
   int LENGTH_SHORT = -1;
   int LENGTH_LONG = 0; */
    public static void snackBarMsg(View view, String text, int duration, boolean actionButton) {
        final Snackbar snackbar = Snackbar.make(view, text, duration);
        if (actionButton) {
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.setAction(getAppsContext().getString(R.string.snack_bar_bn), v -> snackbar.dismiss());
        }
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getAppsContext().getResources().getColor(R.color.color5));
        TextView snackBarViewActionText = (TextView) snackBarView.findViewById(com.google.android.material.R.id.snackbar_action);
        snackBarViewActionText.setAllCaps(false);
        TextView snackBarViewText = (TextView) snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        snackBarViewText.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void redirect(@NonNull Activity activityFrom, @NonNull Class<? extends Activity> activityToOpen, boolean isFinish) {
        Intent intent = new Intent(activityFrom, activityToOpen);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activityFrom.startActivity(intent);
        activityFrom.overridePendingTransition(0, 0);
        if (isFinish) {
            activityFrom.finish();
        }
    }

    public static void redirect(@NonNull Activity activityFrom, @NonNull Class<? extends Activity> activityToOpen, boolean isFinish, @NonNull Bundle bundle) {
        Intent intent = new Intent(activityFrom, activityToOpen);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activityFrom.startActivity(intent);
        activityFrom.overridePendingTransition(0, 0);
        if (isFinish) {
            activityFrom.finish();
        }
    }
}