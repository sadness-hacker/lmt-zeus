package com.lmt.zeus.id.jpa;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * @description 增强自增主键方法,存在id保存时不生成新id,否则指定的id会被替换为自增的id
 *
 * @author bazhandao
 * @date 2020/12/5 18:01
 * @since JDK1.8
 */
public class ZeusIdentityGenerator extends IdentityGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        // 1.已存在指定的id,直接返回
        Serializable id = s.getEntityPersister(null, obj).getClassMetadata().getIdentifier(obj, s);
        if (id != null) {
            return id;
        }
        // 2.不存在指定的id,生成自增id返回
        return super.generate(s, obj);
    }
}
