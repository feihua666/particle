package com.particle.data.common.tool;

import java.time.LocalDate;

/**
 * <p>
 * 日期的相关 工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/5/31 18:14
 */
public class SomeDateTool {
    /**
     * 判断当前日期是否在7月1号及之后
     * 主要用于判断年报，在7月1号及之后，正常一般有 T-1 年的年报了，否则年报为 T-2 年
     * @return
     */
    public static Boolean isNowAfter7Month1Day() {
        LocalDate now = LocalDate.now();
        int monthValue = now.getMonthValue();
        if (monthValue >= 7) {
            return true;
        }
        return false;
    }

    /**
     * 获取最新的年报年份 ，7月1号及之后，正常一般有 T-1 年的年报了，否则年报为 T-2 年
     * @return
     */
    public static Integer getLatestAnnualReportYear() {
        LocalDate now = LocalDate.now();
        int minus = 2;
        if (isNowAfter7Month1Day()) {
            minus = 1;
        }
        return now.getYear() - minus;
    }
}
