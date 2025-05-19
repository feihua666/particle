package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPunishmentWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 企业行政处罚 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Data
@Schema
public class DataCompanyPunishmentCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "企业名称")
    private String companyName;


    @Schema(description = "法人名称")
    private String legalPersonName;


    @Schema(description = "是否法人为自然人")
    private Boolean isLegalPersonNaturalPerson;


    @Schema(description = "法人公司id")
    private Long legalPersonCompanyId;


    @Schema(description = "法人个人id")
    private Long legalPersonCompanyPersonId;


    @Schema(description = "行政处罚决定书文号")
    private String punishNo;


    @Schema(description = "违法行为类型 ")
    private String illegalType;


    @Schema(description = "处罚依据")
    private String punishBasis;


    @Schema(description = "违法事实")
    private String illegalFact;


    @Schema(description = "处罚类别")
    private String punishType;


    @Schema(description = "处罚内容")
    private String punishContent;


    @Schema(description = "罚款金额（万元）")
    private BigDecimal fineAmount;


    @Schema(description = "罚款金额币种")
    private Long fineAmountCurrencyDictId;


    @Schema(description = "没收金额（万元）")
    private BigDecimal confiscateAmount;


    @Schema(description = "没收金额币种")
    private Long confiscateAmountCurrencyDictId;


    @Schema(description = "作出行政处罚决定机关公司id")
    private Long instituteCompanyId;


    @Schema(description = "作出行政处罚决定机关名称")
    private String instituteName;


    @Schema(description = "暂扣或吊销证照名称及编号")
    private String suspendOrRevokeLicenseNameCode;


    @Schema(description = "作出行政处罚决定开始日期")
    private LocalDate punishDecisionStartDate;


    @Schema(description = "作出行政处罚决定结束日期")
    private LocalDate punishDecisionEndDate;


    @Schema(description = "发布开始日期")
    private LocalDate publishStartDate;


    @Schema(description = "发布结束日期")
    private LocalDate publishEndDate;


    @Schema(description = "数据来源")
    private String dataFrom;


    @Schema(description = "数据来源公司id")
    private Long dataFromCompanyId;


    @Schema(description = "数据来源名称")
    private String dataFromName;


    @Schema(description = "备注")
    private String remark;


    @Schema(description = "是否数据标识为工商公示")
    private Boolean isDataFlagGs;


    @Schema(description = "是否数据标识为信用中国")
    private Boolean isDataFlagXyzg;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyPunishmentCreateCommand createByWarehouseCommand(DataCompanyPunishmentWarehouseCommand dataCompanyPunishmentWarehouseCommand) {
        DataCompanyPunishmentCreateCommand command = new DataCompanyPunishmentCreateCommand();
        command.companyId = dataCompanyPunishmentWarehouseCommand.getCompanyId();
        command.companyName = dataCompanyPunishmentWarehouseCommand.getCompanyName();
        command.legalPersonName = dataCompanyPunishmentWarehouseCommand.getLegalPersonName();
        command.legalPersonCompanyId = dataCompanyPunishmentWarehouseCommand.getLegalPersonCompanyId();
        command.legalPersonCompanyPersonId = dataCompanyPunishmentWarehouseCommand.getLegalPersonCompanyPersonId();
        command.punishNo = dataCompanyPunishmentWarehouseCommand.getPunishNo();
        command.illegalType = dataCompanyPunishmentWarehouseCommand.getIllegalType();
        command.punishContent = dataCompanyPunishmentWarehouseCommand.getPunishContent();
        command.punishType = dataCompanyPunishmentWarehouseCommand.getPunishType();
        command.punishBasis = dataCompanyPunishmentWarehouseCommand.getPunishBasis();
        command.illegalFact = dataCompanyPunishmentWarehouseCommand.getIllegalFact();
        command.fineAmount = dataCompanyPunishmentWarehouseCommand.getFineAmount();
        command.fineAmountCurrencyDictId = dataCompanyPunishmentWarehouseCommand.getFineAmountCurrencyDictId();
        command.confiscateAmount = dataCompanyPunishmentWarehouseCommand.getConfiscateAmount();
        command.confiscateAmountCurrencyDictId = dataCompanyPunishmentWarehouseCommand.getConfiscateAmountCurrencyDictId();
        command.instituteCompanyId = dataCompanyPunishmentWarehouseCommand.getInstituteCompanyId();
        command.instituteName = dataCompanyPunishmentWarehouseCommand.getInstituteName();
        command.suspendOrRevokeLicenseNameCode = dataCompanyPunishmentWarehouseCommand.getSuspendOrRevokeLicenseNameCode();
        command.punishDecisionStartDate = dataCompanyPunishmentWarehouseCommand.getPunishDecisionStartDate();
        command.punishDecisionEndDate = dataCompanyPunishmentWarehouseCommand.getPunishDecisionEndDate();
        command.publishStartDate = dataCompanyPunishmentWarehouseCommand.getPublishStartDate();
        command.publishEndDate = dataCompanyPunishmentWarehouseCommand.getPublishEndDate();
        command.dataFrom = dataCompanyPunishmentWarehouseCommand.getDataFrom();
        command.dataFromCompanyId = dataCompanyPunishmentWarehouseCommand.getDataFromCompanyId();
        command.dataFromName = dataCompanyPunishmentWarehouseCommand.getDataFromName();
        command.remark = dataCompanyPunishmentWarehouseCommand.getRemark();
        command.isDataFlagGs = dataCompanyPunishmentWarehouseCommand.getIsDataFlagGs();
        command.isDataFlagXyzg = dataCompanyPunishmentWarehouseCommand.getIsDataFlagXyzg();
        command.dataMd5 = dataCompanyPunishmentWarehouseCommand.obtainDataMd5();

        return command;
    }

}
