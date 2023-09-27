package com.particle.global.tool.document;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 封装基于 wkhtmltopdf 的工具，需要预先安装 wkhtmltopdf 工具到系统中，并保证 wkhtmltopdf 命令可用
 * 参考：https://wkhtmltopdf.org/
 * </p>
 *
 * @author yangwei
 * @since 2023/9/27 09:51
 */
@Slf4j
public class WkhtmltopdfTool {



    /**
     * html转pdf工具封装
     * @param cmdConfig
     */
    public static void   htmlToPdf(WkhtmltopdfCmdConfig cmdConfig){


        long start = System.currentTimeMillis();

        log.info("Wkhtmltopdf start");
        String cmd = cmdConfig.toCmdString(false);
        log.info("Wkhtmltopdf cmd={}",cmd);

        exec("",cmd);

        long duration = System.currentTimeMillis() - start;
        log.info("Wkhtmltopdf end,duration={}ms",duration);
    }

    /**
     * html转pdf工具封装
     * @param cmdConfig
     */
    public static void htmlToPdfEn(WkhtmltopdfCmdConfig cmdConfig){

        long start = System.currentTimeMillis();
        log.info("Wkhtmltopdf en start");
        String cmd = cmdConfig.toCmdString(true);
        log.info("Wkhtmltopdf en cmd={}",cmd);

        exec("",cmd);

        long duration = System.currentTimeMillis() - start;
        log.info("Wkhtmltopdf en end,duration={}ms",duration);
    }


    /**
     * 直接执行最原始的转换命令，需要系统预先安装对应版本的 wkhtmltopdf
     * @param cmd 命令 必填 一般为 wkhtmltopdf
     * @param flags 参数列表，拼接成一个字符串
     */
    public static void exec(String cmd,String flags) {

        OsInfo osInfo = SystemUtil.getOsInfo();
        String[] cmds = null;
        String cmdNew = cmd + " " + flags;
        if (osInfo.isLinux()) {
            log.debug("current os is linux");
            cmds = new String[]{"/bin/sh", "-c", cmdNew};
        } else if (osInfo.isWindows()) {
            log.debug("current os is windows");
            cmds = new String[]{"cmd.exe", "/C", "start " + cmdNew};
        } else if (osInfo.isMac()) {
            log.debug("current os is mac");
            cmds = new String[]{"/bin/sh", "-c", cmdNew};
        }
        List<String> resultLines = null;
        try {
            resultLines = RuntimeUtil.execForLines(cmds);
        } catch (Exception e) {
            log.error("exec cmd error,cmds={}", Arrays.stream(cmds).collect(Collectors.joining(" ")),e);
            // 异常时打印一下输出信息
            if (CollectionUtil.isNotEmpty(resultLines)) {
                log.error("resultLines is:{}",resultLines.stream().collect(Collectors.joining("\n")));
            }
        }
    }

    /**
     * 保证 wkhtmltopdf 命令可用
     * @param flags
     */
    public static void execWithDefaultCmd(String flags) {
        exec("wkhtmltopdf", flags);
    }


    /**
     * 提供简单和配置，以供方便的生成pdf
     */
    @Data
    public static class WkhtmltopdfCmdConfig{
        /**
         * 文件路径，命令，如：wkhtmltopdf（如果命令全局可用）否则 /usr/bin/wkhtmltopdf
         */
        private String wkhtmltopdfAbsolutePath;
        /**
         * 页脚 html 绝对路径
         */
        private String footerFileAbsolutePath;
        /**
         * 页头 html 绝对路径
         */
        private String headerFileAbsolutePath;
        /**
         * 封面 html 绝对路径
         */
        private String coverFileAbsolutePath;
        /**
         * 目录配置文件绝对路径
         */
        private String tocFileAbsolutePath;
        /**
         * 生成后的文件绝对路径
         */
        private String saveFileAbsolutePath;
        /**
         * 源生成文件 html
         */
        private String htmlFileAbsolutePath;
        /**
         * 开启js调试日志
         */
        private boolean javascriptDebug;
        /**
         * 开启js延迟，由于js加载需要时间，可以延迟生成pdf
         */
        private boolean javascriptDelay;
        /**
         * 与js延迟配套使用，延迟的毫秒数
         */
        private int javascriptDelayMs = 500;


        /**
         * 最少参数创建
         * @param wkhtmltopdfAbsolutePath
         * @param htmlFileAbsolutePath
         * @param saveFileAbsolutePath
         * @return
         */
        public static WkhtmltopdfCmdConfig create(
                String wkhtmltopdfAbsolutePath,
                String htmlFileAbsolutePath,
                String saveFileAbsolutePath
        ){
            WkhtmltopdfCmdConfig wkhtmltopdfCmdConfig = new WkhtmltopdfCmdConfig();
            wkhtmltopdfCmdConfig.setWkhtmltopdfAbsolutePath(wkhtmltopdfAbsolutePath);
            wkhtmltopdfCmdConfig.setHtmlFileAbsolutePath(htmlFileAbsolutePath);
            wkhtmltopdfCmdConfig.setSaveFileAbsolutePath(saveFileAbsolutePath);
            return wkhtmltopdfCmdConfig;
        }

        /**
         * 可执行命令字段串 true英文 false中文
         * @return
         */
        public String toCmdString(Boolean language){
            StringBuilder cmd = new StringBuilder();
            cmd.append(wkhtmltopdfAbsolutePath);
            cmd.append(" ");
            cmd.append("--page-size A4");// 参数
            cmd.append(" ");
            cmd.append("--footer-center ").append(language?"\"  Page [page] of [topage]  \"":"\"——  第 [page]页/共 [topage]页  ——\"");
            cmd.append(" ");
            cmd.append("--footer-font-size ").append("8");
            cmd.append(" ");
            cmd.append("--footer-spacing ").append("10");
            cmd.append(" ");
            cmd.append("--margin-bottom ").append("10");

            if (javascriptDebug) {
                cmd.append(" ");
                cmd.append("--debug-javascript");
            }
            if (javascriptDelay) {
                cmd.append(" ");
                cmd.append("--javascript-delay ").append(javascriptDelayMs);
            }


            if (StrUtil.isNotEmpty(footerFileAbsolutePath)) {
                cmd.append(" ");
                cmd.append("--footer-html ").append(footerFileAbsolutePath);
            }
            cmd.append(" ");
            cmd.append("--header-spacing 10");
            if (StrUtil.isNotEmpty(headerFileAbsolutePath)) {
                cmd.append(" ");
                cmd.append("--header-html ").append(headerFileAbsolutePath);
            }
            if (StrUtil.isNotEmpty(coverFileAbsolutePath)) {
                cmd.append(" ");
                cmd.append("cover ").append(coverFileAbsolutePath);
            }

            if (StrUtil.isNotEmpty(tocFileAbsolutePath)) {
                cmd.append(" ");
                cmd.append("toc --xsl-style-sheet ").append(tocFileAbsolutePath);
            }

            cmd.append(" ");
            cmd.append(htmlFileAbsolutePath);//可多个
            cmd.append(" ");
            cmd.append(saveFileAbsolutePath);//保存路径

            return cmd.toString();
        }

    }
}
