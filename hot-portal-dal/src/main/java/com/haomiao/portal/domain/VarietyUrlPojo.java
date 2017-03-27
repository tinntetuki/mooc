package com.haomiao.portal.domain;

import com.haomiao.portal.vo.VarietyUrlVo;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haomiao on 2016/7/28.
 */
public class VarietyUrlPojo {
    @Id
    private String id;
    private String category;
    private Map<String,List<VarietyUrlVo>> allUrlMap = new HashMap<>();
    private Date addDate;
    private Date updateDate;

    public VarietyUrlPojo() {
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

    public Map<String, List<VarietyUrlVo>> getAllUrlMap() {
        return allUrlMap;
    }

    public void setAllUrlMap(Map<String, List<VarietyUrlVo>> allUrlMap) {
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
        return "VarietyUrlPojo{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", allUrlMap=" + allUrlMap +
                ", addDate=" + addDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
