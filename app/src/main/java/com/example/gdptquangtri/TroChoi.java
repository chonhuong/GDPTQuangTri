package com.example.gdptquangtri;

public class TroChoi {
    private String Ten, NoiDung, PubDate;

    public TroChoi() {

    }

    public TroChoi(String pubdate, String noiDung, String ten) {
        Ten = ten;
        NoiDung = noiDung;
        PubDate = pubdate;
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
