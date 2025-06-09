package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利统计入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentStatisticWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "同族专利数量")
    private Integer familyNum;


    @Schema(description = "扩展同族专利数量")
    private Integer extFamilyNum;


    @Schema(description = "被引证数量")
    private Integer citedNum;


    @Schema(description = "引证专利数量")
    private Integer quoteNum;


    @Schema(description = "权利要求数量")
    private Integer claimNum;


    @Schema(description = "独权数")
    private Integer independentClaimNum;


    @Schema(description = "从权数")
    private Integer dependentClaimNum;


    @Schema(description = "转让次数")
    private Integer transferNum;


    @Schema(description = "许可次数")
    private Integer licenseNum;


    @Schema(description = "质押次数")
    private Integer pledgeNum;


    @Schema(description = "无效次数")
    private Integer invalidNum;


    @Schema(description = "诉讼次数")
    private Integer litigationNum;


    @Schema(description = "IPC分类数量")
    private Integer ipcCategoryNum;


    @Schema(description = "技术评分")
    private BigDecimal technologyScore;

    @Schema(description = "法律评分")
    private BigDecimal legalScore;

    @Schema(description = "战略评分")
    private BigDecimal strategyScore;

    @Schema(description = "市场评分")
    private BigDecimal marketScore;

    @Schema(description = "经济评分")
    private BigDecimal economicScore;

    @Schema(description = "综合评分")
    private BigDecimal comprehensiveScore;

    @Schema(description = "估值（元）")
    private BigDecimal valuation;

    @Schema(description = "等级，A+ 到 D-，12个等级，A+最优")
    private String grade;

    @Schema(description = "评估得分超过比,评估得分在领域内的相对位置")
    private BigDecimal comprehensiveScorePercent;

    @Schema(description = "评估价格超过比,评估价格在领域内的相对位置")
    private BigDecimal valuationPercent;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && Objects.isNull(familyNum)
                && Objects.isNull(extFamilyNum)
                && Objects.isNull(citedNum)
                && Objects.isNull(quoteNum)
                && Objects.isNull(claimNum)
                && Objects.isNull(independentClaimNum)
                && Objects.isNull(dependentClaimNum)
                && Objects.isNull(transferNum)
                && Objects.isNull(licenseNum)
                && Objects.isNull(pledgeNum)
                && Objects.isNull(invalidNum)
                && Objects.isNull(litigationNum)
                && Objects.isNull(ipcCategoryNum)
                && Objects.isNull(technologyScore)
                && Objects.isNull(legalScore)
                && Objects.isNull(strategyScore)
                && Objects.isNull(marketScore)
                && Objects.isNull(economicScore)
                && Objects.isNull(comprehensiveScore)
                && Objects.isNull(valuation)
                && Objects.isNull(grade)
                && Objects.isNull(comprehensiveScorePercent)
                && Objects.isNull(valuationPercent);
    }
    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentStatisticExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(familyNum, exWarehouseVO.getFamilyNum())) {
            this.familyNum = null;
        }
        if (Objects.equals(extFamilyNum, exWarehouseVO.getExtFamilyNum())) {
            this.extFamilyNum = null;
        }
        if (Objects.equals(citedNum, exWarehouseVO.getCitedNum())) {
            this.citedNum = null;
        }
        if (Objects.equals(quoteNum, exWarehouseVO.getQuoteNum())) {
            this.quoteNum = null;
        }
        if (Objects.equals(claimNum, exWarehouseVO.getClaimNum())) {
            this.claimNum = null;
        }
        if (Objects.equals(independentClaimNum, exWarehouseVO.getIndependentClaimNum())) {
            this.independentClaimNum = null;
        }
        if (Objects.equals(dependentClaimNum, exWarehouseVO.getDependentClaimNum())) {
            this.dependentClaimNum = null;
        }
        if (Objects.equals(transferNum, exWarehouseVO.getTransferNum())) {
            this.transferNum = null;
        }
        if (Objects.equals(licenseNum, exWarehouseVO.getLicenseNum())) {
            this.licenseNum = null;
        }
        if (Objects.equals(pledgeNum, exWarehouseVO.getPledgeNum())) {
            this.pledgeNum = null;
        }
        if (Objects.equals(invalidNum, exWarehouseVO.getInvalidNum())) {
            this.invalidNum = null;
        }
        if (Objects.equals(litigationNum, exWarehouseVO.getLitigationNum())) {
            this.litigationNum = null;
        }
        if (Objects.equals(ipcCategoryNum, exWarehouseVO.getIpcCategoryNum())) {
            this.ipcCategoryNum = null;
        }

        if (NumberUtil.equals(technologyScore, exWarehouseVO.getTechnologyScore())) {
            this.technologyScore = null;
        }
        if (NumberUtil.equals(legalScore, exWarehouseVO.getLegalScore())) {
            this.legalScore = null;
        }
        if (NumberUtil.equals(strategyScore, exWarehouseVO.getStrategyScore())) {
            this.strategyScore = null;
        }
        if (NumberUtil.equals(marketScore, exWarehouseVO.getMarketScore())) {
            this.marketScore = null;
        }
        if (NumberUtil.equals(economicScore, exWarehouseVO.getEconomicScore())) {
            this.economicScore = null;
        }
        if (NumberUtil.equals(comprehensiveScore, exWarehouseVO.getComprehensiveScore())) {
            this.comprehensiveScore = null;
        }

        if (Objects.equals(grade, exWarehouseVO.getGrade())) {
            this.grade = null;
        }
        if (NumberUtil.equals(valuation, exWarehouseVO.getValuation())) {
            this.valuation = null;
        }
        if (NumberUtil.equals(comprehensiveScorePercent, exWarehouseVO.getComprehensiveScorePercent())) {
            this.comprehensiveScorePercent = null;
        }
        if (NumberUtil.equals(valuationPercent, exWarehouseVO.getValuationPercent())) {
            this.valuationPercent = null;
        }
    }

    public static DataCompanyIprPatentStatisticWarehouseCommand createByDataCompanyIprPatentStatisticExWarehouseVO(DataCompanyIprPatentStatisticExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentStatisticWarehouseCommand command = new DataCompanyIprPatentStatisticWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.familyNum = exWarehouseVO.getFamilyNum();
        command.extFamilyNum = exWarehouseVO.getExtFamilyNum();
        command.citedNum = exWarehouseVO.getCitedNum();
        command.quoteNum = exWarehouseVO.getQuoteNum();
        command.claimNum = exWarehouseVO.getClaimNum();
        command.independentClaimNum = exWarehouseVO.getIndependentClaimNum();
        command.dependentClaimNum = exWarehouseVO.getDependentClaimNum();
        command.transferNum = exWarehouseVO.getTransferNum();
        command.licenseNum = exWarehouseVO.getLicenseNum();
        command.pledgeNum = exWarehouseVO.getPledgeNum();
        command.invalidNum = exWarehouseVO.getInvalidNum();
        command.litigationNum = exWarehouseVO.getLitigationNum();
        command.ipcCategoryNum = exWarehouseVO.getIpcCategoryNum();
        command.technologyScore = exWarehouseVO.getTechnologyScore();
        command.legalScore = exWarehouseVO.getLegalScore();
        command.strategyScore = exWarehouseVO.getStrategyScore();
        command.marketScore = exWarehouseVO.getMarketScore();
        command.economicScore = exWarehouseVO.getEconomicScore();
        command.comprehensiveScore = exWarehouseVO.getComprehensiveScore();
        command.valuation = exWarehouseVO.getValuation();
        command.grade = exWarehouseVO.getGrade();
        command.comprehensiveScorePercent = exWarehouseVO.getComprehensiveScorePercent();
        command.valuationPercent = exWarehouseVO.getValuationPercent();

        return command;
    }
}
