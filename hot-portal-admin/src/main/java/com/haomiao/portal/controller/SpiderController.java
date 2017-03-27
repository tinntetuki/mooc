package com.haomiao.portal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LJH on 2016-07-20.
 */
@RestController
public class SpiderController {

    @RequestMapping("/test")
    public String test() {
        return "succews";
    }
}
