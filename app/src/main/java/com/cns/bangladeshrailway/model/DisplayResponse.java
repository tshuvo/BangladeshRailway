package com.cns.bangladeshrailway.model;

import com.google.gson.annotations.SerializedName;

public class DisplayResponse extends ApiStatus {

    @SerializedName("refresh_time")
    private int refreshTime;

    @SerializedName("data")
    private DisplayInformation displayInformation;

    public int getRefreshTime() {
        return refreshTime;
    }

    public DisplayInformation getDisplayInformation() {
        return displayInformation;
    }

    public class DisplayInformation {

        @SerializedName("rail_info_id")
        private String railInfoId;

        @SerializedName("status_date")
        private String statusDate;

        @SerializedName("entry_by")
        private String entryBy;

        @SerializedName("entry_date")
        private String entryDate;

        @SerializedName("published_yn")
        private String publishedYn;

        @SerializedName("published_date")
        private String publishedDate;

        @SerializedName("total_station")
        private String totalStation;

        @SerializedName("total_train")
        private String totalTrain;

        @SerializedName("total_intercity")
        private String totalIntercity;

        @SerializedName("total_goodstrain")
        private String totalGoodsTrain;

        @SerializedName("total_otherstrain")
        private String totalOthersTrain;

        @SerializedName("total_loading_wagon")
        private String totalLoadingWagon;

        @SerializedName("total_loading_fertilizer")
        private String totalLoadingFertilizer;

        @SerializedName("total_loading_cereal")
        private String totalLoadingCereal;

        @SerializedName("total_loading_others")
        private String totalLoadingOthers;

        @SerializedName("total_loading_container")
        private String totalLoadingContainer;

        @SerializedName("total_loading_oilwagon")
        private String totalLoadingOilWagon;

        @SerializedName("total_punctual_intercity")
        private String totalPunctualIntercity;

        @SerializedName("total_punctual_mailtrain")
        private String totalPunctualMailTrain;

        @SerializedName("total_punctual_local")
        private String totalPunctualLocal;

        @SerializedName("engine_failure")
        private String engineFailure;

        @SerializedName("engine_delay")
        private String engineDelay;

        @SerializedName("engine_train_name1")
        private String engineTrainNameOne;

        @SerializedName("engine_train_name2")
        private String engineTrainNameTwo;

        @SerializedName("signal_station_name1")
        private String signalStationNameOne;

        @SerializedName("signal_station_name2")
        private String signalStationNameTwo;

        @SerializedName("signal_failure")
        private String signalFailure;

        @SerializedName("speed_restriction")
        private String speedRestriction;

        @SerializedName("accident")
        private String accident;

        @SerializedName("accident_mainline")
        private String accidentMainline;

        @SerializedName("accident_yard_lup")
        private String accidentYardLup;

        @SerializedName("signal_delay")
        private String signalDelay;

        @SerializedName("accident_delay")
        private String accidentDelay;

        @SerializedName("total_locomotive")
        private String totalLocomotive;

        @SerializedName("avail_locomotive")
        private String availLocomotive;

        @SerializedName("total_coach")
        private String totalCoach;

        @SerializedName("total_avail_coach")
        private String totalAvailCoach;

        @SerializedName("interchange_loading")
        private String interchangeLoading;

        @SerializedName("interchange_received")
        private String interchangeReceived;

        @SerializedName("interchange_sent")
        private String interchangeSent;

        @SerializedName("wagon_received")
        private String wagonReceived;

        @SerializedName("wagon_sent")
        private String wagonSent;

        @SerializedName("container_train")
        private String containerTrain;

        @SerializedName("goods_train")
        private String goodsTrain;

        @SerializedName("total_passenger")
        private String totalPassenger;

        @SerializedName("total_passenger_income")
        private String totalPassengerIncome;

        @SerializedName("goods_carried")
        private String goodsCarried;

        @SerializedName("remarks")
        private String remarks;

        @SerializedName("remarks_heading")
        private String remarkColor;

        @SerializedName("total_locomotive_mtge")
        private String totalLocomotiveMtge;

        @SerializedName("total_locomotive_brdge")
        private String totalLocomotiveBrdge;

        @SerializedName("total_coach_mtge")
        private String totalCoachMtge;

        @SerializedName("total_coach_brdge")
        private String totalCoachBrdge;

        @SerializedName("total_avail_coach_mtge")
        private String totalAvailCoachMtge;

        @SerializedName("total_avail_coach_brdge")
        private String totalAvailCoachBrdge;

        @SerializedName("avail_locomotive_mtge")
        private String availLocomotiveMtge;

        @SerializedName("avail_locomotive_brdge")
        private String availLocomotiveBrdge;

        @SerializedName("total_avail_coach_mtge_per")
        private String totalAvailCoachMtgePer;

        @SerializedName("total_avail_coach_brdge_per")
        private String totalAvailCoachBrdgePer;

        @SerializedName("avail_locomotive_mtge_per")
        private String availLocomotiveMtgePer;

        @SerializedName("avail_locomotive_brdge_per")
        private String availLocomotiveBrdgePer;

        @SerializedName("accident_delay_unit")
        private String accidentDelayUnit;

        @SerializedName("running_station")
        private String runningStation;

        @SerializedName("temp_closed_station")
        private String tempClosedStation;


        @SerializedName("speed_restriction_east")
        private String speedRestrictionEast;

        @SerializedName("speed_restriction_west")
        private String speedRestrictionWest;

        @SerializedName("lvlcg_authorized")
        private String lvlCGAuthorized;

