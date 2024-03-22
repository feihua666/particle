package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放接口文档 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Data
@Schema
public class OpenplatformDocApiDocUpdateCommand extends AbstractBaseUpdateCommand {

    @NotNull(message = "开放接口文档接口id 不能为空")
    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;

    @Schema(description = "请求地址前缀")
    private String requestUrlPrefix;


    @NotEmpty(message = "请求地址 不能为空")
        @Schema(description = "请求地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String requestUrl;

    @Schema(description = "请求类型")
    private Long requestTypeDictId;


    @Schema(description = "请求体类型")
    private Long requestBodyTypeDictId;


    @Schema(description = "响应体类型")
    private Long responseBodyTypeDictId;


    @Schema(description = "最大响应时长")
    private Integer responseMaxDuration;


    @Schema(description = "响应时长文本")
    private String responseMaxDurationDesc;


    @Schema(description = "认证方式")
    private String authenticationType;


    @Schema(description = "详细描述")
    private String descriptionDetail;


    @NotEmpty(message = "请求参数示例 不能为空")
        @Schema(description = "请求参数示例",requiredMode = Schema.RequiredMode.REQUIRED)
    private String requestParamExample;


    @NotEmpty(message = "响应参数示例 不能为空")
        @Schema(description = "响应参数示例",requiredMode = Schema.RequiredMode.REQUIRED)
    private String responseParamExample;

    @SetNullWhenNull
	@Schema(description = "开放接口文档模板id")
	private Long openplatformDocApiDocTemplateId;









}