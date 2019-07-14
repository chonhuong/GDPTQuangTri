package com.example.gdptquangtri;

public class NewsPhatSu {
    private String title, link, src, pubDate;
    private int id;
    private byte[] hinhanh;

    public NewsPhatSu(String title, String link, String pubDate, byte[] hinhanh) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.hinhanh = hinhanh;
    }

    public NewsPhatSu(String title, String link, String src, String pubDate) {
        this.title = title;
        this.link = link;

        this.src = src;
        this.pubDate = pubDate;
    }


    public NewsPhatSu(int id, String title, String link, String pubDate, byte[] hinhanh) {

        this.id = id;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;

        this.hinhanh = hinhanh;
    }

    public NewsPhatSu() {
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
