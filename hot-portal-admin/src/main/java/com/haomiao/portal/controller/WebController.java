package com.haomiao.portal.controller;

import com.haomiao.portal.domain.*;
import com.haomiao.portal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @author LJH
 */
@Controller
@RequestMapping("")
public class WebController {
    @Autowired
    VideoPojoRepository videoPojoRepository;
    @Autowired
    TabulationRepository tabulationRepository;
    @Autowired
    VideoRankRepository videoRankRepository;
    @Autowired
    VideoDetailRepository videoDetailRepository;
    @Autowired
    VideoUrlRepository videoUrlRepository;
    @Autowired
    VarietyUrlRepository varietyUrlRepository;

    /**
     * 404 主页
     **/
    @RequestMapping("/error_404.php")
    public ModelAndView error_404(Model model) {
        return new ModelAndView("Error/error_404");
    }

    /**
     * 搜索 主页
     **/
    @RequestMapping(value = "/web/search.php")
    public ModelAndView search(Model model, @RequestParam(required = false, defaultValue = "") String searchStr) {
        return new ModelAndView("Web/videos_search");
    }

    /**
     * web 主页
     **/
    @RequestMapping("/web/index.php")
    public ModelAndView index(Model model) {
        Page<VideoRankPojo> allHotNotice = videoRankRepository.findAll(new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "score")));
        List<VideoRankPojo> movieHotNotice = videoRankRepository.findByCat(1, new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "score")));
        List<VideoRankPojo> tvHotNotice = videoRankRepository.findByCat(2, new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "score")));
        List<VideoRankPojo> varietyHotNotice = videoRankRepository.findByCat(3, new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "score")));
        List<VideoRankPojo> cartoonHotNotice = videoRankRepository.findByCat(4, new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "score")));
        model.addAttribute("allHotNotice", allHotNotice);
        model.addAttribute("tvHotNotice", tvHotNotice);
        model.addAttribute("movieHotNotice", movieHotNotice);
        model.addAttribute("varietyHotNotice", varietyHotNotice);
        model.addAttribute("cartoonHotNotice", cartoonHotNotice);
        List<VideoPojo> tvHotList = videoPojoRepository.findByTid(3, new PageRequest(0, 4));
        List<VideoPojo> wsList = videoPojoRepository.findByTid(406, new PageRequest(0, 4));
        List<VideoPojo> bzList = videoPojoRepository.findByTid(6, new PageRequest(0, 4));
        List<VideoPojo> usList = videoPojoRepository.findByTid(8, new PageRequest(0, 4));
        List<VideoPojo> twList = videoPojoRepository.findByTid(92, new PageRequest(0, 4));
        List<VideoPojo> ryList = videoPojoRepository.findByTid(9, new PageRequest(0, 4));
        List<VideoPojo> ukList = videoPojoRepository.findByTid(10, new PageRequest(0, 4));
        model.addAttribute("tvHotList", tvHotList);
        model.addAttribute("wsList", wsList);
        model.addAttribute("bzList", bzList);
        model.addAttribute("usList", usList);
        model.addAttribute("twList", twList);
        model.addAttribute("ryList", ryList);
        model.addAttribute("ukList", ukList);
        List<VideoPojo> movHotList = videoPojoRepository.findByTid(22, new PageRequest(0, 4));
        List<VideoPojo> happyList = videoPojoRepository.findByTid(23, new PageRequest(0, 4));
        List<VideoPojo> actionList = videoPojoRepository.findByTid(20, new PageRequest(0, 4));
        List<VideoPojo> loveList = videoPojoRepository.findByTid(24, new PageRequest(0, 4));
        List<VideoPojo> scienceList = videoPojoRepository.findByTid(26, new PageRequest(0, 4));
        List<VideoPojo> caseList = videoPojoRepository.findByTid(94, new PageRequest(0, 4));
        model.addAttribute("movHotList", movHotList);
        model.addAttribute("happyList", happyList);
        model.addAttribute("actionList", actionList);
        model.addAttribute("loveList", loveList);
        model.addAttribute("scienceList", scienceList);
        model.addAttribute("caseList", caseList);
        List<VideoPojo> wpVarietyList = videoPojoRepository.findByTid(11, new PageRequest(0, 4));
        List<VideoPojo> dlVarietyList = videoPojoRepository.findByTid(159, new PageRequest(0, 4));
        List<VideoPojo> gtVarietyList = videoPojoRepository.findByTid(12, new PageRequest(0, 4));
        List<VideoPojo> hwVarietyList = videoPojoRepository.findByTid(13, new PageRequest(0, 4));
        model.addAttribute("wpVarietyList", wpVarietyList);
        model.addAttribute("dlVarietyList", dlVarietyList);
        model.addAttribute("gtVarietyList", gtVarietyList);
        model.addAttribute("hwVarietyList", hwVarietyList);
        List<VideoPojo> cartoonHotList = videoPojoRepository.findByTid(18, new PageRequest(0, 4));
        List<VideoPojo> lzCartoonList = videoPojoRepository.findByTid(21, new PageRequest(0, 4));
        List<VideoPojo> youngCartoonList = videoPojoRepository.findByTid(101, new PageRequest(0, 4));
        List<VideoPojo> gcCartoonList = videoPojoRepository.findByTid(19, new PageRequest(0, 4));
        List<VideoPojo> jpCartoonList = videoPojoRepository.findByTid(25, new PageRequest(0, 4));
        List<VideoPojo> usCartoonList = videoPojoRepository.findByTid(110, new PageRequest(0, 4));
        model.addAttribute("cartoonHotList", cartoonHotList);
        model.addAttribute("lzCartoonList", lzCartoonList);
        model.addAttribute("youngCartoonList", youngCartoonList);
        model.addAttribute("gcCartoonList", gcCartoonList);
        model.addAttribute("jpCartoonList", jpCartoonList);
        model.addAttribute("usCartoonList", usCartoonList);
        return new ModelAndView("Web/videos_index");
    }

    /**
     * web 电影
     **/
    @RequestMapping("/web/movie.php")
    public ModelAndView movie(Model model) {
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(1);
        model.addAttribute("hotNotice", hotNotice);
        List<VideoPojo> hotList = videoPojoRepository.findByTid(22, new PageRequest(0, 12));
        List<VideoPojo> happyList = videoPojoRepository.findByTid(23, new PageRequest(0, 4));
        List<VideoPojo> actionList = videoPojoRepository.findByTid(20, new PageRequest(0, 4));
        List<VideoPojo> loveList = videoPojoRepository.findByTid(24, new PageRequest(0, 4));
        List<VideoPojo> scienceList = videoPojoRepository.findByTid(26, new PageRequest(0, 4));
        List<VideoPojo> caseList = videoPojoRepository.findByTid(94, new PageRequest(0, 4));
        List<VideoPojo> scaryList = videoPojoRepository.findByTid(93, new PageRequest(0, 4));
        List<VideoPojo> magicList = videoPojoRepository.findByTid(130, new PageRequest(0, 4));
        List<VideoPojo> warList = videoPojoRepository.findByTid(132, new PageRequest(0, 4));
        model.addAttribute("hotList", hotList);
        model.addAttribute("happyList", happyList);
        model.addAttribute("actionList", actionList);
        model.addAttribute("loveList", loveList);
        model.addAttribute("scienceList", scienceList);
        model.addAttribute("caseList", caseList);
        model.addAttribute("scaryList", scaryList);
        model.addAttribute("magicList", magicList);
        model.addAttribute("warList", warList);
        List<VideoPojo> ZhouHotList = videoPojoRepository.findByCatAndActorContaining(1, "周星驰", new PageRequest(0, 10));
        List<VideoPojo> LiuHotList = videoPojoRepository.findByCatAndActorContaining(1, "刘德华", new PageRequest(0, 10));
        List<VideoPojo> LiHotList = videoPojoRepository.findByCatAndActorContaining(1, "李连杰", new PageRequest(0, 10));
        model.addAttribute("ZhouHotList", ZhouHotList);
        model.addAttribute("LiuHotList", LiuHotList);
        model.addAttribute("LiHotList", LiHotList);
        return new ModelAndView("Web/videos_movie");
    }

    /**
     * web 电视剧
     **/
    @RequestMapping("/web/tv.php")
    public ModelAndView tv(Model model) {
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(2);
        model.addAttribute("hotNotice", hotNotice);
        List<VideoPojo> hotList = videoPojoRepository.findByTid(3, new PageRequest(0, 12));
        List<VideoPojo> wsList = videoPojoRepository.findByTid(406, new PageRequest(0, 12));
        List<VideoPojo> bzList = videoPojoRepository.findByTid(6, new PageRequest(0, 12));
        List<VideoPojo> usList = videoPojoRepository.findByTid(8, new PageRequest(0, 12));
        List<VideoPojo> twList = videoPojoRepository.findByTid(92, new PageRequest(0, 12));
        List<VideoPojo> ryList = videoPojoRepository.findByTid(9, new PageRequest(0, 12));
        List<VideoPojo> ukList = videoPojoRepository.findByTid(10, new PageRequest(0, 12));
        model.addAttribute("hotList", hotList);
        model.addAttribute("wsList", wsList);
        model.addAttribute("bzList", bzList);
        model.addAttribute("usList", usList);
        model.addAttribute("twList", twList);
        model.addAttribute("ryList", ryList);
        model.addAttribute("ukList", ukList);
        return new ModelAndView("Web/videos_tv");
    }

    /**
     * web 综艺
     **/
    @RequestMapping("/web/variety.php")
    public ModelAndView variety(Model model) {
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(3);
        model.addAttribute("hotNotice", hotNotice);
        List<VideoPojo> wpList = videoPojoRepository.findByTid(11, new PageRequest(0, 12));
        List<VideoPojo> dlList = videoPojoRepository.findByTid(159, new PageRequest(0, 12));
        List<VideoPojo> gtList = videoPojoRepository.findByTid(12, new PageRequest(0, 12));
        List<VideoPojo> hwList = videoPojoRepository.findByTid(13, new PageRequest(0, 12));
        model.addAttribute("wpList", wpList);
        model.addAttribute("dlList", dlList);
        model.addAttribute("gtList", gtList);
        model.addAttribute("hwList", hwList);
        return new ModelAndView("Web/videos_variety");
    }

    /***************************** 综艺类型列表（结束） ***************************************/
    /**
     * web 动漫
     **/
    @RequestMapping("/web/cartoon.php")
    public ModelAndView cartoon(Model model) {
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(4);
        List<VideoPojo> japanNotice = videoPojoRepository.findByCatAndAreaContaining(4, "日本", new PageRequest(0, 10));
        List<VideoPojo> dlNotice = videoPojoRepository.findByCatAndAreaContaining(4, "大陆", new PageRequest(0, 10));
        List<VideoPojo> usNotice = videoPojoRepository.findByCatAndAreaContaining(4, "美国", new PageRequest(0, 10));
        model.addAttribute("hotNotice", hotNotice);
        model.addAttribute("japanNotice", japanNotice);
        model.addAttribute("dlNotice", dlNotice);
        model.addAttribute("usNotice", usNotice);
        List<VideoPojo> hotList = videoPojoRepository.findByTid(18, new PageRequest(0, 12));
        List<VideoPojo> lzList = videoPojoRepository.findByTid(21, new PageRequest(0, 12));
        List<VideoPojo> youngList = videoPojoRepository.findByTid(101, new PageRequest(0, 12));
        List<VideoPojo> gcList = videoPojoRepository.findByTid(19, new PageRequest(0, 12));
        List<VideoPojo> jpList = videoPojoRepository.findByTid(25, new PageRequest(0, 12));
        List<VideoPojo> usList = videoPojoRepository.findByTid(110, new PageRequest(0, 12));
        List<VideoPojo> movieList = videoPojoRepository.findByTid(27, new PageRequest(0, 12));
        model.addAttribute("hotList", hotList);
        model.addAttribute("lzList", lzList);
        model.addAttribute("youngList", youngList);
        model.addAttribute("gcList", gcList);
        model.addAttribute("jpList", jpList);
        model.addAttribute("usList", usList);
        model.addAttribute("movieList", movieList);
        return new ModelAndView("Web/videos_cartoon");
    }

    /**
     * web 播放页 ---电影
     *
     * @throws Exception
     **/
    @RequestMapping("/web/play_movie.php")
    public ModelAndView play_movie(Model model, @RequestParam(required = false, defaultValue = "1") String video_head_id) throws Exception {
        VideoDetailPojo entity = videoDetailRepository.findOne(video_head_id);
        //TODO
        model.addAttribute("entity", entity);
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(1);
        model.addAttribute("hotNotice", hotNotice);
        return new ModelAndView("Web/videos_play_movie");
    }

    /**
     * web 播放页--电视剧
     *
     * @throws Exception
     **/
    @RequestMapping("/web/play_tv.php")
    public ModelAndView play_tv(Model model, @RequestParam(required = false, defaultValue = "40") String video_head_id) throws Exception {
        VideoDetailPojo entity = videoDetailRepository.findOne(video_head_id);
        String word = entity.getWord();
        if (word.length() >= 250) {
            entity.setWord(word.substring(0, 190) + "......");
        }
        VideoUrlPojo urlEntity = videoUrlRepository.findOne(video_head_id);
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(2);
        model.addAttribute("entity", entity);
        model.addAttribute("urlEntity", urlEntity);
        model.addAttribute("hotNotice", hotNotice);
        return new ModelAndView("Web/videos_play_tv");
    }

    /**
     * web 播放页--综艺
     *
     * @throws Exception
     **/
    @RequestMapping("/web/play_variety.php")
    public ModelAndView play_variety(Model model, @RequestParam(required = false, defaultValue = "40") String video_head_id) throws Exception {
        VideoDetailPojo entity = videoDetailRepository.findOne(video_head_id);
        String word = entity.getWord();
        if (word.length() >= 250) {
            entity.setWord(word.substring(0, 190) + "......");
        }
        VarietyUrlPojo urlEntity = varietyUrlRepository.findOne(video_head_id);
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(3);
        model.addAttribute("entity", entity);
        model.addAttribute("urlEntity", urlEntity);
        model.addAttribute("hotNotice", hotNotice);
        return new ModelAndView("Web/videos_play_variety");
    }

    /**
     * web 播放页 ---动漫
     *
     * @throws Exception
     **/
    @RequestMapping("/web/play_cartoon.php")
    public ModelAndView play_cartoon(Model model, @RequestParam(required = false, defaultValue = "1") String video_head_id) throws Exception {
        VideoDetailPojo entity = videoDetailRepository.findOne(video_head_id);
        String word = entity.getWord();
        if (word.length() >= 250) {
            entity.setWord(word.substring(0, 190) + "......");
        }
        VideoUrlPojo urlEntity = videoUrlRepository.findOne(video_head_id);
        List<VideoRankPojo> hotNotice = videoRankRepository.findByCat(4);
        List<VideoPojo> japanNotice = videoPojoRepository.findByCatAndAreaContaining(4, "日本", new PageRequest(0, 10));
        List<VideoPojo> dlNotice = videoPojoRepository.findByCatAndAreaContaining(4, "大陆", new PageRequest(0, 10));
        List<VideoPojo> usNotice = videoPojoRepository.findByCatAndAreaContaining(4, "美国", new PageRequest(0, 10));
        model.addAttribute("entity", entity);
        model.addAttribute("urlEntity", urlEntity);
        model.addAttribute("hotNotice", hotNotice);
        model.addAttribute("japanNotice", japanNotice);
        model.addAttribute("dlNotice", dlNotice);
        model.addAttribute("usNotice", usNotice);
        return new ModelAndView("Web/videos_play_cartoon");
    }

    /**
     * web 列表
     **/
    @SuppressWarnings("unchecked")
    @RequestMapping("/web/list.php")
    public ModelAndView videoList(
            Model model,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @RequestParam(required = false, defaultValue = "movie") String navCode,
            @RequestParam(required = false, defaultValue = "all") String areaCode,
            @RequestParam(required = false, defaultValue = "all") String typeCode,
            @RequestParam(required = false, defaultValue = "all") String yearCode,
            @RequestParam(required = false, defaultValue = "all") String actorCode) {

        return new ModelAndView("Web/videos_list");
    }
}
