package com.haomiao.portal;

import com.alibaba.fastjson.JSONObject;
import com.haomiao.portal.domain.VideoDetailPojo;
import com.haomiao.portal.domain.VideoPojo;
import com.haomiao.portal.domain.VideoRankPojo;
import com.haomiao.portal.repository.VideoDetailRepository;
import com.haomiao.portal.repository.VideoPojoRepository;
import com.haomiao.portal.repository.VideoRankRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.List;

/**
 * Created by haomiao on 2016/7/20.
 */
@SpringBootApplication
public class VideoDetailProcessor implements PageProcessor {
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
        VideoPojoRepository videoPojoRepository = ctx.getBean(VideoPojoRepository.class);
        VideoDetailProcessor videoDetailProcessor = new VideoDetailProcessor();
        videoDetailProcessor.videoDetailRepository = ctx.getBean(VideoDetailRepository.class);
        for(int cat=1;cat<5;cat++){
            List<VideoPojo> videoPojos = videoPojoRepository.findByCat(cat);
            for (VideoPojo videoPojo : videoPojos) {
                String id = videoPojo.getId();
                String url = "http://android.api.360kan.com/coverpage/?id=" + id + "&cat=" + cat + "&method=coverpage.data&ver=96";
                Spider spider = Spider.create(videoDetailProcessor).thread(5);
                spider.addUrl(url);
                spider.run();
            }
        }
    }
}
