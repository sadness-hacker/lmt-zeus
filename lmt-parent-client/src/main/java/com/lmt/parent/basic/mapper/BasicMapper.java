package com.lmt.parent.basic.mapper;

import com.lmt.parent.basic.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * @description 基础Mapper接口
 * @author bazhandao
 * @date 2018/11/9 10:38
 * @since JDK1.8
 */
public interface BasicMapper<T extends Entity, PK extends Serializable> {
    /**
     * @description 插入实体到数据表
     * @param entity
     * @return int 插入成功的数量
     */
    public int insert(T entity);

    /**
     * @description 批量插入entity到数据表
     * @param entityList
     * @return int 插入成功的数量
     */
    public int batchInsert(List<T> entityList);


    /**
     * @description 根据主键pk查寻entity实体
     * @param pk 主键
     *
     * @return T
     */
    public T get(PK pk);


    /**
     * @description 根据主键pk列表查寻entity实体列表
     * @param pkList
     * @return List<T>
     */
    public List<T> listByPKList(List<PK> pkList);

    /**
     * @description 根据主键pk删除记录
     * @param pk (主键值，非空)
     * @return int 删除成功的记录数
     */
    public int deleteByPk(PK pk);

    /**
     * @description 根据实体删除记录
     * @param entity 不能所有字段都为空，否则sql执行出错
     * @return int 删除成功的记录数
     */
    public int delete(T entity);

    /**
     * @description 根据主键列表删除记录
     * @param pkList
     * @return int 删除成功的记录数
     */
    public int deleteByPKList(List<PK> pkList);

    /**
     * @description 根据主键pk更新记录
     * @param entity 主键pk字段不能为空
     * @return int 更新成功的记录数
     */
    public int update(T entity);


    /**
     * @description 根据主实体统计记录数
     * @param entity
     * @return int
     */
    public long count(T entity);

    /**
     * @description 根据主实体查询记录
     * @param entity
     * @return List<T>
     */
    public List<T> query(T entity);

    /**
     * @description 根据主实体查询记录，根据指定的字段进行排序
     * @param entity
     * @param sortField 排序字段名（字段名或对应的java实体属性名）
     * @param sortOrder asc或desc
     * @return List<T>
     */
    public List<T> queryOrderBy(T entity, String sortField, String sortOrder);
}
