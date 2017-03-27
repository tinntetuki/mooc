package com.haomiao.portal.domain;

import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MoocCourse implements Serializable,Comparable<MoocCourse>{
    @Id
    private  String  id;
    private String cate; //分类
    private String school; //学校
    private String platform; //平台
    private String lang;   //语言
    private String hotTag;//热门分类
    private String herf; //课程url
    private String imgSrc; //图片
    private String title;
    private String ename;
    private String subhead1; //经典课程，专项课程，微专业
    private String subhead2;
    private String content;
    private Date startDate; //开课日期
    private Date date;   //更新时间
    private String courseGoUrl;//去上课的url
    private String courseContent; //课程内容
    private String excerpt;
    private String year;
    private String month;
    private String day;
    private String sdate; //开课时间
    private String hot;//热度

    public MoocCourse() {
        super();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getHotTag() {
        return hotTag;
    }

    public void setHotTag(String hotTag) {
        this.hotTag = hotTag;
    }

    public String getHerf() {
        return herf;
    }

    public void setHerf(String herf) {
        this.herf = herf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCourseGoUrl() {
        return courseGoUrl;
    }

    public void setCourseGoUrl(String courseGoUrl) {
        this.courseGoUrl = courseGoUrl;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getSubhead1() {
        return subhead1;
    }

    public void setSubhead1(String subhead1) {
        this.subhead1 = subhead1;
    }

    public String getSubhead2() {
        return subhead2;
    }

    public void setSubhead2(String subhead2) {
        this.subhead2 = subhead2;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "MoocCourse{" +
                "id='" + id + '\'' +
                ", cate='" + cate + '\'' +
                ", school='" + school + '\'' +
                ", platform='" + platform + '\'' +
                ", lang='" + lang + '\'' +
                ", hotTag='" + hotTag + '\'' +
                ", herf='" + herf + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", title='" + title + '\'' +
                ", ename='" + ename + '\'' +
                ", subhead1='" + subhead1 + '\'' +
                ", subhead2='" + subhead2 + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", date=" + date +
                ", courseGoUrl='" + courseGoUrl + '\'' +
                ", courseContent='" + courseContent + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", sdate='" + sdate + '\'' +
                ", hot='" + hot + '\'' +
                '}';
    }


    @Override
    public int compareTo(MoocCourse o) {
        if(o.getStartDate().getTime() > this.getStartDate().getTime()){
            return  1;
        }else if(o.getStartDate().getTime() < this.getStartDate().getTime()){
            return  -1;
        }else{
            return  0;
        }
    }

    public int compareViews(MoocCourse o) {
        Integer thisHot = 0;
        if(!(StringUtils.isEmpty(this.getHot()) || "null".equals(this.getHot()))){
            thisHot = Integer.valueOf(this.getHot());
        }
        Integer oHot = 0;
        if(!(StringUtils.isEmpty(o.getHot()) || "null".equals(o.getHot()))){
            oHot = Integer.valueOf(o.getHot());
        }
        return oHot - thisHot;
    }
  public int compareId(MoocCourse o) {
        Integer thisHot = Integer.valueOf(this.getId());
        Integer oHot = Integer.valueOf(o.getId());

        return oHot - thisHot;
    }



    public static void main(String[] args) {
        MoocCourse m = new MoocCourse();
        System.out.println(Integer.valueOf(m.getHot()));
    }


}
