package com.particle.data.common.tool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>
 * 数字相关 工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/6/3 11:41
 */
public class SomeNumberTool {

    /**
     * 计算百分比
     *
     * @param num1 分子
     * @param num2 分母
     * @return
     */
    public static BigDecimal divGetBigDecimal(BigDecimal num1, Integer num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        if (num2 == 0) {
            return null;
        }
        BigDecimal bigDecimal = NumberUtil.div(num1, num2, 2);
        return bigDecimal;
    }
    /**
     * 计算百分比
     *
     * @param num1 分子
     * @param num2 分母
     * @return
     */
    public static BigDecimal divGetBigDecimal(Integer num1, Integer num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        if (num2 == 0) {
            return null;
        }
        BigDecimal bigDecimal = NumberUtil.div(num1, num2, 2);
        return bigDecimal;
    }
    /**
     * 计算百分比
     *
     * @param num1 分子
     * @param num2 分母
     * @return
     */
    public static BigDecimal divBigDecimal(BigDecimal num1, BigDecimal num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        if (num2.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        BigDecimal bigDecimal = NumberUtil.div(num1, num2, 2);
        return bigDecimal;
    }
    /**
     * 数字转中文
     * @param n
     * @return
     */
    public static String toChinese(Number n) {
        return Convert.digitToChinese(n);
    }

    /**
     * 取最大值
     * @param bigDecimals
     * @return
     */
    public static BigDecimal maxBigDecimal(BigDecimal... bigDecimals) {
        BigDecimal max = null;
        for (BigDecimal bigDecimal : bigDecimals) {
            if (bigDecimal == null) {
                bigDecimal = BigDecimal.ZERO;
            }
            if (max == null) {
                max = bigDecimal;
            } else {
                if (max.compareTo(bigDecimal) < 0) {
                    max = bigDecimal;
                }
            }
        }
        return max;
    }

    /**
     * 求和
     * @param bigDecimals
     * @return
     */
    public static BigDecimal addBigDecimal(BigDecimal... bigDecimals) {

        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : bigDecimals) {
            if (bigDecimal == null) {
                bigDecimal = BigDecimal.ZERO;
            }
            sum = sum.add(bigDecimal);
        }
        return sum;
    }

}
