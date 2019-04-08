package com.lmt.parent.basic.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.lmt.parent.basic.entity.Entity;
import com.lmt.parent.client.enums.BasicExceptionEnum;
import com.lmt.parent.client.exception.BasicException;
import com.lmt.parent.lang.id.IdGenerator;
import com.lmt.parent.basic.mapper.BasicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * @description 基础Service类
 * @author bazhandao
 * @date 2018/11/9 10:52
 * @since JDK1.8
 */
public class BasicServiceImpl<T extends Entity,PK extends Serializable> implements BasicService<T, PK>{

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IdGenerator idGenerator;

    private BasicMapper basicMapper;

    private Class<T> entityClass;

    public BasicServiceImpl() {
        //获取当前泛型类
        Type type = this.getClass().getGenericSuperclass();
        if (type.toString().indexOf("BasicService") != -1) {
            ParameterizedType type1 = (ParameterizedType) type;
            Type[] types = type1.getActualTypeArguments();
            entityClass = (Class<T>) types[0];
        }else{
            type = ((Class<T>)type).getGenericSuperclass();
            ParameterizedType type1 = (ParameterizedType) type;
            Type[] types = type1.getActualTypeArguments();
            entityClass = (Class<T>) types[0];
        }
    }

    /**
     * @description 获取该service注入的对应的mapper
     * @return
     */
    @Override
    public BasicMapper<T> getBasicMapper() {
        if (basicMapper == null) {
            String name = entityClass.getSimpleName() + "Mapper";
            String first = String.valueOf(name.charAt(0));
            name = name.replaceFirst(first, first.toLowerCase());
            basicMapper = applicationContext.getBean(name, BasicMapper.class);
        }
        return basicMapper;
    }

    /**
     * @description 插入entity到数据表
     * @param entity
     * @return int
     *
     */
    @Override
    public int insert(T entity) {
        // id为null时设置id
        if (getPkValue(entity) == null && idGenerator != null) {
            setPkValue(entity, (PK) idGenerator.generatorId());
        }
        return getBasicMapper().insert(entity);
    }

    /**
     * @description 批量插入entity到数据表
     * @param entityList
     * @return int
     */
    @Override
    public int batchInsert(List<T> entityList) {
        return getBasicMapper().insertList(entityList);
    }

    /**
     * @description 根据主键pk查寻entity实体
     * @param pk
     * @return T
     */
    @Override
    public T get(PK pk) {
        return getBasicMapper().selectByPrimaryKey(pk);
    }

    /**
     * @description 根据主键id列表查寻AaImport实体列表
     * @param pkList
     * @return List<AaImport>
     *
     */
    @Override
    public List<T> listByPKList(List<PK> pkList) {
        return getBasicMapper().selectByIds(Joiner.on(",").join(pkList));
    }

    /**
     * @description 根据主键删除记录
     * @param pk
     * @return int
     */
    @Override
    public int deleteByPk(PK pk) {
        return getBasicMapper().deleteByPrimaryKey(pk);
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
        return getBasicMapper().deleteByIds(Joiner.on(",").join(pkList));
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
        return getBasicMapper().updateByPrimaryKeySelective(entity);
    }

    /**
     * @description 根据主实体统计记录数
     * @param entity
     * @return long
     */
    @Override
    public long count(T entity) {
        return getBasicMapper().selectCount(entity);
    }

    /**
     * @description 根据主实体查询记录
     * @param entity
     * @return List<T>
     */
    @Override
    public List<T> query(T entity) {
        return getBasicMapper().select(entity);
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
     * 更新或保存
     *
     * @param entity
     * @return
     */
    @Override
    public int saveOrUpdate(T entity) {
        Object id = getPkValue(entity);
        if (id == null || get((PK) id) == null) {
            return insert(entity);
        } else {
            return update(entity);
        }
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
        return new PageInfo<>(list);
    }

    /**
     * 反射获取pk值,仅支持唯一主键,不支持多pk
     * @author bazhandao
     * @date 2019-04-08
     * @param entity
     * @return
     */
    private Object getPkValue(T entity) {
        try {
            Set<EntityColumn> pkColumns = EntityHelper.getEntityTable(entityClass).getEntityClassPKColumns();
            for (EntityColumn c : pkColumns) {
                return c.getEntityField().getValue(entity);
            }
        } catch (Exception e) {
            throw BasicException.wrap(BasicExceptionEnum.ENTITY_ID_REFLECT_GETTER_ERROR.getCode(), BasicExceptionEnum.ENTITY_ID_REFLECT_GETTER_ERROR.getMsg(), e);
        }
        return null;
    }

    /**
     * 反射设置id字段,仅支持唯一主键,不支持多pk
     * @author bazhandao
     * @date 2019-04-08
     * @param entity
     * @param id
     */
    private void setPkValue(T entity, PK id) {
        try {
            Set<EntityColumn> pkColumns = EntityHelper.getEntityTable(entityClass).getEntityClassPKColumns();
            for (EntityColumn c : pkColumns) {
                Field field = entityClass.getDeclaredField(c.getEntityField().getName());
                field.setAccessible(true);
                field.set(entity, id);
                return;
            }
        } catch (Exception e) {
            throw BasicException.wrap(BasicExceptionEnum.ENTITY_ID_REFLECT_SETTER_ERROR.getCode(), BasicExceptionEnum.ENTITY_ID_REFLECT_GETTER_ERROR.getMsg(), e);
        }
    }


}
