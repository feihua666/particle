package com.particle.openplatform.client.providerrecord.dto.command.representation;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Gt;
import com.particle.global.light.share.mybatis.anno.Lt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口供应商调用记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Data
@Schema
public class OpenplatformProviderRecordQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "调用记录id")
    private Long openplatformOpenapiRecordId;


    @Schema(description = "客户id")
    private Long customerId;


    @Schema(description = "接口地址")
    private String requestUrl;


    @Schema(description = "请求参数md5")
    private String requestParameterMd5;

    @Gt("requestAt")
    @Schema(description = "开始请求时间")
    private LocalDateTime requestAtStart;

    @Lt("requestAt")
    @Schema(description = "开始请求时间")
    private LocalDateTime requestAtEnd;


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


    @Schema(description = "响应业务编码或业务状态码")
    private String responseBusinessStatus;


    @Schema(description = "供应商id")
    private Long openplatformProviderId;


    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;

	@Schema(description = "是否命中缓存")
	private Boolean isCacheHit;

	@Schema(description = "消费金额，单位分")
	private Integer feeAmount;

	@Schema(description = "消费金额缘由，字典id")
	private Long feeReasonDictId;

	@Schema(description = "描述")
	private String remark;









}