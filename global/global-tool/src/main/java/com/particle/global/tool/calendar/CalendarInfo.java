package com.particle.global.tool.calendar;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 日历节日信息
 * Created by yangwei
 * Created at 2018/9/20 15:17
 */
@Getter
@Setter
public class CalendarInfo implements Serializable {
    private Date date;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    /**
     * 星期几，星期日为0
     */
    private int week;
    private LunarDate lunarDate;
    private int lunarYear;
    private int lunarMonth;
    private int lunarDay;
    private int lunarHour;
    private int lunarMinute;
    private int lunarSecond;
    /**
     * 该年是否闰月
     */
    private boolean leap;
    /**
     * 如果闰月，闰的是几月
     */
    private int leapMonth;
    /**
     * 当前日期是否在闰月中
     */
    private boolean currentLeap;
    private LunarCalendarTool.SymbolicAnimals animal;
    private LunarCalendarTool.Tiangan tianganYear;
    private LunarCalendarTool.Dizhi dizhiYear;

    private LunarCalendarTool.Tiangan tianganMonth;
    private LunarCalendarTool.Dizhi dizhiMonth;

    private LunarCalendarTool.Tiangan tianganDay;
    private LunarCalendarTool.Dizhi dizhiDay;

    private LunarCalendarTool.Tiangan tianganHour;
    private LunarCalendarTool.Dizhi dizhiHour;

    private LunarCalendarTool.SolarTerm24 solarTerm24;

    private LunarCalendarTool.Constellation constellation;

    private LunarCalendarTool.WeekSolarHoliday weekSolarHoliday;

    private LunarCalendarTool.SolarHoliday solarHoliday;

    private  LunarCalendarTool.TraditionalFestival traditionalFestival;

    private LunarCalendarTool.ChinaConstellation chinaConstellation;

}
