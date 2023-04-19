package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜单功能 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@OrderBy("seq")
@Data
@ApiModel
public class FuncQueryListCommand extends AbstractBaseTreeQueryCommand {

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

    @ApiModelProperty("归属组件")
    private String componentOf;

    @ApiModelProperty("父级id")
    private Long parentId;

    @ApiModelProperty("功能应用id")
    private Long funcApplicationId;
}
