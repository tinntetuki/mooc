package com.haomiao.portal;

import com.haomiao.portal.domain.HomeCourse;
import com.haomiao.portal.repository.MoocHomeRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章详情（demo）
 * Created by haomiao on 2016/7/19.
 */
@Component
@SpringBootApplication
public class MoocHomeProcessor implements PageProcessor, CommandLineRunner {
    public static Logger logger = LoggerFactory.getLogger(MoocHomeProcessor.class);
    MoocHomeRepository moocHomeRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        try {
            List<HomeCourse> homeCourses = new ArrayList<HomeCourse>();
            Date date = new Date();
            List<String> coursesHtmls = page.getHtml().xpath("//*[@id=\'content-index\']").all();
            for (String coursesHtml : coursesHtmls){
                String type = Jsoup.parse(coursesHtml).getElementsByTag("h2").text();
                if(StringUtils.isEmpty(type)){
                    continue;
                }else{
                    List<Element> courseDivHtmls =  Jsoup.parse(coursesHtml).body().select("div.classic-index");
                    for (Element e : courseDivHtmls){
                        String courseUrl = Jsoup.parse(e.html()).body().getElementsByTag("a").attr("href");
                        String id = courseUrl.substring(courseUrl.lastIndexOf("/")+1,courseUrl.indexOf(".html"));
                        String imgSrc =  Jsoup.parse(e.html()).body().getElementsByTag("img").attr("src");
                        String title = Jsoup.parse(e.html()).body().getElementsByTag("h5").text();
                        String text = Jsoup.parse(e.html()).body().getElementsByTag("span").text();
                        HomeCourse homeCourse = new HomeCourse(id,type,courseUrl,imgSrc,title,text,date);
                        homeCourses.add(homeCourse);
                    }
                }
                
                //// TODO: 2016/7/29 抓详细课程页
                moocHomeRepository.save(homeCourses);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        SpringApplication.run(MoocHomeProcessor.class,args);
        ConfigurableApplicationContext ctx = SpringApplication.run(MoocHomeProcessor.class,args);
        MoocHomeProcessor moocProcessor=new MoocHomeProcessor();
        MoocHomeRepository moocHomeRepository = ctx.getBean(MoocHomeRepository.class);
        moocProcessor.moocHomeRepository=moocHomeRepository;
        Spider spider = Spider.create(moocProcessor).thread(1);
        spider.addUrl("http://www.mooc.cn/");
        spider.run();
       // Spider.create(new MoocProcessor()).addUrl("http://www.mooc.cn/course/609.html").thread(5).run();
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
