package com.particle.func.client.dto.command;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 功能组 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Data
@ApiModel
public class FuncGroupUpdateCommand extends AbstractBaseUpdateCommand {


    @ApiModelProperty("编码，模糊查询")
    private String code;

    @ApiModelProperty("名称，模糊查询")
    private String name;

    @ApiModelProperty("描述")
    private String remark;


}
