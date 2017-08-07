package com.haomiao.portal;

import com.alibaba.fastjson.JSONObject;
import com.haomiao.portal.domain.Tabulation;
import com.haomiao.portal.repository.TabulationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

/**
 * 分类和标签
 * Created by haomiao on 2016/7/20.
 */
@Component
public class TabulationProcessor implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    public static Logger logger = LoggerFactory.getLogger(TabulationProcessor.class);

    TabulationRepository tabulationRepository;

    @Override
    public void process(Page page) {
        String jsonStr = page.getRawText().toString().substring(32);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject dataJsonStr = jsonObject.getJSONObject("data").getJSONObject("data");
        Json json = new Json(dataJsonStr.toJSONString());
        Tabulation tabulation = json.toObject(Tabulation.class);
        logger.info(tabulation.toString());
        tabulation.setType("variety");
        tabulationRepository.save(tabulation);
    }

    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        String varietyUrl = "http://android.api.360kan.com/channel/?cid=6&method=channel.tabs&ver=96";
        //String cartoonUrl = "http://android.api.360kan.com/channel/?cid=5&method=channel.tabs&ver=96";
        //String movUrl = "http://android.api.360kan.com/channel/?cid=4&method=channel.tabs&ver=96";
        //String tvUrl = "http://android.api.360kan.com/channel/?cid=3&method=channel.tabs&ver=96";
        SpringApplication.run(TabulationProcessor.class,args);
        ConfigurableApplicationContext ctx = SpringApplication.run(TabulationProcessor.class,args);
        TabulationProcessor tabulationProcessor=new TabulationProcessor();
        TabulationRepository tabulationRepository = ctx.getBean(TabulationRepository.class);
        tabulationProcessor.tabulationRepository=tabulationRepository;
        Spider spider = Spider.create(tabulationProcessor).thread(1);
        spider.addUrl(varietyUrl);
        spider.run();
    }
}
