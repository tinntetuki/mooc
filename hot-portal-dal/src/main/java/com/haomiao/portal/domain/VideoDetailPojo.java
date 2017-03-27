package com.haomiao.portal.domain;



import com.haomiao.portal.vo.ShareDataVo;
import com.haomiao.portal.vo.SiteVo;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by haomiao on 2016/7/20.
 */
public class VideoDetailPojo {
    @Id
    private String id;
    private int cat;
    private String title;
    private String cover;
    private String word;
    private String updateStatus;
    private ShareDataVo shareData;
    private List<String> qvodPlaylink;
    private List<SiteVo> sites;
    private String score;
    private String doubanUrl;
    private List<String> doubanComment = new ArrayList<>();
    private List<String> actor = new ArrayList<>();
    private List<String> director;
    private List<String> type;
    private List<String> area;
    private String year;
    private List<String> tvStation;
    private String upinfo;
    private String total;
    private int finish;
    private String lasttitle;
    private Date addDate;

    public VideoDetailPojo() {
        this.addDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public ShareDataVo getShareData() {
        return shareData;
    }

    public void setShareData(ShareDataVo shareData) {
        this.shareData = shareData;
    }

    public List<String> getQvodPlaylink() {
        return qvodPlaylink;
    }

    public void setQvodPlaylink(List<String> qvodPlaylink) {
        this.qvodPlaylink = qvodPlaylink;
    }

    public List<SiteVo> getSites() {
        return sites;
    }

    public void setSites(List<SiteVo> sites) {
        this.sites = sites;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDoubanUrl() {
        return doubanUrl;
    }

    public void setDoubanUrl(String doubanUrl) {
        this.doubanUrl = doubanUrl;
    }

    public List<String> getDoubanComment() {
        return doubanComment;
    }

    public void setDoubanComment(List<String> doubanComment) {
        this.doubanComment = doubanComment;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getTvStation() {
        return tvStation;
    }

    public void setTvStation(List<String> tvStation) {
        this.tvStation = tvStation;
    }

    public String getUpinfo() {
        return upinfo;
    }

    public void setUpinfo(String upinfo) {
        this.upinfo = upinfo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getLasttitle() {
        return lasttitle;
    }

    public void setLasttitle(String lasttitle) {
        this.lasttitle = lasttitle;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "VideoDetailPojo{" +
                "id='" + id + '\'' +
                ", cat=" + cat +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", word='" + word + '\'' +
                ", updateStatus='" + updateStatus + '\'' +
                ", shareData=" + shareData +
                ", qvodPlaylink=" + qvodPlaylink +
                ", sites=" + sites +
                ", score='" + score + '\'' +
                ", doubanUrl='" + doubanUrl + '\'' +
                ", doubanComment=" + doubanComment +
                ", actor=" + actor +
                ", director=" + director +
                ", type=" + type +
                ", area=" + area +
                ", year='" + year + '\'' +
                ", tvStation=" + tvStation +
                ", upinfo='" + upinfo + '\'' +
                ", total='" + total + '\'' +
                ", finish=" + finish +
                ", lasttitle='" + lasttitle + '\'' +
                ", addDate=" + addDate +
                '}';
    }
}
