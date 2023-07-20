package com.particle.func.client.dto.command;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 功能组 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Data
@Schema
public class FuncGroupUpdateCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "编码不能为空")
    @Schema(description = "编码",required = true)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @Schema(description = "名称",required = true)
    private String name;

    @Schema(description = "描述")
    private String remark;


}
