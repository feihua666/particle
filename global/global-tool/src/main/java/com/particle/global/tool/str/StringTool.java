package com.particle.global.tool.str;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author yangwei
 * @since 2022-04-13 19:52
 */
public class StringTool {

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,最后转为大写
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toUpperCase());
        }
        matcher.appendTail(sb);
        String result = sb.toString().toLowerCase();
        if (result.startsWith("_")) {
            result = result.substring(1);
        }
        return result;
    }

    /**
     * 下划线转驼峰,正常输出
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str.toLowerCase());
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 字符串引用，如果str为空将使用referenceStr，否则将str中的占位字符替换为referenceStr
     * @param str
     * @param referenceStr
     * @return
     */
    public static String referenceStr(String str, String referenceStr) {

        if (StrUtil.isEmpty(str)) {
            return StrUtil.emptyToNull(referenceStr);
        }
        String tempReferenceStr = StrUtil.nullToEmpty(referenceStr);
        return str.replace("{{referenceStr}}", tempReferenceStr);
    }

    /**
     * 判断是否是md5
     * @param input
     * @return
     */
    public static boolean isMd5(String input) {
        if (input.length() != 32) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((c < '0' || c > '9') && (c < 'a' || c > 'f')) {
                return false;
            }
        }
        return true;
    }
    /**
     * 拼接两个字符串，如果a的结尾与b的开头重叠，则去重后拼接。
     * @param a 第一个字符串
     * @param b 第二个字符串
     * @return 拼接后的字符串
     */
    public static String concatWithOverlap(String a, String b) {
        if (StrUtil.hasBlank(a, b)) {
            return StrUtil.nullToEmpty(a) + StrUtil.nullToEmpty(b);
        }

        // 从最大可能重叠长度开始，递减判断
        int maxOverlap = Math.min(a.length(), b.length());
        for (int i = maxOverlap; i > 0; i--) {
            // 使用subSuf获取a的末尾i位，使用sub获取b的开头i位
            if (StrUtil.subSuf(a, a.length() - i).equals(StrUtil.sub(b, 0, i))) {
                // 找到重叠，拼接a和b去除重叠部分后的内容
                return a + StrUtil.sub(b, i, b.length());
            }
        }
        // 无重叠，直接拼接
        return a + b;
    }
    /**
     * 拼接多个字符串，依次处理重叠
     * @param strings 要拼接的字符串数组
     * @return 拼接后的字符串
     */
    public static String concatWithOverlap(String... strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder(strings[0]);

        for (int i = 1; i < strings.length; i++) {
            String current = result.toString();
            String next = strings[i];

            // 对当前结果和下一个字符串进行智能拼接
            result.setLength(0);
            result.append(concatWithOverlap(current, next));
        }

        return result.toString();
    }
}
