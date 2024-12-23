package com.particle.openplatform.client.provider.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台供应商接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Data
@Schema
public class OpenplatformProviderApiVO extends AbstractBaseIdVO {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "供应商名称")
    private String name;

    @Schema(description = "数据查询数据源接口id")
    private Long dataQueryDatasourceApiId;

    @Schema(description = "计费id")
    private Long openplatformOpenapiFeeId;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "脚本类型")
    private String scriptTypeDictValue;

    @Schema(description = "脚本内容")
    private String scriptContent;

    @Schema(description = "读取等待时间")
    private Integer readTimeout;

    @Schema(description = "连接等待时间")
    private Integer connectTimeout;

    @Schema(description = "描述")
    private String remark;



}
