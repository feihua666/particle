package com.particle.dict.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 字典 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@PropValid
@Data
@ApiModel
public class DictCreateCommand extends AbstractBaseCommand {


    /**
     * 添加字典组，编码必填
     */
    @PropValid.DependCondition(message = "字典编码不能为空",dependProp = "isGroup",ifEqual = "true")
    @ApiModelProperty(value = "字典编码,模糊查询，字典组时必填",required = true)
    private String code;

    @ApiModelProperty("字典名称,模糊查询")
    private String name;

    /**
     * 添加字典项，字典值必填
     */
    @PropValid.DependCondition(message = "字典值不能为空",dependProp = "isGroup",ifEqual = "false")
    @ApiModelProperty("字典值,模糊查询")
    private String value;

    @ApiModelProperty("字典值,单位")
    private String valueUnit;

    @ApiModelProperty("是否为系统字典，一般系统字典代码中会做判断，不能修改或删除")
    private Boolean isSystem;

    @ApiModelProperty("是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询")
    private Boolean isPublic;

    @NotNull(message = "是否为字典组不以为空")
    @ApiModelProperty("是否为字典组，不是字典组就是字典项目，没有其它的")
    private Boolean isGroup;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    /**
     * 禁用时，禁用原因必填
     */
    @PropValid.DependCondition(message = "禁用原因不能为空",dependProp = "isDisabled",ifEqual = "true")
    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("私有标识,模糊查询")
    private String privateFlag;

    @PropValid.Depend(message = "私有标识说明不能为空",dependProp = "privateFlag")
    @ApiModelProperty("私有标识说明")
    private String privateFlagMemo;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @PropValid.Depend(message = "分组标识说明不能为空",dependProp = "groupFlag")
    @ApiModelProperty("分组标识说明")
    private String groupFlagMemo;

    @ApiModelProperty("标签，多个以逗号分隔，用来区分字典项")
    private String tags;

    @ApiModelProperty("描述")
    private String remark;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序,默认按该字段升序排序",required = true)
    private Integer seq;

    /**
     * 如果添加字典项，必须指定父级
     */
    @PropValid.DependCondition(message = "父级id不能为空",dependProp = "isGroup",ifEqual = "false")
    @ApiModelProperty("父级id")
    private Long parentId;
}
