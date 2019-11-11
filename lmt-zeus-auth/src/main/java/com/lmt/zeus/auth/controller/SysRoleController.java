/**
 * Generate by lmt-code-generator 2019-11-07 16:18:55
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.controller;

import com.lmt.zeus.auth.entity.SysRole;
import com.lmt.zeus.auth.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description SysRoleController
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:18:55
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/api/sys/auth/sysrole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

}
