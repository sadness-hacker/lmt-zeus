package com.lmt.zeus.mybatis.mapper;

import com.lmt.zeus.mybatis.provider.QueryOrderByProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @description 实现根据条件查寻并根据指定字段排序接口
 * @author bazhandao
 * @date 2018/12/10 22:46
 * @since JDK1.8
 */
@RegisterMapper
public interface QueryOrderByMapper<T> {

    /**
     * 实现根据条件查寻并排序功能
     * @author bazhandao
     * @date 2018-12-10
     * @param entity
     * @param sortField
     * @param sortOrder asc or desc 默认值asc
     * @return
     */
    @SelectProvider(type = QueryOrderByProvider.class, method = "dynamicSQL")
    List<T> queryOrderBy(@Param(value = "entity") T entity, @Param(value = "sortField") String sortField, @Param(value = "sortOrder") String sortOrder);

}
