/**
 * Generate by lmt-code-generator 2018-11-07 16:00:06
 * you can find lmt-code-generator project on github, please visit:
 *
 */
package com.lmt.parent.test.basic.mapper;
import com.lmt.parent.basic.mapper.BasicMapper;
import com.lmt.parent.test.entity.AaCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @description AaCode基础Mapper接口
 * @author Generate by lmt-code-generator
 * @date 2018-11-07 16:00:06
 * @since JDK 1.8
 *
 */
public interface BasicAaCodeMapper extends BasicMapper<AaCode, String> {

    /**
     * @description 插入aaCode到数据表
     * @param aaCode
     * @return int
     */
    public int insert(@Param(value = "aaCode") AaCode aaCode);

    /**
     * @description 批量插入aaCode到数据表
     * @param aaCodeList
     * @return int
     */
    public int batchInsert(List<AaCode> aaCodeList);

        
    /**
     * @description 根据主键id查寻AaCode实体
     * @param ddGuid
     *
     * @return AaCode
     */
    public AaCode get(@Param(value = "ddGuid") String ddGuid);


    /**
     * @description 根据主键id列表查寻AaCode实体列表
     * @param ddGuidList
     * @return List<AaCode>
     */
    public List<AaCode> listByPKList(List<String> ddGuidList);

    /**
     * @description 根据主键id删除记录
     * @param @Param(value = "ddGuid") String ddGuid (非空)
     * @return int
     */
    public int deleteByPk(@Param(value = "ddGuid") String ddGuid);

    /**
     * @description 根据实体删除记录
     * @param aaCode 不能所有字段都为空，否则sql执行出错
     * @return int
     */
    public int delete(@Param(value = "aaCode") AaCode aaCode);

    /**
     * @description 根据主键列表删除记录
     * @param pkList
     * @return int 删除成功的记录数
     */
    public int deleteByPKList(List<String> pkList);

    /**
     * @description 根据主键id更新记录
     * @param aaCode    主键id字段不能为空
     * @return int
     */
    public int update(@Param(value = "aaCode") AaCode aaCode);


    /**
     * @description 根据主实体统计记录数
     * @param aaCode
     * @return int
     */
    public long count(@Param(value = "aaCode") AaCode aaCode);

    /**
     * @description 根据主实体查询记录
     * @param aaCode
     * @return int
     */
    public List<AaCode> query(@Param(value = "aaCode") AaCode aaCode);

    /**
     * @description 根据主实体查询记录，根据指定的字段进行排序
     * @param aaCode
     * @param sortField 排序字段
     * @param sortOrder asc或desc
     * @return List<AaCode>
     */
    public List<AaCode> queryOrderBy(
            @Param(value = "aaCode") AaCode aaCode,
            @Param(value = "sortField") String sortField,
            @Param(value = "sortOrder") String sortOrder);

}
