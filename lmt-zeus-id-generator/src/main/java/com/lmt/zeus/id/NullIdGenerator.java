package com.lmt.zeus.id;

import tk.mybatis.mapper.genid.GenId;

/**
 * @description 生成空id生成器
 *
 * @author bazhandao
 * @date 2019/11/15 15:55
 * @since JDK1.8
 */
public class NullIdGenerator implements GenId<Object> {

    @Override
    public Object genId(String table, String column) {
        return null;
    }
}
