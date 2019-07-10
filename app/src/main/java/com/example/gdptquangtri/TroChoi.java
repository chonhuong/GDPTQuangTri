package com.example.gdptquangtri;

public class TroChoi {
    private String Ten, NoiDung, pubdate;

    public TroChoi() {

    }

    public TroChoi(String pubdate, String noiDung, String ten) {
        Ten = ten;
        NoiDung = noiDung;
        this.pubdate = pubdate;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
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
