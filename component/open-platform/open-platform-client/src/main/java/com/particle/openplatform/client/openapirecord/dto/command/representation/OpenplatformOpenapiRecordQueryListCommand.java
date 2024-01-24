package com.particle.openplatform.client.openapirecord.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Gt;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.Lt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 开放平台开放接口调用记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Data
@Schema
public class OpenplatformOpenapiRecordQueryListCommand extends AbstractBaseQueryCommand {



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

    @Gt("requestHandleAt")
    @Schema(description = "开始处理时间开始")
    private LocalDateTime requestHandleAtStart;

    @Lt("requestHandleAt")
    @Schema(description = "开始处理时间结束")
    private LocalDateTime requestHandleAtEnd;


    @Schema(description = "响应结果md5")
    private String responseResultMd5;


    @Schema(description = "日志追踪id")
    private String traceId;



    @Gt("handleDuration")
    @Schema(description = "处理时长开始")
    private Integer handleDurationStart;

    @Lt("handleDuration")
    @Schema(description = "处理时长结束")
    private Integer handleDurationEnd;


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