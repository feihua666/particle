package com.particle.func.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 功能组 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel
public class FuncGroupCreateCommand extends AbstractBaseCommand {


    @ApiModelProperty("编码，模糊查询")
    private String code;

    @ApiModelProperty("名称，模糊查询")
    private String name;

    @ApiModelProperty("描述")
    private String remark;


}
