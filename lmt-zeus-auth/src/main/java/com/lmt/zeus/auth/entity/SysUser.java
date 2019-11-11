/**
 * Generate by lmt-code-generator 2019-11-07 16:35:24
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.entity;

import com.lmt.zeus.parent.Entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description 用户表
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:35:24
 * @since JDK 1.8
 */
@Setter
@Getter
@ToString(callSuper = true)
@Table(name = "lmt_sys_user")
public class SysUser extends Entity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
    * 自增ID
    */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    
    /**
     * 登录用户名
    */
    @Column(name = "username")
    private String username;

        
    /**
     * 用户密码
    */
    @Column(name = "password")
    private String password;

        
    /**
     * 真实姓名
    */
    @Column(name = "realname")
    private String realname;

        
    /**
     * 手机号
    */
    @Column(name = "mobile")
    private String mobile;

        
    /**
     * 邮箱
    */
    @Column(name = "email")
    private String email;

        
    /**
     * 状态:0-未启用,1-启用,2-禁用
    */
    @Column(name = "status")
    private Integer status;

        
    /**
     * 创建时间
    */
    @Column(name = "created_time")
    private Date createdTime;

        
    /**
     * 更新时间
    */
    @Column(name = "updated_time")
    private Date updatedTime;

    
}
