package com.particle.feedback.client.feedback.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 意见反馈 手动处理指令对象
 * </p>
 *
 * @author yw
 * @since 2024-02-26 13:58:41
 */
@Data
@Schema
public class FeedbackManualHandleCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "处理结果 不能为空")
    @Schema(description = "处理结果",requiredMode = Schema.RequiredMode.REQUIRED)
    private String handleResult;

}
