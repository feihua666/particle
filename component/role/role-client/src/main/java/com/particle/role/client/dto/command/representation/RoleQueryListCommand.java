package com.particle.role.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleQueryListCommand extends AbstractBaseTreeQueryCommand {


    @Like
    @ApiModelProperty("角色编码,左匹配查询")
    private String code;

    @Like
    @ApiModelProperty("角色名称,左匹配查询")
    private String name;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("父级")
    private Long parentId;
}
