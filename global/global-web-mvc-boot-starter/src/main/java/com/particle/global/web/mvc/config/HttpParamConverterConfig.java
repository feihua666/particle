package com.particle.global.web.mvc.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.calendar.CalendarTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: ciaj.
 * @Date: 2020/8/9 13:30
 * @Description: 日期转换
 * @version: 1.0
 */
@Configuration
public class HttpParamConverterConfig {

    private static DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(CalendarTool.DateStyle.YYYY_MM_DD.getValue());

    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if(StrUtil.isBlank(source)){
                    return null;
                }
                try {
                    return LocalDate.parse(source,localDateFormatter);
                } catch (Exception e) {
                    Date date = DateUtil.parse(source, CalendarTool.DateStyle.YYYY_MM_DD.getValue(), CalendarTool.DateStyle.YYYY_MM_DD_EN.getValue());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CalendarTool.DateStyle.YYYY_MM_DD.getValue());
                    source = simpleDateFormat.format(date);
                    return LocalDate.parse(source, DateTimeFormatter.ofPattern(CalendarTool.DateStyle.YYYY_MM_DD.getValue()));
                }

            }
        };
    }

    /**
     * LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if(StrUtil.isBlank(source)){
                    return null;
                }
                try {
                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
                } catch (Exception e) {
                    Date date = DateUtil.parse(source, DatePattern.UTC_SIMPLE_PATTERN,DatePattern.UTC_SIMPLE_MS_PATTERN,DatePattern.UTC_PATTERN, CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue(), CalendarTool.DateStyle.YYYY_MM_DD.getValue());
                    source = DateUtil.format(date,CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()));
                }
            }
        };
    }

    /**
     * LocalTime转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                if(StrUtil.isBlank(source)) {
                    return null;
                }
                return LocalTime.parse(source, DateTimeFormatter.ofPattern(CalendarTool.DateStyle.HH_MM_SS.getValue()));
            }
        };
    }

    /**
     * Date转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public Converter<String, Date> dateConverter() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                try {
                    return DateUtil.parse(source, CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue(), CalendarTool.DateStyle.YYYY_MM_DD.getValue());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
