package com.particle.dept.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Data
@Schema
public class DeptUpdateCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "部门编码")
    private String code;


    @NotEmpty(message = "部门名称 不能为空")
    @Schema(description = "部门名称",required = true)
    private String name;


    @NotNull(message = "类型 不能为空")
    @Schema(description = "类型",required = true)
    private Long typeDictId;


    @Schema(description = "负责人用户id")
    private Long masterUserId;


    @NotNull(message = "是否虚拟部门 不能为空")
    @Schema(description = "是否虚拟部门",required = true)
    private Boolean isVirtual;


    @NotNull(message = "是否为公司 不能为空")
    @Schema(description = "是否为公司",required = true)
    private Boolean isComp;


    @Schema(description = "描述")
    private String remark;


    @Schema(description = "父级")
    private Long parentId;
}
