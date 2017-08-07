package com.haomiao.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haomiao.portal.domain.VideoRankPojo;
import com.haomiao.portal.repository.VideoRankRepository;
import com.haomiao.portal.vo.VideoRankListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

/**
 * 视频榜单
 * Created by haomiao on 2016/7/21.
 */
@Component
public class VideoRankListProcessor implements PageProcessor{
    @Autowired
    static VideoRankRepository videoRankRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        String jsonStr = page.getRawText().toString().substring(32);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject dataJsonStr = jsonObject.getJSONObject("data").getJSONObject("data");
        String url = page.getUrl().toString();
        String type = url.substring(url.lastIndexOf("=")+1);
        if("3".equals(type)){
            JSONArray jsonArray = dataJsonStr.getJSONArray("data");
            for(int j=0;j<jsonArray.size();j++){
                jsonArray.getJSONObject(j).remove("actor");
            }
        }
        Json json = new Json(dataJsonStr.toJSONString());
        VideoRankListVo videoRankListVo = json.toObject(VideoRankListVo.class);
        for(VideoRankPojo videoRankPojo : videoRankListVo.getData()){
            videoRankRepository.save(videoRankPojo);
        }
    }
    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args){
        for(int i=0;i<4;i++){
            String url = "http://android.api.360kan.com/ranking/?method=ranking.list&c="+(i+1);
            Spider spider = Spider.create(new VideoRankListProcessor()).thread(1);
            spider.addUrl(url);
            spider.run();
        }
    }
}
