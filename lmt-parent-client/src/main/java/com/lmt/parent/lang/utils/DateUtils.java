package com.lmt.parent.lang.utils;

import com.lmt.parent.client.enums.BasicExceptionEnum;
import com.lmt.parent.client.exception.BasicException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @description 日期工具类
 * @author bazhandao
 * @date 2018/11/8 17:52
 * @since JDK1.8
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * yyyyMMdd格式
     */
    public static final String YYYYMMDD = "yyyyMMdd";
    /**
     * yyyyMMddHHmmss格式
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /**
     * yyyy-MM-dd格式
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss格式
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * HHmmss格式
     */
    public static final String HHMMSS = "HHmmss";

    /**
     * HH:mm:ss格式
     */
    public static final String HH_MM_SS = "HH:mm:ss";

    /**
     * 系统默认ZoneId
     */
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    /**
     * 根据formatter格式化日期为指定格式
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @param formatter
     * @return
     */
    public static String format(Date date, String formatter) {
        if(date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    /**
     * 根据字符串解析为日期
     * @author bazhandao
     * @date 2018-11-10
     * @param dateStr
     * @param formatter
     * @return
     */
    public static Date parse(String dateStr, String formatter) {
        if(StringUtils.isBlank(dateStr)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("格式化日期出错!date={},formatter={},{}", dateStr , formatter, e);
            throw BasicException.wrap(BasicExceptionEnum.DATE_PARSE_ERROR.getCode(), BasicExceptionEnum.DATE_PARSE_ERROR.getMsg(), e)
                    .set("date", dateStr)
                    .set("formatter", formatter);
        }
    }

    /**
     * 格式化成yyyyMMdd形式
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static String formatYYYYMMDD(Date date) {
        return format(date, YYYYMMDD);
    }

    /**
     * 格式化成yyyy-MM-dd形式
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static String formatYYYY_MM_DD(Date date) {
        return format(date, YYYY_MM_DD);
    }

    /**
     * 格式化成yyyyMMddHHmmss形式
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static String formatYYYYMMDDHHMMSS(Date date) {
        return format(date, YYYYMMDDHHMMSS);
    }

    /**
     * 格式化成yyyy-MM-dd HH:mm:ss形式
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static String formatYYYY_MM_DD_HH_MM_SS(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 格式化成HHmmss格式
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static String formatHHMMSS(Date date) {
        return format(date, HHMMSS);
    }

    /**
     * 格式化成HH:mm:ss格式
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static String formatHH_MM_SS(Date date) {
        return format(date,HH_MM_SS);
    }

    /**
     * LocalDate转date
     * @author bazhandao
     * @date 2018-11-10
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        if(localDate == null) return null;
        return Date.from(localDate.atStartOfDay(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * LocalDateTime转date
     * @author bazhandao
     * @date 2019-11-10
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if(localDateTime == null) return null;
        return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * Date转LocalDate
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        if(date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_ID).toLocalDate();
    }

    /**
     * Date转LocalDateTime
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if(date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_ID);
    }

    /**
     * Date转LocalTime
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static LocalTime toLocalTime(Date date) {
        if(date == null) return null;
        return LocalDateTime.ofInstant(date.toInstant(), DEFAULT_ZONE_ID).toLocalTime();
    }

    /**
     * 日期加上指定天数
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, int days) {
        if(date == null) return null;
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.plusDays(days);
        return toDate(localDateTime);
    }

    /**
     * 日期减去指定天数
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, int days) {
        if(date == null) return null;
        LocalDateTime localDateTime = toLocalDateTime(date);
        localDateTime = localDateTime.minusDays(days);
        return toDate(localDateTime);
    }

    /**
     * 舍去时间，只保留日期
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static Date getDate(Date date) {
        if(date == null) return null;
        LocalDate localDate = toLocalDate(date);
        return toDate(localDate);
    }

    /**
     * 舍去年月日，只保留时间
     * @author bazhandao
     * @date 2018-11-10
     * @param date
     * @return
     */
    public static Date getTime(Date date) {
        LocalTime localTime = toLocalTime(date);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(0, 0, 0), localTime);
        return toDate(localDateTime);
    }
}
