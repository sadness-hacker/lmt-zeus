/**
 * Generate by lmt-code-generator 2019-11-07 16:18:54
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.controller;

import com.lmt.zeus.auth.entity.SysResource;
import com.lmt.zeus.auth.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description SysResourceController
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:18:54
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/api/sys/auth/sysresource")
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

}
