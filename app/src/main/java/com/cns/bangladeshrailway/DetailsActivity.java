package com.cns.bangladeshrailway;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;

import com.cns.bangladeshrailway.listener.AppsExistListener;
import com.cns.bangladeshrailway.model.DisplayResponse;
import com.cns.bangladeshrailway.networks.HttpRequest;
import com.cns.bangladeshrailway.networks.HttpResponse;
import com.cns.bangladeshrailway.utils.AppsConstant;
import com.cns.bangladeshrailway.utils.Common;
import com.cns.bangladeshrailway.utils.InternetChangeReceiver;
import com.cns.bangladeshrailway.utils.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cns.bangladeshrailway.networks.ApiUrl.URL_RAIL_INFO;

public class DetailsActivity extends FragmentActivity implements AppsConstant, AppsExistListener {

    private ProgressDialog pDialog;
    private DetailsActivity context;
    private static final int apiIntervalTime = 5;
    private TrainItemViewAdapter trainItemViewAdapter;
    private AppCompatTextView acTvDate, acTvTime, acTvRemarks;
    private LinearLayout llSignalHolder, llDateTimeHolder, llMarqueeHolder;
    private AppCompatImageView acIvSignalSever;
    public static AppCompatImageView acIvSignalInternet;
    private boolean isFirstTime = true;
    private InternetChangeReceiver internetChangeReceiver;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dtails_view);

        onInit();

        /* Response.Listener listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(DetailsActivity.this, response, Toast.LENGTH_SHORT).show();
                    parseResponse(response);
                } catch (JSONException e) {
                    Toast.makeText(DetailsActivity.this, "error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailsActivity.this, "error " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest request = new StringRequest(Request.Method.POST, API_ENDPOINT, listener, errorListener);
        Volley.newRequestQueue(this).add(request); */
    }

    private void onInit() {

        this.context = DetailsActivity.this;

        handler = new Handler(Looper.getMainLooper());
        onLineOffLineIndicator();
        pDialog = new ProgressDialog(context);
        acTvRemarks = (AppCompatTextView) findViewById(R.id.ac_tv_remarks);
        acTvDate = (AppCompatTextView) findViewById(R.id.ac_tv_date);
        acTvTime = (AppCompatTextView) findViewById(R.id.ac_tv_time);
        llSignalHolder = (LinearLayout) findViewById(R.id.ll_signal_holder);
        llDateTimeHolder = (LinearLayout) findViewById(R.id.ll_date_time_holder);
        llMarqueeHolder = (LinearLayout) findViewById(R.id.ll_marquee_holder);
        acIvSignalInternet = (AppCompatImageView) findViewById(R.id.ac_iv_signal_internet);
        acIvSignalSever = (AppCompatImageView) findViewById(R.id.ac_iv_signal_sever);
        RecyclerView rvTrainItem = (RecyclerView) findViewById(R.id.train_item_recycler);

        trainItemViewAdapter = new TrainItemViewAdapter(context, item -> {
            // TrainItem trainItem = (TrainItem) item;
            // Toast.makeText(context, "ID " + trainItem.getId(), Toast.LENGTH_SHORT).show();
        });

        rvTrainItem.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        rvTrainItem.setNestedScrollingEnabled(false);
        rvTrainItem.setAdapter(trainItemViewAdapter);

        SharedPreferencesManager manager = SharedPreferencesManager.getInstance(context);
        Map<String, String> params = new HashMap<>();
        params.put("divice_pin", manager.getSharePrefString(AUTH_CODE, ""));
        params.put("divice_mac", manager.getSharePrefString(DEVICE_ID, ""));

        if (Common.ifInternetIsOn(context, DetailsActivity.this)) {
            Common.startWaitingDialog(pDialog, getString(R.string.txt_waiting_bn), false);
            getDisplayInformation(params);
        }
    }

    @SuppressLint("SetTextI18n")
    private void getDisplayInformation(Map<String, String> params) {
        List<TrainItem> trainItemList = new ArrayList<>();
        try {
            HttpRequest.POST(context, URL_RAIL_INFO, params, new HttpResponse() {
                @Override
                public void onSuccess(String response) {
                    if (!response.endsWith("}}")) {
                        response = response + "}";
                    }
                    DisplayResponse displayResponse = new Gson().fromJson(response, DisplayResponse.class);
                    if (displayResponse.getResponseCode() == 1) {

                        isFirstTime = false;
                        acIvSignalSever.setImageResource(R.drawable.ic_server_connected);
                        DisplayResponse.DisplayInformation displayInformation = displayResponse.getDisplayInformation();

                        //========== ITEM ONE START ==========
                        TrainItem trainItem1 = new TrainItem(
                                1,
                                STATION + COLON_DASH,
                                COLON + enToBn(displayInformation.getTotalStation()) + TI,
                                COLON + enToBn(displayInformation.getRunningStation()) + TI,
                                COLON + enToBn(displayInformation.getTempClosedStation()) + TI,
                                "",
                                "",
                                MOT,
                                CHALU,
                                SAMOYIK_BONDHO,
                                "",
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem1);
                        //========== ITEM ONE END ==========

                        //========== ITEM TWO START ==========
                        TrainItem trainItem2 = new TrainItem(
                                2,
                                TRAIN + COLON_DASH,
                                COLON + enToBn(displayInformation.getTotalTrain()) + TI,
                                COLON + enToBn(displayInformation.getTotalIntercity()) + TI,
                                COLON + enToBn(displayInformation.getTotalOthersTrain()) + TI,
                                COLON + enToBn(displayInformation.getTotalGoodsTrain()) + TI,
                                "",
                                MOT,
                                ANTA_NAGAR,
                                ONNO_ANNO,
                                MALBAHI,
                                "",
                                getResources().getColor(R.color.blue_light)
                        );
                        trainItemList.add(trainItem2);
                        //========== ITEM TWO END ==========

                        //========== ITEM THREE START ==========
                        TrainItem trainItem3 = new TrainItem(
                                3,
                                COACH_SONKHA + COLON_DASH,
                                COLON + enToBn(displayInformation.getTotalCoach()) + TI,
                                COLON + enToBn(displayInformation.getTotalCoachMtge()) + TI,
                                COLON + enToBn(displayInformation.getTotalCoachBrdge()) + TI,
                                "",
                                "",
                                MOT,
                                METER_GAUGE,
                                BROAD_GAUGE,
                                "",
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem3);
                        //========== ITEM THREE END ==========

                        //========== ITEM FOUR START ==========
                        TrainItem trainItem4 = new TrainItem(
                                4,
                                LOCO_SONKHA + COLON_DASH,
                                COLON + enToBn(displayInformation.getTotalLocomotive()) + TI,
                                COLON + enToBn(displayInformation.getTotalLocomotiveMtge()) + TI,
                                COLON + enToBn(displayInformation.getTotalLocomotiveBrdge()) + TI,
                                "",
                                "",
                                MOT,
                                METER_GAUGE,
                                BROAD_GAUGE,
                                "",
                                "",
                                getResources().getColor(R.color.blue_light)
                        );
                        trainItemList.add(trainItem4);
                        //========== ITEM FOUR END ==========

                        //========== ITEM FIVE START ==========
                        TrainItem trainItem5 = new TrainItem(
                                5,
                                LEVEL_CROSSING_GATE + COLON_DASH,
                                COLON + enToBn(displayInformation.getLvlCGAuthorized()) + TI,
                                COLON + enToBn(displayInformation.getLvlCGUnauthorized()) + TI,
                                "",
                                "",
                                "",
                                ONUMODITO,
                                O_ONUMODITO,
                                "",
                                "",
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem5);
                        //========== ITEM FIVE END ==========

                        //========== ITEM SIX START ==========
                        TrainItem trainItem6 = new TrainItem(
                                6,
                                SOMOYUNUBORTITA + COLON_DASH,
                                COLON + enToBn(displayInformation.getTotalPunctualIntercity()) + PERCENTAGE,
                                COLON + enToBn(displayInformation.getTotalPunctualMailTrain()) + PERCENTAGE,
                                COLON + enToBn(displayInformation.getTotalPunctualLocal()) + PERCENTAGE,
                                "",
                                "",
                                ANTA_NAGAR,
                                MAIL,
                                LOCAL,
                                "",
                                "",
                                getResources().getColor(R.color.blue_light)
                        );
                        trainItemList.add(trainItem6);
                        //========== ITEM SIX END ==========

                        //========== ITEM SEVEN START ==========
                        TrainItem trainItem7 = new TrainItem(
                                7,
                                JATRI_PORIBOHON + COLON_DASH,
                                COLON + enToBn(displayInformation.getTotalPassenger()) + JON,
                                COLON + enToBn(displayInformation.getTotalPassengerIncome()) + TAKA,
                                "",
                                "",
                                "",
                                JATRI_SONGKHA,
                                JATRI_AY,
                                "",
                                "",
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem7);
                        //========== ITEM SEVEN END ==========

                        //========== ITEM EIGHT START ==========
                        TrainItem trainItem8 = new TrainItem(
                                8,
                                COACH_PRAPPOTA + COLON_DASH,
                                COLON + enToBn(displayInformation.getTotalAvailCoachMtge()) + TI + BRACKET_OPEN + enToBn(displayInformation.getTotalAvailCoachMtgePer()) + PERCENTAGE + BRACKET_END,
                                COLON + enToBn(displayInformation.getTotalAvailCoachBrdge()) + TI + BRACKET_OPEN + enToBn(displayInformation.getTotalAvailCoachBrdgePer()) + PERCENTAGE + BRACKET_END,
                                "",
                                "",
                                "",
                                METER_GAUGE,
                                BROAD_GAUGE,
                                "",
                                "",
                                "",
                                getResources().getColor(R.color.blue_light)
                        );
                        trainItemList.add(trainItem8);
                        //========== ITEM EIGHT END ==========

                        //========== ITEM NINE START ==========
                        TrainItem trainItem9 = new TrainItem(
                                9,
                                LOCO_PRAPPOTA + COLON_DASH,
                                COLON + enToBn(displayInformation.getAvailLocomotiveMtge()) + TI + BRACKET_OPEN + enToBn(displayInformation.getAvailLocomotiveMtgePer()) + PERCENTAGE + BRACKET_END,
                                COLON + enToBn(displayInformation.getAvailLocomotiveBrdge()) + TI + BRACKET_OPEN + enToBn(displayInformation.getAvailLocomotiveBrdgePer()) + PERCENTAGE + BRACKET_END,
                                "",
                                "",
                                "",
                                METER_GAUGE,
                                BROAD_GAUGE,
                                "",
                                "",
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem9);
                        //========== ITEM NINE END ==========

                        //========== ITEM TEN START ==========
                        TrainItem trainItem10 = new TrainItem(
                                10,
                                WAGON_LOADING + COLON_DASH,
                                // enToBn(displayInformation.getTotalLoadingWagon()) + TI,
                                COLON + enToBn(displayInformation.getTotalLoadingContainer()) + TI_USE,
                                COLON + enToBn(displayInformation.getTotalLoadingOilWagon()) + METRIC_TONNE,
                                COLON + enToBn(displayInformation.getTotalLoadingFertilizer()) + METRIC_TONNE,
                                COLON + enToBn(displayInformation.getTotalLoadingCereal()) + METRIC_TONNE,
                                COLON + enToBn(displayInformation.getTotalLoadingOthers()) + METRIC_TONNE,
                                CONTAINER,
                                JALANI,
                                SAR,
                                KHADDO_SOSSO,
                                ONNO_ANNO,
                                getResources().getColor(R.color.blue_light)
                        );
                        trainItemList.add(trainItem10);
                        //========== ITEM TEN END ==========

                        //========== ITEM ELEVEN START ==========
                        TrainItem trainItem11 = new TrainItem(
                                11,
                                ENGINE_FAILURE + COLON_DASH,
                                COLON + enToBn(displayInformation.getEngineFailure()) + TI,
                                COLON + enToBn(displayInformation.getEngineDelay()) + " " + displayInformation.getEngineDelayUnit(),
                                COLON + trainCodeEnToBn(displayInformation.getEngineTrainNameOne()),
                                trainCodeEnToBn(displayInformation.getEngineTrainNameTwo()),
                                "",
                                SONGKHA,
                                BILOMBOTA,
                                TRAIN_NAME,
                                "",
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem11);
                        //========== ITEM ELEVEN END ==========

                        //========== ITEM TWELVE START ==========
                        TrainItem trainItem12 = new TrainItem(
                                12,
                                SIGNAL_FAILURE + COLON_DASH,
                                COLON + enToBn(displayInformation.getSignalFailure()) + TI,
                                COLON + enToBn(displayInformation.getSignalDelay()) + " " + displayInformation.getSignalDelayUnit(),
                                COLON + (displayInformation.getSignalStationNameOne() == null ? "" : displayInformation.getSignalStationNameOne()),
                                (displayInformation.getSignalStationNameTwo() == null ? "" : displayInformation.getSignalStationNameTwo()),
                                "",
                                SONGKHA,
                                BILOMBOTA,
                                STATIONARNAME,
                                "",
                                "",
                                getResources().getColor(R.color.blue_light)
                        );
                        trainItemList.add(trainItem12);
                        //========== ITEM TWELVE END ==========

                        //========== ITEM THIRTEEN START ==========
                        TrainItem trainItem13 = new TrainItem(
                                13,
                                SPEED_RESTRICTION + COLON_DASH,
                                COLON + enToBn(displayInformation.getSpeedRestrictionEast()) + TI,
                                COLON + enToBn(displayInformation.getSpeedRestrictionWest()) + TI, // @todo have to complete
                                COLON + enToBn(displayInformation.getSpeedRestriction()) + TI,// @todo have to complete
                                "",
                                "",
                                PURBANCHOL,
                                POSCHIMANCHOL,
                                MOT,
                                "",
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem13);
                        //========== ITEM THIRTEEN END ==========

                        //========== ITEM FOURTEEN START ==========
                        TrainItem trainItem14 = new TrainItem(
                                14,
                                DURGHOTONA + COLON_DASH,
                                COLON + enToBn(displayInformation.getAccident()) + TI,
                                COLON + enToBn(displayInformation.getAccidentMainline()) + TI,
                                COLON + enToBn(displayInformation.getAccidentYardLup()) + TI,
                                COLON + enToBn(displayInformation.getAccidentDelay()) + " " + displayInformation.getAccidentDelayUnit(),
//                                COLON + enToBn(displayInformation.getAccidentDelay()) ,
                                "",
                                SONGKHA,
                                MAINLINE,
                                YEARDLOOP,
                                BILOMBOTA,
                                "",
                                getResources().getColor(R.color.blue_light)
                        );
                        trainItemList.add(trainItem14);
                        //========== ITEM FOURTEEN END ==========

                        //========== ITEM FIFTEEN START ==========
                        TrainItem trainItem15 = new TrainItem(
                                15,
                                INTERCHANGE_POINT + COLON_DASH,
                                COLON + enToBn(displayInformation.getInterchangeReceived()) + REK,
                                COLON + enToBn(displayInformation.getWagonReceived()) + TI,
                                COLON + enToBn(displayInformation.getInterchangeSent()) + REK,
                                COLON + enToBn(displayInformation.getWagonSent()) + TI,
                                "",
                                TRAIN_GROHON,
                                WAGON_GROHON,
                                TRAIN_PRERON,
                                WAGON_PRERON,
                                "",
                                getResources().getColor(R.color.blue_ocean)
                        );
                        trainItemList.add(trainItem15);
                        //========== ITEM FIFTEEN END ==========

                        trainItemViewAdapter.setResult(trainItemList);
                        acTvDate.setText(Common.formatApiDate(displayInformation.getStatusDate()));
                        acTvTime.setText(Common.currentTime() + getString(R.string.txt_ghotoka));
                        acTvRemarks.setText(displayInformation.getRemarks());
                        String colorCode = displayInformation.getRemarkColor().startsWith("#") ? displayInformation.getRemarkColor() : "#" + displayInformation.getRemarkColor();

                        acTvRemarks.setTextColor(Color.parseColor(colorCode));
                        acTvRemarks.setSelected(true);

                        if (llDateTimeHolder.getVisibility() != View.VISIBLE) {
                            llDateTimeHolder.setVisibility(View.VISIBLE);
                        }
                        if (llMarqueeHolder.getVisibility() != View.VISIBLE) {
                            llMarqueeHolder.setVisibility(View.VISIBLE);
                        }

                        handler.postDelayed(() -> Common.stopWaitingDialog(pDialog), 1000);
                        getDisplayInfoTimeScheduler(params, displayResponse.getRefreshTime());
                    } else {
                        Common.stopWaitingDialog(pDialog);
                        getDisplayInfoTimeScheduler(params, apiIntervalTime);
                        Apps.snackBarMsg(acTvRemarks.getRootView(), displayResponse.getResponseMessage(), 0, true);
                    }

                    if (llSignalHolder.getVisibility() != View.VISIBLE) {
                        llSignalHolder.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onError(String error) {
                    Common.stopWaitingDialog(pDialog);
                    if (isFirstTime) {
                        Common.serverOff(context, DetailsActivity.this);
                    } else {
                        getDisplayInfoTimeScheduler(params, apiIntervalTime);
                        acIvSignalSever.setImageResource(R.drawable.ic_server_disconnected);
                        if (llSignalHolder.getVisibility() != View.VISIBLE) {
                            llSignalHolder.setVisibility(View.VISIBLE);
                        }
                    }
                    // Apps.snackBarMsg(acTvRemarks.getRootView(), getString(R.string.error_msg_bn), 0, true);
                }
            });
        } catch (Exception e) {
            Common.stopWaitingDialog(pDialog);
            if (isFirstTime) {
                Common.serverOff(context, DetailsActivity.this);
            } else {
                getDisplayInfoTimeScheduler(params, apiIntervalTime);
                acIvSignalSever.setImageResource(R.drawable.ic_server_disconnected);
                if (llSignalHolder.getVisibility() != View.VISIBLE) {
                    llSignalHolder.setVisibility(View.VISIBLE);
                }
            }
            e.printStackTrace();
        }
    }

    @Override
    public void appsExist() {
        finish();
        System.exit(0);
    }

    private String enToBn(String en) {
        return Common.digitEnglishToBengali(Common.numberFormatWithComma(en == null ? "0" : en.equals("") ? "0" : en));
    }

    private String trainCodeEnToBn(String trainCode) {
        if (trainCode == null || trainCode.isEmpty()) {
            return "";
        }
        String[] trainCodeArr = trainCode.split(",");
        int lastItem = trainCodeArr.length - 1;
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < trainCodeArr.length; i++) {
            stringBuffer.append(Common.digitEnglishToBengali(trainCodeArr[i])).append(i == lastItem ? "" : ",");
        }
        return stringBuffer.toString();
    }

    private void getDisplayInfoTimeScheduler(Map<String, String> params, int minutes) {
        handler.postDelayed(() -> {
            runOnUiThread(() -> {
                getDisplayInformation(params);
            });
        }, (minutes * (60 * 1000)));

        /* schedulerStatus = false;
        TimerTask fiveMinutesTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (Common.isNetworkAvailable(context)) {
                        getDisplayInformation(params);
                    }
                });
            }
        };
        new Timer().schedule(fiveMinutesTask, 10000, minutes * (60 * 1000)); */
    }

    public void onLineOffLineIndicator() {
        internetChangeReceiver = new InternetChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(internetChangeReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        Common.stopWaitingDialog(pDialog);
        unregisterReceiver(internetChangeReceiver);
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    /* private void parseResponse(String response) throws JSONException {
        hidepDialog();
        Log.e("response", response);
        JSONObject root = new JSONObject(response);

        JSONObject dataListObject = root.getJSONObject("data");

        TrainItem trainItem = new TrainItem(1, "মোট ট্রেন : ", dataListObject.getString("total_train") + " টি। ", "আন্তঃনগর : " + dataListObject.getString("total_intercity")
                + " টি। ", "মালবাহী: " + dataListObject.getString("total_goodstrain") + "টি। ", "অন্যান্য: " + dataListObject.getString("total_otherstrain")
                + " টি। ", R.drawable.br, R.color.header_tag);
        TrainItem trainItem2 = new TrainItem(2, "মোট ষ্টেশন : ", dataListObject.getString("total_station") + " টি। ", "", "", "", R.drawable.br, R.color.header_tag);
        TrainItem trainItem3 = new TrainItem(2, "মোট কোচসংখ্যা : ", dataListObject.getString("total_coach") + " টি। ", "প্রাপ্যতা: " + dataListObject.getString("total_avail_coach") + " টি। ", "", "", R.drawable.br, R.color.header_tag);
        TrainItem trainItem4 = new TrainItem(2, "মমালামাল বহন : ", dataListObject.getString("goods_carried") + " মে.টন", "", "", "", R.drawable.br, R.color.header_tag);
        List<TrainItem> trainItemList = new ArrayList<>();
        trainItemList.add(trainItem);
        trainItemList.add(trainItem2);
        trainItemList.add(trainItem3);
        trainItemList.add(trainItem4);

        RecyclerViewAdapter trainAdapter = new RecyclerViewAdapter(context, new ItemClick() {
            @Override
            public void onItemClicked(Object item) {

            }
        });
        itemView = findViewById(R.id.train_item_recycler);
        itemView.setLayoutManager(new GridLayoutManager(DetailsActivity.this, 4));
        itemView.setHasFixedSize(true);
        itemView.setNestedScrollingEnabled(false);
        itemView.setClipToPadding(false);
        itemView.setAdapter(trainAdapter);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    } */
}