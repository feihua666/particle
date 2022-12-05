package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜单功能 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel
public class FuncPageQueryCommand extends AbstractBasePageQueryCommand {


    @Like
    @ApiModelProperty("编码，左前缀匹配")
    private String code;

    @Like
    @ApiModelProperty("名称，左模糊查询")
    private String name;

    @ApiModelProperty("功能分组id")
    private Long funcGroupId;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("类型,字典id")
    private Long typeDictId;

}
