# 一、tk.mybatis中应用
## 1.添加pom依赖
```xml
<dependency>
    <artifactId>lmt-zeus-id-generator</artifactId>
    <groupId>com.lmt.zeus</groupId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## 2.配置文件添加配置(application.properties)
```properties
# 启动zeus id生成器
lmt.zeus.id.generator-type=zeusIdGenerator
```

## 3.实体中id字段添加注解
```java
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

import tk.mybatis.mapper.annotation.KeySql;

/**
 * @description 测试Id
 *
 * @author bazhandao
 * @date 2020/3/30 16:01
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "lmt_sys_test_id")
public class SysTestId {
    @Id
    @KeySql(genId = com.lmt.zeus.id.ZeusIdGenerator.class)
    private Long id;

    @Column(name = "created_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createdTime;

    @Column(name = "updated_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date updatedTime;

}
```

# 二、JPA中应用

## 1.添加pom依赖
```xml
<dependency>
    <artifactId>lmt-zeus-id-generator</artifactId>
    <groupId>com.lmt.zeus</groupId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## 2.配置文件添加配置(application.properties)
```properties
# 启动zeus id生成器
lmt.zeus.id.generator-type=zeusIdGenerator
```

## 3.实体上id字段添加注解
```java
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 测试Id
 *
 * @author bazhandao
 * @date 2020/3/30 16:01
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "lmt_sys_test_id")
public class SysTestId {

    /**
     * id字段,使用UidGenerator生成id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uidGenerator")
    @GenericGenerator(name = "uidGenerator", strategy = "com.lmt.zeus.id.jpa.ZeusIdentifierGenerator")
    private Long id;

    @Column(name = "created_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createdTime;

    @Column(name = "updated_time", columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date updatedTime;

}
```
