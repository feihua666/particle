package com.particle.openplatform.client.openapirecord.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放平台开放接口调用记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Data
@Schema
public class OpenplatformOpenapiRecordVO extends AbstractBaseIdVO {

    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_app, byFieldName = "openplatformAppId", mapValueField = "name")
    @Schema(description = "开放平台应用名称")
    private String openplatformAppName;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "用户id")
    private Long userId;

    @TransBy(type = TransConstants.TRANS_USER_INFO_BY_ID,byFieldName = "userId",mapValueField = "nickname")
    @Schema(description = "用户昵称")
    private String userNickname;

    @Schema(description = "是否为app调用")
    private Boolean isApp;

    @Schema(description = "客户id")
    private Long customerId;

    @TransBy(type = TransConstants.TRANS_CRM_CUSTOMER_BY_ID,byFieldName = "customerId",mapValueField = "name")
    @Schema(description = "客户id")
    private String customerName;

    @Schema(description = "开放接口id")
    private Long openplatformOpenapiId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi, byFieldName = "openplatformOpenapiId", mapValueField = "name")
    @Schema(description = "开放接口名称")
    private String openplatformOpenapiName;

    @Schema(description = "接口地址")
    private String requestUrl;

    @Schema(description = "请求时间戳")
    private Long requestTimestamp;

    @TransBy(type = com.particle.global.light.share.trans.TransConstants.TRANS_DATETIME,byFieldName = "requestTimestamp")
    @Schema(description = "请求时间戳日期字符串")
    private String requestTimestampDateTimeStr;

    @Schema(description = "请求流水号")
    private String requestNonce;

    @Schema(description = "请求签名")
    private String requestSignature;

    @Schema(description = "请求参数md5")
    private String requestParameterMd5;

	@Schema(description = "开始处理时间")
	private LocalDateTime requestHandleAt;

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

	@Schema(description = "消费金额，单位分")
	private Integer feeAmount;

	@Schema(description = "消费金额缘由，字典id")
	private Long feeReasonDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "feeReasonDictId",mapValueField = "name")
    @Schema(description = "消费金额缘由，字典名称")
    private String feeReasonDictName;

	@Schema(description = "描述")
	private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime createAt;

}
