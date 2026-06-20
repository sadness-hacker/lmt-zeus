package com.lmt.zeus.jpa;

import com.lmt.zeus.parent.utils.BeanUtils;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @description 扩展SimpleJpaRepository,save()时不更新null字段
 *
 * @author bazhandao
 * @date 2020/3/4 18:01
 * @since JDK1.8
 */
public class ZeusJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ID> jpaEntityInformation;
    private final EntityManager entityManager;

    /**
     * Creates a new {@link ZeusJpaRepository} to manage objects of the given {@link JpaEntityInformation}.
     *
     * @param entityInformation must not be {@literal null}.
     * @param entityManager     must not be {@literal null}.
     */
    @Autowired
    public ZeusJpaRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.jpaEntityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    /**
     * 覆盖原来实现,不更新null字段
     * @author bazhandao
     * @date 2020-03-16
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Entity must not be null");
        ID id = jpaEntityInformation.getId(entity);
        if (id != null) {
            Optional<T> op = findById(id);
            if (op.isPresent()) {
                T t = op.get();
                BeanUtils.copyPropertiesWithoutNull(entity, t);
                entity = (S) t;
            } else if (entity instanceof ZeusJpaEntity) {
                // id != null && 数据库不存在时,设置_new标识为true
                ((ZeusJpaEntity<ID>) entity).set_new(true);
            }
        }
        return super.save(entity);
    }
}
