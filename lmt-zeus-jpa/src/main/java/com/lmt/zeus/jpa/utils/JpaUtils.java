package com.lmt.zeus.jpa.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Map;

/**
 * @description Jpa工具类
 *
 * @author bazhandao
 * @date 2021/9/30 12:08
 * @since JDK1.8
 */
public class JpaUtils {

    /**
     * JPA查寻数据
     * @param em
     * @param querySql
     * @param clazz
     * @param nativeQuery
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> findAll(EntityManager em, String querySql, Class<T> clazz, boolean nativeQuery, Map<String, Object> params) {
        TypedQuery<T> query;
        if (nativeQuery) {
            query = em.createNamedQuery(querySql, clazz);
        } else {
            query = em.createQuery(querySql, clazz);
        }
        return query.getResultList();
    }

    /**
     * JPA分页查寻数据
     * @param em
     * @param querySql
     * @param clazz
     * @param nativeQuery
     * @param params
     * @param <T>
     * @return
     */
    public static <T> Page<T> findAll(EntityManager em, String querySql, String countSql, Class<T> clazz, int pageNum, int pageSize, boolean nativeQuery, Map<String, Object> params) {
        Query query;
        if (nativeQuery) {
            query = em.createNativeQuery(querySql, clazz);
        } else {
            query = em.createQuery(querySql, clazz);
        }
        query.setFirstResult(pageNum - 1);
        query.setMaxResults(pageSize);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<T> list = query.getResultList();
        long total = count(em, countSql, nativeQuery, params);
        return new PageImpl<>(list, PageRequest.of(pageNum - 1, pageSize), total);
    }

    /**
     * JPA统计数据
     * @param em
     * @param nativeQuery
     * @param params
     * @return
     */
    public static long count(EntityManager em, String baseQueryHql, boolean nativeQuery, Map<String, Object> params) {
        Query query;
        if (nativeQuery) {
            query = em.createNativeQuery(baseQueryHql, Long.class);
        } else {
            query = em.createQuery(baseQueryHql, Long.class);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        Long result = (Long) query.getSingleResult();
        return result == null ? 0 : result;
    }

}
