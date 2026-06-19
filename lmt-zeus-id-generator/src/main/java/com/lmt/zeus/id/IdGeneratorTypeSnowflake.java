package com.lmt.zeus.id;

import com.lmt.zeus.id.jpa.ZeusIdentifierGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 雪花算法IdGeneratorType注解，加在id字段上
 */
@IdGeneratorType(ZeusIdentifierGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface IdGeneratorTypeSnowflake {

}
