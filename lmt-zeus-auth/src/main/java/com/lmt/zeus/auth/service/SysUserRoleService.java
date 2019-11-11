/**
 * Generate by lmt-code-generator 2019-11-07 16:57:57
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service;

import com.lmt.zeus.auth.entity.SysUserRole;
import java.util.List;

/**
 * @description SysUserRoleService服务接口定义
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:57:57
 * @since JDK1.8
 */
public interface SysUserRoleService {

    /**
     * 根据主键查寻
     * @param id
     * @return
    */
    public SysUserRole get(Long id);

    /**
     * 新增
     * @param sysUserRole
    */
    public void insert(SysUserRole sysUserRole);

    /**
     * 批量插入
     * @param sysUserRoleList
     */
    public void insertList(List<SysUserRole> sysUserRoleList);

    /**
     * 根据主键更新
     * @param sysUserRole
    */
    public void update(SysUserRole sysUserRole);

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
     * @param sysUserRole
    */
    public void saveOrUpdate(SysUserRole sysUserRole);

    /**
     * 根据主键列表查寻列表
     * @param ids
     * @return
     */
    public List<SysUserRole> listByIds(List<Long> ids);


    /**
     * 根据主键数组查寻
     * @param ids
     * @return
     */
    public List<SysUserRole> listByIds(Long [] ids);

    /**
     * 根据条件统计
     * @param sysUserRole
     * @return
     */
    public int count(SysUserRole sysUserRole);


    /**
     * 获取所有数据，返回列表
     * @return
     */
    public List<SysUser> listAll();

    /**
     * 根据条件查寻列表
     * @param sysUserRole
     * @return
     */
    public List<SysUserRole> query(SysUserRole sysUserRole);

    /**
     * 根据条件查寻唯一结果
     * @param sysUserRole
     * @return
     */
    public SysUserRole queryOne(SysUserRole sysUserRole);

    /**
     * 根据指定字段排序查询列表
     * @param sysUserRole
     * @param sortField
     * @param sortOrder
     * @return
     */
    public List<SysUserRole> queryOrderBy(SysUserRole sysUserRole, String sortField, String sortOrder);

}
