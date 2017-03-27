package com.haomiao.portal;

import com.alibaba.fastjson.JSONObject;
import com.haomiao.portal.domain.VarietyUrlPojo;
import com.haomiao.portal.domain.VideoDetailPojo;
import com.haomiao.portal.repository.VarietyUrlRepository;
import com.haomiao.portal.repository.VideoDetailRepository;
import com.haomiao.portal.vo.SiteVo;
import com.haomiao.portal.vo.VarietyListVo;
import com.haomiao.portal.vo.VarietyUrlVo;
import com.haomiao.portal.vo.VideoListVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.*;

/**
 * Created by haomiao on 2016/7/28.
 */
@SpringBootApplication
public class VarietyUrlProcessor implements PageProcessor {
    VideoDetailRepository videoDetailRepository;
    VarietyUrlRepository varietyUrlRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private Map<String,List<String>> idAndSitesMap = new HashMap<>();
    @Override
    public void process(Page page) {
        System.out.println(page.getUrl().toString());
        System.out.println("*******************************************************");
        String jsonStr = page.getRawText().substring(32);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject dataJsonStr = jsonObject.getJSONObject("data").getJSONObject("data");
        Json json = new Json(dataJsonStr.toJSONString());
        VarietyListVo varietyListVo = json.toObject(VarietyListVo.class);
        if (varietyListVo.getAllepisode() == null && varietyListVo.getAllepisode().isEmpty()) {
            return;
        }
        String url = page.getUrl().toString();
        String id = url.substring(url.indexOf("id=") + 3, url.indexOf("&cat"));
        VarietyUrlPojo varietyUrlPojo;
        //根据id查找记录，有实体，则使用该实体，否则创建新的实体。
        VarietyUrlPojo queryOne = varietyUrlRepository.findOne(id);
        if (queryOne == null) {
            varietyUrlPojo = new VarietyUrlPojo();
            varietyUrlPojo.setId(id);
            varietyUrlPojo.setCategory("3");
        } else {
            varietyUrlPojo = queryOne;
        }
        String site = url.substring(url.indexOf("site=") + 5, url.indexOf("&method"));
        List<VarietyUrlVo> varietyUrlVos = new ArrayList<>();
        for (VarietyUrlVo varietyUrlVo : varietyListVo.getAllepisode()) {
            if(!varietyUrlVos.isEmpty()){
                int exist = 0;
                for(VarietyUrlVo varietyUrl : varietyUrlVos){
                    if(varietyUrlVo.getName().equals(varietyUrl.getName())){
                        exist = 1;
                        break;
                    }
                }
                if(exist == 0){
                    varietyUrlVos.add(varietyUrlVo);
                }
            }else{
                varietyUrlVos.add(varietyUrlVo);
            }
        }
        //判断varietyUrlPojo中是否含有site,如果有，则继续为该site增加list，否则新建site.
        List<VarietyUrlVo> varietyUrlVoList = varietyUrlPojo.getAllUrlMap().get(site);
        if (varietyUrlVoList == null) {
            varietyUrlPojo.getAllUrlMap().put(site, varietyUrlVos);
        } else {
            varietyUrlPojo.getAllUrlMap().get(site).addAll(varietyUrlVos);
        }
        varietyUrlRepository.save(varietyUrlPojo);
        if(idAndSitesMap.containsKey(id)){
            List<String> siteList = idAndSitesMap.get(id);
            if(!siteList.contains(site)){
                siteList.add(site);
                for (int from = 20; from < 100; from = from + 20) {
                    String newUrl = "http://android.api.360kan.com/episode/?id=" + id + "&cat=3&from=" + from + "&count=20&site=" + site + "&method=episode.multi";
                    page.addTargetRequest(newUrl);
                }
            }
        }else{
            List<String> siteList = new ArrayList<>();
            siteList.add(site);
            idAndSitesMap.put(id,siteList);
            for (int from = 20; from < 100; from = from + 20) {
                String newUrl = "http://android.api.360kan.com/episode/?id=" + id + "&cat=3&from=" + from + "&count=20&site=" + site + "&method=episode.multi";
                page.addTargetRequest(newUrl);
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //http://android.api.360kan.com/episode/?id=ZsEob6Jv7JQ3Dz&cat=3&from=40&count=20&site=imgo&method=episode.multi
        VarietyUrlProcessor varietyUrlProcessor = new VarietyUrlProcessor();
        ConfigurableApplicationContext ctx = SpringApplication.run(VarietyUrlProcessor.class, args);
        VideoDetailRepository videoDetailRepository = ctx.getBean(VideoDetailRepository.class);
        VarietyUrlRepository varietyUrlRepository = ctx.getBean(VarietyUrlRepository.class);
        varietyUrlProcessor.videoDetailRepository = videoDetailRepository;
        varietyUrlProcessor.varietyUrlRepository = varietyUrlRepository;
        List<VideoDetailPojo> videoDetailPojos = videoDetailRepository.findByCat(3);
        for (VideoDetailPojo videoDetailPojo : videoDetailPojos) {
            String id = videoDetailPojo.getId();
            for (SiteVo siteVo : videoDetailPojo.getSites()) {
                String site = siteVo.getSite();
                String url = "http://android.api.360kan.com/episode/?id=" + id + "&cat=3&from=0&count=20&site=" + site + "&method=episode.multi";
                Spider spider = Spider.create(varietyUrlProcessor).thread(1);
                spider.addUrl(url);
                spider.run();
            }
        }
    }
}
