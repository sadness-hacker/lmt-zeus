package com.lmt.parent.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description for this class
 *
 * @author bazhandao
 * @date 2018/11/9 14:21
 * @since JDK1.8
 */
@Controller
@RequestMapping(value = "/t")
public class IndexController {

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index() {
        return "ok";
    }

}
