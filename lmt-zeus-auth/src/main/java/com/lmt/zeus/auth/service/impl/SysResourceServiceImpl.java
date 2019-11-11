/**
 * Generate by lmt-code-generator @date 2019-11-07 17:00:46
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service.impl;

import com.lmt.zeus.auth.entity.SysResource;
import com.lmt.zeus.auth.mapper.SysResourceMapper;
import com.lmt.zeus.auth.service.SysResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description SysResourceServiceImpl服务实现
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 17:00:46
 * @since JDK1.8
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 根据主键查寻
     *
     * @param id
     * @return
     */
    @Override
    public SysResource get(Long id) {
        return sysResourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param sysResource
     */
    @Override
    public void insert(SysResource sysResource) {
        sysResourceMapper.insertSelective(sysResource);
    }

    /**
     * 批量插入
     *
     * @param sysResourceList
     */
    @Override
    public void insertList(List<SysResource> sysResourceList) {
        sysResourceMapper.insertList(sysResourceList);
    }

    /**
     * 根据主键更新
     *
     * @param sysResource
     */
    @Override
    public void update(SysResource sysResource) {
        sysResourceMapper.updateByPrimaryKeySelective(sysResource);
    }

    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        sysResourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键列表删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Long> ids) {
        sysResourceMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 根据主键数组删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(Long[] ids) {
        sysResourceMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 保存或更新
     *
     * @param sysResource
     */
    @Override
    public void saveOrUpdate(SysResource sysResource) {
        if (sysResource.getId() == null || get(sysResource.getId()) == null) {
            insert(sysResource);
        } else {
            update(sysResource);
        }
    }

    /**
     * 根据id查寻列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysResource> listByIds(List<Long> ids) {
        return sysResourceMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据id数组查寻
     * @param ids
     * @return
     */
    @Override
    public List<SysResource> listByIds(Long [] ids) {
        return sysResourceMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据条件统计
     *
     * @param sysResource
     * @return
     */
    @Override
    public int count(SysResource sysResource) {
        return sysResourceMapper.selectCount(sysResource);
    }

    /**
     * 获取所有数据，返回列表
     *
     * @return
     */
    @Override
    public List<SysResource> listAll() {
        return sysResourceMapper.selectAll();
    }

    /**
     * 根据条件查寻列表
     *
     * @param sysResource
     * @return
     */
    @Override
    public List<SysResource> query(SysResource sysResource) {
        return sysResourceMapper.select(sysResource);
    }

    /**
     * 根据条件查寻唯一结果
     *
     * @param sysResource
     * @return
     */
    @Override
    public SysResource queryOne(SysResource sysResource) {
        return sysResourceMapper.selectOne(sysResource);
    }

    /**
     * 根据指定字段排序查询列表
     *
     * @param sysResource
     * @param sortField
     * @param sortOrder
     * @return
     */
    @Override
    public List<SysResource> queryOrderBy(SysResource sysResource, String sortField, String sortOrder) {
        return sysResourceMapper.queryOrderBy(sysResource, sortField, sortOrder);
    }

}
