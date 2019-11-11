/**
 * Generate by lmt-code-generator @date 2019-11-07 17:00:46
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service.impl;

import com.lmt.zeus.auth.entity.SysRoleResource;
import com.lmt.zeus.auth.mapper.SysRoleResourceMapper;
import com.lmt.zeus.auth.service.SysRoleResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description SysRoleResourceServiceImpl服务实现
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 17:00:46
 * @since JDK1.8
 */
@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    /**
     * 根据主键查寻
     *
     * @param id
     * @return
     */
    @Override
    public SysRoleResource get(Long id) {
        return sysRoleResourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param sysRoleResource
     */
    @Override
    public void insert(SysRoleResource sysRoleResource) {
        sysRoleResourceMapper.insertSelective(sysRoleResource);
    }

    /**
     * 批量插入
     *
     * @param sysRoleResourceList
     */
    @Override
    public void insertList(List<SysRoleResource> sysRoleResourceList) {
        sysRoleResourceMapper.insertList(sysRoleResourceList);
    }

    /**
     * 根据主键更新
     *
     * @param sysRoleResource
     */
    @Override
    public void update(SysRoleResource sysRoleResource) {
        sysRoleResourceMapper.updateByPrimaryKeySelective(sysRoleResource);
    }

    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        sysRoleResourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键列表删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Long> ids) {
        sysRoleResourceMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 根据主键数组删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(Long[] ids) {
        sysRoleResourceMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 保存或更新
     *
     * @param sysRoleResource
     */
    @Override
    public void saveOrUpdate(SysRoleResource sysRoleResource) {
        if (sysRoleResource.getId() == null || get(sysRoleResource.getId()) == null) {
            insert(sysRoleResource);
        } else {
            update(sysRoleResource);
        }
    }

    /**
     * 根据id查寻列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysRoleResource> listByIds(List<Long> ids) {
        return sysRoleResourceMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据id数组查寻
     * @param ids
     * @return
     */
    @Override
    public List<SysRoleResource> listByIds(Long [] ids) {
        return sysRoleResourceMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据条件统计
     *
     * @param sysRoleResource
     * @return
     */
    @Override
    public int count(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.selectCount(sysRoleResource);
    }

    /**
     * 获取所有数据，返回列表
     *
     * @return
     */
    @Override
    public List<SysRoleResource> listAll() {
        return sysRoleResourceMapper.selectAll();
    }

    /**
     * 根据条件查寻列表
     *
     * @param sysRoleResource
     * @return
     */
    @Override
    public List<SysRoleResource> query(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.select(sysRoleResource);
    }

    /**
     * 根据条件查寻唯一结果
     *
     * @param sysRoleResource
     * @return
     */
    @Override
    public SysRoleResource queryOne(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.selectOne(sysRoleResource);
    }

    /**
     * 根据指定字段排序查询列表
     *
     * @param sysRoleResource
     * @param sortField
     * @param sortOrder
     * @return
     */
    @Override
    public List<SysRoleResource> queryOrderBy(SysRoleResource sysRoleResource, String sortField, String sortOrder) {
        return sysRoleResourceMapper.queryOrderBy(sysRoleResource, sortField, sortOrder);
    }

}
