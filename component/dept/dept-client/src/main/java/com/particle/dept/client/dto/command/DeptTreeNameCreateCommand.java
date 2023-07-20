package com.particle.dept.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 部门树名称 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Data
@Schema
public class DeptTreeNameCreateCommand extends AbstractBaseCommand {



    @Schema(description = "部门树名称编码")
    private String code;


    @NotEmpty(message = "部门树名称 不能为空")
    @Schema(description = "部门树名称",required = true)
    private String name;


    @Schema(description = "描述")
    private String remark;

}
