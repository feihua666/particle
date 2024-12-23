package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by yangwei
 * Created at 2024-08-06 11:00:51
 */
@Setter
@Getter
@Schema(description = "json解析表单对象")
public class JsonParseMultipleLineCommand extends AbstractBaseCommand {

    @NotEmpty(message = "json字符串 不能为空")
    @Schema(description = "json字符串")
    private List<String> jsonStrs;
}
