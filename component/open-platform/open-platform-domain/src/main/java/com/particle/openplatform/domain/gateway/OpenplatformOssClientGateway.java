package com.particle.openplatform.domain.gateway;

import java.io.InputStream;

/**
 * <p>
 * 开放平台网关对象存储服务接口
 * </p>
 *
 * @author yangwei
 * @since 2024/9/26 13:27
 */
public interface OpenplatformOssClientGateway {

    /**
     * 上传文件
     * @param objectName 对象名称，可以带路径，如：/b/c.txt
     * @param inputStream 输入文件流
     * @param mimeType 文件类型，如：application/octet-stream
     * @return 返回文件url
     */
    public String upload(String objectName, InputStream inputStream, String mimeType);

    /**
     * 文件上传，文件路径为绝对路径
     * @param objectName objectName 对象名称，可以带路径，如：/b/c.txt
     * @param fileAbsolutePath 要上传的文件绝对路径
     * @param mimeType mimeType 文件类型，如：application/octet-stream
     * @return 返回文件url
     */
    public String upload(String objectName, String fileAbsolutePath, String mimeType);
}
