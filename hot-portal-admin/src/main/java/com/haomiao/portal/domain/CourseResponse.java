package com.haomiao.portal.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/24.
 */
public class CourseResponse implements Serializable{
    private String courseContent;
    private String pageContent;

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    @Override
    public String toString() {
        return "CourseResponse{" +
                "courseContent='" + courseContent + '\'' +
                ", pageContent='" + pageContent + '\'' +
                '}';
    }
}
