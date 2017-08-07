package com.haomiao.portal.controller;

import com.haomiao.portal.domain.*;
import com.haomiao.portal.repository.MoocCourseRepository;
import com.haomiao.portal.repository.MoocHomeRepository;
import com.haomiao.portal.repository.SchoolResourcesRepository;
import com.haomiao.portal.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by haomiao on 2016/8/1.
 */
@Controller
@RequestMapping("mk")
public class MkController {

    @Autowired
    MoocHomeRepository moocHomeRepository;
    @Autowired
    MoocCourseRepository moocCourseRepository;
    @Autowired
    SchoolResourcesRepository schoolResourcesRepository;


    //注入application.properties的属性到指定变量中.
    @Value("${ipAddress}")
    private String host;

    private static String prePagemessage = "<nav class=\"navigation pagination\" role=\"navigation\">\n" +
            "<h2 class=\"screen-reader-text\"> </h2> <div class=\"nav-links\">";
    private String lastPageMessage = "<span class=\"prev page-numbers\"  value=\"pageNum\">上页</span>";
    private String firstPageMessage = "<span class=\"page-numbers\"  value=\"pageNum\"><span></span>1<span></span></span>";
    private static String devicePageMessage= "<span class=\"page-numbers dots\">…</span>";
    private String lastPageMessage1 = "<span class=\"page-numbers\"  value=\"pageNum\"><span></span>lastPage<span></span></span>";
    private String currentPageMessage = "<span class=\"page-numbers current\" value=\"pageNum\"><span></span>nowPage<span></span></span>";
    private String nestPageMessage = "<span class=\"page-numbers\"  value=\"pageNum\"><span></span>nestPage<span></span></span>";
    private String totalPageMessage = "<span class=\"page-numbers\"  value=\"pageNum\"><span></span>totalPage<span></span></span>";
    private String nestPageMessage1 = "<span class=\"next page-numbers\"  value=\"pageNum\">下页</span></div>";
    private static  String subPageMessage = "</nav>";


    /**
     *  主页
     **/
    @RequestMapping("/home")
    public ModelAndView home(Model model) {
        addStatmentAndTitle(model);

        List<HomeCourse> newList=moocHomeRepository.findByType("最新课程",new PageRequest(0,10));
        List<HomeCourse> jdList=moocHomeRepository.findByType("经典课程",new PageRequest(0,5));
        List<HomeCourse> autonomous = moocHomeRepository.findByType("自主课程",new PageRequest(0,10));
        List<HomeCourse> zxList=moocHomeRepository.findByType("专项课程",new PageRequest(0,10));

        initHomeImgSrcAndHerf(newList);
        initHomeImgSrcAndHerf(jdList);
        initHomeImgSrcAndHerf(autonomous);
        initHomeImgSrcAndHerf(zxList);
        HomeCompartor mc = new HomeCompartor();

        Collections.sort(newList,mc);
        Collections.sort(jdList,mc);
        Collections.sort(autonomous,mc);
        Collections.sort(zxList,mc);

        model.addAttribute("newList",newList.subList(0,10));
        model.addAttribute("jdList",jdList.subList(0,5));
        model.addAttribute("autonomous",autonomous.subList(0,10));
        model.addAttribute("zxList",zxList.subList(0,10));


        addAttribute(model);
        model.addAttribute("headUrl","style=\"background-image: url(http://"+host+":1008/moocCourse/imgSrc/head2.jpg);\"");
        return new ModelAndView("mk/home");
    }

    private void addStatmentAndTitle(Model model) {
        Jedis jedis = JedisUtil.getInstance();
        if(jedis == null){
            model.addAttribute("portalStatement", "");
            model.addAttribute("portalTitle","热点门户·教育");
        }else{
            try {
                String key = "portalStatement";
                String keyTitle = "portalTitle";
                if(jedis.exists(key)){
                    String portalStatement = jedis.get(key);

                    model.addAttribute("portalStatement", portalStatement);
                }else{
                    model.addAttribute("portalStatement", "");
                }
                if(jedis.exists(keyTitle)){
                    String portalTitle = jedis.get(keyTitle);

                    model.addAttribute("portalTitle", portalTitle);
                }else{
                    model.addAttribute("portalTitle", "热点门户·教育");
                }

            } catch (JedisException e) {
                if (jedis != null) {
                    JedisUtil.closeBrokenJedis(jedis);
                    jedis = null;
                }
            } catch (Exception e) {
            } finally {
                if (jedis != null) {

                }
            }
        }
    }

