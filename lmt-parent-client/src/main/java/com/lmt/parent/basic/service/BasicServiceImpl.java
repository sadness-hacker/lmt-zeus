package com.lmt.parent.basic.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmt.parent.basic.entity.Entity;
import com.lmt.parent.basic.mapper.BasicMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @description 基础Service类
 * @author bazhandao
 * @date 2018/11/9 10:52
 * @since JDK1.8
 */
public abstract class BasicServiceImpl<T extends Entity,PK extends Serializable> implements BasicService<T, PK>{

    /**
     * @description 获取该service注入的对应的mapper
     * @return
     */
    public abstract BasicMapper<T, PK> getBasicMapper();

    /**
     * @description 插入entity到数据表
     * @param entity
     * @return int
     *
     */
    @Override
    public int insert(T entity) {
        return getBasicMapper().insert(entity);
    }

    /**
     * @description 批量插入entity到数据表
     * @param entityList
     * @return int
     */
    @Override
    public int batchInsert(List<T> entityList) {
        return getBasicMapper().batchInsert(entityList);
    }

    /**
     * @description 根据主键pk查寻entity实体
     * @param pk
     * @return T
     */
    @Override
    public T get(PK pk) {
        return getBasicMapper().get(pk);
    }

    /**
     * @description 根据主键id列表查寻AaImport实体列表
     * @param pkList
     * @return List<AaImport>
     *
     */
    @Override
    public List<T> listByPKList(List<PK> pkList) {
        return getBasicMapper().listByPKList(pkList);
    }

    /**
     * @description 根据主键删除记录
     * @param pk
     * @return int
     */
    @Override
    public int deleteByPk(PK pk) {
        return getBasicMapper().deleteByPk(pk);
    }

    /**
     * @description 根据实体删除记录
     * @param entity
     * @return int
     */
    @Override
    public int delete(T entity) {
        return getBasicMapper().delete(entity);
    }

    /**
     * @description 根据主键列表删除记录
     * @param pkList
     * @return
     */
    @Override
    public int deleteByPKList(List<PK> pkList) {
        return getBasicMapper().deleteByPKList(pkList);
    }

    /**
     *
     * @description 根据主键id更新记录
     * @param entity (主键字段不能为空)
     * @return int
     *
     */
    @Override
    public int update(T entity) {
        return getBasicMapper().update(entity);
    }

    /**
     * @description 根据主实体统计记录数
     * @param entity
     * @return long
     */
    @Override
    public long count(T entity) {
        return getBasicMapper().count(entity);
    }

    /**
     * @description 根据主实体查询记录
     * @param entity
     * @return List<T>
     */
    @Override
    public List<T> query(T entity) {
        return getBasicMapper().query(entity);
    }

    /**
     * @description 根据主实体查询记录，根据指定的字段进行排序
     * @param entity
     * @param sortField 排序字段(可空)
     * @param sortOrder asc或desc(可空)
     * @return int
     */
    @Override
    public List<T> queryOrderBy(T entity, String sortField, String sortOrder) {
        return getBasicMapper().queryOrderBy(entity, sortField, sortOrder);
    }

    /**
     * 分页查寻
     * @param entity
     * @param sortField 排序字段名
     * @param sortOrder 排序顺序
     * @param pageNum   页面
     * @param pageSize  每页记录数
     * @return
     */
    @Override
    public PageInfo<T> queryByPage(T entity, String sortField, String sortOrder, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = queryOrderBy(entity, sortField, sortOrder);
        return new PageInfo<T>(list);
    }

}
