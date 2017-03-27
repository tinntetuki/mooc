package com.haomiao.portal.vo;

import com.haomiao.portal.domain.VideoRankPojo;

import java.util.List;

/**
 * Created by haomiao on 2016/7/21.
 */
public class VideoRankListVo {
    private String total;
    private String curPage;
    private String totalPage;
    private List<VideoRankPojo> data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<VideoRankPojo> getData() {
        return data;
    }

    public void setData(List<VideoRankPojo> data) {
        this.data = data;
    }
}
