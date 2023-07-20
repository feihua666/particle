package com.particle.role.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 角色 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@OrderBy("seq")
@Data
@Schema
public class RolePageQueryCommand extends AbstractBaseTreePageQueryCommand {

    @Like
    @Schema(description = "角色编码,左匹配查询")
    private String code;

    @Like
    @Schema(description = "角色名称,左匹配查询")
    private String name;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "是否超级管理员")
    private Boolean isSuperadmin;
}
