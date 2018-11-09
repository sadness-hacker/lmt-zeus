package com.lmt.parent.basic.controller;

import com.github.pagehelper.PageInfo;
import com.lmt.parent.basic.entity.Entity;
import com.lmt.parent.basic.service.BasicService;
import com.lmt.parent.client.exception.BasicException;
import com.lmt.parent.client.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @description 基础Controller类
 * @author bazhandao
 * @date 2018/11/8 17:47
 * @since JDK1.8
 */
@Slf4j
public abstract class BasicController<T extends Entity, PK extends Serializable> {

    /**
     * @description 获取该controller对应的service类
     * @author bazhandao
     * @date 2018-11-09
     * @return
     */
    public abstract BasicService<T, PK> getBasicService();

    /**
     * 该controller对应的实体类
     */
    private Class<?> entityClass;

    /**
     * @description 从泛型获取对应的实体类
     * @author bazhandao
     * @date 2018-11-09
     */
    public BasicController() {
        Type type = getClass().getGenericSuperclass();
        // 判断 是否泛型
        if (type instanceof ParameterizedType) {
            // 返回表示此类型实际类型参数的Type对象的数组.
            // 当有多个泛型类时，数组的长度就不是1了
            Type[] ptype = ((ParameterizedType) type).getActualTypeArguments();
            //将第一个泛型T对应的类赋值给entityClass
            this.entityClass = (Class) ptype[0];
        } else {
            //若没有给定泛型，将Entity类赋值给entityClass
            this.entityClass = Entity.class;
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, name = "新增entity")
    @ResponseBody
    public Result<Integer> insert(T entity) {
        log.debug("[{}] insert, entity={}", entityClass.getSimpleName(), entity);
        Result<Integer> result = new Result<>();
        try {
            int i = getBasicService().insert(entity);
            result.setData(i);
        } catch (BasicException e) {
            log.error("[{}] insert error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] insert error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(9999, "添加失败!");
        }
        log.debug("[{}] insert, result={}", entityClass.getSimpleName(), result);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, name = "更新entity")
    @ResponseBody
    public Result<Integer> update(T entity) {
        log.debug("[{}] update, entity={}", entityClass.getSimpleName(), entity);
        Result<Integer> result = new Result<>();
        try {
            int i = getBasicService().update(entity);
            result.setData(i);
        } catch (BasicException e) {
            log.error("[{}] update error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] update error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(9999, "更新失败!");
        }
        log.debug("[{}] update, result={}", entityClass.getSimpleName(), result);
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, name = "更新或新增Model")
    @ResponseBody
    public Result<Integer> save(T entity) {
        log.debug("[{}] save, entity={}", entityClass.getSimpleName(), entity);
        Result<Integer> result = new Result<>();
        try {
            int i = getBasicService().saveOrUpdate(entity);
            result.setData(i);
        } catch (BasicException e) {
            log.error("[{}] save error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] save error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(9999, "保存失败!");
        }
        log.debug("[{}] save, result={}", entityClass.getSimpleName(), result);
        return result;
    }

    @RequestMapping(value = "/deleteByPk", method = RequestMethod.POST, name = "根据主键删除Model")
    @ResponseBody
    public Result<Integer> deleteByPk(PK pk) {
        log.debug("[{}]  deleteByPk, pk={}", entityClass.getSimpleName(), pk);
        Result<Integer> result = new Result<>();
        try {
            int i = getBasicService().deleteByPk(pk);
            result.setData(i);
        } catch (BasicException e) {
            log.error("[{}] delete by pk error! pk={},{}", entityClass.getSimpleName(), pk, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] delete by pk error! pk={},{}", entityClass.getSimpleName(), pk, e);
            result.setError(9999, "删除失败!");
        }
        log.debug("[{}] deleteByPk, result={}", entityClass.getSimpleName(), result);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, name = "根据实体条件删除")
    @ResponseBody
    public Result<Integer> delete(T entity) {
        log.debug("[{}] delete, entity={}", entityClass.getSimpleName(), entity);
        Result<Integer> result = new Result<>();
        try {
            int i = getBasicService().delete(entity);
            result.setData(i);
        } catch (BasicException e) {
            log.error("[{}] delete by entity error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] delete by entity error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(9999, "删除失败!");
        }
        log.debug("[{}] delete, result={}", entityClass.getSimpleName(), result);
        return result;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, name = "根据主键查寻")
    @ResponseBody
    public Result<T> get(PK pk) {
        log.debug("[{}] get, pk={}", entityClass.getSimpleName(), pk);
        Result<T> result = new Result<>();
        try {
            T model = getBasicService().get(pk);
            result.setData(model);
        } catch (BasicException e) {
            log.error("[{}] get error! pk={},{}", entityClass.getSimpleName(), pk, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] get by pk error! pk={},{}", entityClass.getSimpleName(), pk, e);
            result.setError(9999, "查寻失败!");
        }
        log.debug("[{}] get, result={}", entityClass.getSimpleName(), result);
        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET, name = "查寻Entity列表")
    @ResponseBody
    public Result<List<T>> query(T entity) {
        log.debug("[{}] query, entity={}", entityClass.getSimpleName(), entity);
        Result<List<T>> result = new Result<>();
        try {
            List<T> modelList = getBasicService().query(entity);
            result.setData(modelList);
        } catch (BasicException e) {
            log.error("[{}] query error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] query error! entity={},{}", entityClass.getSimpleName(), entity, e);
            result.setError(9999, "查寻失败!");
        }
        log.debug("[{}] query, result={}", entityClass.getSimpleName(), result);
        return result;
    }

    @RequestMapping(value = "/queryByPage", method = RequestMethod.GET, name = "分页查寻Entity列表,并根据指定自动排序")
    @ResponseBody
    public Result<PageInfo<T>> queryByPage(
            T entity,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "sortField", defaultValue = "") String sortField,
            @RequestParam(value = "sortOrder", defaultValue = "") String sortOrder) {
        log.debug("[{}] queryByPage, entity={},pageNum={},pageSize={},sortField={},sortOrder={}", entityClass.getSimpleName(), entity, pageNum, pageSize, sortField, sortOrder);
        Result<PageInfo<T>> result = new Result<>();
        try {
            PageInfo<T> pageInfo = getBasicService().queryByPage(entity, sortField , sortOrder, pageNum, pageSize);
            result.setData(pageInfo);
        } catch (BasicException e) {
            log.error("[{}] query error! entity={},pageNum={},pageSize={},sortField={},sortOrder={},{}", entityClass.getSimpleName(), entity, pageNum, pageSize, sortField, sortOrder, e);
            result.setError(e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("[{}] query error! entity={},pageNum={},pageSize={},sortField={},sortOrder={},{}", entityClass.getSimpleName(), entity, pageNum, pageSize, sortField, sortOrder, e);
            result.setError(9999, "查寻失败!");
        }
        log.debug("[{}] queryByPage, result={}", entityClass.getSimpleName(), result);
        return result;
    }
}