        @SerializedName("engine_delay_unit")
        private String engineDelayUnit;

        public String getEngineDelayUnit() {
            return engineDelayUnit;
        }

        public String getSignalDelayUnit() {
            return signalDelayUnit;
        }

        @SerializedName("signal_delay_unit")
        private String signalDelayUnit;

        public String getAccidentDelayUnit() {
            return accidentDelayUnit;
        }

        public String getRunningStation() {
            return runningStation;
        }

        public String getTempClosedStation() {
            return tempClosedStation;
        }

        public String getSpeedRestrictionEast() {
            return speedRestrictionEast;
        }

        public String getSpeedRestrictionWest() {
            return speedRestrictionWest;
        }

        public String getLvlCGAuthorized() {
            return lvlCGAuthorized;
        }

        public String getLvlCGUnauthorized() {
            return lvlCGUnauthorized;
        }

        @SerializedName("lvlcg_unauthorized")
        private String lvlCGUnauthorized;

        public String getRailInfoId() {
            return railInfoId;
        }

        public String getStatusDate() {
            return statusDate;
        }

        public String getEntryBy() {
            return entryBy;
        }

        public String getEntryDate() {
            return entryDate;
        }

        public String getPublishedYn() {
            return publishedYn;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public String getTotalStation() {
            return totalStation;
        }

        public String getTotalTrain() {
            return totalTrain;
        }

        public String getTotalIntercity() {
            return totalIntercity;
        }

        public String getTotalGoodsTrain() {
            return totalGoodsTrain;
        }

        public String getTotalOthersTrain() {
            return totalOthersTrain;
        }

        public String getTotalLoadingWagon() {
            return totalLoadingWagon;
        }

        public String getTotalLoadingContainer() {
            return totalLoadingContainer;
        }

        public String getTotalLoadingOilWagon() {
            return totalLoadingOilWagon;
        }

        public String getTotalPunctualIntercity() {
            return totalPunctualIntercity;
        }

        public String getTotalPunctualMailTrain() {
            return totalPunctualMailTrain;
        }

        public String getTotalPunctualLocal() {
            return totalPunctualLocal;
        }

        public String getEngineFailure() {
            return engineFailure;
        }

        public String getSignalFailure() {
            return signalFailure;
        }

        public String getSpeedRestriction() {
            return speedRestriction;
        }

        public String getAccident() {
            return accident;
        }

        public String getTotalLocomotive() {
            return totalLocomotive;
        }

        public String getAvailLocomotive() {
            return availLocomotive;
        }

        public String getTotalCoach() {
            return totalCoach;
        }

        public String getTotalAvailCoach() {
            return totalAvailCoach;
        }

        public String getInterchangeLoading() {
            return interchangeLoading;
        }

        public String getInterchangeReceived() {
            return interchangeReceived;
        }

        public String getInterchangeSent() {
            return interchangeSent;
        }

        public String getContainerTrain() {
            return containerTrain;
        }

        public String getGoodsTrain() {
            return goodsTrain;
        }

        public String getTotalPassenger() {
            return totalPassenger;
        }

        public String getTotalPassengerIncome() {
            return totalPassengerIncome;
        }

        public String getGoodsCarried() {
            return goodsCarried;
        }

        public String getRemarks() {
            return remarks;
        }

        public String getRemarkColor() {
            return remarkColor;
        }

        public String getTotalLocomotiveMtge() {
            return totalLocomotiveMtge;
        }

        public String getTotalLocomotiveBrdge() {
            return totalLocomotiveBrdge;
        }

        public String getTotalCoachMtge() {
            return totalCoachMtge;
        }

        public String getTotalCoachBrdge() {
            return totalCoachBrdge;
        }

        public String getTotalAvailCoachMtge() {
            return totalAvailCoachMtge;
        }

        public String getTotalAvailCoachBrdge() {
            return totalAvailCoachBrdge;
        }

        public String getAvailLocomotiveMtge() {
            return availLocomotiveMtge;
        }

        public String getAvailLocomotiveBrdge() {
            return availLocomotiveBrdge;
        }

        public String getTotalAvailCoachMtgePer() {
            return totalAvailCoachMtgePer;
        }

        public String getTotalAvailCoachBrdgePer() {
            return totalAvailCoachBrdgePer;
        }

        public String getAvailLocomotiveMtgePer() {
            return availLocomotiveMtgePer;
        }

        public String getAvailLocomotiveBrdgePer() {
            return availLocomotiveBrdgePer;
        }

        public String getTotalLoadingFertilizer() {
            return totalLoadingFertilizer;
        }

        public String getTotalLoadingCereal() {
            return totalLoadingCereal;
        }

        public String getTotalLoadingOthers() {
            return totalLoadingOthers;
        }

        public String getEngineDelay() {
            return engineDelay;
        }

        public String getEngineTrainNameOne() {
            return engineTrainNameOne;
        }

        public String getEngineTrainNameTwo() {
            return engineTrainNameTwo;
        }

        public String getSignalStationNameOne() {
            return signalStationNameOne;
        }

        public String getSignalStationNameTwo() {
            return signalStationNameTwo;
        }

        public String getAccidentMainline() {
            return accidentMainline;
        }

        public String getAccidentYardLup() {
            return accidentYardLup;
        }

        public String getSignalDelay() {
            return signalDelay;
        }

        public String getAccidentDelay() {
            return accidentDelay;
        }

        public String getWagonReceived() {
            return wagonReceived;
        }

        public String getWagonSent() {
            return wagonSent;
        }
    }
}