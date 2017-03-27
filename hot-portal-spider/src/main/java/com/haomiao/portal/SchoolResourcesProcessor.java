package com.haomiao.portal;

import com.haomiao.portal.domain.HomeCourse;
import com.haomiao.portal.domain.SchoolResources;
import com.haomiao.portal.repository.MoocHomeRepository;
import com.haomiao.portal.repository.SchoolResourcesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/1.
 */
@Component
@SpringBootApplication
@Configuration
public class SchoolResourcesProcessor{
    public static Logger logger = LoggerFactory.getLogger(MoocCourseProcessor.class);
    SchoolResourcesRepository schoolResourcesRepository;
    MoocHomeRepository moocHomeRepository;

    public static void main(String[] args) {

        SpringApplication.run(SchoolResourcesProcessor.class,args);
        ConfigurableApplicationContext ctx = SpringApplication.run(SchoolResourcesProcessor.class,args);
        SchoolResourcesProcessor courseProcessor=new SchoolResourcesProcessor();
        SchoolResourcesRepository schoolResourcesRepository = ctx.getBean(SchoolResourcesRepository.class);
        courseProcessor.schoolResourcesRepository=schoolResourcesRepository;

        MoocHomeRepository moocHomeRepository = ctx.getBean(MoocHomeRepository.class);
        courseProcessor.moocHomeRepository=moocHomeRepository;

        List<HomeCourse> list = moocHomeRepository.findAll();
        list.subList(0,10);
        List<SchoolResources> resources = new ArrayList<SchoolResources>();
        for(HomeCourse homeCourse : list) {
            SchoolResources schoolResources = new SchoolResources(homeCourse.getType(), homeCourse.getUrl(), homeCourse.getImgSrc(), homeCourse.getTitle(), homeCourse.getTitle(), homeCourse.getDate());
            schoolResourcesRepository.save(schoolResources);
        }
    }
}
