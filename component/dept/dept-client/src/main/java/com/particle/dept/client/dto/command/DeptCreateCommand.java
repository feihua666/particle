package com.particle.dept.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Data
@ApiModel
public class DeptCreateCommand extends AbstractBaseCommand {



    @ApiModelProperty(value = "部门编码")
    private String code;


    @NotEmpty(message = "部门名称 不能为空")
    @ApiModelProperty(value = "部门名称",required = true)
    private String name;


    @NotNull(message = "类型 不能为空")
    @ApiModelProperty(value = "类型",required = true)
    private Long typeDictId;


    @ApiModelProperty(value = "负责人用户id")
    private Long masterUserId;


    @NotNull(message = "是否虚拟部门 不能为空")
    @ApiModelProperty(value = "是否虚拟部门",required = true)
    private Boolean isVirtual;


    @NotNull(message = "是否为公司 不能为空")
    @ApiModelProperty(value = "是否为公司",required = true)
    private Boolean isComp;


    @ApiModelProperty(value = "描述")
    private String remark;


    @ApiModelProperty("父级")
    private Long parentId;
}
