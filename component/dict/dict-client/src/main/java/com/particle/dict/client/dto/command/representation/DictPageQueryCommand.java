package com.particle.dict.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 字典 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel
public class DictPageQueryCommand extends AbstractBasePageQueryCommand {


    @Like
    @ApiModelProperty("字典编码,左前缀匹配")
    private String code;

    @Like
    @ApiModelProperty("字典名称,左前缀匹配")
    private String name;

    @ApiModelProperty("是否为系统字典，一般系统字典代码中会做判断，不能修改或删除")
    private Boolean isSystem;

    @ApiModelProperty("是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询")
    private Boolean isPublic;

    @ApiModelProperty("是否为字典组，不是字典组就是字典项目，没有其它的")
    private Boolean isGroup;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @Like
    @ApiModelProperty("私有标识，左前缀匹配")
    private String privateFlag;

    @Like
    @ApiModelProperty("分组标识，左前缀匹配")
    private String groupFlag;

    @Like
    @ApiModelProperty("标签，左前缀匹配")
    private String tags;

    @ApiModelProperty("父级id")
    private Long parentId;
}
