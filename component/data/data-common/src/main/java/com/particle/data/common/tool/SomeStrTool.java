package com.particle.data.common.tool;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 处理字符串相关的工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/5/9 10:02
 */
public class SomeStrTool {

    /**
     * 标准化公司名称
     * @param companyName
     * @return
     */
    public static String normalizeCompanyName(String companyName) {
        if (StrUtil.isEmpty(companyName)) {
            return null;
        }
        String result = companyName.replace("(", "（").replace(")", "）");
        return result;
    }
    /**
     * 标准化公司英文名称
     * @param companyEnName
     * @return
     */
    public static String normalizeCompanyEnName(String companyEnName) {
        if (StrUtil.isEmpty(companyEnName)) {
            return null;
        }
        if (StrUtil.containsAny(companyEnName, "没有", "设置")) {
            return null;
        }
        String result = companyEnName.replace("（", "(").replace("）", ")");
        return result;
    }
    /**
     * 标准化组织机构代码
     * @param orgCode
     * @return
     */
    public static String normalizeOrgCode(String orgCode) {
        if (StrUtil.isEmpty(orgCode)) {
            return null;
        }
        String result = orgCode;
        if (!result.contains("-")) {
            int length = result.length();
            int i = length - 2;
            result = result.substring(0, i) + "-" + result.substring(i,length);
        }
        return result;
    }

    /**
     * 解析日期字符串
     * @param dateStr 2023-05-09
     * @return
     */
    public static LocalDate parseLocalDateByNormal(String dateStr) {
        if (StrUtil.isNotEmpty(dateStr)) {
            return LocalDate.parse(dateStr);
        }
        return null;
    }
    /**
     * 解析日期字符串
     * @param dateStr 20230509
     * @return
     */
    public static LocalDate parseLocalDateByShortest(String dateStr) {
        if (StrUtil.isNotEmpty(dateStr) && StrUtil.isNotBlank(dateStr)) {
            return LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
        }
        return null;
    }
    /**
     * 解析日期字符串
     * @param dateTimeStr 2007/10/26 00:00:00
     * @return
     */
    public static LocalDate parseLocalDateByDateTimeWithSlash(String dateTimeStr) {
        if (StrUtil.isNotEmpty(dateTimeStr)) {
            LocalDateTime localDateTime = LocalDateTimeUtil.parse(dateTimeStr, "yyyy/MM/dd HH:mm:ss");
            return localDateTime.toLocalDate();
        }
        return null;
    }

    /**
     * 解析为整型
     * @param str
     * @return
     */
    public static Integer parseInt(String str) {
        if (StrUtil.isEmpty(str)) {
            return null;
        }
        return Integer.parseInt(str);
    }
    /**
     * 解析为BigDecimal
     * @param str
     * @return
     */
    public static BigDecimal parseBigDecimal(String str) {
        if (StrUtil.isEmpty(str)) {
            return null;
        }
        // 将字符串转换为 BigDecimal
        BigDecimal bigDecimal = new BigDecimal(str);
        return bigDecimal;
    }
    /**
     * 解析为BigDecimal，并保留两位小数，采用四舍五入模式
     * @param str
     * @return
     */
    public static BigDecimal parseBigDecimalWithScale2HalfUp(String str) {
        if (StrUtil.isEmpty(str)) {
            return null;
        }
        // 将字符串转换为 BigDecimal
        BigDecimal bigDecimal = new BigDecimal(str);
        // 保留两位小数，采用四舍五入模式
        BigDecimal result = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return result;
    }

    /**
     * 标准化邮箱，如果不是邮箱，则返回null
     * @param email
     * @return
     */
    public static String normalizeEmail(String email) {
        if (StrUtil.isEmpty(email)) {
            return null;
        }
        if (Validator.isEmail(email,true)) {
            return email;
        }
        return null;
    }
    private static Map<String,Boolean> parseBooleanMap = new HashMap<>();
    static {
        parseBooleanMap.put("true",true);
        parseBooleanMap.put("false",false);
        parseBooleanMap.put("TRUE",true);
        parseBooleanMap.put("FALSE",false);
        parseBooleanMap.put("是",true);
        parseBooleanMap.put("否",false);
        parseBooleanMap.put("1",true);
        parseBooleanMap.put("0",false);
        parseBooleanMap.put("y",true);
        parseBooleanMap.put("n",false);
        parseBooleanMap.put("Y",true);
        parseBooleanMap.put("N",false);
        parseBooleanMap.put("yes",true);
        parseBooleanMap.put("no",false);
        parseBooleanMap.put("YES",true);
        parseBooleanMap.put("NO",false);

    }

    /**
     * 将字符串解析为布尔值
     * @param str
     * @return
     */
    public static Boolean parseBoolean(String str) {

        if (StrUtil.isEmpty(str)) {
            return null;
        }
        return parseBooleanMap.get(str);
    }
}
