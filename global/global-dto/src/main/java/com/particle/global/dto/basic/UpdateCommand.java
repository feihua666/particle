package com.particle.global.dto.basic;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/7/4 16:16
 */
@Data
@Schema
public class UpdateCommand extends IdCommand{

    public static UpdateCommand empty  = new UpdateCommand();

    @Min(value = 1,message = "数据版本不能小于1")
    @NotNull(message = "数据版本不能为空")
    @Schema(title = "数据版本",description = "数据版本用来充当数据乐观锁字段，验证数据是否已经修改")
    private Integer version;
}
