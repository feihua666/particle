package com.particle.global.tool.str;

import cn.hutool.core.util.StrUtil;

/**
 * 路径工具
 * @author yangwei
 * @since 2022-04-13 19:52
 */
public class PathTool {

    public static final String SLASH = "/";

    /**
     * 确保开始以/开头
     * @param str
     * @return
     */
    public static String ensureBeginSlash(String str){
        if (str != null) {
            if (!str.startsWith(SLASH)) {
                return SLASH + str;
            }
        }
        return str;
    }
    /**
     * 确保开始不以/开头
     * @param str
     * @return
     */
    public static String ensureNotBeginSlash(String str){
        if (str != null) {
            if (str.startsWith(SLASH)) {
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
    public static String ensureEndSlash(String str){
        if (str != null) {
            if (!str.endsWith(SLASH)) {
                return str + SLASH;
            }
        }
        return str;
    }
    /**
     * 确保不以/结尾
     * @param str
     * @return
     */
    public static String ensureNotEndSlash(String str){
        if (str != null) {
            if (str.endsWith(SLASH)) {
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
    public static String concat(String ...str){
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        if (str.length == 1) {
            sb.append(StrUtil.nullToEmpty(str[0]));
        }else if (str.length == 2) {
            sb.append(StrUtil.nullToEmpty(ensureNotEndSlash(str[0])));
            sb.append(StrUtil.nullToEmpty(ensureBeginSlash(str[1])));
        }else {
            for (int i = 0; i < str.length; i++) {
                if(i == 0){
                    sb.append(StrUtil.nullToEmpty(ensureNotEndSlash(str[i])));
                }else if(i == str.length - 1){
                    sb.append(StrUtil.nullToEmpty(ensureBeginSlash(str[i])));
                }else {
                    sb.append(StrUtil.nullToEmpty(ensureNotEndSlash(ensureBeginSlash(str[i]))));
                }
            }
        }

        return sb.toString();
    }
}
