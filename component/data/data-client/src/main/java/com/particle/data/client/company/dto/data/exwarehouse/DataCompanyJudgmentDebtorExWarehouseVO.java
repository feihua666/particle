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
 * 企业被执行人 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyJudgmentDebtorExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "案号")
    private String caseNo;

    @Schema(description = "被执行人名称")
    private String executedPersonName;

    @Schema(description = "是否被执行人为自然人")
    private Boolean isExecutedPersonNaturalPerson;

    @Schema(description = "法人公司id")
    private Long executedPersonCompanyId;

    @Schema(description = "法人个人id")
    private Long executedPersonCompanyPersonId;

    @Schema(description = "被执行人证照/证件号码")
    private String executedPersonLicenseNo;

    @Schema(description = "执行法院公司id")
    private Long executeCourtCompanyId;

    @Schema(description = "执行法院名称")
    private String executeCourtName;

    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;

    @Schema(description = "结案日期")
    private LocalDate finishedCaseDate;

    @Schema(description = "是否已结案")
    private Boolean isFinished;

    @Schema(description = "执行标的金额（万元）")
    private BigDecimal executeAmount;

    @Schema(description = "执行标的金额币种")
    private Long executeAmountCurrencyDictId;

	@Schema(description = "数据md5,case_no + executed_person_name + file_case_date")
	private String dataMd5;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeAmountCurrencyDictId",mapValueField = "name")
    @Schema(description = "执行标的金额币种对应字典名称")
    private String executeAmountCurrencyDictName;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}