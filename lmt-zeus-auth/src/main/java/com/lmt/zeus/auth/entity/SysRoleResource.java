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
 * @description 角色资源表
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:35:24
 * @since JDK 1.8
 */
@Setter
@Getter
@ToString(callSuper = true)
@Table(name = "lmt_sys_role_resource")
public class SysRoleResource extends Entity {

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
     * 角色id
    */
    @Column(name = "role_id")
    private Long roleId;

        
    /**
     * 资源id
    */
    @Column(name = "resource_id")
    private Long resourceId;

        
    /**
     * 创建时间
    */
    @Column(name = "created_time")
    private Date createdTime;

    
}
