package com.particle.openplatform.client.providerrecord.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放平台开放接口供应商调用记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Data
@Schema
public class OpenplatformProviderRecordVO extends AbstractBaseIdVO {

    @Schema(description = "调用记录id")
    private Long openplatformOpenapiRecordId;

    @Schema(description = "客户id")
    private Long customerId;

	@Schema(description = "接口名称")
	private String requestName;

    @TransBy(type = TransConstants.TRANS_CRM_CUSTOMER_BY_ID,byFieldName = "customerId",mapValueField = "name")
    @Schema(description = "客户id")
    private String customerName;

    @Schema(description = "接口地址")
    private String requestUrl;

    @Schema(description = "请求参数md5")
    private String requestParameterMd5;

	@Schema(description = "开始请求时间")
	private LocalDateTime requestAt;

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

    @Schema(description = "响应业务编码或业务状态码")
    private String responseBusinessStatus;

    @Schema(description = "供应商id")
    private Long openplatformProviderId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_provider, byFieldName = "openplatformProviderId", mapValueField = "name")
    @Schema(description = "供应商名称")
    private String openplatformProviderName;

    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;

	@Schema(description = "是否命中缓存")
	private Boolean isCacheHit;

	@Schema(description = "消费金额，单位分")
	private Integer feeAmount;

	@Schema(description = "消费金额缘由，字典id")
	private Long feeReasonDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "feeReasonDictId",mapValueField = "name")
    @Schema(description = "消费金额缘由，字典名称")
    private String feeReasonDictName;

    @TransBy(type = TransConstants.TRANS_DATAQUERY_PROVIDER_BY_USER_ID,byFieldName = "dataQueryProviderId",mapValueField = "name")
    @Schema(description = "数据查询供应商")
    private String dataQueryProviderName;

	@Schema(description = "描述")
	private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime createAt;

}