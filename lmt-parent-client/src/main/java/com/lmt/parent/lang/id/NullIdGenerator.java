package com.lmt.parent.lang.id;

/**
 * @description 不生成id,一直返回null
 *
 * @author bazhandao
 * @date 2019/4/8 15:01
 * @since JDK1.8
 */
public class NullIdGenerator<PK> implements IdGenerator<PK> {
    /**
     * 生成id
     *
     * @return
     */
    @Override
    public PK generatorId() {
        return null;
    }
}
