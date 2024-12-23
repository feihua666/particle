package com.particle.dream.client.ssq.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

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
public class SsqCodeOpenedCheckWinVO extends VO {

    @Schema(description = "开奖期号")
    private Integer openedPhase;

    @Schema(description = "开奖期号对应的开奖号码,红球使用逗号分隔，红蓝使用空格分隔，格式为：1,2,3,4,5 6")
    private String openedPhaseNum;

    @Schema(description = "检查结果，key=传入的一组号码，value=中奖等级数字")
    private Map<String,Integer> checkWin;

    public void addCheckWinItem(String checkWinKey,Integer checkWinValue)
    {
        if (checkWin == null) {
            checkWin = new java.util.HashMap<>();
        }
        checkWin.put(checkWinKey,checkWinValue);
    }

}
