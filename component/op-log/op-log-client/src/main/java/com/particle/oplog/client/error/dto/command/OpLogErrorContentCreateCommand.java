package com.particle.oplog.client.error.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 操作异常日志内容 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Data
@Schema
public class OpLogErrorContentCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "异常日志id 不能为空")
        @Schema(description = "异常日志id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long opLogErrorId;


    @NotEmpty(message = "异常内容 不能为空")
        @Schema(description = "异常内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;


    public static OpLogErrorContentCreateCommand create(Long opLogErrorId, String content) {
        OpLogErrorContentCreateCommand opLogErrorContentCreateCommand = new OpLogErrorContentCreateCommand();
        opLogErrorContentCreateCommand.setOpLogErrorId(opLogErrorId);
        opLogErrorContentCreateCommand.setContent(content);
        return opLogErrorContentCreateCommand;
    }
}
