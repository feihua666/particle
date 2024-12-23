package com.particle.openplatform.client.openapi.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放接口批量查询记录明细 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Data
@Schema
public class OpenplatformOpenapiBatchQueryRecordDetailVO extends AbstractBaseIdVO {

    @Schema(description = "开放接口批量查询记录id")
    private Long openplatformOpenapiBatchQueryRecordId;

    @Schema(description = "执行状态")
    private Long executeStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeStatusDictId",mapValueField = "name")
    @Schema(description = "执行状态对应字典名称")
    private String executeStatusDictName;

    @Schema(description = "是否成功")
    private Boolean isSuccess;

	@Schema(description = "请求流水号，查询后有值")
	private String requestNonce;

    @Schema(description = "查询时间")
    private LocalDateTime queryAt;

    @Schema(description = "追踪id")
    private String traceId;

    @Schema(description = "追踪分支id")
    private String spanId;

    @Schema(description = "请求参数")
    private String requestParam;

    @Schema(description = "请求查询字符串")
    private String queryString;

    @Schema(description = "响应结果")
    private String responseResult;



}
