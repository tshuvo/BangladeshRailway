package com.cns.bangladeshrailway;

public class TrainItem {

    private int id;
    private String title;
    private String bodyOne;
    private String bodyTwo;
    private String bodyThree;
    private String bodyFour;
    private String bodyFive;
    private String bodyOneLbl;
    private String bodyTwoLbl;
    private String bodyThreeLbl;
    private String bodyFourLbl;
    private String bodyFiveLbl;
    private int colorCode;

    public TrainItem(int id, String title, String bodyOne, String bodyTwo, String bodyThree, String bodyFour, String bodyFive, String bodyOneLbl,
                     String bodyTwoLbl, String bodyThreeLbl, String bodyFourLbl, String bodyFiveLbl, int colorCode) {
        this.id = id;
        this.title = title;
        this.bodyOne = bodyOne;
        this.bodyTwo = bodyTwo;
        this.bodyThree = bodyThree;
        this.bodyFour = bodyFour;
        this.bodyFive = bodyFive;
        this.bodyOneLbl = bodyOneLbl;
        this.bodyTwoLbl = bodyTwoLbl;
        this.bodyThreeLbl = bodyThreeLbl;
        this.bodyFourLbl = bodyFourLbl;
        this.bodyFiveLbl = bodyFiveLbl;
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyOne() {
        return bodyOne;
    }

    public String getBodyTwo() {
        return bodyTwo;
    }

    public String getBodyThree() {
        return bodyThree;
    }

    public String getBodyFour() {
        return bodyFour;
    }

    public String getBodyFive() {
        return bodyFive;
    }

    public String getBodyOneLbl() {
        return bodyOneLbl;
    }

    public String getBodyTwoLbl() {
        return bodyTwoLbl;
    }

    public String getBodyThreeLbl() {
        return bodyThreeLbl;
    }

    public String getBodyFourLbl() {
        return bodyFourLbl;
    }

    public String getBodyFiveLbl() {
        return bodyFiveLbl;
    }

    public int getColorCode() {
        return colorCode;
    }
}