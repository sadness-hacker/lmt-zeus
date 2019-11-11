/**
 * Generate by lmt-code-generator 2019-11-07 16:35:23
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
 * @description 资源表
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:35:23
 * @since JDK 1.8
 */
@Setter
@Getter
@ToString(callSuper = true)
@Table(name = "lmt_sys_resource")
public class SysResource extends Entity {

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
     * 资源编码
    */
    @Column(name = "code")
    private String code;

        
    /**
     * 资源名称
    */
    @Column(name = "name")
    private String name;

        
    /**
     * 资源类型编码
    */
    @Column(name = "type_code")
    private String typeCode;

        
    /**
     * 资源链接
    */
    @Column(name = "url")
    private String url;

        
    /**
     * 扩展属性
    */
    @Column(name = "ext")
    private String ext;

        
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
