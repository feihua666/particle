package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业裁判文书 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "案号")
    private String caseNo;

    @Schema(description = "案由")
    private String caseReason;

    @Schema(description = "案件审理法院公司id")
    private Long caseCourtCompanyId;

    @Schema(description = "案件审理法院名称")
    private String caseCourtName;

    @Schema(description = "案件裁判日期")
    private LocalDate caseJudgeDate;

    @Schema(description = "案件审理程序")
    private String caseTrialProcedure;

    @Schema(description = "法律依据")
    private String caseLegalBasis;

    @Schema(description = "案件类型")
    private Long caseTypeDictId;

	@Schema(description = "案件类型名称")
	private String caseTypeName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "caseTypeDictId",mapValueField = "value")
    @Schema(description = "案件类型对应字典值")
    private String caseTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "caseTypeDictId",mapValueField = "name")
    @Schema(description = "案件类型对应字典名称")
    private String caseTypeDictName;

    @Schema(description = "案件涉及金额（万元）")
    private BigDecimal caseAmount;

    @Schema(description = "案件涉及金额币种")
    private Long caseAmountCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "caseAmountCurrencyDictId",mapValueField = "name")
    @Schema(description = "案件涉及金额币种对应字典名称")
    private String caseAmountCurrencyDictName;

    @Schema(description = "文书类型")
    private Long documentTypeDictId;

	@Schema(description = "文书类型名称")
	private String documentTypeName;


    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "documentTypeDictId",mapValueField = "value")
    @Schema(description = "文书类型对应字典值")
    private String documentTypeDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "documentTypeDictId",mapValueField = "name")
    @Schema(description = "文书类型对应字典名称")
    private String documentTypeDictName;

    @Schema(description = "文书发布日期")
    private LocalDate documentPublishDate;

    @Schema(description = "文书发布标题")
    private String documentPublishTitle;

	@Schema(description = "数据md5,case_no + case_reason + case_judge_date + case_trial_procedure + case_type_name + document_type_name")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

    @Schema(description = "当事人信息")
    private List<DataCompanyJudgmentDocumentPartyExWarehouseVO> parties;

    @Schema(description = "裁判文书文本内容")
    private DataCompanyJudgmentDocumentContentExWarehouseVO content;


}
