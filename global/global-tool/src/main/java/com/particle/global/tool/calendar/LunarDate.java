package com.particle.global.tool.calendar;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * java.utils.Date 在闰年的时候表示不了农历，这里新建一个农历日期
 * Created by yangwei
 * Created at 2018/9/20 20:48
 */
@Getter
@Setter
public class LunarDate implements Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public LunarDate(){}
    public LunarDate(int year,int month,int day,int hour,int minute,int second){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {

        return LunarCalendarTool.lunarDateToString(this, CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS_CN);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(!(obj instanceof LunarDate)){
            return false;
        }
        LunarDate date = (LunarDate) obj;
        return this.year == date.getYear()
                && this.month == date.getMonth()
                && this.day == date.getDay()
                && this.minute == date.getMinute()
                && this.hour == date.getHour()
                && this.second == date.getSecond();
    }
}
