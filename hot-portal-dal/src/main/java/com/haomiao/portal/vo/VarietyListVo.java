package com.haomiao.portal.vo;

import java.util.List;

/**
 * Created by haomiao on 2016/7/28.
 */
public class VarietyListVo {
    private String cat;
    private String title;
    private String from;
    private List<VarietyUrlVo> allepisode;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<VarietyUrlVo> getAllepisode() {
        return allepisode;
    }

    public void setAllepisode(List<VarietyUrlVo> allepisode) {
        this.allepisode = allepisode;
    }
}
