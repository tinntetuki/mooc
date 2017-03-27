package com.haomiao.portal.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by haomiao on 2016/7/20.
 */
public class SiteVo {
    private String name;
    private String site;
    private String upinfo;
    private String defaultPlaylink;
    private String xstm;
    private Date pubdate;
    private List<String> lost;
    private int isredirect;
    private int adsupport;
    private int adDuration;
    private int picDuration;
    private int isbesttvAd;
    private String adCorp;
    private int disableddownload;
    private List<QualityVo> quality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUpinfo() {
        return upinfo;
    }

    public void setUpinfo(String upinfo) {
        this.upinfo = upinfo;
    }

    public String getDefaultPlaylink() {
        return defaultPlaylink;
    }

    public void setDefaultPlaylink(String defaultPlaylink) {
        this.defaultPlaylink = defaultPlaylink;
    }

    public String getXstm() {
        return xstm;
    }

    public void setXstm(String xstm) {
        this.xstm = xstm;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public List<String> getLost() {
        return lost;
    }

    public void setLost(List<String> lost) {
        this.lost = lost;
    }

    public int getIsredirect() {
        return isredirect;
    }

    public void setIsredirect(int isredirect) {
        this.isredirect = isredirect;
    }

    public int getAdsupport() {
        return adsupport;
    }

    public void setAdsupport(int adsupport) {
        this.adsupport = adsupport;
    }

    public int getAdDuration() {
        return adDuration;
    }

    public void setAdDuration(int adDuration) {
        this.adDuration = adDuration;
    }

    public int getPicDuration() {
        return picDuration;
    }

    public void setPicDuration(int picDuration) {
        this.picDuration = picDuration;
    }

    public int getIsbesttvAd() {
        return isbesttvAd;
    }

    public void setIsbesttvAd(int isbesttvAd) {
        this.isbesttvAd = isbesttvAd;
    }

    public String getAdCorp() {
        return adCorp;
    }

    public void setAdCorp(String adCorp) {
        this.adCorp = adCorp;
    }

    public int getDisableddownload() {
        return disableddownload;
    }

    public void setDisableddownload(int disableddownload) {
        this.disableddownload = disableddownload;
    }

    public List<QualityVo> getQuality() {
        return quality;
    }

    public void setQuality(List<QualityVo> quality) {
        this.quality = quality;
    }
}
