package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentWarehouseCommand;

/**
 * <p>
 * 企业裁判文书 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentCreateCommand extends AbstractBaseCommand {



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


    @NotNull(message = "案件类型 不能为空")
    @Schema(description = "案件类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long caseTypeDictId;

	@Schema(description = "案件类型名称")
	private String caseTypeName;


    @Schema(description = "案件涉及金额（万元）")
    private BigDecimal caseAmount;


    @Schema(description = "案件涉及金额币种")
    private Long caseAmountCurrencyDictId;


    @NotNull(message = "文书类型 不能为空")
    @Schema(description = "文书类型",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long documentTypeDictId;

	@Schema(description = "文书类型名称")
	private String documentTypeName;


    @Schema(description = "文书发布日期")
    private LocalDate documentPublishDate;


    @Schema(description = "文书发布标题")
    private String documentPublishTitle;


    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyJudgmentDocumentCreateCommand createByWarehouseCommand(DataCompanyJudgmentDocumentWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyJudgmentDocumentCreateCommand command = new DataCompanyJudgmentDocumentCreateCommand();
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
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
