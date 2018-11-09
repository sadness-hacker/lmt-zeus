package com.lmt.parent.test.controller;

import com.lmt.parent.basic.controller.BasicController;
import com.lmt.parent.basic.service.BasicService;
import com.lmt.parent.test.entity.AaCode;
import com.lmt.parent.test.service.AaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @description for this class
 * @author bazhandao
 * @date 2018/11/9 12:03
 * @since JDK1.8
 */
@Controller
@RequestMapping(value = "/xxoo/aacode")
public class AaCodeController extends BasicController<AaCode, String> {

    @Autowired
    private AaCodeService aaCodeService;

    @Override
    public BasicService<AaCode, String> getBasicService() {
        return aaCodeService;
    }

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index() {
        return "OK";
    }

}
