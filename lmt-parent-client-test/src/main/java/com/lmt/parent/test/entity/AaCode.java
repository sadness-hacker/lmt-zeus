/**
 * Generate by lmt-code-generator 2018-11-07 10:41:52
 * you can find lmt-code-generator project on github, please visit:
 *
 */
package com.lmt.parent.test.entity;

import com.lmt.parent.basic.entity.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @description 代码
 * @author Generate by lmt-code-generator
 * @date 2018-11-07 10:41:52
 * @since JDK 1.8
 */
@Setter
@Getter
@ToString(callSuper = true)
public class AaCode extends Entity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** T_SJAA_CODE,DD_GUID,代码ID*/
    private String ddGuid;

    /** T_SJAA_CODE,DD_TYPE_GUID,代码类型ID*/
    private String ddTypeGuid;

    /** T_SJAA_CODE,DD_TYPE_CODE,代码类型编码*/
    private String ddTypeCode;

    /** T_SJAA_CODE,DD_CODE,编码*/
    private String ddCode;

    /** T_SJAA_CODE,DD_NAME,名称*/
    private String ddName;

    /** T_SJAA_CODE,DD_DESC,描述*/
    private String ddDesc;

    /** T_SJAA_CODE,PARENT_GUID,上级ID*/
    private String parentGuid;

    /** T_SJAA_CODE,IS_END_LEVEL,是否末级*/
    private String isEndLevel;

    /** T_SJAA_CODE,PATH_LEVEL,层级*/
    private Short pathLevel;

    /** T_SJAA_CODE,PATH_CODE,路径*/
    private String pathCode;

    /** T_SJAA_CODE,REMARK,备注*/
    private String remark;

    /** T_SJAA_CODE,ROW_ORDER,排序*/
    private String rowOrder;

    /** T_SJAA_CODE,EXT_COL1,扩展属性1*/
    private String extCol1;

    /** T_SJAA_CODE,EXT_COL2,扩展属性2*/
    private String extCol2;

    /** T_SJAA_CODE,EXT_COL3,扩展属性3*/
    private String extCol3;

    /** T_SJAA_CODE,EXT_COL4,扩展属性4*/
    private String extCol4;

    /** T_SJAA_CODE,EXT_COL5,扩展属性5*/
    private String extCol5;

    /** T_SJAA_CODE,EXT_COL6,扩展属性6*/
    private String extCol6;

    /** T_SJAA_CODE,EXT_COL7,扩展属性7*/
    private String extCol7;

    /** T_SJAA_CODE,EXT_COL8,扩展属性8*/
    private String extCol8;

    /** T_SJAA_CODE,EXT_COL9,扩展属性9*/
    private String extCol9;

    /** T_SJAA_CODE,EXT_COL10,扩展属性10*/
    private String extCol10;

    /** T_SJAA_CODE,CREATE_DATE,创建日期*/
    private Date createDate;

    /** T_SJAA_CODE,CREATE_USER_ID,创建人ID*/
    private String createUserId;

    /** T_SJAA_CODE,MODIFY_DATE,修改日期*/
    private Date modifyDate;

    /** T_SJAA_CODE,MODIFY_USER_ID,修改人ID*/
    private String modifyUserId;

    /** T_SJAA_CODE,DELETE_DATE,删除日期*/
    private Date deleteDate;

    /** T_SJAA_CODE,DELETE_USER_ID,删除人*/
    private String deleteUserId;

    /** T_SJAA_CODE,DELETE_FLAG,删除标志*/
    private String deleteFlag;


}
