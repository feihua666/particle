package com.particle.openplatform.client.doc.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 开放接口文档模板 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Data
@Schema
public class OpenplatformDocApiDocTemplatePageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "模板名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "请求地址前缀,左前缀匹配")
    private String requestUrlPrefix;

	@Schema(description = "内网请求地址前缀，可全局配置")
	private String requestUrlIntranetPrefix;


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









}