    private void initHomeImgSrcAndHerf(List<HomeCourse> newList) {
        for(HomeCourse h : newList ){
            h.setImgSrc( "http://"+host+":1008/moocCourse/imgSrc/" + h.getImgSrc().substring(h.getImgSrc().lastIndexOf("/")+1));
            h.setUrl("http://"+host+":3300/mk/list/" + h.getId());
        }
    }


    /**
     * 列表
     **/
    @RequestMapping("/list")
    public ModelAndView list(Model model,HttpServletRequest request) {

        //分页功能
        String url = request.getRequestURL().toString();  //请求路径
        String sss = request.getQueryString();            //参数


        if(!StringUtils.isEmpty(sss)){
            url = url + "?" + sss;
        }

        if(!StringUtils.isEmpty(sss)){

        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年    MM月dd日");
        List<MoocCourse> moocCourses = moocCourseRepository.findAll();
        initImgSrcAndHerf(moocCourses);

        //当前页
        Integer nowPage = 1;

        Integer pageNums =  0;
        if(!CollectionUtils.isEmpty(moocCourses)){
           pageNums = getPageNums(moocCourses);
        }
        String pageStr = getPageContentStr(nowPage,pageNums);

        //最新课程
        Page<MoocCourse> newCourses = getNewMoocCourses();

        //好课汇
        List<MoocCourse> hotCourses = getHotCourses();


        List<MoocCourse> views = getMoocCoursesViews(moocCourses, nowPage, pageNums);


        String sb = getCourseContentStr(views);

        model.addAttribute("courseContent",sb);
        model.addAttribute("pageMessage",pageStr);
        model.addAttribute("moocCourses",views);
        model.addAttribute("newCourses",newCourses);
        model.addAttribute("hotCourses",hotCourses);


        addAttribute(model);
        addStatmentAndTitle(model);




        return new ModelAndView("mk/list");
    }

    /**
     * 文章详情
     **/
    @RequestMapping("/list/*")
    public ModelAndView article(Model model,HttpServletRequest request) {

        String id = request.getRequestURL().toString().substring(request.getRequestURL().toString().lastIndexOf("/")+1);
        MoocCourse course = moocCourseRepository.findById(id);

        Integer hot = 0;
        if(StringUtils.isEmpty(course.getHot()) || "null".equals(course.getHot())){
            hot = 1;
        }else{
            hot = Integer.valueOf(course.getHot())+1;
        }

        //每次查看详情，热度+1
        course.setHot(String.valueOf(hot));

        moocCourseRepository.save(course);

        course.setImgSrc( "http://"+host+":1008/moocCourse/imgSrc/" + course.getImgSrc().substring(course.getImgSrc().lastIndexOf("/")+1));

        //最新课程
        Page<MoocCourse> newCourses = getNewMoocCourses();

        //相关课程
        List<MoocCourse> list = moocCourseRepository.findAll();
        List<MoocCourse> relateCourses = new ArrayList<MoocCourse>();
        for(MoocCourse m : list){
            if(m.getSchool() ==null || m.getCate() == null || m.getPlatform() == null || m.getLang() == null){
                continue;
            }
            if(m.getSchool().equals(course.getSchool()) || m.getCate().equals(course.getCate()) || m.getPlatform().equals(course.getPlatform()) || m.getLang().equals(course.getLang())){
                relateCourses.add(m);
            }
        }

        initImgSrcAndHerf(relateCourses);

        model.addAttribute("course",course);
        model.addAttribute("newCourses",newCourses);
        model.addAttribute("relateCourses",relateCourses.subList(0,8));

        addAttribute(model);
        addStatmentAndTitle(model);

        return new ModelAndView("mk/article");
    }

    /**
     * 文章详情
     **/
    @RequestMapping("article")
    public ModelAndView articles(Model model) {
        return new ModelAndView("mk/article");
    }

    /**
     * 课程搜索
     **/
    @RequestMapping("/search")
    public ModelAndView search(Model model,String s) {
        List<MoocCourse> courses = moocCourseRepository.findByCourseContentLike(s);
        initImgSrcAndHerf(courses);

        Integer nowPage = 1;
        Integer pageNums =  0;
        if(!CollectionUtils.isEmpty(courses)){
            pageNums = getPageNums(courses);
        }
        String pageStr = getPageContentStr(nowPage,pageNums);

        List<MoocCourse> views = getMoocCoursesViews(courses, nowPage, pageNums);

        String sb = getCourseContentStr(views);

        StringBuilder contentStr = new StringBuilder();
        contentStr.append(sb).append("<span hidden=\"hidden\" id=\"search\">").append(s).append("</span>");

        //最新课程
        Page<MoocCourse> newCourses = getNewMoocCourses();
        //好课汇
        List<MoocCourse> hotCourses = getHotCourses();

        model.addAttribute("courseContent",contentStr.toString());
        model.addAttribute("pageMessage",pageStr);
        model.addAttribute("moocCourses",courses);
        model.addAttribute("newCourses",newCourses);
        model.addAttribute("hotCourses",hotCourses);


        addAttribute(model);

        addStatmentAndTitle(model);
        return new ModelAndView("mk/list");
    }

    private void addAttribute(Model model) {
        model.addAttribute("homeUrl","http://"+host+":3300/mk/home");
        model.addAttribute("hotPortalImage","http://"+host+":1008/moocCourse/imgSrc/logo.png");
        model.addAttribute("searchUrl","http://"+host+":3300/mk/search");
        model.addAttribute("schoolResourcesUrl","http://"+host+":3300/mk/schoolResources");
        model.addAttribute("listUrl","http://"+host+":3300/mk/list");
    }

    private Integer getPageNums(List<MoocCourse> courses) {
        Integer pageNums;
        Integer remainder  = courses.size() % 20;
        if(remainder == 0 ){
            pageNums = courses.size()/20;
        }else{
            pageNums = courses.size()/20 + 1;
        }
        return pageNums;
    }

    private List<MoocCourse> getHotCourses() {
        List<MoocCourse> hotCourses = moocCourseRepository.findAll();
        Collections.sort(hotCourses);
        List<MoocCourse> list = hotCourses.subList(0,4);
        initImgSrcAndHerf(list);
        return list;
    }

    private void initImgSrcAndHerf(List<MoocCourse> list) {
        for(MoocCourse m : list){
            m.setImgSrc( "http://"+host+":1008/moocCourse/imgSrc/" + m.getImgSrc().substring(m.getImgSrc().lastIndexOf("/")+1));
            m.setHerf("http://"+host+":3300/mk/list/" + m.getId());
        }
    }

    private Page<MoocCourse> getNewMoocCourses() {
        Page<MoocCourse> newCourses = moocCourseRepository.findAll(new PageRequest(0, 4));
        for (MoocCourse m : newCourses) {
            m.setImgSrc( "http://"+host+":1008/moocCourse/imgSrc/" + m.getImgSrc().substring(m.getImgSrc().lastIndexOf("/") + 1));
            m.setHerf("http://"+host+":3300/mk/list/" + m.getId());
        }
        return newCourses;
    }

    @RequestMapping("/list/page/*")
    public ModelAndView listPage(Model model,HttpServletRequest request,String cate,String school,String platform,String lang,String corder) {

        String url = request.getRequestURL().toString();
        String sss = request.getQueryString();
        if(!StringUtils.isEmpty(sss)){
            url = url + "?" + sss;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年    MM月dd日");
        List<MoocCourse> moocCourses = moocCourseRepository.findAll();
        for(MoocCourse m : moocCourses){
            m.setImgSrc( "http://"+host+":1008/moocCourse/imgSrc/" + m.getImgSrc().substring(m.getImgSrc().lastIndexOf("/")+1));
            m.setHerf("http://"+host+":3300/mk/list/" + m.getId());
        }

        if(!StringUtils.isEmpty(cate)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            while(iter.hasNext()){
                MoocCourse s = iter.next();
                if(!StringUtils.isEmpty(s.getCate())){
                    if(!s.getCate().contains(cate)){
                        iter.remove();
                    }
                }else {
                    iter.remove();
                }
            }
        }if(!StringUtils.isEmpty(school)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            while(iter.hasNext()){
                MoocCourse s = iter.next();
                if(!StringUtils.isEmpty(s.getSchool())){
                    if(!s.getSchool().contains(school)){
                        iter.remove();
                    }
                }else {
                    iter.remove();
                }
            }
        }
        if(!StringUtils.isEmpty(platform)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            while(iter.hasNext()){
                MoocCourse s = iter.next();
                if(!StringUtils.isEmpty(s.getPlatform())){
                    if(!s.getPlatform().contains(platform)){
                        iter.remove();
                    }
                }else {
                    iter.remove();
                }
            }
        }
        if(!StringUtils.isEmpty(lang)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            if("其他".equals(lang)){
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(s.getLang().contains("中文")||s.getLang().contains("英语")||s.getLang().contains("法语")){
                        iter.remove();
                    }
                }
            }else{
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(!StringUtils.isEmpty(s.getLang())){
                        if(!s.getLang().contains(lang)){
                            iter.remove();
                        }
                    }else {
                        iter.remove();
                    }
                }
            }

        }

        if(!StringUtils.isEmpty(corder)){
            if("sdate".equals(corder)){
                Collections.sort(moocCourses);
            }else if("views".equals(corder)){
               // moocCourses.sort(MoocCourse::compareViews);

                MyCompartor mc = new MyCompartor();

                Collections.sort(moocCourses,mc);
            }

        }


        //当前页
        Integer nowPage = 1;
        if(url.contains("page")){
            if(url.contains("?")){
                nowPage = Integer.valueOf(url.substring(url.indexOf("page/")+5,url.indexOf("?")));
            }else{
                nowPage = Integer.valueOf(url.substring(url.indexOf("page/")+5));
            }

        }
        Integer pageNums =  0;
        if(!CollectionUtils.isEmpty(moocCourses)){
            pageNums = getPageNums(moocCourses);
        }
        StringBuilder pageStr = new StringBuilder();

        if(! (pageNums <=  1)){
            pageStr.append(prePagemessage); //最前面的
            String firstPageUrl = url;
            if(nowPage >1 ){
                String preUrl = url.substring(0,url.indexOf("page/")+5);
                String subUrl = "";
                if(url.contains("?")){
                    firstPageUrl  = url.replace(url.substring(url.indexOf("/page/"),url.indexOf("?")),"");
                    subUrl = url.substring(url.indexOf("?"));
                }else{
                    firstPageUrl  = url.replace(url.substring(url.indexOf("/page/")),"");
                }
                pageStr.append(lastPageMessage.replace("lastHerf",preUrl + String.valueOf(nowPage - 1 ) + subUrl));//上一页
            }
            //第一页
            if(nowPage > 1){
                pageStr.append(firstPageMessage.replace("firstHerf",firstPageUrl));
            }

            if(nowPage >= 4){
                pageStr.append(devicePageMessage);
            }

            //上一页
            if(nowPage >= 3){

                String preUrl = url.substring(0,url.indexOf("page/")+5);
                String subUrl = "";
                if(url.contains("?")){
                    subUrl = url.substring(url.indexOf("?"));
                }
                String lastHerf = lastPageMessage1.replace("lastHerf",preUrl + String.valueOf(nowPage - 1 ) + subUrl).replace("lastPage",String.valueOf(nowPage - 1));
                pageStr.append(lastHerf);
            }
            //当前页
            pageStr.append(currentPageMessage.replace("nowPage",String.valueOf(nowPage)));

            //下一页
            if(pageNums > nowPage){
                String nestHerf = "";
                if(nowPage > 1){
                    String preUrl = url.substring(0,url.indexOf("page/")+5);
                    String subUrl = "";
                    if(url.contains("?")){
                        subUrl = url.substring(url.indexOf("?"));
                    }

                    nestHerf = nestPageMessage.replace("nestHerf",preUrl + String.valueOf(nowPage + 1 ) + subUrl).replace("nestPage",String.valueOf(nowPage + 1));
                }else{
                    nestHerf = nestPageMessage.replace("nestHerf",url.replace("/mk/list","/mk/list/page/2")).replace("nestPage",String.valueOf(2));
                }
                pageStr.append(nestHerf);
            }

            if(pageNums - nowPage >2){
                pageStr.append(devicePageMessage);
            }

            //最后一页
            if(pageNums - nowPage >= 2){
                String preUrl = url.substring(0,url.indexOf("page/")+5);
                String subUrl = "";
                if(url.contains("?")){
                    subUrl = url.substring(url.indexOf("?"));
                }

                String totalHerf = totalPageMessage.replace("totalHerf",preUrl + String.valueOf(pageNums) + subUrl).replace("totalPage",String.valueOf(pageNums));
                pageStr.append(totalHerf);
            }

            //下一页
            if(pageNums - nowPage >= 1){
                String nestHerf = "";
                if(nowPage > 1){
                    String preUrl = url.substring(0,url.indexOf("page/")+5);
                    String subUrl = "";
                    if(url.contains("?")){
                        subUrl = url.substring(url.indexOf("?"));
                    }
                    nestHerf = nestPageMessage1.replace("nestHerf",preUrl + String.valueOf(nowPage + 1 ) + subUrl).replace("nestPage",String.valueOf(nowPage + 1));
                }else{
                    nestHerf = nestPageMessage1.replace("nestHerf",url.replace("/mk/list","/mk/list/page/2")).replace("nestPage",String.valueOf(2));
                }
                pageStr.append(nestHerf);
            }


            pageStr.append(subPageMessage);

        }

        //最新课程
        Page<MoocCourse> newCourses = getNewMoocCourses();

        //好课汇
        List<MoocCourse> hotCourses = getHotCourses();


        List<MoocCourse> views = new ArrayList<MoocCourse>();
        Integer start = 0;
        Integer end = 19;
        start += 20*(nowPage-1) ;
        end += 20*(nowPage-1);
        if(!CollectionUtils.isEmpty(moocCourses)){
            if(moocCourses.size()< end){
                end = moocCourses.size();
            }
            if(nowPage != pageNums){
                views = moocCourses.subList(start,end);
            }else{
                views = moocCourses.subList(start,moocCourses.size());
            }
        }


        model.addAttribute("pageMessage",pageStr.toString());
        model.addAttribute("moocCourses",views);
        model.addAttribute("newCourses",newCourses);
        model.addAttribute("hotCourses",hotCourses);
        addStatmentAndTitle(model);
        return new ModelAndView("mk/list");
    }




