package com.particle.dream.client.ssq.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 双色球开奖参数调参预测指令对象
 * </p>
 *
 * @author yangwei
 * @since 2024/6/5 13:15
 */
@Data
@Schema
public class SsqCodeOpenedPredictionParameterTuningCommand extends AbstractBaseCommand {

    @NotNull(message = "开奖期号年份 不能为空")
    @Schema(description = "开奖期号年份")
    private Integer openedPhaseYear;

    @NotNull(message = "分区最小值 不能为空")
    @Schema(description = "分区最小值")
    private Integer regionCountMin;

    @NotNull(message = "分区最大值 不能为空")
    @Schema(description = "分区最大值")
    private Integer regionCountMax;

    @NotNull(message = "分区最大值 不能为空")
    @Schema(description = "是否使用红球序号，否则使用全部序号")
    private Boolean isUserRedSeqNo;

    @NotNull(message = "分区开奖数量最小值 不能为空")
    @Schema(description = "分区开奖数量最小值")
    private Integer openedCountMin;


    @NotNull(message = "分区开奖数量最大值 不能为空")
    @Schema(description = "分区开奖数量最大值")
    private Integer openedCountMax;

    /**
     * 在匹配的区间内预测的百分位数，如：50代表50%，表示从中间开始预测
     */
    @NotNull(message = "预测范围基线百分数开始 不能为空")
    @Schema(description = "预测范围基线百分数开始")
    private Double predictBasePercentStart;

    @NotNull(message = "预测范围基线百分数结束 不能为空")
    @Schema(description = "预测范围基线百分数结束")
    private Double predictBasePercentEnd;

    @NotNull(message = "预测范围基线百分数步长 不能为空")
    @Schema(description = "预测范围基线百分数步长")
    private Double predictBasePercentStep;

    /**
     * 在匹配的区间内预测的号码个数，根据基线百分位数，为中心左右各取1/2的个数，凑齐分区内预测个数
     */
    @NotNull(message = "分区内预测个数 不能为空")
    @Schema(description = "分区内预测个数")
    private Integer predictPerRegionCount;

    @NotNull(message = "预测未来的开奖期号年份开始 不能为空")
    @Schema(description = "预测未来的开奖期号年份开始")
    private Integer predictOpenedPhaseYearStart;

    @NotNull(message = "预测未来的开奖期号数开始 不能为空")
    @Schema(description = "预测未来的开奖期号数开始")
    private Integer predictOpenedPhaseNumStart;

    @Schema(description = "预测未来的开奖期号年份结束")
    private Integer predictOpenedPhaseYearEnd;

    @Schema(description = "预测未来的开奖期号数结束")
    private Integer predictOpenedPhaseNumEnd;
}
