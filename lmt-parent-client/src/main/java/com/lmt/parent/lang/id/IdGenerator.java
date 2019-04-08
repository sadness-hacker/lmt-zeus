package com.lmt.parent.lang.id;

/**
 * @description id生成接口
 *
 * @author bazhandao
 * @date 2019/4/8 14:59
 * @since JDK1.8
 */
public interface IdGenerator<PK> {

    /**
     * 生成id
     * @return
     */
    public PK generatorId();

}
