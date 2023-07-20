package com.particle.role.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Schema
public class RoleUpdateCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "角色编码")
    private String code;

    @NotEmpty(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String name;

    @NotNull(message = "是否禁用不能为空")
    @Schema(description = "是否禁用",required = true)
    private Boolean isDisabled;
    /**
     * 禁用时，禁用原因必填
     */
    @SetNullWhenNull
    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "是否超级管理员")
    private Boolean isSuperadmin;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq = 10;

    @Schema(description = "父级")
    private Long parentId;
}
