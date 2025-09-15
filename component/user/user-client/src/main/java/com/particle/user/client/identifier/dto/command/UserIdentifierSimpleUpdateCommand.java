package com.particle.user.client.identifier.dto.command;

import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录标识 修改创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-09-15 10:45:45
 */
@PropValid
@Data
@Schema
public class UserIdentifierSimpleUpdateCommand extends UserIdentifierSimpleCreateCommand {

    @Min(value = 1,message = "id 不能小于1")
    @Schema(description = "id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Min(value = 1,message = "数据版本不能小于1")
    @Schema(title = "数据版本",description = "数据版本用来充当数据乐观锁字段，验证数据是否已经修改")
    private Integer version;
}
