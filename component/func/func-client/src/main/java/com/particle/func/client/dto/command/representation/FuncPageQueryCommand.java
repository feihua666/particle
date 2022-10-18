package com.particle.func.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
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


    @ApiModelProperty("编码，模糊查询")
    private String code;

    @ApiModelProperty("名称，模糊查询")
    private String name;

    @ApiModelProperty("功能分组id")
    private Long funcGroupId;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("地址")
    private String url;

    @ApiModelProperty("shiro权限串，多个以逗号分隔")
    private String permissions;

    @ApiModelProperty("类型,字典id")
    private Long typeDictId;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("排序,默认按该字段升序排序")
    private Integer seq;


}
