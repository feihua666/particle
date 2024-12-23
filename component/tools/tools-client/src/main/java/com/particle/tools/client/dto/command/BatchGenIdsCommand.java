package com.particle.tools.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangwei
 * Created at 2024-07-25 11:59:16
 */
@Setter
@Getter
@Schema(description = "批量生成id表单对象")
public class BatchGenIdsCommand extends AbstractBaseCommand {

    @Schema(description = "生成的个数")
    @Min(value = 1,message = "生成的个数不能小于1")
    @Max(value = 1000,message = "生成的个数不能超过1000")
    private Integer num = 100;
}
