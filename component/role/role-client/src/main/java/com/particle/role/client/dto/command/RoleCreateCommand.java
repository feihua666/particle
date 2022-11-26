package com.particle.role.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@ApiModel
public class RoleCreateCommand extends AbstractBaseCommand {


    @ApiModelProperty("角色编码,模糊查询")
    private String code;

    @ApiModelProperty("角色名称,模糊查询")
    private String name;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("层级、深度")
    private Integer level;

    @ApiModelProperty("父级")
    private Long parentId;

    @ApiModelProperty("LEVEL为1的父id")
    private Long parentId1;

    @ApiModelProperty("LEVEL为2的父id")
    private Long parentId2;

    @ApiModelProperty("LEVEL为3的父id")
    private Long parentId3;

    @ApiModelProperty("LEVEL为4的父id")
    private Long parentId4;

    @ApiModelProperty("LEVEL为5的父id")
    private Long parentId5;

    @ApiModelProperty("LEVEL为6的父id")
    private Long parentId6;

    @ApiModelProperty("LEVEL为7的父id")
    private Long parentId7;

    @ApiModelProperty("LEVEL为8的父id")
    private Long parentId8;

    @ApiModelProperty("LEVEL为9的父id")
    private Long parentId9;

    @ApiModelProperty("LEVEL为10的父id")
    private Long parentId10;


}
