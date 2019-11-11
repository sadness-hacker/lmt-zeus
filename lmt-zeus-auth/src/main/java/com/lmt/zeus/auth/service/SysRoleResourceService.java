/**
 * Generate by lmt-code-generator 2019-11-07 16:57:57
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service;

import com.lmt.zeus.auth.entity.SysRoleResource;
import java.util.List;

/**
 * @description SysRoleResourceService服务接口定义
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:57:57
 * @since JDK1.8
 */
public interface SysRoleResourceService {

    /**
     * 根据主键查寻
     * @param id
     * @return
    */
    public SysRoleResource get(Long id);

    /**
     * 新增
     * @param sysRoleResource
    */
    public void insert(SysRoleResource sysRoleResource);

    /**
     * 批量插入
     * @param sysRoleResourceList
     */
    public void insertList(List<SysRoleResource> sysRoleResourceList);

    /**
     * 根据主键更新
     * @param sysRoleResource
    */
    public void update(SysRoleResource sysRoleResource);

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
     * @param sysRoleResource
    */
    public void saveOrUpdate(SysRoleResource sysRoleResource);

    /**
     * 根据主键列表查寻列表
     * @param ids
     * @return
     */
    public List<SysRoleResource> listByIds(List<Long> ids);


    /**
     * 根据主键数组查寻
     * @param ids
     * @return
     */
    public List<SysRoleResource> listByIds(Long [] ids);

    /**
     * 根据条件统计
     * @param sysRoleResource
     * @return
     */
    public int count(SysRoleResource sysRoleResource);


    /**
     * 获取所有数据，返回列表
     * @return
     */
    public List<SysUser> listAll();

    /**
     * 根据条件查寻列表
     * @param sysRoleResource
     * @return
     */
    public List<SysRoleResource> query(SysRoleResource sysRoleResource);

    /**
     * 根据条件查寻唯一结果
     * @param sysRoleResource
     * @return
     */
    public SysRoleResource queryOne(SysRoleResource sysRoleResource);

    /**
     * 根据指定字段排序查询列表
     * @param sysRoleResource
     * @param sortField
     * @param sortOrder
     * @return
     */
    public List<SysRoleResource> queryOrderBy(SysRoleResource sysRoleResource, String sortField, String sortOrder);

}
