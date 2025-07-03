package com.particle.report.adapter.api.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.particle.global.oss.service.GlobalOssClientService;
import com.particle.global.tool.file.FileTool;
import com.particle.report.app.executor.IReportApiGenerateResultHandler;
import com.particle.report.client.dto.data.ReportApiGenerateVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;

/**
 * <p>
 * 如果生成的是一个文件，那么将文件上传到oss中
 * 参见配置 {@link com.particle.report.ReportAutoConfiguration#reportApiGenerateResultHandler()}
 * </p>
 *
 * @author yangwei
 * @since 2023/10/8 16:05
 */
@Slf4j
public class ReportApiGenerateResultOssHandlerImpl implements IReportApiGenerateResultHandler {

    // 不能添加前缀斜杠，经测试 阿里 oss key不支持以斜杠开头
    private static final String path = "report";

    @Autowired
    private GlobalOssClientService globalOssClientService;


    @SneakyThrows
    @Override
    public void handle(ReportApiGenerateVO reportApiGenerateVO) {
        String url = reportApiGenerateVO.getUrl();
        if (!FileUtil.exist(url)) {
            log.debug("report url is not a file or file not exist, upload to oss ignored!.url={}", url);
            return;
        }
        File file = FileUtil.file(url);
        String originalFilename = file.getName();
        InputStream inputStream = new FileInputStream(file);
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = IdUtil.fastSimpleUUID()+ "--" + originalFilename;
        String objectName =  Optional.ofNullable(path).orElse("") + "/" + DateUtil.date().toDateStr() + "/" + newFileName;
        Long fileLength = file.length();
        log.info("start upload report file to oss，path={},originalFilename={},fileLength={},objectName={}",path,originalFilename,fileLength,objectName);

        String mimeType = FileTool.getMimeType(objectName);
        String upload = globalOssClientService.upload(objectName, inputStream,null,mimeType,true);
        IoUtil.close(inputStream);

        log.info("end upload report file to oss,uploadResult={}",upload);
        reportApiGenerateVO.setUrl(upload);
    }
}
