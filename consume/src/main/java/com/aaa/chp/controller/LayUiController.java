package com.aaa.chp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zts on 2020/3/23.
 * https://blog.csdn.net/wulianzhazha/article/details/92209750
 */
@Controller
public class LayUiController {

    @GetMapping("layUi")
    public String index() {
        return "index.html";
    }

    //http://localhost:8088/layUi1
    @GetMapping("layUi1")
    public String index1() {
        return "index1111.html";
    }

    //http://localhost:8088/layUi2
    @GetMapping("layUi2")
    public String index2() {
        return "index2.html";
    }


}
