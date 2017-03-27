package com.haomiao.portal.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by haomiao on 2016/7/20.
 */
public class VideoPojo {
    @Id
    private String id;
    private int tid;
    private int cat;//1：电影 2：电视剧 3：综艺 4：动漫
    private String title;
    private String cover;
    private String year;
    private String score;
    private String upinfo;
    private int finish;
    private String epiname;
    private String word;
    private int render;
    private List<String> area;
    private List<String> actor = new ArrayList<>();
    private List<String> moviecat;
    private Date addDate;

    public VideoPojo() {
        this.addDate = new Date();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUpinfo() {
        return upinfo;
    }

    public void setUpinfo(String upinfo) {
        this.upinfo = upinfo;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getEpiname() {
        return epiname;
    }

    public void setEpiname(String epiname) {
        this.epiname = epiname;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getRender() {
        return render;
    }

    public void setRender(int render) {
        this.render = render;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public List<String> getMoviecat() {
        return moviecat;
    }

    public void setMoviecat(List<String> moviecat) {
        this.moviecat = moviecat;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "VideoPojo{" +
                "id='" + id + '\'' +
                ", tid=" + tid +
                ", cat=" + cat +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", year='" + year + '\'' +
                ", score='" + score + '\'' +
                ", upinfo='" + upinfo + '\'' +
                ", finish=" + finish +
                ", epiname='" + epiname + '\'' +
                ", word='" + word + '\'' +
                ", render=" + render +
                ", area=" + area +
                ", actor=" + actor +
                ", moviecat=" + moviecat +
                ", addDate=" + addDate +
                '}';
    }
}
