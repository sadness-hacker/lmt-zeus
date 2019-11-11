/**
 * Generate by lmt-code-generator @date 2019-11-07 17:00:46
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service.impl;

import com.lmt.zeus.auth.entity.SysUserRole;
import com.lmt.zeus.auth.mapper.SysUserRoleMapper;
import com.lmt.zeus.auth.service.SysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description SysUserRoleServiceImpl服务实现
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 17:00:46
 * @since JDK1.8
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据主键查寻
     *
     * @param id
     * @return
     */
    @Override
    public SysUserRole get(Long id) {
        return sysUserRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param sysUserRole
     */
    @Override
    public void insert(SysUserRole sysUserRole) {
        sysUserRoleMapper.insertSelective(sysUserRole);
    }

    /**
     * 批量插入
     *
     * @param sysUserRoleList
     */
    @Override
    public void insertList(List<SysUserRole> sysUserRoleList) {
        sysUserRoleMapper.insertList(sysUserRoleList);
    }

    /**
     * 根据主键更新
     *
     * @param sysUserRole
     */
    @Override
    public void update(SysUserRole sysUserRole) {
        sysUserRoleMapper.updateByPrimaryKeySelective(sysUserRole);
    }

    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        sysUserRoleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键列表删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Long> ids) {
        sysUserRoleMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 根据主键数组删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(Long[] ids) {
        sysUserRoleMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 保存或更新
     *
     * @param sysUserRole
     */
    @Override
    public void saveOrUpdate(SysUserRole sysUserRole) {
        if (sysUserRole.getId() == null || get(sysUserRole.getId()) == null) {
            insert(sysUserRole);
        } else {
            update(sysUserRole);
        }
    }

    /**
     * 根据id查寻列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysUserRole> listByIds(List<Long> ids) {
        return sysUserRoleMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据id数组查寻
     * @param ids
     * @return
     */
    @Override
    public List<SysUserRole> listByIds(Long [] ids) {
        return sysUserRoleMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据条件统计
     *
     * @param sysUserRole
     * @return
     */
    @Override
    public int count(SysUserRole sysUserRole) {
        return sysUserRoleMapper.selectCount(sysUserRole);
    }

    /**
     * 获取所有数据，返回列表
     *
     * @return
     */
    @Override
    public List<SysUserRole> listAll() {
        return sysUserRoleMapper.selectAll();
    }

    /**
     * 根据条件查寻列表
     *
     * @param sysUserRole
     * @return
     */
    @Override
    public List<SysUserRole> query(SysUserRole sysUserRole) {
        return sysUserRoleMapper.select(sysUserRole);
    }

    /**
     * 根据条件查寻唯一结果
     *
     * @param sysUserRole
     * @return
     */
    @Override
    public SysUserRole queryOne(SysUserRole sysUserRole) {
        return sysUserRoleMapper.selectOne(sysUserRole);
    }

    /**
     * 根据指定字段排序查询列表
     *
     * @param sysUserRole
     * @param sortField
     * @param sortOrder
     * @return
     */
    @Override
    public List<SysUserRole> queryOrderBy(SysUserRole sysUserRole, String sortField, String sortOrder) {
        return sysUserRoleMapper.queryOrderBy(sysUserRole, sortField, sortOrder);
    }

}
