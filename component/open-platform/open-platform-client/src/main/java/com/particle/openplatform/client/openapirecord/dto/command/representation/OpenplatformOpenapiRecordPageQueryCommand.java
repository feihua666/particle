package com.particle.openplatform.client.openapirecord.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口调用记录 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Data
@Schema
public class OpenplatformOpenapiRecordPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;


    @Schema(description = "应用id")
    private String appId;


    @Schema(description = "用户id")
    private Long userId;


    @Schema(description = "是否为app调用")
    private Boolean isApp;


    @Schema(description = "客户id")
    private Long customerId;


    @Schema(description = "开放接口id")
    private Long openplatformOpenapiId;


    @Schema(description = "接口地址")
    private String requestUrl;



    @Schema(description = "请求流水号")
    private String requestNonce;



    @Schema(description = "请求参数md5")
    private String requestParameterMd5;


    @Schema(description = "响应结果md5")
    private String responseResultMd5;


    @Schema(description = "日志追踪id")
    private String traceId;


    @Schema(description = "处理时长")
    private Integer handleDuration;


    @Schema(description = "是否包含有效响应数据")
    private Boolean isResponseHasEffectiveValue;


    @Schema(description = "响应http状态码")
    private Integer responseHttpStatus;


    @Schema(description = "响应业务状态码")
    private String responseBusinessStatus;

	@Schema(description = "是否存在供应商调用记录")
	private Boolean isExistProviderRecord;

	@Schema(description = "描述")
	private String remark;









}