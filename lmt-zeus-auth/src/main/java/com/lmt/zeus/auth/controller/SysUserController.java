/**
 * Generate by lmt-code-generator 2019-11-07 16:18:55
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.controller;

import com.lmt.zeus.auth.entity.SysUser;
import com.lmt.zeus.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description SysUserController
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:18:55
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/api/sys/auth/sysuser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

}
