package com.lmt.zeus.id.controller;

import com.lmt.zeus.id.snow.UidGenerator;
import com.lmt.zeus.parent.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description id生成器测试
 *
 * @author bazhandao
 * @date 2019/11/17 14:11
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/api")
public class TestController {

    @Autowired
    private UidGenerator uidGenerator;

    @RequestMapping(value = "/snowflake")
    public Result<List<Long>> snowflake() {
        Result<List<Long>> result = new Result<>();
        long start = System.currentTimeMillis();
        int max = 50000;
        List<Long> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            list.add(uidGenerator.getUID());
        }
        System.out.println(System.currentTimeMillis() - start);
        result.setData(list);
        return result;
    }

}
