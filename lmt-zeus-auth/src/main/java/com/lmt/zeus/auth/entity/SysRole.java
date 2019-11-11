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
 * @description 角色表
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:35:24
 * @since JDK 1.8
 */
@Setter
@Getter
@ToString(callSuper = true)
@Table(name = "lmt_sys_role")
public class SysRole extends Entity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
    * 自增id
    */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    
    /**
     * 角色编码
    */
    @Column(name = "code")
    private String code;

        
    /**
     * 角色名称
    */
    @Column(name = "name")
    private String name;

        
    /**
     * 更新时间
    */
    @Column(name = "updated_time")
    private Date updatedTime;

        
    /**
     * 创建时间
    */
    @Column(name = "created_time")
    private Date createdTime;

    
}
