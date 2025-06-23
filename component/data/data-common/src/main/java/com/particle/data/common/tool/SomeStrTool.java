package com.particle.data.common.tool;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

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
     * 标准化字符串
     * @param str
     * @return
     */
    public static String normalizeStr(String str, Collection<String> invalidStrs) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        if (invalidStrs != null && invalidStrs.contains(str)) {
            return null;
        }
        return str.trim();
    }
    /**
     * 标准化字符串
     * @param str
     * @return
     */
    public static String normalizeStr(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        return str.trim();
    }
    /**
     * 标准化公司名称
     * @param companyName
     * @return
     */
    public static String normalizeCompanyName(String companyName) {
        if (StrUtil.isBlank(companyName)) {
            return null;
        }
        String result = companyName.replace("(", "（").replace(")", "）");
        return result;
    }

    /**
     * 公司名称是否一致
     * @param companyName
     * @param companyName2
     * @return
     */
    public static boolean companyNameEquals(String companyName, String companyName2) {
        return StrUtil.equals(normalizeCompanyName(companyName), normalizeCompanyName(companyName2));
    }
    /**
     * 标准统一社会信用代码
     * @param uscc
     * @return
     */
    public static String normalizeCompanyUscc(String uscc) {
        if (StrUtil.isBlank(uscc)) {
            return null;
        }
        if (!Validator.isCreditCode(uscc)) {
            return null;
        }
        return uscc;
    }
    /**
     * 标准化公司英文名称
     * @param companyEnName
     * @return
     */
    public static String normalizeCompanyEnName(String companyEnName) {
        if (StrUtil.isBlank(companyEnName)) {
            return null;
        }
        if (StrUtil.containsAny(companyEnName, "没有", "设置")) {
            return null;
        }
        String result = companyEnName.replace("（", "(").replace("）", ")");
        return result;
    }
    /**
     * 公司英文名称是否一致
     * @param companyName
     * @param companyName2
     * @return
     */
    public static boolean companyEnNameEquals(String companyName, String companyName2) {
        return StrUtil.equals(normalizeCompanyEnName(companyName), normalizeCompanyEnName(companyName2));
    }
    /**
     * 标准化注册号
     * @param regNo
     * @return
     */
    public static String normalizeCompanyRegNo(String regNo) {
        if (StrUtil.isBlank(regNo)) {
            return null;
        }
        return regNo;
    }
    /**
     * 标准化组织机构代码
     * @param orgCode
     * @return
     */
    public static String normalizeCompanyOrgCode(String orgCode) {
        if (StrUtil.isBlank(orgCode)) {
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
     * @param dateStr 2023-05-09 或 2021-12-15 00:00:00
     * @return
     */
    public static LocalDate parseLocalDateByNormal(String dateStr) {
        if (StrUtil.isNotEmpty(dateStr)) {
            return LocalDate.parse(dateStr.substring(0, 10));
        }
        return null;
    }
    /**
     * 解析日期字符串
     * @param dateStr 毫秒时间戳（13位）如：1748509699724、秒时间戳（10位）如：1748509699
     * @return
     */
    public static LocalDate parseLocalDateByTimestamp(String dateStr) {

        if (StrUtil.isNotEmpty(dateStr)) {
            Long timestamp = null;
            if (dateStr.length() == 13) {
                timestamp = Long.parseLong(dateStr);
            } else if (dateStr.length() == 10) {
                timestamp = Long.parseLong(dateStr) * 1000;
            }
            if (timestamp != null) {
                LocalDate localDate = Instant.ofEpochMilli(timestamp)
                        .atZone(ZoneId.systemDefault()) // 使用系统默认时区
                        .toLocalDate();
                return localDate;
            }
        }
        return null;
    }
    /**
     * 解析日期字符串
     * @param dateStr 2018年06月18日
     * @return
     */
    public static LocalDate parseLocalDateByChinese(String dateStr) {
        if (StrUtil.isNotEmpty(dateStr)) {
            // 自定义日期格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
            return LocalDate.parse(dateStr, formatter);
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
        if (StrUtil.isBlank(str)) {
            return null;
        }
        return Integer.parseInt(str);
    }

    /**
     * 提取数字前缀
     * @param str
     * @return
     */
    public static String extractPrefixNumber(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        Matcher matcher = SomeStrAmountTool.prefixNumberPattern.matcher(str.trim());
        if (matcher.find()) {
            // 提取数字部分
            String numberStr = matcher.group(1);
            return numberStr;
        }
        return null;
    }

    /**
     * 解析为BigDecimal
     * @param str
     * @return
     */
    public static BigDecimal parseBigDecimal(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        // 将字符串转换为 BigDecimal
        BigDecimal bigDecimal = new BigDecimal(str);
        return bigDecimal;
    }
    /**
     * 解析为BigDecimal
     * @param str 如：2.3、2.3%
     * @return
     */
    public static BigDecimal parseBigDecimalWithPercent(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        // 检查是否包含百分号
        if (str.endsWith("%")) {
            // 去掉百分号并转换为小数形式
            str = str.substring(0, str.length() - 1);
            BigDecimal bigDecimal = new BigDecimal(str);
            return bigDecimal.divide(new BigDecimal("100"));
        } else {
            // 直接转换为 BigDecimal
            return new BigDecimal(str);
        }
    }
    /**
     * 解析为BigDecimal，并保留两位小数，采用四舍五入模式
     * @param str
     * @return
     */
    public static BigDecimal parseBigDecimalWithScale2HalfUp(String str) {
        if (StrUtil.isBlank(str)) {
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
        if (StrUtil.isBlank(email)) {
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

        if (StrUtil.isBlank(str)) {
            return null;
        }
        return parseBooleanMap.get(str);
    }

    /**
     * 是否企业选择不公示，主要是年报中数据判断
     * @param str
     * @return
     */
    public static Boolean isNotPublic(String str) {
        return "企业选择不公示".equals(str);
    }
}
