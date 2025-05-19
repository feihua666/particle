package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业行政处罚入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyPunishmentWarehouseCommand extends AbstractBaseCommand {

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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyPunishmentDataMd5(companyName, punishNo, punishContent);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(companyName)
                && StrUtil.isEmpty(legalPersonName)
                && Objects.isNull(isLegalPersonNaturalPerson)
                && Objects.isNull(legalPersonCompanyId)
                && Objects.isNull(legalPersonCompanyPersonId)
                && StrUtil.isEmpty(punishNo)
                && StrUtil.isEmpty(illegalType)
                && StrUtil.isEmpty(punishBasis)
                && StrUtil.isEmpty(illegalFact)
                && StrUtil.isEmpty(punishType)
                && StrUtil.isEmpty(punishContent)
                && Objects.isNull(fineAmount)
                && Objects.isNull(fineAmountCurrencyDictId)
                && Objects.isNull(confiscateAmount)
                && Objects.isNull(confiscateAmountCurrencyDictId)
                && Objects.isNull(instituteCompanyId)
                && StrUtil.isEmpty(instituteName)
                && StrUtil.isEmpty(suspendOrRevokeLicenseNameCode)
                && Objects.isNull(punishDecisionStartDate)
                && Objects.isNull(punishDecisionEndDate)
                && Objects.isNull(publishStartDate)
                && Objects.isNull(publishEndDate)
                && StrUtil.isEmpty(dataFrom)
                && Objects.isNull(dataFromCompanyId)
                && StrUtil.isEmpty(dataFromName)
                && StrUtil.isEmpty(remark)
                && Objects.isNull(isDataFlagGs)
                && Objects.isNull(isDataFlagXyzg);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyPunishmentExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyName, exWarehouseVO.getCompanyName())) {
            this.companyName = null;
        }
        if (Objects.equals(legalPersonName, exWarehouseVO.getLegalPersonName())) {
            this.legalPersonName = null;
        }
        if (Objects.equals(isLegalPersonNaturalPerson, exWarehouseVO.getIsLegalPersonNaturalPerson())) {
            this.isLegalPersonNaturalPerson = null;
        }
        if (Objects.equals(legalPersonCompanyId, exWarehouseVO.getLegalPersonCompanyId())) {
            this.legalPersonCompanyId = null;
        }
        if (Objects.equals(legalPersonCompanyPersonId, exWarehouseVO.getLegalPersonCompanyPersonId())) {
            this.legalPersonCompanyPersonId = null;
        }
        if (Objects.equals(punishNo, exWarehouseVO.getPunishNo())) {
            this.punishNo = null;
        }
        if (Objects.equals(illegalType, exWarehouseVO.getIllegalType())) {
            this.illegalType = null;
        }
        if (Objects.equals(punishBasis, exWarehouseVO.getPunishBasis())) {
            this.punishBasis = null;
        }
        if (Objects.equals(illegalFact, exWarehouseVO.getIllegalFact())) {
            this.illegalFact = null;
        }
        if (Objects.equals(punishType, exWarehouseVO.getPunishType())) {
            this.punishType = null;
        }
        if (Objects.equals(punishContent, exWarehouseVO.getPunishContent())) {
            this.punishContent = null;
        }
        if (NumberUtil.equals(fineAmount, exWarehouseVO.getFineAmount())) {
            this.fineAmount = null;
        }
        if (Objects.equals(fineAmountCurrencyDictId, exWarehouseVO.getFineAmountCurrencyDictId())) {
            this.fineAmountCurrencyDictId = null;
        }
        if (NumberUtil.equals(confiscateAmount, exWarehouseVO.getConfiscateAmount())) {
            this.confiscateAmount = null;
        }
        if (Objects.equals(confiscateAmountCurrencyDictId, exWarehouseVO.getConfiscateAmountCurrencyDictId())) {
            this.confiscateAmountCurrencyDictId = null;
        }
        if (Objects.equals(instituteCompanyId, exWarehouseVO.getInstituteCompanyId())) {
            this.instituteCompanyId = null;
        }
        if (Objects.equals(instituteName, exWarehouseVO.getInstituteName())) {
            this.instituteName = null;
        }
        if (Objects.equals(suspendOrRevokeLicenseNameCode, exWarehouseVO.getSuspendOrRevokeLicenseNameCode())) {
            this.suspendOrRevokeLicenseNameCode = null;
        }
        if (Objects.equals(punishDecisionStartDate, exWarehouseVO.getPunishDecisionStartDate())) {
            this.punishDecisionStartDate = null;
        }
        if (Objects.equals(punishDecisionEndDate, exWarehouseVO.getPunishDecisionEndDate())) {
            this.punishDecisionEndDate = null;
        }
        if (Objects.equals(publishStartDate, exWarehouseVO.getPublishStartDate())) {
            this.publishStartDate = null;
        }
        if (Objects.equals(publishEndDate, exWarehouseVO.getPublishEndDate())) {
            this.publishEndDate = null;
        }
        if (Objects.equals(dataFrom, exWarehouseVO.getDataFrom())) {
            this.dataFrom = null;
        }
        if (Objects.equals(dataFromCompanyId, exWarehouseVO.getDataFromCompanyId())) {
            this.dataFromCompanyId = null;
        }
        if (Objects.equals(dataFromName, exWarehouseVO.getDataFromName())) {
            this.dataFromName = null;
        }
        if (Objects.equals(remark, exWarehouseVO.getRemark())) {
            this.remark = null;
        }
        if (Objects.equals(isDataFlagGs, exWarehouseVO.getIsDataFlagGs())) {
            this.isDataFlagGs = null;
        }
        if (Objects.equals(isDataFlagXyzg, exWarehouseVO.getIsDataFlagXyzg())) {
            this.isDataFlagXyzg = null;
        }

    }

    public static DataCompanyPunishmentWarehouseCommand createByDataCompanyPunishmentExWarehouseVO(DataCompanyPunishmentExWarehouseVO exWarehouseVO) {
        DataCompanyPunishmentWarehouseCommand command = new DataCompanyPunishmentWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyName = exWarehouseVO.getCompanyName();
        command.legalPersonName = exWarehouseVO.getLegalPersonName();
        command.legalPersonCompanyId = exWarehouseVO.getLegalPersonCompanyId();
        command.legalPersonCompanyPersonId = exWarehouseVO.getLegalPersonCompanyPersonId();
        command.punishNo = exWarehouseVO.getPunishNo();
        command.illegalType = exWarehouseVO.getIllegalType();
        command.punishContent = exWarehouseVO.getPunishContent();
        command.punishType = exWarehouseVO.getPunishType();
        command.punishBasis = exWarehouseVO.getPunishBasis();
        command.illegalFact = exWarehouseVO.getIllegalFact();
        command.fineAmount = exWarehouseVO.getFineAmount();
        command.fineAmountCurrencyDictId = exWarehouseVO.getFineAmountCurrencyDictId();
        command.confiscateAmount = exWarehouseVO.getConfiscateAmount();
        command.confiscateAmountCurrencyDictId = exWarehouseVO.getConfiscateAmountCurrencyDictId();
        command.instituteCompanyId = exWarehouseVO.getInstituteCompanyId();
        command.instituteName = exWarehouseVO.getInstituteName();
        command.suspendOrRevokeLicenseNameCode = exWarehouseVO.getSuspendOrRevokeLicenseNameCode();
        command.punishDecisionStartDate = exWarehouseVO.getPunishDecisionStartDate();
        command.punishDecisionEndDate = exWarehouseVO.getPunishDecisionEndDate();
        command.publishStartDate = exWarehouseVO.getPublishStartDate();
        command.publishEndDate = exWarehouseVO.getPublishEndDate();
        command.dataFrom = exWarehouseVO.getDataFrom();
        command.dataFromCompanyId = exWarehouseVO.getDataFromCompanyId();
        command.dataFromName = exWarehouseVO.getDataFromName();
        command.remark = exWarehouseVO.getRemark();
        command.isDataFlagGs = exWarehouseVO.getIsDataFlagGs();
        command.isDataFlagXyzg = exWarehouseVO.getIsDataFlagXyzg();

        return command;
    }
}
