package com.haomiao.portal.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/8/26.
 */
public class SchoolResources implements Serializable{
    @Id
    private String id;

    private String type;

    private String url;

    private String imgSrc;

    private String title;

    private String text;

    private Date date;

    public SchoolResources() {
        super();
        this.id = UUID.randomUUID().toString();
    }

    public SchoolResources(String id, String type, String url, String imgSrc, String title, String text, Date date) {
        super();
        this.id = id;
        this.type = type;
        this.url = url;
        this.imgSrc = imgSrc;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public SchoolResources(String type, String url, String imgSrc, String title, String text, Date date) {
        super();
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.url = url;
        this.imgSrc = imgSrc;
        this.title = title;
        this.text = text;
        this.date = date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SchoolResources{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
