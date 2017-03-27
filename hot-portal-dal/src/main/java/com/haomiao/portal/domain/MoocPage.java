package com.haomiao.portal.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

/**
 * Created by haomiao on 2016/7/19.
 */
public class MoocPage {
    @Id
    private String id;
    private String url;
    private String content;
    private  Date date;


    public MoocPage() {
        super();
        this.id = UUID.randomUUID().toString();
    }

    public MoocPage(String url, String content) {
        super();
        this.id = UUID.randomUUID().toString();
        this.url = url;
        this.content = content;
    }

    public MoocPage(String url, String content, Date date) {
        super();
        this.id = UUID.randomUUID().toString();
        this.url = url;
        this.content = content;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MoocPage{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
