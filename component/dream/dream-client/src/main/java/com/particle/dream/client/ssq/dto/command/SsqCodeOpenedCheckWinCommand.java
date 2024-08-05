package com.particle.dream.client.ssq.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 双色球判断是否中奖 指令对象
 * </p>
 *
 * @author yangwei
 * @since 2024-07-30 14:38:40
 */
@Data
@Schema
public class SsqCodeOpenedCheckWinCommand extends AbstractBaseCommand {

    @Schema(description = "开奖期号，不填写默认使用最新一期")
    private Integer openedPhase;

    @NotEmpty(message = "选择的号码 不能为空")
    @Schema(description = "选择的号码,红球使用逗号分隔，红蓝使用空格分隔，格式为：1,2,3,4,5 6")
    private List<String> selectedNums;

}
