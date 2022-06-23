package com.particle.global.tool.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 农历日期工具类
 * Created by yangwei
 * Created at 2019/12/23 13:38
 */
public class CalendarInfoTool {


    /**
     * 根据公历日期获取详细内容
     * @param date 公历日期
     * @return
     */
    public static CalendarInfo getCalendarByDate(Date date) {
        CalendarInfo calendarDto = new CalendarInfo();

        calendarDto.setDate(date);
        int year = CalendarTool.getYear(date);
        int month = CalendarTool.getMonth(date);
        int day = CalendarTool.getDay(date);
        int hour = CalendarTool.getHour(date);
        int minute = CalendarTool.getMinute(date);
        int second = CalendarTool.getMinute(date);
        calendarDto.setYear(year);
        calendarDto.setMonth(month);
        calendarDto.setDay(day);
        calendarDto.setHour(hour);
        calendarDto.setMinute(minute);
        calendarDto.setSecond(second);
        calendarDto.setWeek(CalendarTool.getDayOfWeek(date) - 1);
        //转农历
        LunarDate lunarDate = LunarCalendarTool.CalendarToLunar(date);
        int lunarYear = lunarDate.getYear();
        int lunarMonth = lunarDate.getMonth();
        int lunarDay = lunarDate.getDay();

        int lunarHour = CalendarTool.getHour(date);
        int lunarMinute = CalendarTool.getMinute(date);
        int lunarSecond = CalendarTool.getMinute(date);

        calendarDto.setLunarYear(lunarYear);
        calendarDto.setLunarMonth(lunarMonth);
        calendarDto.setLunarDay(lunarDay);
        calendarDto.setLunarHour(lunarHour);
        calendarDto.setLunarMinute(lunarMinute);
        calendarDto.setLunarSecond(lunarSecond);

        calendarDto.setLunarDate(lunarDate);
        //闰月
        int leapMonth = LunarCalendarTool.leapMonth(year);

        if (leapMonth == 0) {
            calendarDto.setLeap(false);
        }else{
            calendarDto.setLeap(true);
            calendarDto.setLeapMonth(leapMonth);

            if(lunarMonth == leapMonth){
                // 农历转公历
                Date todate = LunarCalendarTool.LunarToCalendar(lunarDate);
                // 如果转公历日期与原日期相等，则日期是闰月的第一个月
                if(todate.equals(date)){
                    calendarDto.setCurrentLeap(false);
                }else {
                    calendarDto.setCurrentLeap(true);
                }
            }

        }
        // 动物年
        LunarCalendarTool.SymbolicAnimals animals = LunarCalendarTool.symbolicAnimalsYear(lunarYear);
        calendarDto.setAnimal(animals);

        // 天干地支年
        LunarCalendarTool.Tiangan tianganYear = LunarCalendarTool.tianganYear(lunarYear);
        LunarCalendarTool.Dizhi dizhiYear = LunarCalendarTool.dizhiYear(lunarYear);
        calendarDto.setTianganYear(tianganYear);
        calendarDto.setDizhiYear(dizhiYear);

        // 天干地支月
        LunarCalendarTool.Tiangan tianganMonth = LunarCalendarTool.tianganMonth(lunarYear,lunarMonth);
        LunarCalendarTool.Dizhi dizhiMonth = LunarCalendarTool.dizhiMonth(lunarMonth);
        calendarDto.setTianganMonth(tianganMonth);
        calendarDto.setDizhiMonth(dizhiMonth);

        // 天干地支日
        LunarCalendarTool.Tiangan tianganDay = LunarCalendarTool.tianganDay(lunarYear,lunarMonth,lunarDay);
        LunarCalendarTool.Dizhi dizhiDay = LunarCalendarTool.dizhiDay(lunarYear,lunarMonth,lunarDay);
        calendarDto.setTianganDay(tianganDay);
        calendarDto.setDizhiDay(dizhiDay);

        // 天干地支时辰
        LunarCalendarTool.Tiangan tianganHour = LunarCalendarTool.tianganHour(lunarYear,lunarMonth,lunarDay,lunarHour,lunarMinute);
        LunarCalendarTool.Dizhi dizhiHour = LunarCalendarTool.dizhiHour(lunarHour,lunarMinute);
        calendarDto.setTianganHour(tianganHour);
        calendarDto.setDizhiHour(dizhiHour);

        // 24节气
        LunarCalendarTool.SolarTerm24 solarTerm24 = LunarCalendarTool.getSolarTerm24(date);
        calendarDto.setSolarTerm24(solarTerm24);

        // 星座
        LunarCalendarTool.Constellation constellation = LunarCalendarTool.getConstellation(date);
        calendarDto.setConstellation(constellation);

        //某月第几周星期几节日
        LunarCalendarTool.WeekSolarHoliday weekSolarHoliday = (LunarCalendarTool.getWeekHoliday(date));
        calendarDto.setWeekSolarHoliday(weekSolarHoliday);

        //公历节日
        LunarCalendarTool.SolarHoliday solarHoliday = LunarCalendarTool.getSolarHoliday(date);
        calendarDto.setSolarHoliday(solarHoliday);

        // 农历节日
        LunarCalendarTool.TraditionalFestival traditionalFestival = LunarCalendarTool.getTraditionalFestival(lunarYear,lunarMonth,lunarDay);
        calendarDto.setTraditionalFestival(traditionalFestival);

        // 二十八星宿
        LunarCalendarTool.ChinaConstellation chinaConstellation = LunarCalendarTool.getChinaConstellation(lunarMonth,lunarDay);
        calendarDto.setChinaConstellation(chinaConstellation);
        return calendarDto;
    }

    /**
     * 根据农历日期获取详细内容
     * 如果给定的农历日期是闰月的话，则得到的公历日期是闰月第一个月对应的日期
     * @param lunarDate 农历日期
     * @return
     */
    public static CalendarInfo getCalendarByLunarDate(LunarDate lunarDate) {
        return getCalendarByDate(LunarCalendarTool.LunarToCalendar(lunarDate));
    }

    /**
     * 根据公历日期获取一段时间的连续详细内容
     * @param start 公历开始日期
     * @param end 公历结束日期
     * @return 包括开始日期和结束日期,如果开始日期大于结束日期，返回null
     */
    public static List<CalendarInfo> getCalendarsByDate(Date start, Date end) {
        List<CalendarInfo> list = null;
        if(start.compareTo(end) <= 0){
            list = new ArrayList<>();
            list.add(getCalendarByDate(start));
            int intevalDays = CalendarTool.getIntervalDays(end,start);

            for (int i = 0; i < intevalDays; i++) {
                Date temp = CalendarTool.addDay(start,i + 1);
                list.add(getCalendarByDate(temp));
            }
        }
        return list;
    }

    /**
     * 根据农历日期获取一段时间的连接详细内容
     * 如果给定的农历日期是闰月的话，则得到的公历日期是闰月第一个月对应的日期
     * @param lunarStart 农历开始日期
     * @param lunarEnd 农历结束日期
     * @return 包括开始日期和结束日期,如果开始日期大于结束日期，返回null
     */
    public static List<CalendarInfo> getCalendarsByLunarDate(LunarDate lunarStart, LunarDate lunarEnd) {
        return getCalendarsByDate(LunarCalendarTool.LunarToCalendar(lunarStart), LunarCalendarTool.LunarToCalendar(lunarEnd));
    }
}
