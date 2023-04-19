package com.particle.dept.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门树 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Data
@ApiModel
public class DeptTreeUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "部门id 不能为空")
    @ApiModelProperty(value = "部门id",required = true)
    private Long deptId;


    @NotNull(message = "部门树名称id 不能为空")
    @ApiModelProperty(value = "部门树名称id",required = true)
    private Long deptTreeNameId;


    @ApiModelProperty(value = "描述")
    private String remark;


    @ApiModelProperty("父级")
    private Long parentId;


}
