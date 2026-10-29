package com.lmt.zeus.jpa;

import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

/**
 * 扩展Persistable，解决手动指定id时无法插入的问题
 * @param <T>
 */
public abstract class ZeusJpaEntity<T> implements Persistable<T> {

    @Transient
    private boolean _new;

    @Override
    public boolean isNew() {
        return _new || getId() == null;
    }

    public void set_new(boolean _new) {
        this._new = _new;
    }

}
