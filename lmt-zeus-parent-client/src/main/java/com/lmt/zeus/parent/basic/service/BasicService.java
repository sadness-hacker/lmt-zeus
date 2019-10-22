package com.lmt.zeus.parent.basic.service;

import com.github.pagehelper.PageInfo;
import com.lmt.zeus.parent.entity.Entity;
import com.lmt.zeus.parent.basic.mapper.BasicMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @description 基础Service接口
 * @author bazhandao
 * @date 2018/11/9 10:56
 * @since JDK1.8
 */
public interface BasicService<T extends Entity, PK extends Serializable> {

    /**
     * @description 获取该service注入的对应的mapper
     * @return
     */
    public BasicMapper<T> getBasicMapper();

    /**
     * @description 插入entity到数据表
     * @param entity
     * @return int
     */
    public int insert(T entity);

    /**
     * @description 批量插入entity到数据表
     * @param entityList
     * @return int
     */
    public int batchInsert(List<T> entityList);

    /**
     * @description 根据主键pk查寻entity实体
     * @return AaCode
     */
    public T get(PK pk);

    /**
     * @description 根据主键pk列表查寻AaCode实体列表
     * @param pkList
     * @return List<T>
     */
    public List<T> listByPKList(List<PK> pkList);

    /**
     * @description 根据主键pk删除记录
     * @return int
     */
    public int deleteByPk(PK pk);

    /**
     * @description 根据实体删除记录
     * @param entity 不能所有字段都为空，否则sql执行出错
     * @return int
     */
    public int delete(T entity);

    /**
     * @description 根据主键pk列表删除记录
     * @param pkList
     * @return int
     */
    public int deleteByPKList(List<PK> pkList);

    /**
     * @description 根据主键id更新记录
     * @param entity    主键id字段不能为空
     * @return int
     */
    public int update(T entity);


    /**
     * @description 根据主实体统计记录数
     * @param aaCode
     * @return int
     */
    public long count(T aaCode);

    /**
     * @description 根据主实体查询记录
     * @param entity
     * @return int
     */
    public List<T> query(T entity);

    /**
     * @description 根据主实体查询记录，根据指定的字段进行排序
     * @param aaCode
     * @param sortField 排序字段(可空)
     * @param sortOrder asc或desc(可空)
     * @return int
     */
    public List<T> queryOrderBy(T aaCode, String sortField, String sortOrder);

    /**
     * 更新或保存
     * @param entity
     * @return
     */
    public int saveOrUpdate(T entity);

    /**
     * 分页查寻
     * @param entity
     * @param sortField  排序字段名
     * @param sortOrder  排序顺序
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @return
     */
    public PageInfo<T> queryByPage(T entity, String sortField, String sortOrder, int pageNum, int pageSize);


}
