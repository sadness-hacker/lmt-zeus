package com.lmt.zeus.id.jpa;

import com.lmt.zeus.id.snow.UidGenerator;
import com.lmt.zeus.parent.utils.SpringContextUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @description 基于雪花算法生成id
 *
 * @author bazhandao
 * @date 2020/3/27 22:12
 * @since JDK1.8
 */
public class ZeusIdentifierGenerator implements IdentifierGenerator {

    /**
     * Generate a new identifier.
     *
     * @param session The session from which the request originates
     * @param object  the entity or collection (idbag) for which the id is being generated
     * @return a new identifier
     * @throws HibernateException Indicates trouble generating the identifier
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        UidGenerator uidGenerator = SpringContextUtils.getBean("uidGenerator", UidGenerator.class);
        return uidGenerator.getUID();
    }
}
