package com.lmt.zeus.jpa;

import org.hibernate.Interceptor;

import java.io.Serializable;

/**
 * 实现Hibernate的拦截器
 */
public class ZeusHibernateInterceptor implements Interceptor, Serializable {

    /**
     * 自定义Transient判断
     * @param entity
     * @return
     */
    @Override
    public Boolean isTransient(Object entity) {
        if (entity instanceof ZeusJpaEntity) {
            return ((ZeusJpaEntity) entity).isNew();
        }
        return null;
    }

}
