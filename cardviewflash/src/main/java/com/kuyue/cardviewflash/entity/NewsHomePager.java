package com.kuyue.cardviewflash.entity;

/**
 * Created by sen young on 2017/2/16 17:12.
 * 邮箱:595327086@qq.com.
 */

public class NewsHomePager {

    private String adUrl;
//    private Column column;
    private int id;
    private String img;
    private String imgUrl;
    private String materielName;
    private String name;
    private String smallImgUrl;
    private String summary;
    private String title;
    private String type;
    private String url;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSmallImgUrl() {
        return this.smallImgUrl;
    }

    public void setSmallImgUrl(String str) {
        this.smallImgUrl = str;
    }

    public String getMaterielName() {
        return this.materielName;
    }

    public void setMaterielName(String str) {
        this.materielName = str;
    }

    public String getAdUrl() {
        return this.adUrl;
    }

    public void setAdUrl(String str) {
        this.adUrl = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

//    public Column getColumn() {
//        return this.column;
//    }
//
//    public void setColumn(Column column) {
//        this.column = column;
//    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }


}
