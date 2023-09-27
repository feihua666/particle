package com.particle.global.tool.template;

import com.particle.global.tool.document.WkhtmltopdfTool;

import java.io.File;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/9/27 11:05
 */
public class WkhtmltopdfToolTest {

    public static void main(String[] args) {
        WkhtmltopdfTool.WkhtmltopdfCmdConfig wkhtmltopdfCmdConfig = WkhtmltopdfTool.WkhtmltopdfCmdConfig.create(
                "wkhtmltopdf",
                "/var/folders/tz/4mkhbfhn0913k038jjy1tsrc0000gn/T/particle_report/杭州大自然科技股份有限公司-20230927110324.html",
                "/Users/yw/yuansu/temp/杭州大自然科技股份有限公司-20230927110324.pdf");

        wkhtmltopdfCmdConfig.setCoverFileAbsolutePath("/Users/yw/yuansu/temp/tool/cover.html");
        wkhtmltopdfCmdConfig.setTocFileAbsolutePath("/Users/yw/yuansu/temp/tool/toc.xml");
        // wkhtmltopdf 工具在有页眉和页脚时，每一页会打开两个页眉页脚文件，这可能会受到操作系统限制，打开文件句柄数过多问题，需要修改系统限制
        wkhtmltopdfCmdConfig.setFooterFileAbsolutePath("/Users/yw/yuansu/temp/tool/footer.html");
        wkhtmltopdfCmdConfig.setHeaderFileAbsolutePath("/Users/yw/yuansu/temp/tool/header.html");
        // js 调式，可以在执行转pdf命令时，将js错误信息打印出来
        //wkhtmltopdfCmdConfig.setJavascriptDebug(true);
        // 延迟转换，这会等待一定时间，等待js逻辑处理完成
        //wkhtmltopdfCmdConfig.setJavascriptDelay(true);

        WkhtmltopdfTool.htmlToPdf(wkhtmltopdfCmdConfig);
    }
}
