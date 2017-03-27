package com.haomiao.portal.domain;

import org.springframework.util.StringUtils;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MoocCompartorId implements Comparator<MoocCourse> {
    @Override
    public int compare(MoocCourse o1, MoocCourse o2) {

        Integer thisId = 0;
        if(!(StringUtils.isEmpty(o1.getId()) || "null".equals(o1.getId()))) {
            thisId = Integer.valueOf(o1.getId());
        }
        Integer oId = 0;
        if(!(StringUtils.isEmpty(o2.getId()) || "null".equals(o2.getId()))){
            oId = Integer.valueOf(o2.getId());
        }
        return oId - thisId;
    }
}
