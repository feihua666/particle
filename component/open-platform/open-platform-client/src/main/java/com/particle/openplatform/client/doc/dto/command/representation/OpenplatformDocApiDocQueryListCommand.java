package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Data
@Schema
public class OpenplatformDocApiDocQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "开放接口文档接口id")
	private Long openplatformDocApiId;

    @Schema(description = "请求地址前缀")
    private String requestUrlPrefix;

	@Schema(description = "内网请求地址前缀，可全局配置")
	private String requestUrlIntranetPrefix;


    @Schema(description = "请求地址")
    private String requestUrl;


    @Schema(description = "请求类型")
    private Long requestTypeDictId;


    @Schema(description = "请求体类型")
    private Long requestBodyTypeDictId;

	@Schema(description = "请求参数类型，字典id，如：string、array")
	private Long requestParamTypeDictId;

	@Schema(description = "请求参数嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object")
	private Long requestParamNestTypeDictId;


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


    @Schema(description = "请求参数示例")
    private String requestParamExample;


    @Schema(description = "响应参数示例")
    private String responseParamExample;

	@Schema(description = "开放接口文档模板id")
	private Long openplatformDocApiDocTemplateId;









}
