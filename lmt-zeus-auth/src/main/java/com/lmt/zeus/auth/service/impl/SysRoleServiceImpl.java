/**
 * Generate by lmt-code-generator @date 2019-11-07 17:00:46
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service.impl;

import com.lmt.zeus.auth.entity.SysRole;
import com.lmt.zeus.auth.mapper.SysRoleMapper;
import com.lmt.zeus.auth.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description SysRoleServiceImpl服务实现
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 17:00:46
 * @since JDK1.8
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据主键查寻
     *
     * @param id
     * @return
     */
    @Override
    public SysRole get(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param sysRole
     */
    @Override
    public void insert(SysRole sysRole) {
        sysRoleMapper.insertSelective(sysRole);
    }

    /**
     * 批量插入
     *
     * @param sysRoleList
     */
    @Override
    public void insertList(List<SysRole> sysRoleList) {
        sysRoleMapper.insertList(sysRoleList);
    }

    /**
     * 根据主键更新
     *
     * @param sysRole
     */
    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        sysRoleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键列表删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Long> ids) {
        sysRoleMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 根据主键数组删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(Long[] ids) {
        sysRoleMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 保存或更新
     *
     * @param sysRole
     */
    @Override
    public void saveOrUpdate(SysRole sysRole) {
        if (sysRole.getId() == null || get(sysRole.getId()) == null) {
            insert(sysRole);
        } else {
            update(sysRole);
        }
    }

    /**
     * 根据id查寻列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysRole> listByIds(List<Long> ids) {
        return sysRoleMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据id数组查寻
     * @param ids
     * @return
     */
    @Override
    public List<SysRole> listByIds(Long [] ids) {
        return sysRoleMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据条件统计
     *
     * @param sysRole
     * @return
     */
    @Override
    public int count(SysRole sysRole) {
        return sysRoleMapper.selectCount(sysRole);
    }

    /**
     * 获取所有数据，返回列表
     *
     * @return
     */
    @Override
    public List<SysRole> listAll() {
        return sysRoleMapper.selectAll();
    }

    /**
     * 根据条件查寻列表
     *
     * @param sysRole
     * @return
     */
    @Override
    public List<SysRole> query(SysRole sysRole) {
        return sysRoleMapper.select(sysRole);
    }

    /**
     * 根据条件查寻唯一结果
     *
     * @param sysRole
     * @return
     */
    @Override
    public SysRole queryOne(SysRole sysRole) {
        return sysRoleMapper.selectOne(sysRole);
    }

    /**
     * 根据指定字段排序查询列表
     *
     * @param sysRole
     * @param sortField
     * @param sortOrder
     * @return
     */
    @Override
    public List<SysRole> queryOrderBy(SysRole sysRole, String sortField, String sortOrder) {
        return sysRoleMapper.queryOrderBy(sysRole, sortField, sortOrder);
    }

}
