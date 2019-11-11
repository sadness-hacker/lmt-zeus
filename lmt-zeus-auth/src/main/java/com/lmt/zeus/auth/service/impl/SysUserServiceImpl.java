/**
 * Generate by lmt-code-generator @date 2019-11-07 17:00:46
 * you can find lmt-code-generator project on github
 * please visit: https://github.com/sadness-hacker/lmt-code-generator
 *
 */
package com.lmt.zeus.auth.service.impl;

import com.lmt.zeus.auth.entity.SysUser;
import com.lmt.zeus.auth.mapper.SysUserMapper;
import com.lmt.zeus.auth.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description SysUserServiceImpl服务实现
 *
 * @author Generate by lmt-code-generator
 * @date 2019-11-07 17:00:46
 * @since JDK1.8
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据主键查寻
     *
     * @param id
     * @return
     */
    @Override
    public SysUser get(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param sysUser
     */
    @Override
    public void insert(SysUser sysUser) {
        sysUserMapper.insertSelective(sysUser);
    }

    /**
     * 批量插入
     *
     * @param sysUserList
     */
    @Override
    public void insertList(List<SysUser> sysUserList) {
        sysUserMapper.insertList(sysUserList);
    }

    /**
     * 根据主键更新
     *
     * @param sysUser
     */
    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        sysUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键列表删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Long> ids) {
        sysUserMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 根据主键数组删除
     *
     * @param ids
     */
    @Override
    public void deleteByIds(Long[] ids) {
        sysUserMapper.deleteByIds((StringUtils.join(ids, ",")));
    }

    /**
     * 保存或更新
     *
     * @param sysUser
     */
    @Override
    public void saveOrUpdate(SysUser sysUser) {
        if (sysUser.getId() == null || get(sysUser.getId()) == null) {
            insert(sysUser);
        } else {
            update(sysUser);
        }
    }

    /**
     * 根据id查寻列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysUser> listByIds(List<Long> ids) {
        return sysUserMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据id数组查寻
     * @param ids
     * @return
     */
    @Override
    public List<SysUser> listByIds(Long [] ids) {
        return sysUserMapper.selectByIds(StringUtils.join(ids, ","));
    }

    /**
     * 根据条件统计
     *
     * @param sysUser
     * @return
     */
    @Override
    public int count(SysUser sysUser) {
        return sysUserMapper.selectCount(sysUser);
    }

    /**
     * 获取所有数据，返回列表
     *
     * @return
     */
    @Override
    public List<SysUser> listAll() {
        return sysUserMapper.selectAll();
    }

    /**
     * 根据条件查寻列表
     *
     * @param sysUser
     * @return
     */
    @Override
    public List<SysUser> query(SysUser sysUser) {
        return sysUserMapper.select(sysUser);
    }

    /**
     * 根据条件查寻唯一结果
     *
     * @param sysUser
     * @return
     */
    @Override
    public SysUser queryOne(SysUser sysUser) {
        return sysUserMapper.selectOne(sysUser);
    }

    /**
     * 根据指定字段排序查询列表
     *
     * @param sysUser
     * @param sortField
     * @param sortOrder
     * @return
     */
    @Override
    public List<SysUser> queryOrderBy(SysUser sysUser, String sortField, String sortOrder) {
        return sysUserMapper.queryOrderBy(sysUser, sortField, sortOrder);
    }

}
