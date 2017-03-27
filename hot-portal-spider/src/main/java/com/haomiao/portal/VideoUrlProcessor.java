package com.haomiao.portal;

import com.alibaba.fastjson.JSONObject;
import com.haomiao.portal.domain.VideoDetailPojo;
import com.haomiao.portal.domain.VideoUrlPojo;
import com.haomiao.portal.repository.VideoDetailRepository;
import com.haomiao.portal.repository.VideoUrlRepository;
import com.haomiao.portal.vo.PlayLinkHtmlVo;
import com.haomiao.portal.vo.SiteVo;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haomiao on 2016/7/26.
 */
@Component
@SpringBootApplication
public class VideoUrlProcessor implements PageProcessor{
    VideoUrlRepository videoUrlRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    List<VideoUrlPojo> errorList = new ArrayList<>();
    @Override
    public void process(Page page) {
        System.out.println(page.getUrl().toString());
        System.out.println("*******************************************************");
        String url = page.getUrl().toString();
        String site = url.substring(url.indexOf("site=")+5);
        String id = url.substring(url.indexOf("id=")+3,url.indexOf("&site"));
        String category = url.substring(url.indexOf("category=")+9,url.indexOf("category=")+10);
        Map<Integer,String> siteUrls = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(page.getRawText());
        Json json = new Json(jsonObject.toJSONString());
        PlayLinkHtmlVo playLinkHtmlVo = json.toObject(PlayLinkHtmlVo.class);
        String playLinkHtmlStr = playLinkHtmlVo.getData();
        if("".equals(playLinkHtmlStr)){
            System.out.println("###################################################################");
            VideoUrlPojo videoUrlPojo = new VideoUrlPojo();
            videoUrlPojo.setId(id);
            videoUrlPojo.setCategory(site);
            errorList.add(videoUrlPojo);
            System.out.println(playLinkHtmlVo.getMsg());
            System.out.println("###################################################################");
            return;
        }
        Html playLinkHtml = Html.create(playLinkHtmlStr);
        List<String> titleHtml = playLinkHtml.xpath("//div[@class='num-tab-main g-clear js-tab']//a/@href").all();
        int count = 1;
        for(String href : titleHtml){
            if(!"#".equals(href)){
                siteUrls.put(count,href);
                count++;
            }
        }
        VideoUrlPojo queryOne = videoUrlRepository.findOne(id);
        if(queryOne==null){
            VideoUrlPojo videoUrlPojo = new VideoUrlPojo();
            Map<String,Map<Integer,String>> allUrlMap = new HashMap<>();
            allUrlMap.put(site,siteUrls);
            videoUrlPojo.setId(id);
            videoUrlPojo.setCategory(category);
            videoUrlPojo.setAllUrlMap(allUrlMap);
            videoUrlRepository.save(videoUrlPojo);
        }else{
            queryOne.getAllUrlMap().put(site,siteUrls);
            videoUrlRepository.save(queryOne);
        }
        for(VideoUrlPojo videoUrlPojo : errorList){
            System.out.println(videoUrlPojo.toString());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args){
        //String url = "www.360kan.com/cover/switchsite?site=qiyi&id=Qrlvc07kSWXsM3&category=2";
        VideoUrlProcessor videoUrlProcessor = new VideoUrlProcessor();
        ConfigurableApplicationContext ctx = SpringApplication.run(VideoUrlProcessor.class,args);
        VideoDetailRepository videoDetailRepository = ctx.getBean(VideoDetailRepository.class);
        videoUrlProcessor.videoUrlRepository = ctx.getBean(VideoUrlRepository.class);
        String pattern = "http://www.360kan.com/cover/switchsite?category=2&id=";
        List<VideoDetailPojo> videoDetailPojos = videoDetailRepository.findByCat(2);
        for(VideoDetailPojo videoDetailPojo : videoDetailPojos){
            String id = videoDetailPojo.getId();
            for(SiteVo siteVo : videoDetailPojo.getSites()){
                String site = siteVo.getSite();
                String url = pattern+id+"&site="+site;
                Spider spider = Spider.create(videoUrlProcessor).thread(5);
                spider.addUrl(url);
                spider.run();
            }
        }
/*        String url = "http://www.360kan.com/cover/switchsite?category=2&id=Q4Vraq4ZdWTtOH&site=migu";
        //String url = "http://www.360kan.com/cover/switchsite?site=qiyi&id=Q4Jwb07kSWPnNn&category=2";
        //String url = "http://www.360kan.com/cover/switchsite?site=qiyi&id=Qrlvc07kSWXsM3&category=2";
        Spider spider = Spider.create(new VideoUrlProcessor()).thread(1);
        spider.addUrl(url.toString());
        spider.run();*/
    }
}
