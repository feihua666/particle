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
        return sb.toString().toLowerCase();
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
}
