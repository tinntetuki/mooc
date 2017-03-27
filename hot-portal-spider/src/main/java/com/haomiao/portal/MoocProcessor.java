package com.haomiao.portal;

import com.haomiao.portal.domain.Course;
import com.haomiao.portal.domain.Subject;
import com.haomiao.portal.repository.CourseRepository;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 文章详情（demo）
 * Created by haomiao on 2016/7/19.
 */
@Component
@SpringBootApplication
public class MoocProcessor implements PageProcessor, CommandLineRunner {
    public static Logger logger = LoggerFactory.getLogger(MoocProcessor.class);
    CourseRepository courseRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        Course course = new Course();
        String titleHtml = page.getHtml().xpath("//h1[@class='course-title']").get();
        String courseExcerptHtml= page.getHtml().xpath("//div[@class='course-excerpt']").get();
        String courseImgHtml = page.getHtml().xpath("//div[@class='courseimg']//img/@src").get();
        String courseUrlHtml = page.getHtml().xpath("//div[@class='course_url']//a/@href").get();
        String courseClassifyHtml = page.getHtml().xpath("//div[@class='course_url']//ul").get();
        String courseClassify = Jsoup.parse(courseClassifyHtml).body().getElementsByTag("li").text();
        String courseSchoolHtml = page.getHtml().xpath("//div[@class='course-school']").get();
        String courseContentHtml = page.getHtml().xpath("//div[@class='inner']").get();
        String courseDateHtml = page.getHtml().xpath("//div[@class='coursetime-date']").get();
        String courseYearHtml = page.getHtml().xpath("//div[@class='coursetime-year']").get();
        String courseDate = Jsoup.parse(courseDateHtml).body().text().concat(Jsoup.parse(courseYearHtml).body().text());
        courseDate = courseDate.replaceAll(" ","").replaceAll("开课","");
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日yyyy年");

        course.setCourseUrl(courseUrlHtml);
        course.setImgUrl(courseImgHtml);
        Subject subject = new Subject();
        subject.setUrl(Jsoup.parse(courseClassifyHtml).body().getElementsByTag("a").attr("href"));
        subject.setName(courseClassify.substring(courseClassify.indexOf("类")+2,courseClassify.indexOf("平")).trim());
        course.setCommunity(courseClassify.substring(courseClassify.indexOf("台")+2,courseClassify.indexOf("语")).trim());
        course.setLanguage(courseClassify.substring(courseClassify.indexOf("言")+2));
        course.setSchool(Jsoup.parse(courseSchoolHtml).body().text());
        course.setContent(Jsoup.parse(courseContentHtml).body().html());
        course.setSubject(subject);
        course.setTitle(Jsoup.parse(titleHtml).body().text());
        course.setExcerpt(Jsoup.parse(courseExcerptHtml).body().text());
        try {
            course.setStartDate(sdf.parse(courseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logger.info(course.toString());
        courseRepository.save(course);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        SpringApplication.run(MoocProcessor.class,args);
        ConfigurableApplicationContext ctx = SpringApplication.run(MoocProcessor.class,args);
        MoocProcessor moocProcessor=new MoocProcessor();
        CourseRepository courseRepository = ctx.getBean(CourseRepository.class);
        moocProcessor.courseRepository=courseRepository;
        Spider spider = Spider.create(moocProcessor).thread(1);
        spider.addUrl("http://www.mooc.cn/course/609.html");
        spider.run();
       // Spider.create(new MoocProcessor()).addUrl("http://www.mooc.cn/course/609.html").thread(5).run();
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
