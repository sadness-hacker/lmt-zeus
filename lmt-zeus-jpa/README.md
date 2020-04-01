# JPA踩坑记：Spring Data Jpa 原生SQL联表查询返回自定义DTO

> 关键字: JPA复杂查询,JPA返回自定义实体,JPA返回自定义DTO,JPA联表查询,JPA原生SQL查询,JPA踩坑

> 新冠疫情之下,闲来无事研究了一下JPA,发现坑还是不少的,特地记录一下,有兴趣的道友可以看看。

> 在灵活性上JPA比不上MyBatis,比如想联表查询返回一个自定义的实体Dto,结果发现不能直接返回自定义的实体,典型错误如下:

```log
2020-03-31 14:52:20.306 ERROR 82562 --- [io-19880-exec-1] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [/stock-data] threw exception [Request processing failed; nested exception is org.springframework.core.convert.ConverterNotFoundException: No converter found capable of converting from type [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap] to type [com.lmt.stock.data.XxxDto]] with root cause

org.springframework.core.convert.ConverterNotFoundException: No converter found capable of converting from type [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap] to type [com.lmt.stock.data.XxxDto]
	at org.springframework.core.convert.support.GenericConversionService.handleConverterNotFound(GenericConversionService.java:321) ~[spring-core-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.core.convert.support.GenericConversionService.convert(GenericConversionService.java:194) ~[spring-core-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.core.convert.support.GenericConversionService.convert(GenericConversionService.java:174) ~[spring-core-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.data.repository.query.ResultProcessor$ProjectingConverter.convert(ResultProcessor.java:297) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.data.repository.query.ResultProcessor$ChainingConverter.lambda$and$0(ResultProcessor.java:217) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.data.repository.query.ResultProcessor$ChainingConverter.convert(ResultProcessor.java:228) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.data.repository.query.ResultProcessor.processResult(ResultProcessor.java:156) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.data.jpa.repository.query.AbstractJpaQuery.doExecute(AbstractJpaQuery.java:157) ~[spring-data-jpa-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.data.jpa.repository.query.AbstractJpaQuery.execute(AbstractJpaQuery.java:142) ~[spring-data-jpa-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.doInvoke(RepositoryFactorySupport.java:618) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.invoke(RepositoryFactorySupport.java:605) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:80) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:366) ~[spring-tx-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:99) ~[spring-tx-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139) ~[spring-tx-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:149) ~[spring-data-jpa-2.2.5.RELEASE.jar:2.2.5.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:95) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212) ~[spring-aop-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at com.sun.proxy.$Proxy112.findXxxDtoByAccountCode(Unknown Source) ~[na:na]
	at com.lmt.stock.controller.StockController.getXxxDto(StockController.java:43) ~[classes/:na]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_181]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_181]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_181]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_181]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:106) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:879) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:793) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:634) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883) ~[spring-webmvc-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:741) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53) ~[tomcat-embed-websocket-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.4.RELEASE.jar:5.2.4.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202) ~[tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:139) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:367) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1639) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_181]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_181]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-9.0.31.jar:9.0.31]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_181]
```

> 针对这个问题,上网搜寻一番,大概发现如下4个解决方案:

## 方案1.HQL查询+实体全参数的构造方法

> 实体定义
```java
@Getter
@Setter
public class XxxDto {

    BigDecimal profitPercent;

    String accountCode;
    
    public XxxDto() {}
    
    public XxxDto(String accountCode, BigDecimal profitPercent) {
        this.accountCode = accountCode;
        this.profitPercent = profitPercent;
    }

}
```

> 查询接口
```java
import com.lmt.stock.data.XxxDto;
import com.lmt.stock.data.entity.StockHisHq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author bazhandao
 * @date 2020/3/23 11:08
 * @since JDK1.8
 */
public interface StockHisHqRepository extends JpaRepository<StockHisHq, Long> {

    // 注意这里只能用HQL查询,不能加nativeQuery = true
    @Query(value = "select new com.lmt.stock.data.XxxDto(a.accountCode as accountCode, b.profitPercent as profitPercent) from TradeAccount a, TradeOrder b where a.accountCode = b.accountCode and a.accountCode = ?1")
    List<XxxDto> findXxxDtoByAccountCode(String accountCode);

}
```

