package com.haomiao.portal.domain;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/8/7.
 */
public class HomeCompartor implements Comparator<HomeCourse> {

    @Override
    public int compare(HomeCourse o1, HomeCourse o2) {
        return Integer.valueOf(o2.getId()) - Integer.valueOf(o1.getId());
    }
}
