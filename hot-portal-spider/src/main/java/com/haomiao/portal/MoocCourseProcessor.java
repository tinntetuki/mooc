package com.haomiao.portal;

import com.haomiao.portal.domain.MoocCourse;
import com.haomiao.portal.repository.MoocCourseRepository;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
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

/**
 * 文章详情（demo）
 * Created by haomiao on 2016/7/19.
 */
@Component
@SpringBootApplication
public class MoocCourseProcessor implements PageProcessor, CommandLineRunner {
    public static Logger logger = LoggerFactory.getLogger(MoocCourseProcessor.class);
    MoocCourseRepository moocCourseRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        try {
            String id = page.getUrl().toString().substring(page.getUrl().toString().lastIndexOf("/")+1,page.getUrl().toString().indexOf(".html"));
            MoocCourse moocCourse = moocCourseRepository.findById(id);


            String courseUrlHtml = page.getHtml().xpath("//div[@class='coursego']").get();
            String courseClassifyHtml = page.getHtml().xpath("//div[@class='course_url']//ul").get();
            String courseClassify = Jsoup.parse(courseClassifyHtml).body().getElementsByTag("li").text();
            String courseSchoolHtml = page.getHtml().xpath("//div[@class='course-school']").get();
            String courseExcerptHtml= page.getHtml().xpath("//div[@class='course-excerpt']").get();
            String courseContentHtml = page.getHtml().xpath("//div[@class='inner']").get();


            String cate = courseClassify.substring(courseClassify.indexOf("分类：")+3,courseClassify.indexOf("平台：")).trim();
            String platform = courseClassify.substring(courseClassify.indexOf("平台：")+3,courseClassify.lastIndexOf("语言：")).trim();
            String lang = courseClassify.substring(courseClassify.indexOf("语言：")+3).trim();
            String school = Jsoup.parse(courseSchoolHtml).body().getElementsByTag("h2").text();
            String excerpt = Jsoup.parse(courseExcerptHtml).body().text();
            String content = Jsoup.parse(courseContentHtml).body().html();
            String url = Jsoup.parse(courseUrlHtml).body().getElementsByTag("a").attr("href");

            //如果超时重新爬
            if( StringUtils.isEmpty(url)){
                SpringApplication.run(MoocCourseProcessor.class);
                ConfigurableApplicationContext ctx = SpringApplication.run(MoocCourseProcessor.class);
                MoocCourseProcessor courseProcessor=new MoocCourseProcessor();
                MoocCourseRepository moocCourseRepository = ctx.getBean(MoocCourseRepository.class);
                courseProcessor.moocCourseRepository=moocCourseRepository;
                Spider spider = Spider.create(courseProcessor).thread(1);
                spider.addUrl(page.getUrl().toString());
                spider.run();
            }else{
                moocCourse.setCate(cate);
                moocCourse.setPlatform(platform);
                moocCourse.setLang(lang);
                moocCourse.setSchool(school);
                moocCourse.setExcerpt(excerpt);
                moocCourse.setCourseContent(content);
                moocCourse.setCourseGoUrl(url);

                //// TODO: 2016/8/2   更新数据库中id为    的课程信息
                //System.out.println(moocCourse.toString());
                moocCourseRepository.save(moocCourse);
            }
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        SpringApplication.run(MoocCourseProcessor.class,args);
        ConfigurableApplicationContext ctx = SpringApplication.run(MoocCourseProcessor.class,args);
        MoocCourseProcessor moocCourseProcessor=new MoocCourseProcessor();
        MoocCourseRepository moocCourseRepository = ctx.getBean(MoocCourseRepository.class);
        moocCourseProcessor.moocCourseRepository=moocCourseRepository;
        Spider spider = Spider.create(moocCourseProcessor).thread(1);
        spider.addUrl("http://www.mooc.cn/course/5940.html");
        spider.run();
        // Spider.create(new MoocProcessor()).addUrl("http://www.mooc.cn/course/609.html").thread(5).run();
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
