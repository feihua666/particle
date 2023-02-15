package com.particle.global.tool.str;

import java.io.File;

/**
 * 文件路径工具
 * @author yangwei
 * @since 2022-04-13 19:52
 */
public class FilePathTool {

    public static final String FileSeparator = File.separator;


    /**
     * 确保开始以/开头
     * @param str
     * @return
     */
    public static String ensureBeginFileSeparator(String str){
        return PathTool.ensureBeginSeparator(str, FileSeparator);
    }
    /**
     * 确保开始不以/开头
     * @param str
     * @return
     */
    public static String ensureNotBeginFileSeparator(String str){
        return PathTool.ensureNotBeginSeparator(str, FileSeparator);
    }
    /**
     * 确保结尾以/结束
     * @param str
     * @return
     */
    public static String ensureEndFileSeparator(String str){
        return PathTool.ensureEndSeparator(str, FileSeparator);
    }
    /**
     * 确保不以/结尾
     * @param str
     * @return
     */
    public static String ensureNotEndFileSeparator(String str){
        return PathTool.ensureNotEndSeparator(str, FileSeparator);
    }

    /**
     * 拼接路径,第一个元素的开头和最后一个元素的结尾不拼接/，
     * @param str
     * @return
     */
    public static String concat(String ...str){
        return PathTool.concat(FileSeparator, str);
    }
}
