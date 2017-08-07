package com.haomiao.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haomiao.portal.domain.Tabulation;
import com.haomiao.portal.repository.TabulationRepository;
import com.haomiao.portal.repository.VideoPojoRepository;
import com.haomiao.portal.vo.VideoListVo;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 视频列表（根据标签）
 * Created by haomiao on 2016/7/20.
 */
@Component

public class VideoListProcessor implements PageProcessor {
    TabulationRepository tabulationRepository;
    VideoPojoRepository videoPojoRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private Set<Integer> tidSet = new HashSet<>();

    @Override
    public void process(Page page) {
        System.out.println("-----------------------------------------------------------------------------------------------");
        String jsonStr = page.getRawText().substring(32);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject dataJsonStr = jsonObject.getJSONObject("data").getJSONObject("data");
        String url = page.getUrl().toString();
        String cid = url.substring(url.indexOf("cid=") + 4, url.indexOf("cid=") + 5);
        if ("3".equals(cid)) {
            JSONArray jsonArray = dataJsonStr.getJSONArray("datas");
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonArray.getJSONObject(i).remove("actor");
            }
        }
        Json json = new Json(dataJsonStr.toJSONString());
        VideoListVo videoListVo = json.toObject(VideoListVo.class);
        int tid = videoListVo.getTid();
        int total = videoListVo.getTotal();
        if (!tidSet.contains(tid)) {
            tidSet.add(tid);
            for (int start = 20; start < total; start = start + 20) {
                String newUrl = "http://android.api.360kan.com/channel/?cid=" + cid + "&tid=" + tid + "&start=" + start + "&count=20&method=channel.datas";
                page.addTargetRequest(newUrl);
            }
        }
     /*   videoListVo.getDatas().forEach(videoPojo -> {
            videoPojo.setTid(tid);
            System.out.println(videoPojo.toString());
        });*/
        videoPojoRepository.save(videoListVo.getDatas());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        SpringApplication.run(TabulationProcessor.class, args);
        ConfigurableApplicationContext ctx = SpringApplication.run(VideoListProcessor.class, args);
        VideoListProcessor categoryListPageProcessor = new VideoListProcessor();
        TabulationRepository tabulationRepository = ctx.getBean(TabulationRepository.class);
        VideoPojoRepository videoPojoRepository = ctx.getBean(VideoPojoRepository.class);
        categoryListPageProcessor.tabulationRepository = tabulationRepository;
        categoryListPageProcessor.videoPojoRepository = videoPojoRepository;
        List<Tabulation> tabulations = tabulationRepository.findAll();
/*        for (Tabulation tabulation : tabulations) {
            tabulation.getList().forEach(categoryVo -> {
                int cid = 3;
                switch (tabulation.getType()) {
                    case "tv":
                        cid = 2;
                        break;
                    case "mov":
                        cid = 1;
                        break;
                    case "variety":
                        cid = 3;
                        break;
                    case "cartoon":
                        cid = 4;
                        break;
                }
                String url = "http://android.api.360kan.com/channel/?cid=" + cid + "&tid=" + categoryVo.getTid() + "&start=0&count=20&method=channel.datas";
                System.out.println(url);
                System.out.println("*********************************************************************************************************8");
                Spider spider = Spider.create(categoryListPageProcessor).thread(1);
                spider.addUrl(url);
                spider.run();
            });
        }*/
    }
}
