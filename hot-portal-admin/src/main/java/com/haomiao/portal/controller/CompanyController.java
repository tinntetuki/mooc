package com.haomiao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LJH
 */
@Controller
@RequestMapping("")
public class CompanyController {

    /**
     * 公司介绍
     **/
    @RequestMapping("web/abouts_detail.php")
    public ModelAndView abouts_detail(Model model) {
        model.addAttribute("aboutCode", "abouts_detail");
        return new ModelAndView("Web/abouts_detail");
    }

    /**
     * 意见反馈
     **/
    @RequestMapping("web/abouts_feedback.php")
    public ModelAndView abouts_feedback(Model model) {
        model.addAttribute("aboutCode", "abouts_feedback");
        return new ModelAndView("Web/abouts_feedback");
    }

    /**
     * 版权说明
     **/
    @RequestMapping("web/abouts_copyright.php")
    public ModelAndView abouts_copyright(Model model) {
        model.addAttribute("aboutCode", "abouts_copyright");
        return new ModelAndView("Web/abouts_copyright");
    }

    /**
     * 联系我们
     **/
    @RequestMapping("web/abouts_cooperation.php")
    public ModelAndView abouts_cooperation(Model model) {
        model.addAttribute("aboutCode", "abouts_cooperation");
        return new ModelAndView("Web/abouts_cooperation");
    }

    /**
     * 排行榜
     **/
    @RequestMapping("web/chart_top.php")
    public ModelAndView chart_top(Model model) {
        model.addAttribute("pageCode", "chat_top");
        return new ModelAndView("Web/videos_chart_top");
    }

    /**
     * 总排行版
     **/
    @RequestMapping("web/chart.php")
    public ModelAndView chart(Model model) {
        //model.addAttribute("pageCode", "chat_top");
        return new ModelAndView("Web/videos_chart");
    }
}
