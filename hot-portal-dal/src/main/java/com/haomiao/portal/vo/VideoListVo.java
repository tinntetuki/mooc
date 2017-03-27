package com.haomiao.portal.vo;


import com.haomiao.portal.domain.VideoPojo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by haomiao on 2016/7/20.
 */
public class VideoListVo {
    private int total;
    private int tid;
    private List<VideoPojo> datas = new ArrayList<>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public List<VideoPojo> getDatas() {
        return datas;
    }

    public void setDatas(List<VideoPojo> datas) {
        this.datas = datas;
    }


}
