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
 * 企业年报对外投资 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignInvestExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;

    @Schema(description = "年报年度")
    private Integer year;

    @Schema(description = "序号")
    private Integer serialNumber;

    @Schema(description = "对外投资企业")
    private Long investCompanyId;

	@Schema(description = "对外投资企业名称")
	private String investCompanyName;

	@Schema(description = "对外投资企业统一社会信用代码")
	private String investCompanyUscc;

    @Schema(description = "对外投资比例")
    private BigDecimal investPercent;

    @Schema(description = "对外投资金额（万元）")
    private BigDecimal investAmount;

    @Schema(description = "对外投资金额币种")
    private Long investAmountCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "investAmountCurrencyDictId",mapValueField = "value")
    @Schema(description = "对外投资金额币种对应字典值")
    private String investAmountCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "investAmountCurrencyDictId",mapValueField = "name")
    @Schema(description = "对外投资金额币种对应字典名称")
    private String investAmountCurrencyDictName;

	@Schema(description = "数据md5,invest_company_name + invest_company_uscc")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
