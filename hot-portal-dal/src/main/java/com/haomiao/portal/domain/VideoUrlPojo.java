package com.haomiao.portal.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频实际播放地址
 * Created by haomiao on 2016/7/26.
 */
public class VideoUrlPojo {
    @Id
    private String id;
    private String category;
    //key:siteName渠道名称（imgo,pptv,pps,letv等等）,value:url集合
    private Map<String,Map<Integer,String>> allUrlMap=new HashMap<>();
    private Date addDate;
    private Date updateDate;

    public VideoUrlPojo() {
        this.addDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Map<Integer, String>> getAllUrlMap() {
        return allUrlMap;
    }

    public void setAllUrlMap(Map<String, Map<Integer, String>> allUrlMap) {
        this.allUrlMap = allUrlMap;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "VideoUrlPojo{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", allUrlMap=" + allUrlMap +
                ", addDate=" + addDate +
                ", updateDate=" + updateDate +
                '}';
    }
}