/**
 * Generate by lmt-code-generator 2019-11-07 16:57:57
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service;

import com.lmt.zeus.auth.entity.SysUser;
import java.util.List;

/**
 * @description SysUserService服务接口定义
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:57:57
 * @since JDK1.8
 */
public interface SysUserService {

    /**
     * 根据主键查寻
     * @param id
     * @return
    */
    public SysUser get(Long id);

    /**
     * 新增
     * @param sysUser
    */
    public void insert(SysUser sysUser);

    /**
     * 批量插入
     * @param sysUserList
     */
    public void insertList(List<SysUser> sysUserList);

    /**
     * 根据主键更新
     * @param sysUser
    */
    public void update(SysUser sysUser);

    /**
     * 根据主键删除
     * @param id
     */
    public void delete(Long id);

    /**
     * 根据主键列表删除
     * @param ids
     */
    public void deleteByIds(List<Long> ids);

    /**
     * 根据主键数组删除
     * @param ids
     */
    public void deleteByIds(Long [] ids);

    /**
     * 保存或更新
     * @param sysUser
    */
    public void saveOrUpdate(SysUser sysUser);

    /**
     * 根据主键列表查寻列表
     * @param ids
     * @return
     */
    public List<SysUser> listByIds(List<Long> ids);


    /**
     * 根据主键数组查寻
     * @param ids
     * @return
     */
    public List<SysUser> listByIds(Long [] ids);

    /**
     * 根据条件统计
     * @param sysUser
     * @return
     */
    public int count(SysUser sysUser);


    /**
     * 获取所有数据，返回列表
     * @return
     */
    public List<SysUser> listAll();

    /**
     * 根据条件查寻列表
     * @param sysUser
     * @return
     */
    public List<SysUser> query(SysUser sysUser);

    /**
     * 根据条件查寻唯一结果
     * @param sysUser
     * @return
     */
    public SysUser queryOne(SysUser sysUser);

    /**
     * 根据指定字段排序查询列表
     * @param sysUser
     * @param sortField
     * @param sortOrder
     * @return
     */
    public List<SysUser> queryOrderBy(SysUser sysUser, String sortField, String sortOrder);

}
