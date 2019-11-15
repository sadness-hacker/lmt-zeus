package com.lmt.zeus.id;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * @description uuid算法id生成器
 *
 * @author bazhandao
 * @date 2019/11/15 15:50
 * @since JDK1.8
 */
public class UuidGenerator implements GenId<String> {

    /**
     * 基于Java的基本uuid生成
     * @param table
     * @param column
     * @return
     */
    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
