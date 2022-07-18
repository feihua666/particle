package com.particle.area.client.dto.command;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 区域 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Data
@ApiModel(value="区域 通用分页查询指令对象")
public class AreaPageQueryCommand extends AbstractBasePageQueryCommand {


    @ApiModelProperty("编码，唯一,模糊查询")
    private String code;

    @ApiModelProperty("区域名称,模糊查询")
    private String name;

    @ApiModelProperty("区域名称,模糊查询")
    private String nameSimple;

    @ApiModelProperty("类型，字典id")
    private Long typeDictId;

    @ApiModelProperty("父级id")
    private Long parentId;

}
