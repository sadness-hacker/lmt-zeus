package com.lmt.zeus.parent.mybatis;

import com.lmt.zeus.parent.mybatis.mapper.QueryOrderByMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.example.SelectCountByExampleMapper;
import tk.mybatis.mapper.common.example.SelectOneByExampleMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @description 基础Mapper接口
 * @author bazhandao
 * @date 2018/11/9 10:38
 * @since JDK1.8
 */
public interface BasicMapper<T> extends QueryOrderByMapper<T>,
        IdsMapper<T>,
        DeleteByPrimaryKeyMapper<T>,
        BaseInsertMapper<T>,
        BaseSelectMapper<T>,
        UpdateByPrimaryKeySelectiveMapper<T>,
        InsertListMapper<T>,
        SelectCountByExampleMapper<T>,
        SelectOneByExampleMapper<T>,
        SelectByExampleMapper<T> {

}
