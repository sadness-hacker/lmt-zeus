/**
 * Generate by lmt-code-generator 2019-11-07 16:57:56
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service;

import com.lmt.zeus.auth.entity.SysResource;
import java.util.List;

/**
 * @description SysResourceService服务接口定义
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 16:57:56
 * @since JDK1.8
 */
public interface SysResourceService {

    /**
     * 根据主键查寻
     * @param id
     * @return
    */
    public SysResource get(Long id);

    /**
     * 新增
     * @param sysResource
    */
    public void insert(SysResource sysResource);

    /**
     * 批量插入
     * @param sysResourceList
     */
    public void insertList(List<SysResource> sysResourceList);

    /**
     * 根据主键更新
     * @param sysResource
    */
    public void update(SysResource sysResource);

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
     * @param sysResource
    */
    public void saveOrUpdate(SysResource sysResource);

    /**
     * 根据主键列表查寻列表
     * @param ids
     * @return
     */
    public List<SysResource> listByIds(List<Long> ids);


    /**
     * 根据主键数组查寻
     * @param ids
     * @return
     */
    public List<SysResource> listByIds(Long [] ids);

    /**
     * 根据条件统计
     * @param sysResource
     * @return
     */
    public int count(SysResource sysResource);


    /**
     * 获取所有数据，返回列表
     * @return
     */
    public List<SysUser> listAll();

    /**
     * 根据条件查寻列表
     * @param sysResource
     * @return
     */
    public List<SysResource> query(SysResource sysResource);

    /**
     * 根据条件查寻唯一结果
     * @param sysResource
     * @return
     */
    public SysResource queryOne(SysResource sysResource);

    /**
     * 根据指定字段排序查询列表
     * @param sysResource
     * @param sortField
     * @param sortOrder
     * @return
     */
    public List<SysResource> queryOrderBy(SysResource sysResource, String sortField, String sortOrder);

}
