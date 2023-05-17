package com.particle.dept.client.deptuserrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门用户关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Data
@ApiModel
public class DeptUserRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "用户id 不能为空")
        @ApiModelProperty(value = "用户id",required = true)
    private Long userId;


    @NotNull(message = "部门id 不能为空")
        @ApiModelProperty(value = "部门id",required = true)
    private Long deptId;









}
