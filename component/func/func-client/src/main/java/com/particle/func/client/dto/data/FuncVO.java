package com.particle.func.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.global.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.Trans;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜单功能 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@ApiModel
public class FuncVO extends AbstractBaseIdTreeVO {


    @ApiModelProperty("编码，模糊查询")
    private String code;

    @ApiModelProperty("名称，模糊查询")
    private String name;

    @ApiModelProperty("功能分组id")
    private Long funcGroupId;
    @ApiModelProperty("功能分组名称")
    @TransBy(tableName = TransTableNameConstants.component_func_group, byFieldName = "funcGroupId", mapValueField = "name")
    private String funcGroupName;

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
    @ApiModelProperty("类型,字典名称")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    private String typeDictName;
    @ApiModelProperty("类型,字典值")
    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    private String typeDictValue;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("排序,默认按该字段升序排序")
    private Integer seq;


}