## 方案2.实体定义成接口的形式
> 实体定义
```java
// 只需要有get方法即可,注意命名要规范
public interface YyyDto {
    
    String getAccountCode();

    BigDecimal getProfitPercent();
    
}
```

> 查询接口
```java
import com.lmt.stock.data.YyyDto;
import com.lmt.stock.data.entity.StockHisHq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author bazhandao
 * @date 2020/3/23 11:08
 * @since JDK1.8
 */
public interface StockHisHqRepository extends JpaRepository<StockHisHq, Long> {
    
    // 这里可以用HQL查询,也可以用原生SQL查询,YyyDto是一个接口,这里返回的是JPA生成的YyyDto的代理类
    // 查寻出的字段命名要规范,否则与接口中的get方法对应不上
    // @Query(value = "select a.account_code as accountCode, b.profit_percent as profitPercent from trade_account a, trade_order b where a.account_code = b.account_code and a.account_code = ?1", nativeQuery = true)
    @Query(value = "select a.accountCode as accountCode, b.profitPercent as profitPercent from TradeAccount a, TradeOrder b where a.accountCode = b.accountCode and a.accountCode = ?1")
    List<YyyDto> findXxxDtoByAccountCode(String accountCode);

}
```

> ps: YyyDto是一个接口,这里返回的是JPA生成的YyyDto的代理类,是可以直接json序列化成json字符串的

## 方案3.查寻出Map结果,利用工具类转换成需要的实体
> 实体定义
```java
@Getter
@Setter
public class XxxDto {

    BigDecimal profitPercent;

    String accountCode;

}
```

> 查询接口
```java
import com.lmt.stock.data.entity.StockHisHq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author bazhandao
 * @date 2020/3/23 11:08
 * @since JDK1.8
 */
public interface StockHisHqRepository extends JpaRepository<StockHisHq, Long> {
    
    // 这里可以用HQL查询,也可以用原生SQL查询
    // 查寻出的字段命名要规范
    // @Query(value = "select a.account_code as accountCode, b.profit_percent as profitPercent from trade_account a, trade_order b where a.account_code = b.account_code and a.account_code = ?1", nativeQuery = true)
    @Query(value = "select a.accountCode as accountCode, b.profitPercent as profitPercent from TradeAccount a, TradeOrder b where a.accountCode = b.accountCode and a.accountCode = ?1")
    List<Map<String, Object>> findXxxDtoByAccountCode(String accountCode);

}
```

> 工具类
```java
public class BeanUtils extends org.springframework.beans.BeanUtils{
    
    /**
    * List<Map<String, Object>> 转换为List<T>
    * @param mapList
    * @param clazz
    * @param <T>
    * @return 
    * @throws IllegalAccessException
    * @throws InstantiationException
    */
    public static <T> List<T> toList(List<Map<String, Object>> mapList, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        if (mapList == null || clazz == null) {
            return null;
        }
        List<T> list = new ArrayList<>(mapList.size());
        for (Map<String, Object> map : mapList) {
            T t = clazz.newInstance();
            copyProperties(map, t);
            list.add(t);
        }
        return list;
    }
    
    /**
     * 从map中复制属性对对象
     * @author bazhandao
     * @date 2018-11-10
     * @param map
     * @param target
     */
    public static void copyProperties(Map<String,Object> map, Object target) {
        if(map == null || target == null || map.isEmpty()){
            return;
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if(targetPd.getWriteMethod() == null) {
                continue;
            }
            try {
                String key = targetPd.getName();
                Object value = map.get(key);
                // 这里判断以下value是否为空
                setValue(target, targetPd, value);
            } catch (Exception ex) {
                throw new FatalBeanException("Could not copy properties from source to target", ex);
            }
        }
    }

    /**
     * 设置值到目标bean
     * @param target
     * @param targetPd
     * @param value
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void setValue(Object target, PropertyDescriptor targetPd, Object value) throws IllegalAccessException, InvocationTargetException {
        // 这里判断以下value是否为空
        if (value != null) {
            Method writeMethod = targetPd.getWriteMethod();
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                writeMethod.setAccessible(true);
            }
            writeMethod.invoke(target, value);
        }
    }
}

```

