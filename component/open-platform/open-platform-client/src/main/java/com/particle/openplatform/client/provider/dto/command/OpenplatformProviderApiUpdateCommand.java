package com.particle.openplatform.client.provider.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 开放平台供应商接口 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Data
@Schema
public class OpenplatformProviderApiUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "供应商名称 不能为空")
        @Schema(description = "供应商名称",requiredMode = Schema.RequiredMode.REQUIRED)
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
