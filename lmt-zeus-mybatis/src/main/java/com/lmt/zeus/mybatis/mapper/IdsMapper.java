package com.lmt.zeus.mybatis.mapper;

import com.lmt.zeus.mybatis.provider.IdsProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @description 根据id列表操作
 *
 * @author bazhandao
 * @date 2019/11/15 10:31
 * @since JDK1.8
 */
@RegisterMapper
public interface IdsMapper<T> {

    /**
     * 根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
     *
     * @param ids
     * @return
     */
    @SelectProvider(type = IdsProvider.class, method = "dynamicSQL")
    List<T> selectByIds(List<?> ids);

    /**
     * 根据主键字符串进行删除，类中只有存在一个带有@Id注解的字段
     *
     * @param ids
     * @return
     */
    @DeleteProvider(type = IdsProvider.class, method = "dynamicSQL")
    int deleteByIds(List<?> ids);
}
