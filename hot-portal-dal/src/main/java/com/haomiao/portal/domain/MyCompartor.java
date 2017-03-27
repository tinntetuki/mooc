package com.haomiao.portal.domain;

import org.springframework.util.StringUtils;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MyCompartor implements Comparator<MoocCourse> {
    @Override
    public int compare(MoocCourse o1, MoocCourse o2) {

        Integer thisHot = 0;
        if(!(StringUtils.isEmpty(o1.getHot()) || "null".equals(o1.getHot()))){
            thisHot = Integer.valueOf(o1.getHot());
        }
        Integer oHot = 0;
        if(!(StringUtils.isEmpty(o2.getHot()) || "null".equals(o2.getHot()))){
            oHot = Integer.valueOf(o2.getHot());
        }
        return oHot - thisHot;
    }
}
