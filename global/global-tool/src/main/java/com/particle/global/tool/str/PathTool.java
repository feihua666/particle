package com.particle.global.tool.str;

import cn.hutool.core.util.StrUtil;

/**
 * 网络路径工具
 * @author yangwei
 * @since 2022-04-13 19:52
 */
public class PathTool {
    
    /**
     * 确保开始以/开头
     * @param str
     * @return
     */
    public static String ensureBeginSeparator(String str,String separator){
        if (str != null) {
            if (!str.startsWith(separator)) {
                return separator + str;
            }
        }
        return str;
    }
    /**
     * 确保开始不以/开头
     * @param str
     * @return
     */
    public static String ensureNotBeginSeparator(String str,String separator){
        if (str != null) {
            if (str.startsWith(separator)) {
                return str.substring(1);
            }
        }
        return str;
    }
    /**
     * 确保结尾以/结束
     * @param str
     * @return
     */
    public static String ensureEndSeparator(String str,String separator){
        if (str != null) {
            if (!str.endsWith(separator)) {
                return str + separator;
            }
        }
        return str;
    }
    /**
     * 确保不以/结尾
     * @param str
     * @return
     */
    public static String ensureNotEndSeparator(String str,String separator){
        if (str != null) {
            if (str.endsWith(separator)) {
                return str.substring(0, str.length() - 1);
            }
        }
        return str;
    }
    /**
     * 确保开始以.开头
     * @param str
     * @return
     */
    public static String ensureBeginDot(String str){
        if (str != null) {
            if (!str.startsWith(".")) {
                return "." + str;
            }
        }
        return str;
    }
    /**
     * 拼接路径,第一个元素的开头和最后一个元素的结尾不拼接/，
     * @param str
     * @return
     */
    public static String concat(String separator,String ...str){
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        if (str.length == 1) {
            sb.append(StrUtil.nullToEmpty(str[0]));
        }else if (str.length == 2) {
            sb.append(StrUtil.nullToEmpty(ensureNotEndSeparator(str[0],separator)));
            sb.append(StrUtil.nullToEmpty(ensureBeginSeparator(str[1],separator)));
        }else {
            for (int i = 0; i < str.length; i++) {
                if(i == 0){
                    sb.append(StrUtil.nullToEmpty(ensureNotEndSeparator(str[i],separator)));
                }else if(i == str.length - 1){
                    sb.append(StrUtil.nullToEmpty(ensureBeginSeparator(str[i],separator)));
                }else {
                    sb.append(StrUtil.nullToEmpty(ensureNotEndSeparator(ensureBeginSeparator(str[i],separator),separator)));
                }
            }
        }

        return sb.toString();
    }
}