> ps: 注意SQL查寻出的字段名要与实体中的字段名一致,也可扩展工具类实现查寻出数据库原始字段名再转换的驼峰命名对应到实体中.

## 方案4.@SqlResultSetMapping注解实现
> 实体定义
```java
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

@Getter
@Setter
@SqlResultSetMapping(
    name = "xxxDto",
    entities = {
        @EntityResult(
            entityClass = XxxDto.class, // 当前类名
            fields = {
                @FieldResult(name = "accountCode", column = "account_code"),
                @FieldResult(name = "profitPercent", column = "profit_percent")
            }
        )
    }
)
public class XxxDto {

    BigDecimal profitPercent;

    String accountCode;

}
```

> 接口定义
```java
import com.lmt.stock.data.XxxDto;
import com.lmt.stock.data.entity.StockHisHq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author bazhandao
 * @date 2020/3/23 11:08
 * @since JDK1.8
 */
public interface StockHisHqRepository extends JpaRepository<StockHisHq, Long> {
    
    // 这里可以用HQL查询,也可以用原生SQL查询
    // 查寻出的字段命名要规范
    // @Query(value = "select a.account_code as accountCode, b.profit_percent as profitPercent from trade_account a, trade_order b where a.account_code = b.account_code and a.account_code = ?1", nativeQuery = true)
    @Query(value = "select a.accountCode as accountCode, b.profitPercent as profitPercent from TradeAccount a, TradeOrder b where a.accountCode = b.accountCode and a.accountCode = ?1")
    List<XxxDto> findXxxDtoByAccountCode(String accountCode);

}
```

## 方案5. 独创方案,自定义注解+注入convert实现

> 注意看错误日志ConverterNotFoundException: No converter found capable of converting from type
> 既然是converter没有找到那就注入对应的converter就完事儿了,
> 查出的原始类型为AbstractJpaQuery$TupleConverter$TupleBackedMap,是一个Map,也就是Map转实体呗,对应的实体转入一个Map转dto的converter就行了
> 继续看错误日志,	at org.springframework.data.repository.query.ResultProcessor.processResult(ResultProcessor.java:156) ~[spring-data-commons-2.2.5.RELEASE.jar:2.2.5.RELEASE]
> 在这个ResultProcessor有所发现,用到的ConversionService是通过DefaultConversionService.getSharedInstance()获取的
> 那么把我们需要注入的converter注入到DefaultConversionService.getSharedInstance()应该就能实现自动转换为自定义Dto了

> 实现方案:
> 1.添加自定义注解@JpaDto
JpaDto.java
```java
package com.lmt.zeus.jpa.annotation;

/**
 * @description 自定义注解表示,加在类上表示是一个JpaDto类
 *
 * @author bazhandao
 * @date 2020/3/26 16:39
 * @since JDK1.8
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Component
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaDto {

}
```

> 2.添加ZeusJpaConfiguration类实现注入加有@JpaDto注解的类对应的converter功能
ZeusJpaConfiguration.java
```java
package com.lmt.zeus.jpa.config;

import com.lmt.zeus.jpa.annotation.JpaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import javax.annotation.PostConstruct;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @description
 *
 * @author bazhandao
 * @date 2020/3/26 17:51
 * @since JDK1.8
 */
@Slf4j
@Configuration
public class ZeusJpaConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 初始化注入@JpaDto对应的Converter
     */
    @PostConstruct
    public void init() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(JpaDto.class);
        for (Object o : map.values()) {
            Class c = o.getClass();
            log.info("Jpa添加Converter,class={}", c.getName());
            GenericConversionService genericConversionService = ((GenericConversionService) DefaultConversionService.getSharedInstance());
            genericConversionService.addConverter(Map.class, c, m -> {
                try {
                    Object obj = c.newInstance();
                    // 这里可以扩展,注入的converter,实现sql查寻出的结果为数据库中带下划线的字段,通过程序转为驼峰命名再设置到实体中
                    // 也可以做类型转换判断,这里未做类型判断,直接copy到dto中,类型不匹配的时候可能会出错
                    return copyMapToObj(m, obj);
                } catch (Exception e) {
                    throw new FatalBeanException("Jpa结果转换出错,class=" + c.getName(), e);
                }
            });
        }
    }

    /**
     * 将map中的值copy到bean中对应的字段上
     * @author bazhandao
     * @date 2020-03-26
     * @param map
     * @param target
     * @return
     */
    private Object copyMapToObj(Map<String, Object> map, Object target) {
        if(map == null || target == null || map.isEmpty()){
            return target;
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if(targetPd.getWriteMethod() == null) {
                continue;
            }
            try {
                String key = targetPd.getName();
                Object value = map.get(key);
                if (value == null) {
                    continue;
                }
                Method writeMethod = targetPd.getWriteMethod();
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(target, value);
            } catch (Exception ex) {
                throw new FatalBeanException("Could not copy properties from source to target", ex);
            }
        }
        return target;
    }
}

```

