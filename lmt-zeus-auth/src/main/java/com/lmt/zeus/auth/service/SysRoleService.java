/**
 * Generate by lmt-code-generator 2019-11-07 16:57:56
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service;

import com.lmt.zeus.auth.entity.SysRole;
import java.util.List;

/**
 * @description SysRoleService服务接口定义
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:57:56
 * @since JDK1.8
 */
public interface SysRoleService {

    /**
     * 根据主键查寻
     * @param id
     * @return
    */
    public SysRole get(Long id);

    /**
     * 新增
     * @param sysRole
    */
    public void insert(SysRole sysRole);

    /**
     * 批量插入
     * @param sysRoleList
     */
    public void insertList(List<SysRole> sysRoleList);

    /**
     * 根据主键更新
     * @param sysRole
    */
    public void update(SysRole sysRole);

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
     * @param sysRole
    */
    public void saveOrUpdate(SysRole sysRole);

    /**
     * 根据主键列表查寻列表
     * @param ids
     * @return
     */
    public List<SysRole> listByIds(List<Long> ids);


    /**
     * 根据主键数组查寻
     * @param ids
     * @return
     */
    public List<SysRole> listByIds(Long [] ids);

    /**
     * 根据条件统计
     * @param sysRole
     * @return
     */
    public int count(SysRole sysRole);


    /**
     * 获取所有数据，返回列表
     * @return
     */
    public List<SysUser> listAll();

    /**
     * 根据条件查寻列表
     * @param sysRole
     * @return
     */
    public List<SysRole> query(SysRole sysRole);

    /**
     * 根据条件查寻唯一结果
     * @param sysRole
     * @return
     */
    public SysRole queryOne(SysRole sysRole);

    /**
     * 根据指定字段排序查询列表
     * @param sysRole
     * @param sortField
     * @param sortOrder
     * @return
     */
    public List<SysRole> queryOrderBy(SysRole sysRole, String sortField, String sortOrder);

}
