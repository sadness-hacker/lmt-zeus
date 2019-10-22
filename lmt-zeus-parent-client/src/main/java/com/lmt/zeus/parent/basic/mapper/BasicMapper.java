package com.lmt.zeus.parent.basic.mapper;

import com.lmt.zeus.parent.mybatis.mapper.QueryOrderByMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @description 基础Mapper接口
 * @author bazhandao
 * @date 2018/11/9 10:38
 * @since JDK1.8
 */
public interface BasicMapper<T> extends Mapper<T>, IdsMapper<T>, InsertListMapper<T>, QueryOrderByMapper<T> {

}
