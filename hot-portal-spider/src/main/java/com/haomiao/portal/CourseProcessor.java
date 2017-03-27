package com.haomiao.portal;

import com.haomiao.portal.domain.MoocCourse;
import com.haomiao.portal.repository.MoocCourseRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/1.
 */
@Component
@SpringBootApplication
@Configuration
@EnableScheduling
public class CourseProcessor implements PageProcessor, CommandLineRunner {
    public static Logger logger = LoggerFactory.getLogger(CourseProcessor.class);
    MoocCourseRepository moocCourseRepository;
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void run(String... strings) throws Exception {
    }

    @Override
    public void process(Page page) {
        List<MoocCourse> courses = new ArrayList<MoocCourse>();

        List<String> coursesHtmls = page.getHtml().xpath("//*[@id=\"newscontent\"]/div").all();
        for(String e : coursesHtmls){
                if(e.toString().contains("page-numbers")){
                    continue;
                }

                String courseUrl = Jsoup.parse(e).body().getElementsByTag("a").attr("href");
                String id = courseUrl.substring(courseUrl.lastIndexOf("/")+1,courseUrl.indexOf(".html"));
                MoocCourse c = moocCourseRepository.findById(id);

                if(c != null  && !StringUtils.isEmpty(c.getCourseGoUrl())){
                    break;
                }

                String imgSrc =  Jsoup.parse(e).body().getElementsByTag("img").attr("src");
                String title = Jsoup.parse(e).body().getElementsByTag("h1").text();
                String content = Jsoup.parse(e).body().getElementsByTag("h2").text();
                String startDate = Jsoup.parse(e).body().select("div.courselist-date").get(0).text();
                String ename = "";
                List<Element> enames = Jsoup.parse(e).body().select("div.course_enname");
                if(!CollectionUtils.isEmpty(enames)){
                    ename = enames.get(0).text();
                }
                String subhead1 = "";
                String subhead2 = "";
                List<Element> subheads = Jsoup.parse(e).body().select("#newscontent > div:nth-child(8) > div.inner > div.courselist-content > div.news-subhead2.news-subhead-red");

                if(!CollectionUtils.isEmpty(subheads)) {
                    if(subheads.size() == 1){
                        subhead1 = subheads.get(0).text();
                    }else{
                        subhead2 = subheads.get(1).text();
                    }

                }

                MoocCourse  moocCourse = new MoocCourse();
                StringBuilder dateStr = new StringBuilder();
                moocCourse.setSdate(startDate);
                if("任何时间".equals(startDate)){
                    dateStr.append("1999/12/31");
                }else if ("暂无开课".equals(startDate)){
                    dateStr.append("2099/12/31");
                }else{
                    String  year = startDate.substring(0,startDate.indexOf("年"));
                    String month = startDate.substring(startDate.indexOf("年")+2,startDate.indexOf("月"));
                    String  day = startDate.substring(startDate.indexOf("月")+1,startDate.indexOf("日"));

                    moocCourse.setYear(year);
                    moocCourse.setMonth(month);
                    moocCourse.setDay(day);

                    dateStr = new StringBuilder().append(year).append("/").append(Integer.valueOf(month) < 10 ? "0"+month : month).append("/").append(Integer.valueOf(day) < 10 ? "0"+day : day);
                }



                moocCourse.setId(id);
                moocCourse.setHerf(courseUrl);
                moocCourse.setImgSrc(imgSrc);
                moocCourse.setTitle(title);
                moocCourse.setContent(content);
                moocCourse.setEname(ename);
                moocCourse.setSubhead1(subhead1);
                moocCourse.setSubhead2(subhead2);


                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                try {
                    moocCourse.setStartDate(sdf.parse(dateStr.toString()));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                moocCourse.setDate(new Date());
                courses.add(moocCourse);

        }
        if(!CollectionUtils.isEmpty(courses)){
            moocCourseRepository.save(courses);

            //爬课程详情
            for(MoocCourse m : courses){
                SpringApplication.run(MoocCourseProcessor.class);






                ConfigurableApplicationContext ctx = SpringApplication.run(MoocCourseProcessor.class);
                MoocCourseProcessor courseProcessor=new MoocCourseProcessor();
                MoocCourseRepository moocCourseRepository = ctx.getBean(MoocCourseRepository.class);
                courseProcessor.moocCourseRepository=moocCourseRepository;
                Spider spider = Spider.create(courseProcessor).thread(1);
                spider.addUrl(m.getHerf());
                spider.run();
            }
        }


        if(page.getHtml().toString().contains("next page-numbers")){
            Integer pageNum;
            if(page.getUrl().toString().contains("page")){
                pageNum = Integer.valueOf(page.getUrl().toString().substring(page.getUrl().toString().lastIndexOf("/")+1))+1;
            }else{
                pageNum = 2;
            }
            SpringApplication.run(CourseProcessor.class);
            ConfigurableApplicationContext ctx = SpringApplication.run(CourseProcessor.class);
            CourseProcessor courseProcessor=new CourseProcessor();
            MoocCourseRepository moocCourseRepository = ctx.getBean(MoocCourseRepository.class);
            courseProcessor.moocCourseRepository=moocCourseRepository;
            Spider spider = Spider.create(courseProcessor).thread(1);
            spider.addUrl("http://www.mooc.cn/course/page/" + pageNum);
            spider.run();

        }


    }


    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        SpringApplication.run(CourseProcessor.class,args);
        ConfigurableApplicationContext ctx = SpringApplication.run(CourseProcessor.class,args);
        CourseProcessor courseProcessor=new CourseProcessor();
        MoocCourseRepository moocCourseRepository = ctx.getBean(MoocCourseRepository.class);
        courseProcessor.moocCourseRepository=moocCourseRepository;
        Spider spider = Spider.create(courseProcessor).thread(1);
        spider.addUrl("http://www.mooc.cn/course");
        spider.run();
        // Spider.create(new MoocProcessor()).addUrl("http://www.mooc.cn/course/609.html").thread(5).run();
    }
}
