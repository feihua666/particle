package com.particle.func.client.funcapplicationfuncrel.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 功能应用功能关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Data
@ApiModel
public class FuncApplicationFuncRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "功能应用id 不能为空")
        @ApiModelProperty(value = "功能应用id",required = true)
    private Long funcApplicationId;


    @NotNull(message = "功能id 不能为空")
        @ApiModelProperty(value = "功能id",required = true)
    private Long funcId;









}
