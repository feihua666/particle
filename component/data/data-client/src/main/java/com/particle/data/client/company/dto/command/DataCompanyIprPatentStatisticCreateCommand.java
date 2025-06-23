package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentStatisticWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利统计 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Data
@Schema
public class DataCompanyIprPatentStatisticCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利id 不能为空")
    @Schema(description = "企业知识产权专利id",requiredMode = Schema.RequiredMode.REQUIRED)
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



    public static DataCompanyIprPatentStatisticCreateCommand createByWarehouseCommand(DataCompanyIprPatentStatisticWarehouseCommand dataCompanyIprPatentStatisticWarehouseCommand){
        DataCompanyIprPatentStatisticCreateCommand command = new DataCompanyIprPatentStatisticCreateCommand();
        command.companyIprPatentId = dataCompanyIprPatentStatisticWarehouseCommand.getCompanyIprPatentId();
        command.familyNum = dataCompanyIprPatentStatisticWarehouseCommand.getFamilyNum();
        command.extFamilyNum = dataCompanyIprPatentStatisticWarehouseCommand.getExtFamilyNum();
        command.citedNum = dataCompanyIprPatentStatisticWarehouseCommand.getCitedNum();
        command.quoteNum = dataCompanyIprPatentStatisticWarehouseCommand.getQuoteNum();
        command.claimNum = dataCompanyIprPatentStatisticWarehouseCommand.getClaimNum();
        command.independentClaimNum = dataCompanyIprPatentStatisticWarehouseCommand.getIndependentClaimNum();
        command.dependentClaimNum = dataCompanyIprPatentStatisticWarehouseCommand.getDependentClaimNum();
        command.transferNum = dataCompanyIprPatentStatisticWarehouseCommand.getTransferNum();
        command.licenseNum = dataCompanyIprPatentStatisticWarehouseCommand.getLicenseNum();
        command.pledgeNum = dataCompanyIprPatentStatisticWarehouseCommand.getPledgeNum();
        command.invalidNum = dataCompanyIprPatentStatisticWarehouseCommand.getInvalidNum();
        command.litigationNum = dataCompanyIprPatentStatisticWarehouseCommand.getLitigationNum();
        command.ipcCategoryNum = dataCompanyIprPatentStatisticWarehouseCommand.getIpcCategoryNum();
        command.technologyScore = dataCompanyIprPatentStatisticWarehouseCommand.getTechnologyScore();
        command.legalScore = dataCompanyIprPatentStatisticWarehouseCommand.getLegalScore();
        command.strategyScore = dataCompanyIprPatentStatisticWarehouseCommand.getStrategyScore();
        command.marketScore = dataCompanyIprPatentStatisticWarehouseCommand.getMarketScore();
        command.economicScore = dataCompanyIprPatentStatisticWarehouseCommand.getEconomicScore();
        command.comprehensiveScore = dataCompanyIprPatentStatisticWarehouseCommand.getComprehensiveScore();
        command.valuation = dataCompanyIprPatentStatisticWarehouseCommand.getValuation();
        command.grade = dataCompanyIprPatentStatisticWarehouseCommand.getGrade();
        command.comprehensiveScorePercent = dataCompanyIprPatentStatisticWarehouseCommand.getComprehensiveScorePercent();
        command.valuationPercent = dataCompanyIprPatentStatisticWarehouseCommand.getValuationPercent();


        return command;
    }
}
