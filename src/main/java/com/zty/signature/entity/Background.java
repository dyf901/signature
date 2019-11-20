package com.zty.signature.entity;

public class Background {
    private int id;
    private String imgurl;
    private String img2url;
    private String createtime;

    public Background(){
        super();
    }

    public Background(int id, String imgurl, String img2url, String createtime) {
        this.id = id;
        this.imgurl = imgurl;
        this.img2url = img2url;
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getImg2url() {
        return img2url;
    }

    public void setImg2url(String img2url) {
        this.img2url = img2url;
    }

    @Override
    public String toString() {
        return "Background{" +
                "id=" + id +
                ", imgurl='" + imgurl + '\'' +
                ", img2url='" + img2url + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
