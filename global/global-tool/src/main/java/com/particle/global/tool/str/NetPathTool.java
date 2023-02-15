package com.particle.global.tool.str;

import cn.hutool.core.util.StrUtil;

import java.io.File;

/**
 * 文件路径工具
 * @author yangwei
 * @since 2022-04-13 19:52
 */
public class NetPathTool {

    public static final String SLASH = "/";

    /**
     * 确保开始以/开头
     * @param str
     * @return
     */
    public static String ensureBeginSlash(String str){
        return PathTool.ensureBeginSeparator(str,SLASH);
    }
    /**
     * 确保开始不以/开头
     * @param str
     * @return
     */
    public static String ensureNotBeginSlash(String str){
        return PathTool.ensureNotBeginSeparator(str,SLASH);
    }
    /**
     * 确保结尾以/结束
     * @param str
     * @return
     */
    public static String ensureEndSlash(String str){
        return PathTool.ensureEndSeparator(str,SLASH);
    }
    /**
     * 确保不以/结尾
     * @param str
     * @return
     */
    public static String ensureNotEndSlash(String str){
        return PathTool.ensureNotEndSeparator(str,SLASH);
    }

    /**
     * 拼接路径,第一个元素的开头和最后一个元素的结尾不拼接/，
     * @param str
     * @return
     */
    public static String concat(String ...str){
        return PathTool.concat(SLASH, str);
    }
}
