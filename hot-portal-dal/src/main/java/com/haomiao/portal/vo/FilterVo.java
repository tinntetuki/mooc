package com.haomiao.portal.vo;

import java.util.List;

/**
 * Created by haomiao on 2016/7/20.
 */
public class FilterVo {
    private String param;
    private String name;
    private List<OptionVo> options;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionVo> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVo> options) {
        this.options = options;
    }
}
