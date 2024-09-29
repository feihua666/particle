package com.particle.openplatform.infrastructure.gateway.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.particle.global.oss.service.GlobalOssClientService;
import com.particle.openplatform.domain.gateway.OpenplatformOssClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * <p>
 * 文件服务依赖
 * </p>
 *
 * @author yangwei
 * @since 2024/9/26 13:28
 */
@Component
public class OpenplatformOssClientGatewayImpl implements OpenplatformOssClientGateway {

    @Autowired
    private GlobalOssClientService globalOssClientService;


    @Override
    public String upload(String objectName, InputStream inputStream, String mimeType) {
        String upload;
        try {
            upload = globalOssClientService.upload(objectName, inputStream,null,mimeType,null);
        } finally {
            IoUtil.close(inputStream);
        }
        return upload;
    }

    @Override
    public String upload(String objectName, String fileAbsolutePath, String mimeType) {
        return upload(objectName, FileUtil.getInputStream(fileAbsolutePath),mimeType);
    }
}
