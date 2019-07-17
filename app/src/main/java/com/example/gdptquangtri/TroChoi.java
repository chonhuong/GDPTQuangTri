package com.example.gdptquangtri;

public class TroChoi {
    private String Ten, NoiDung, PubDate, KyTen;

    public TroChoi() {

    }

    public TroChoi(String pubdate, String noiDung, String ten, String kyten) {
        Ten = ten;
        NoiDung = noiDung;
        PubDate = pubdate;
        KyTen = kyten;
    }

    public String getKyTen() {
        return KyTen;
    }

    public void setKyTen(String kyTen) {
        KyTen = kyTen;
    }

    public String getPubDate() {
        return PubDate;
    }

    public void setPubDate(String pubDate) {
        PubDate = pubDate;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }
}
