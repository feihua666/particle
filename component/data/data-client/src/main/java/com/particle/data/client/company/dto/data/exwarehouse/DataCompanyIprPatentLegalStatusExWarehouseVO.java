package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业知识产权专利法律状态 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentLegalStatusExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;

    @Schema(description = "法律状态，字典id")
    private Long legalStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "legalStatusDictId",mapValueField = "value")
    @Schema(description = "法律状态，字典值")
    private String legalStatusDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "legalStatusDictId",mapValueField = "name")
    @Schema(description = "法律状态，字典名称")
    private String legalStatusDictName;

    @Schema(description = "法律状态代码")
    private String legalStatusCode;

    @Schema(description = "原始法律状态名称")
    private String legalStatusName;

    @Schema(description = "英文法律状态名称")
    private String legalStatusNameEn;

    @Schema(description = "中文法律状态名称")
    private String legalStatusNameCn;

    @Schema(description = "原始法律状态详情")
    private String legalStatusDetail;

    @Schema(description = "英文法律状态详情")
    private String legalStatusDetailEn;

    @Schema(description = "中文法律状态详情")
    private String legalStatusDetailCn;

    @Schema(description = "法律状态日期")
    private LocalDate legalStatusDate;

	@Schema(description = "数据md5,legal_status_name + legal_status_detail + legal_status_date")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
