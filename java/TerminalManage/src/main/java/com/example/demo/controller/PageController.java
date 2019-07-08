package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:  跳转到连接页面
 */
@Controller
public class PageController {

    @RequestMapping(value = "page")
    public String gg(){
        return "wen";
    }
    
}
