package com.haomiao.portal.domain;



import com.haomiao.portal.vo.CategoryVo;
import com.haomiao.portal.vo.FilterVo;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

/**
 * Created by haomiao on 2016/7/20.
 */
public class Tabulation {
    @Id
    private String id;
    private String type;//电视剧：tv,电影：mov,综艺：variety,动漫：cartoon
    private List<FilterVo> filter;
    private List<CategoryVo> list;

    public Tabulation() {
        this.id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FilterVo> getFilter() {
        return filter;
    }

    public void setFilter(List<FilterVo> filter) {
        this.filter = filter;
    }

    public List<CategoryVo> getList() {
        return list;
    }

    public void setList(List<CategoryVo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Tabulation{" +
                "filter=" + filter +
                ", list=" + list +
                '}';
    }
}
