package com.particle.global.openapi.enums;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.particle.global.tool.calendar.CalendarTool;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 限制规则周期
 * </p>
 *
 * @author yangwei
 * @since 2024-10-14 13:21:32
 */
public enum LimitRulePeriod {


    /**
     * 每天
     */
    per_day
    ,
    /**
     * 每周
     */
    per_week
    ,
    /**
     * 每月
     */
    per_month;


    /**
     * 计算对应的时间范围
     * @return
     */
    public LimitRulePeriodDateTime computeDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startAt = null;
        LocalDateTime endAt = null;

        if (this == per_day) {
            startAt = now;
            endAt = now;
        }else if (this == per_week) {
            startAt = CalendarTool.getFirstDayOfWeek(now);
            endAt = CalendarTool.getEndDayOfWeek(now);
        }else if (this == per_month) {
            startAt = CalendarTool.getFirstDayOfMonth(now);
            endAt = CalendarTool.getEndDayOfMonth(now);
        }
        startAt = LocalDateTimeUtil.beginOfDay(startAt);
        endAt = LocalDateTimeUtil.endOfDay(endAt);

        return LimitRulePeriodDateTime.of(startAt, endAt);
    }

    @Data
    public static class LimitRulePeriodDateTime {
        private LocalDateTime startAt;
        private LocalDateTime endAt;

        public static LimitRulePeriodDateTime of(LocalDateTime startAt, LocalDateTime endAt) {
            LimitRulePeriodDateTime limitRulePeriodDateTime = new LimitRulePeriodDateTime();
            limitRulePeriodDateTime.startAt = startAt;
            limitRulePeriodDateTime.endAt = endAt;
            return limitRulePeriodDateTime;
        }
    }
}
