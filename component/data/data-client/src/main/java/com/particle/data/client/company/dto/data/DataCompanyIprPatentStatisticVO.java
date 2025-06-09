package com.particle.data.client.company.dto.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利统计 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Data
@Schema
public class DataCompanyIprPatentStatisticVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
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

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
