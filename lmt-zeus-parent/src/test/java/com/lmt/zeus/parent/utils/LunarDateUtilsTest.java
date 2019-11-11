package com.lmt.zeus.parent.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @description 农历工具类测试
 *
 * @author bazhandao
 * @date 2019/10/31 09:50
 * @since JDK1.8
 */
public class LunarDateUtilsTest {

    /**
     * 测试公历转农历
     * @author bazhandao
     * @date 2019-10-31
     */
    @Test
    public void testToLunarDate() {
        String date = LunarDateUtils.toLunarDate(DateUtils.parse("1990-02-28", DateUtils.YYYY_MM_DD));
        Assert.assertEquals("一九九零年二月初四", date);
        date = LunarDateUtils.toLunarDate(DateUtils.parse("2019-10-31", DateUtils.YYYY_MM_DD));
        Assert.assertEquals("二零一九年十月初四", date);
        date = LunarDateUtils.toLunarDate(DateUtils.parse("2019-12-31", DateUtils.YYYY_MM_DD));
        Assert.assertEquals("二零一九年腊月初六", date);
        date = LunarDateUtils.toLunarDate(DateUtils.parse("2019-12-22", DateUtils.YYYY_MM_DD));
        Assert.assertEquals("二零一九年冬月廿七", date);
        date = LunarDateUtils.toLunarDate(DateUtils.parse("2020-01-01", DateUtils.YYYY_MM_DD));
        Assert.assertEquals("二零一九年腊月初七", date);
        date = LunarDateUtils.toLunarDate(DateUtils.parse("2020-12-31", DateUtils.YYYY_MM_DD));
        Assert.assertEquals("二零二零年冬月十七", date);
    }

}
