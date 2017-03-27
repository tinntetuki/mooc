package com.haomiao.portal;

import com.alibaba.fastjson.JSONObject;
import com.haomiao.portal.domain.VideoDetailPojo;
import com.haomiao.portal.domain.VideoRankPojo;
import com.haomiao.portal.repository.VideoDetailRepository;
import com.haomiao.portal.repository.VideoRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.List;

/**
 * Created by haomiao on 2016/7/22.
 */
public class VideoRankDetailProcessor implements PageProcessor {
    VideoDetailRepository videoDetailRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        String jsonStr = page.getRawText().toString().substring(32);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject dataJsonStr = jsonObject.getJSONObject("data").getJSONObject("data");
        String url = page.getUrl().toString();
        String cat = url.substring(url.indexOf("cat=") + 4, url.indexOf("cat=") + 5);
        if ("3".equals(cat)) {
            dataJsonStr.remove("actor");
            dataJsonStr.remove("doubanComment");
        }
        Json json = new Json(dataJsonStr.toJSONString());
        VideoDetailPojo videoDetailPojo = json.toObject(VideoDetailPojo.class);
        videoDetailRepository.save(videoDetailPojo);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(VideoListProcessor.class, args);
        VideoRankRepository videoRankRepository = ctx.getBean(VideoRankRepository.class);
        VideoRankDetailProcessor videoRankDetailProcessor = new VideoRankDetailProcessor();
        videoRankDetailProcessor.videoDetailRepository = ctx.getBean(VideoDetailRepository.class);
        List<VideoRankPojo> videoRankPojos = videoRankRepository.findAll();
        for (VideoRankPojo videoRankPojo : videoRankPojos) {
            int cat = videoRankPojo.getCat();
            String id = videoRankPojo.getId();
            String url = "http://android.api.360kan.com/coverpage/?id=" + id + "&cat=" + cat + "&method=coverpage.data&ver=96";
            Spider spider = Spider.create(videoRankDetailProcessor).thread(1);
            spider.addUrl(url);
            spider.run();
        }
    }
}
