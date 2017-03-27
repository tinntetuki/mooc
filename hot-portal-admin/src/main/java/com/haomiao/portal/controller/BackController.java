package com.haomiao.portal.controller;

import com.haomiao.portal.util.Base64;
import com.haomiao.portal.util.JsonBean;
import com.haomiao.portal.util.RandomValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * 后台导航
 *
 * @author LJH
 */
@Controller
@RequestMapping("")
public class BackController {

    /**
     * 登陆
     **/
    @RequestMapping("/login.php")
    public ModelAndView login() {
        return new ModelAndView("Public/login");
    }

    /**
     * 验证码
     **/
    @RequestMapping("/image.php")
    public ModelAndView image() {
        return new ModelAndView("image");
    }

    /**
     * 验证码
     **/
    @RequestMapping("/createImage.php")
    public void createImage(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆方法
     **/
    @RequestMapping(value = "/doLogin.php", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonBean doLogin(@RequestParam("username") String username,
                     @RequestParam("password") String password,
                     @RequestParam("validateCode") String validateCode,
                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String codeKey = RandomValidateCode.RANDOMCODEKEY;
        String validateCodeSession = (String) request.getSession()
                .getAttribute(codeKey);
        if (validateCodeSession == null
                || !validateCodeSession.equalsIgnoreCase(validateCode)) {
            return new JsonBean("validateCode", false);
        }
        // 加密
        password = Base64.encode(password.getBytes());
        //user = videoServiceImpl.login(username, password);
        Object user = new Object();
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return new JsonBean("", true);
        } else {
            return new JsonBean("", false);
        }

    }

    @RequestMapping(value = "/logout.php", method = RequestMethod.GET)
    public String logout() {
        // 清除session
        /*Enumeration<String> em = getRequest().getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			getRequest().getSession().removeAttribute(
					em.nextElement().toString());
		}
		getRequest().getSession().invalidate();*/
        return "Public/login";
    }

    /**
     * 用户列表
     *
     * @throws UnsupportedEncodingException
     **/
    @RequestMapping("Video/user_index.php")
    public String Video_user_index(Model model,
                                   @RequestParam(required = false, defaultValue = "") String userName)
            throws UnsupportedEncodingException {

        return "User/index";
    }

    /**
     * 首页
     **/
    @RequestMapping("/Video/index.php")
    public ModelAndView index(HttpSession httpSession) {
        Object user = httpSession.getAttribute("user");
        if (user != null) {
            //this.getRequest().setAttribute("user", user);
            return new ModelAndView("index");
        } else {
            return new ModelAndView("Public/login");
        }

    }

    /**
     * 导航
     **/
    @RequestMapping("/Video/navigation_index.php")
    public String Navigation_index(
            Model model,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/navigation_index";
    }

    /**
     * 类型
     **/
    @RequestMapping("/Video/video_type_index.php")
    public String Video_type_index(
            Model model,
            @RequestParam(required = false, defaultValue = "0") Long nav_id,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String videoTypeName) {

        return "Video/video_type_index";
    }

    /**
     * 地区
     **/
    @RequestMapping("/Video/video_area_index.php")
    public String Video_area_index(
            Model model,
            @RequestParam(required = false, defaultValue = "0") Long nav_id,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/video_area_index";
    }

    /**
     * 年代
     **/
    @RequestMapping("/Video/video_year_index.php")
    public String Video_year_index(
            Model model,
            @RequestParam(required = false, defaultValue = "0") Long nav_id,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/video_year_index";
    }

    /**
     * 明星
     **/
    @RequestMapping("/Video/video_actor_index.php")
    public String Video_actor_index(
            Model model,
            @RequestParam(required = false, defaultValue = "0") Long nav_id,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/video_actor_index";
    }

    /**
     * 视频来源
     **/
    @RequestMapping("/Video/video_source_index.php")
    public String Source_index(
            Model model,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/video_source_index";
    }

    /**
     * 导航类型抓取
     **/
    @RequestMapping(value = "Video/getNavChildTypes.php", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonBean fetchNavChildTypes(@RequestParam("nav_id") Long nav_id) {

        return new JsonBean("" + nav_id, true);
    }

    /**
     * 视频抓取主页
     **/
    @RequestMapping(value = "Video/video_fetch_index.php")
    public String fetchVideosIndex(Model model) {

        return "Video/video_fetch_index";
    }

    /**
     * 电影列表主页
     **/
    @RequestMapping("/Video/video_list_index.php")
    public String Video_movielist_index(
            Model model,
            @RequestParam(required = false, defaultValue = "0") Long nav_id,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/video_list_index";
    }


    /**
     * 视频抓取
     **/
    @RequestMapping(value = "/Video/fetchVideosData.php", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonBean fetchVideosData(
            @RequestParam("nav_id") Long nav_id,
            @RequestParam(required = false, defaultValue = "cat=all") String videoType,
            @RequestParam(required = false, defaultValue = "") String nav_name,
            @RequestParam(required = false, defaultValue = "area=11") String videoArea,
            @RequestParam(required = false, defaultValue = "year=all") String videoYear,
            @RequestParam(required = false, defaultValue = "") String videoActor) {
        System.out.println("fetchVideosData");

        return new JsonBean();
    }

    /**
     * 热度视频类型
     **/
    @RequestMapping("/Video/video_hottype_index.php")
    public String videoHotType_index(
            Model model,
            @RequestParam(required = false, defaultValue = "-1") Long nav_id,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/video_hottype_index";
    }

    /**
     * 热点影视列表主页
     **/
    @RequestMapping("/Video/video_hotvideolist_index.php")
    public String Video_hotvideolist_index(Model model) {

        return "Video/video_hotvideolist_index";
    }

    @RequestMapping("/Video/video_addmovievideo_index.php")
    public String video_addMovieVideo_index(Model model) {

        return "Video/video_addmovievideo_index";
    }

    /******************************
     * 后台日志管理模块start
     **********************************/
    @RequestMapping("/Video/log_analyse_index.php")
    public String log_analyse_index(Model model,
                                    @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/log_analyse_index";
    }

    @RequestMapping(value = "/Video/uploadZip", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonBean uploadZip(
            @RequestParam(value = "addLoadDataSource", required = false) MultipartFile fileZip)
            throws Exception {

        return new JsonBean("添加成功", true);
    }


    @RequestMapping("/Video/log_showData_index.php")
    public String log_showData_index(Model model,
                                     @RequestParam(required = false, defaultValue = "") String startDate,
                                     @RequestParam(required = false, defaultValue = "") String endDate,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/log_showdata_index";
    }

    @RequestMapping("/Video/log_analyseLog_index.php")
    public String log_analyseLog_index(Model model,
                                       @RequestParam(required = false, defaultValue = "") String startLogDate,
                                       @RequestParam(required = false, defaultValue = "") String endLogDate,
                                       @RequestParam(required = false, defaultValue = "") String startHotDate,
                                       @RequestParam(required = false, defaultValue = "") String endHotDate,
                                       @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/log_analyseLog_index";
    }


/******************************后台日志管理模块end**********************************/
    /******************************
     * 前台意见反馈start
     **********************************/
    @RequestMapping("/web/suggestFeedBack.php")
    public
    @ResponseBody
    JsonBean suggestFeedBack(@RequestParam(required = false, defaultValue = "") String content,
                             @RequestParam(required = false, defaultValue = "") String contact,
                             HttpServletRequest request) {

        return new JsonBean("" + content, true);
    }


/******************************前台意见反馈end**********************************/
    /******************************
     * 后台反馈模块start
     ******************************/
    @RequestMapping("/Video/showFeedBack.php")
    public String showFeedBack(Model model,
                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        return "Video/feedback_showdata_index";
    }
/******************************后台反馈模块end******************************/
}
