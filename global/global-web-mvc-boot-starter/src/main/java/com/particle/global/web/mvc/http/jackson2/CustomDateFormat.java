package com.particle.global.web.mvc.http.jackson2;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.calendar.CalendarTool;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

/**
 * @Description 日期格式处理
 * @Author Ciaj.
 * @Date 2019/5/8 16:42
 * @Version 1.0
 */
@Slf4j
public class CustomDateFormat extends DateFormat {

    /**
     * 序列化时会执行这个方法
     *
     * @param date
     * @param toAppendTo
     * @param fieldPosition
     * @return
     */
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return new StringBuffer(DateUtil.format(date, CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
    }

    /**
     * 反序列化时执行此方法，我们先让他执行我们自己的format，如果异常则执执行他的
     *
     * @param source
     * @param pos
     * @return
     */
    @Override
    public Date parse(String source, ParsePosition pos) {
        return getParseDate(source);
    }

    /**
     * 反序列化时执行此方法，我们先让他执行我们自己的format，如果异常则执执行他的
     *
     * @param source
     * @return
     */
    @Override
    public Date parse(String source) {
        return getParseDate(source);
    }

    private Date getParseDate(String source) {
        if (StrUtil.isBlank(source)){
            return null;
        }
        try {
            return DateUtil.parse(source, CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 此方法在objectmapper 默认的dateformat里边用到，这里也要重写
     *
     * @return
     */
    @Override
    public Object clone() {
        return new CustomDateFormat();
    }
}
