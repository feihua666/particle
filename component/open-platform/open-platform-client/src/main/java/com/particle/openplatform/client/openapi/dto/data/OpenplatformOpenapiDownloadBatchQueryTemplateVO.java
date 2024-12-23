package com.particle.openplatform.client.openapi.dto.data;

import com.particle.common.client.dto.data.AbstractBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.InputStream;

/**
 * <p>
 * 开放接口下载批量查询模板 响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Data
@Schema
public class OpenplatformOpenapiDownloadBatchQueryTemplateVO extends AbstractBaseVO {

    @Schema(description = "文件输入流")
    private InputStream in;

    @Schema(description = "文件名称,不包括后缀")
    private String name;

    @Schema(description = "文件后缀，如：.xlsx")
    private String extensionSuffix;

    public static OpenplatformOpenapiDownloadBatchQueryTemplateVO create(
            InputStream in,
            String name,
            String extensionSuffix
    ) {
        OpenplatformOpenapiDownloadBatchQueryTemplateVO openplatformOpenapiDownloadBatchQueryTemplateVO = new OpenplatformOpenapiDownloadBatchQueryTemplateVO();
        openplatformOpenapiDownloadBatchQueryTemplateVO.in = in;
        openplatformOpenapiDownloadBatchQueryTemplateVO.name = name;
        openplatformOpenapiDownloadBatchQueryTemplateVO.extensionSuffix = extensionSuffix;
        return openplatformOpenapiDownloadBatchQueryTemplateVO;
    }
}
