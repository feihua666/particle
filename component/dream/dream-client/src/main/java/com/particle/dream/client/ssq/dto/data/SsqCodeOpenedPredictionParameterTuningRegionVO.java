package com.particle.dream.client.ssq.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 双色球开奖参数调参预测 结果对象
 * </p>
 *
 * @author yangwei
 * @since 2024/6/5 13:15
 */
@Data
@Schema
public class SsqCodeOpenedPredictionParameterTuningRegionVO extends VO {

    @Schema(description = "当前区已开奖数量")
    private Integer openedCount;

    @Schema(description = "当前区序号最小值")
    private Integer regionSeqNoMin;

    @Schema(description = "当前区序号最大值")
    private Integer regionSeqNoMax;

    @Schema(description = "区序号")
    private Integer regionNo;

    @Schema(description = "总区数")
    private Integer regionCount;

    @Schema(description = "预测序号最小值，一般不包括值本身")
    private Integer predictSeqNoMin;

    @Schema(description = "预测序号最大值，一般不包括值本身")
    private Integer predictSeqNoMax;

    @Schema(description = "预测基线百分比数")
    private Double predictPercent;

    @Schema(description = "是否预测命中")
    private Boolean isPredictHit;

    @Schema(description = "预测命中期号")
    private List<Integer> predictHitOpenedPhase;

    @Schema(description = "预测命中个数")
    private Integer predictHitOpenedPhaseSize;
}