    @RequestMapping("/getCourseContent")
    @ResponseBody
    public CourseResponse getCourseContent(Model model,HttpServletRequest request,String cate,String school,String platform,String lang,String corder,String ctime,String pageNow,String search) {

        //分页功能
        String url = request.getRequestURL().toString();  //请求路径
        String sss = request.getQueryString();            //参数



        if(!StringUtils.isEmpty(sss)){
            url = url + "?" + sss;
        }

        if(!StringUtils.isEmpty(sss)){

        }
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年    MM月dd日");

        List<MoocCourse> moocCourses = new ArrayList<MoocCourse>();
        if(StringUtils.isEmpty(search)){
             moocCourses = moocCourseRepository.findAll();
        }else{
            moocCourses = moocCourseRepository.findByCourseContentLike(search);
        }


        initImgSrcAndHerf(moocCourses);
        /*for(MoocCourse m : moocCourses){
            m.setImgSrc(imgSrcPreffic + m.getImgSrc().substring(m.getImgSrc().lastIndexOf("/")+1));
            m.setHerf("http://192.168.10.49:3300/mk/list/" + m.getId());
        }*/

        if(!StringUtils.isEmpty(cate) && !"全部".equals(cate)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            while(iter.hasNext()){
                MoocCourse s = iter.next();
                if(!StringUtils.isEmpty(s.getCate())){
                    if(!s.getCate().contains(cate)){
                        iter.remove();
                    }
                }else {
                    iter.remove();
                }
            }
        }

        if(!StringUtils.isEmpty(school) && !"全部".equals(school)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            if("其他".equals(school)){
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(!StringUtils.isEmpty(s.getSchool()) && StringUtils.isEmpty(s.getSchool().replace("耶鲁大学","").replace("哈佛大学","").replace("麻省理工学院","")
                            .replace("斯坦福大学","").replace("华盛顿大学","").replace("普林斯顿大学","").replace("杜克大学","").replace("宾夕法尼亚大学","").replace("美国西北大学","")
                            .replace("清华大学","").replace("国立台湾大学","").trim())){
                        iter.remove();
                    }
                }
            }else{
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(!StringUtils.isEmpty(s.getSchool())){
                        if("国立台大".equals(school)){
                            if(!s.getSchool().contains("国立台")){
                                iter.remove();
                            }
                        }else{
                            if(!s.getSchool().contains(school)){
                                iter.remove();
                            }
                        }
                    }else {
                        iter.remove();
                    }
                }
            }
        }

        if(!StringUtils.isEmpty(platform) && !"全部".equals(platform)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            if("其他".equals(platform)){
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(!StringUtils.isEmpty(s.getPlatform()) && StringUtils.isEmpty(s.getPlatform().replace("中国大学MOOC","").replace("网易云课堂","")
                            .replace("学堂在线","").replace("华文慕课","").replace("顶你学堂","").replace("Coursera","").replace("edX","")
                            .replace("Udacity","").replace("FutureLearn","").trim())){
                        iter.remove();
                    }
                }
            }else{
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(!StringUtils.isEmpty(s.getPlatform())){
                        if(!s.getPlatform().contains(platform)){
                            iter.remove();
                        }
                    }else {
                        iter.remove();
                    }
                }
            }
        }

        if(!StringUtils.isEmpty(lang) && !"全部".equals(lang)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            if("其他".equals(lang)){
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(!StringUtils.isEmpty(s.getLang()) && StringUtils.isEmpty(s.getLang().replace("中文","").replace("英语","").replace("法语","").trim())){
                            iter.remove();
                    }
                }
            }else{
                while(iter.hasNext()){
                    MoocCourse s = iter.next();
                    if(!StringUtils.isEmpty(s.getLang())){
                        if(!s.getLang().contains(lang)){
                            iter.remove();
                        }
                    }else {
                        iter.remove();
                    }
                }
            }

        }

        if(!StringUtils.isEmpty(ctime)  && !"全部".equals(ctime)){
            Iterator<MoocCourse> iter = moocCourses.iterator();
            Date date  = new Date();
            String dateStr = sdf.format(date);

            if("今天".equals(ctime)) {
                while (iter.hasNext()) {
                    MoocCourse s = iter.next();
                    if (!dateStr.equals(s.getSchool())) {
                        iter.remove();
                    }
                }
            }else if("未来7天".equals(ctime)){
                while (iter.hasNext()) {
                    MoocCourse s = iter.next();
                    if ("任何时间".equals(s.getSdate())){
                        iter.remove();
                    }else if ("暂无开课".equals(s.getSdate())){
                        iter.remove();
                    }else{
                        Long time = s.getStartDate().getTime() - date.getTime();
                        if(time < 0 || time > 7*24*3600*1000 ){
                            iter.remove();
                        }
                    }

                }
            }else if("过去7天".equals(ctime)){
                while (iter.hasNext()) {
                    MoocCourse s = iter.next();
                    if ("任何时间".equals(s.getSdate())){
                        iter.remove();
                    }else if ("暂无开课".equals(s.getSdate())){
                        iter.remove();
                    }else {
                        Long time =date.getTime() -s.getStartDate().getTime();
                        if(time <0 || time > 7*24*3600*1000 ){
                            iter.remove();
                        }
                    }
                }

            }else if("定期开课".equals(ctime)){
                while (iter.hasNext()) {
                    MoocCourse s = iter.next();
                    if ("任何时间".equals(s.getSdate())){
                        iter.remove();
                    }
                    if ("暂无开课".equals(s.getSdate())){
                        iter.remove();
                    }
                }
            }else if("自主学习".equals(ctime)){
                while (iter.hasNext()) {
                    MoocCourse s = iter.next();
                    if (!"任何时间".equals(s.getSdate())){
                        iter.remove();
                    }
                }
            }else if("暂无开课".equals(ctime)){
                while (iter.hasNext()) {
                    MoocCourse s = iter.next();
                    if (!"暂无开课".equals(s.getSdate())) {
                        iter.remove();
                    }
                }
            }


        }


        if(!StringUtils.isEmpty(corder)  && !"最新发布".equals(cate)){
            if("开课时间".equals(corder)){
                Collections.sort(moocCourses);
            }else if("热度".equals(corder)){
                //moocCourses.sort(MoocCourse::compareViews);

                MyCompartor mc = new MyCompartor();

                Collections.sort(moocCourses,mc);
            }

        }

        //当前页
        Integer nowPage = Integer.valueOf(pageNow);
        Integer pageNums =  0;
        if(!CollectionUtils.isEmpty(moocCourses)){
            pageNums = getPageNums(moocCourses);
        }
        String pageStr = getPageContentStr(nowPage, pageNums);


        List<MoocCourse> views = getMoocCoursesViews(moocCourses, nowPage, pageNums);


        String sb = getCourseContentStr(views);

        StringBuilder contentStr = new StringBuilder();
        contentStr.append(sb).append("<span hidden=\"hidden\" id=\"search\">").append(search).append("</span>");


        CourseResponse c = new CourseResponse();
        c.setCourseContent(contentStr.toString());
        c.setPageContent(pageStr);
        return c;
    }

    private List<MoocCourse> getMoocCoursesViews(List<MoocCourse> moocCourses, Integer nowPage, Integer pageNums) {
        List<MoocCourse> views = new ArrayList<MoocCourse>();
        Integer start = 0;
        Integer end = 20;
        start += 20*(nowPage-1) ;
        end += 20*(nowPage-1);
        if(!CollectionUtils.isEmpty(moocCourses)){
            MoocCompartorId mc = new MoocCompartorId();

            Collections.sort(moocCourses,mc);

            if(moocCourses.size()< end){
                end = moocCourses.size();
            }
            if(nowPage != pageNums){
                views = moocCourses.subList(start,end);
            }else{
                views = moocCourses.subList(start,moocCourses.size());
            }
        }
        return views;
    }

    private String getPageContentStr(Integer nowPage, Integer pageNums) {
        StringBuilder pageStr = new StringBuilder();

        if(! (pageNums <=  1)){
            pageStr.append(prePagemessage); //最前面的
            if(nowPage >1 ){
                pageStr.append(lastPageMessage.replace("pageNum",String.valueOf(nowPage - 1 )));//上一页
            }
            //第一页
            if(nowPage > 1){
                pageStr.append(firstPageMessage.replace("pageNum","1"));
            }

            if(nowPage >= 4){
                pageStr.append(devicePageMessage);
            }

            //上一页
            if(nowPage >= 3){
                String lastHerf = lastPageMessage1.replace("pageNum",String.valueOf(nowPage - 1 )).replace("lastPage",String.valueOf(nowPage - 1));
                pageStr.append(lastHerf);
            }
            //当前页
            pageStr.append(currentPageMessage.replace("nowPage",String.valueOf(nowPage)).replace("pageNum",String.valueOf(nowPage)));

            //下一页
            if(pageNums > nowPage){
                String nestHerf = "";
                if(nowPage > 1){
                    nestHerf = nestPageMessage.replace("pageNum",String.valueOf(nowPage + 1 )).replace("nestPage",String.valueOf(nowPage + 1));
                }else{
                    nestHerf = nestPageMessage.replace("pageNum","2").replace("nestPage",String.valueOf(2));
                }
                pageStr.append(nestHerf);
            }

            if(pageNums - nowPage >2){
                pageStr.append(devicePageMessage);
            }

        //最后一页
        if(pageNums - nowPage >= 2){
            String totalHerf =  totalPageMessage.replace("pageNum", String.valueOf(pageNums) ).replace("totalPage",String.valueOf(pageNums));
            pageStr.append(totalHerf);
        }

        //下一页
        if(pageNums - nowPage >= 1){
            String nestHerf = "";
            if(nowPage > 1){
                nestHerf = nestPageMessage1.replace("pageNum",String.valueOf(nowPage + 1 )).replace("nestPage",String.valueOf(nowPage + 1));
            }else{
                nestHerf = nestPageMessage1.replace("pageNum","2").replace("nestPage",String.valueOf(2));
            }
            pageStr.append(nestHerf);
        }
        pageStr.append(subPageMessage);

        }
        return pageStr.toString();
    }

    private String getCourseContentStr(List<MoocCourse> views) {
        StringBuilder sb = new StringBuilder();
        for(MoocCourse m : views){
            sb.append("<div class=\"course-list\">\n" +
                    "\n" +
                    "                <div class=\"course-listimg\">\n" +
                    "                    <a target=\"_blank\" href=\""+ m.getHerf() +"\" title=\""+m.getTitle()+" \"><img width=\"180\" height=\"101\" src=\""+m.getImgSrc()+
                    "\" class=\"attachment-medium size-medium wp-post-image\" alt=\"" +m.getTitle() +"\" /></a>\n" +
                    "                </div>\n" +
                    "                <div class=\"inner\">\n" +
                    "                    <h1 class=\"courselist-title\" >\n" +
                    "                        <a target=\"_blank\" href=\""+ m.getHerf() +"\" title=\""+m.getTitle()+"\">"+m.getTitle()+"</a></h1>\n" +
                    "                    <div class=\"course_enname\">"+m.getEname()+"</div>\n" +
                    "                    <div class=\"courselist-content\">\n" +
                    "                        <h2>"+m.getContent()+"</h2>\n" +
                    "                        <div class=\"courselist-meta\">\n" +
                    "                            <span class=\"course-nm\">"+m.getSubhead1()+"</span>\n" +
                    "                            <span class=\"course-nm\">"+m.getSubhead2()+"</span>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <div class=\"courselist-date\">\n" +
                    "                    <a target=\"_blank\" href=\""+m.getHerf()+"\" title=\""+m.getTitle()+"\">\n" +
                    "                "+m.getSdate()+" </a></div>\n" +
                    "            </div>");
        }
        return sb.toString();
    }

    @RequestMapping("/schoolResources")
    public ModelAndView schoolResources(Model model,HttpServletRequest request) {
        List<SchoolResources> schoolResources = schoolResourcesRepository.findAll();

        for(SchoolResources h : schoolResources ){
            h.setImgSrc( "http://"+host+":1008/moocCourse/imgSrc/" + h.getImgSrc().substring(h.getImgSrc().lastIndexOf("/")+1));
        }

        model.addAttribute("schoolResources",schoolResources);
        addAttribute(model);
        model.addAttribute("headUrl","style=\"background-image: url(http://"+host+":1008/moocCourse/imgSrc/head3.jpg);\"");
        addStatmentAndTitle(model);
        return new ModelAndView("mk/schoolResources");
    }



}