> 3.实体类
```java
package com.lmt.stock.data;

import com.lmt.zeus.jpa.annotation.JpaDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @description 小试牛刀
 *
 * @author bazhandao
 * @date 2020/3/26 13:53
 * @since JDK1.8
 */
@JpaDto  // 注意实体类要加上@JpaDto注解,将该类注入到容器
@Getter
@Setter
public class XxxDto {

    BigDecimal profitPercent;

    String accountCode;

}

```

> 4.接口实现
```java
import com.lmt.stock.data.XxxDto;
import com.lmt.stock.data.entity.StockHisHq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author bazhandao
 * @date 2020/3/23 11:08
 * @since JDK1.8
 */
public interface StockHisHqRepository extends JpaRepository<StockHisHq, Long> {
    
    // 这里可以用HQL查询,也可以用原生SQL查询
    // 查寻出的字段命名要规范
    // @Query(value = "select a.account_code as accountCode, b.profit_percent as profitPercent from trade_account a, trade_order b where a.account_code = b.account_code and a.account_code = ?1", nativeQuery = true)
    @Query(value = "select a.accountCode as accountCode, b.profitPercent as profitPercent from TradeAccount a, TradeOrder b where a.accountCode = b.accountCode and a.accountCode = ?1")
    List<XxxDto> findXxxDtoByAccountCode(String accountCode);

}

```

> ps:该方案可以使用原生SQL也可以使用HQL,只要查寻出的结果字段名跟实体中的字段名保持一致即可,还可以对注入的converter做扩展,支持更多功能
> 这里使用了自定义注解将dto注入到容器,通过Spring Boot的Configuration功能实现注入该dto对应的converter功能
> 也可以不用自定义注解,通过自定义类扫描器或者通过配置加载到dto对应的class注入对应的converter,总之只要开拓思维玩法是多样的

## 总结
> 方案1、2、4是Spring Data Jpa中原生支持的方案;
> 方案1要求实体有全参的构造方法,写HQL时需要把参数一一对应,当字段比较多时写起来比较累,不建议使用
> 方案2通过定义一个接口,里边添加上对应字段的get方法即可查寻出对应的结果(JPA生成了该接口的代理类,支持直接序列化为json串),相对简单,建议采用
> 方案4通过@SqlResultSetMapping注解实现,需要在dto类中将结果一一对应绑定,字段多时写起来比较繁锁,不建议使用
> 方案3通过查寻出Map,再利用工具类转换为对应的dto实体,写业务过程会多一步操作,相对比较繁锁,不建议采用
> 方案5通过注解+自动注入converter实现自动转换查寻出的结果即为dto实体,对业务开发无侵入,简化开发过程,建议采用


综上所述,如果对查寻出的结果不做其他复杂操作,则直接使用JPA原生支持的方法2,定义接口dto查寻自定义结果,
如果不习惯查出的结果是一个接口类,可以采用方案5,框架低层注入converter实现查寻自定义结果。

针对JPA实现扩展代码已上传到github,欢迎访问:
https://github.com/sadness-hacker/lmt-zeus/tree/master/lmt-zeus-jpa
