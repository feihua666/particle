package com.particle.dict.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 字典 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel(value="字典 数据通用响应对象")
public class DictVO extends AbstractBaseIdVO {


    @ApiModelProperty("字典编码,模糊查询，字典组时必填")
    private String code;

    @ApiModelProperty("字典名称,模糊查询")
    private String name;

    @ApiModelProperty("字典值,模糊查询")
    private String value;

    @ApiModelProperty("是否为系统字典，一般系统字典代码中会做判断，不能修改或删除")
    private Boolean isSystem;

    @ApiModelProperty("是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询")
    private Boolean isPublic;

    @ApiModelProperty("是否为字典组，不是字典组就是字典项目，没有其它的")
    private Boolean isGroup;

    @ApiModelProperty("是否禁用")
    private Boolean isDisabled;

    @ApiModelProperty("禁用原因")
    private String disabledReason;

    @ApiModelProperty("私有标识,模糊查询")
    private String privateFlag;

    @ApiModelProperty("私有标识备忘")
    private String privateFlagMemo;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @ApiModelProperty("分组标识备忘")
    private String groupFlagMemo;

    @ApiModelProperty("标签，多个以逗号分隔，用来区分字典项")
    private String tags;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("排序,默认按该字段升序排序")
    private Integer seq;


}
