package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业裁判文书入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentWarehouseCommand extends AbstractBaseCommand {



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


    @Schema(description = "案件涉及金额（万元）")
    private BigDecimal caseAmount;


    @Schema(description = "案件涉及金额币种")
    private Long caseAmountCurrencyDictId;

    @Schema(description = "文书类型")
    private Long documentTypeDictId;

	@Schema(description = "文书类型名称")
	private String documentTypeName;


    @Schema(description = "文书发布日期")
    private LocalDate documentPublishDate;


    @Schema(description = "文书发布标题")
    private String documentPublishTitle;

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyJudgmentDocumentDataMd5(caseNo, caseReason, caseJudgeDate, caseTrialProcedure, caseTypeName, documentTypeName);
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(caseReason)
                && Objects.isNull(caseCourtCompanyId)
                && StrUtil.isEmpty(caseCourtName)
                && Objects.isNull(caseJudgeDate)
                && StrUtil.isEmpty(caseTrialProcedure)
                && StrUtil.isEmpty(caseLegalBasis)
                && Objects.isNull(caseTypeDictId)
                && StrUtil.isEmpty(caseTypeName)
                && Objects.isNull(caseAmount)
                && Objects.isNull(caseAmountCurrencyDictId)
                && Objects.isNull(documentTypeDictId)
                && StrUtil.isEmpty(documentTypeName)
                && Objects.isNull(documentPublishDate)
                && StrUtil.isEmpty(documentPublishTitle);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyJudgmentDocumentExWarehouseVO exWarehouseVO) {
        if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(caseReason, exWarehouseVO.getCaseReason())) {
            this.caseReason = null;
        }
        if (Objects.equals(caseCourtCompanyId, exWarehouseVO.getCaseCourtCompanyId())) {
            this.caseCourtCompanyId = null;
        }
        if (Objects.equals(caseCourtName, exWarehouseVO.getCaseCourtName())) {
            this.caseCourtName = null;
        }
        if (Objects.equals(caseJudgeDate, exWarehouseVO.getCaseJudgeDate())) {
            this.caseJudgeDate = null;
        }
        if (Objects.equals(caseTrialProcedure, exWarehouseVO.getCaseTrialProcedure())) {
            this.caseTrialProcedure = null;
        }
        if (Objects.equals(caseLegalBasis, exWarehouseVO.getCaseLegalBasis())) {
            this.caseLegalBasis = null;
        }
        if (Objects.equals(caseTypeDictId, exWarehouseVO.getCaseTypeDictId())) {
            this.caseTypeDictId = null;
        }
        if (Objects.equals(caseTypeName, exWarehouseVO.getCaseTypeName())) {
            this.caseTypeName = null;
        }
        if (NumberUtil.equals(caseAmount, exWarehouseVO.getCaseAmount())) {
            this.caseAmount = null;
        }
        if (Objects.equals(caseAmountCurrencyDictId, exWarehouseVO.getCaseAmountCurrencyDictId())) {
            this.caseAmountCurrencyDictId = null;
        }
        if (Objects.equals(documentTypeDictId, exWarehouseVO.getDocumentTypeDictId())) {
            this.documentTypeDictId = null;
        }
        if (Objects.equals(documentTypeName, exWarehouseVO.getDocumentTypeName())) {
            this.documentTypeName = null;
        }
        if (Objects.equals(documentPublishDate, exWarehouseVO.getDocumentPublishDate())) {
            this.documentPublishDate = null;
        }
        if (Objects.equals(documentPublishTitle, exWarehouseVO.getDocumentPublishTitle())) {
            this.documentPublishTitle = null;
        }
    }

    public static DataCompanyJudgmentDocumentWarehouseCommand createByDataCompanyJudgmentDocumentExWarehouseVO(DataCompanyJudgmentDocumentExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyJudgmentDocumentWarehouseCommand command = new DataCompanyJudgmentDocumentWarehouseCommand();
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.caseReason = dataCompanyBasicWarehouseCommand.getCaseReason();
        command.caseCourtCompanyId = dataCompanyBasicWarehouseCommand.getCaseCourtCompanyId();
        command.caseCourtName = dataCompanyBasicWarehouseCommand.getCaseCourtName();
        command.caseJudgeDate = dataCompanyBasicWarehouseCommand.getCaseJudgeDate();
        command.caseTrialProcedure = dataCompanyBasicWarehouseCommand.getCaseTrialProcedure();
        command.caseLegalBasis = dataCompanyBasicWarehouseCommand.getCaseLegalBasis();
        command.caseTypeDictId = dataCompanyBasicWarehouseCommand.getCaseTypeDictId();
        command.caseTypeName = dataCompanyBasicWarehouseCommand.getCaseTypeName();
        command.caseAmount = dataCompanyBasicWarehouseCommand.getCaseAmount();
        command.caseAmountCurrencyDictId = dataCompanyBasicWarehouseCommand.getCaseAmountCurrencyDictId();
        command.documentTypeDictId = dataCompanyBasicWarehouseCommand.getDocumentTypeDictId();
        command.documentTypeName = dataCompanyBasicWarehouseCommand.getDocumentTypeName();
        command.documentPublishDate = dataCompanyBasicWarehouseCommand.getDocumentPublishDate();
        command.documentPublishTitle = dataCompanyBasicWarehouseCommand.getDocumentPublishTitle();

        return command;
    }
}
