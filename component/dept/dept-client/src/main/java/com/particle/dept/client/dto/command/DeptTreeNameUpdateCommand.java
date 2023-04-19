package com.particle.dept.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 部门树名称 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Data
@ApiModel
public class DeptTreeNameUpdateCommand extends AbstractBaseUpdateCommand {

    @ApiModelProperty(value = "部门树名称编码")
    private String code;


    @NotEmpty(message = "部门树名称 不能为空")
    @ApiModelProperty(value = "部门树名称",required = true)
    private String name;


    @ApiModelProperty(value = "描述")
    private String remark;

}
