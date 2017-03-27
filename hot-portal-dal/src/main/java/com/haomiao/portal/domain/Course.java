package com.haomiao.portal.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

/**
 * Created by haomiao on 2016/7/19.
 */
public class Course {
    @Id
    private String id;
    private String title;//
    private String excerpt;//
    private String imgUrl;//
    private String courseUrl;//
    private String content;//
    private String school;//
    private Date startDate;//
    private Subject subject;//
    private String community;//
    private String language;//

    public Course() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", courseUrl='" + courseUrl + '\'' +
                ", content='" + content + '\'' +
                ", school='" + school + '\'' +
                ", startDate=" + startDate +
                ", subject=" + subject +
                ", community='" + community + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
